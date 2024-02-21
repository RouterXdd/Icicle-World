package ice.classes.maps.generator;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.noise.*;
import ice.classes.blocks.environment.LargeCrater;
import ice.content.IceAttributes;
import ice.content.IceBlocks;
import ice.content.IceLoadouts;
import mindustry.ai.*;
import mindustry.ai.BaseRegistry.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.g3d.PlanetGrid.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

import static ice.content.IceBlocks.*;
import static mindustry.Vars.*;
import static mindustry.content.Blocks.*;

public class RkiTrueGenerator extends PlanetGenerator {
    public static boolean alt = true;

    BaseGenerator basegen = new BaseGenerator();
    float scl = 5f;
    float waterOffset = 0.07f;
    boolean genLakes = true;

    Block[][] arr =
            {
                    {dustFloor, dustFloor, stone, stone, stone, dustFloor, magnetRock, magnetRock, magnetPositiveRock, magnetRock, magnetNegativeRock, stone, dustFloor},
                    {thermalFloor, thermalFloor, calamiteFloor, thermalFloor, calamiteFloor, dustFloor, dustFloor, dustFloor, calamiteFloor, magnetRock, magnetRock, stone, stone},
                    {magnetRock, magnetPositiveRock, magnetRock, stone, thermalFloor, thermalFloor, thermalFloor, stone, magnetRock, magnetNegativeRock, magnetRock, stone, stone, thermalFloor},
                    {calamiteFloor, calamiteFloor, calamiteFloor, dustFloor, dustFloor, calamiteFloor, stone, calamiteFloor, stone, stone, stone, dustFloor, dustFloor}
            };

    ObjectMap<Block, Block> dec = ObjectMap.of(
            dustFloor, IceBlocks.dustSpit,
            IceBlocks.calamiteFloor, IceBlocks.calamiteBoulder,
            IceBlocks.magnetRock, IceBlocks.magnetBoulder,
            magnetPositiveRock, positiveCrystal,
            magnetNegativeRock, negativeCrystal,
            IceBlocks.thermalFloor, IceBlocks.thermalBoulder
    );
    {
        defaultLoadout = IceLoadouts.angryBasic;
    }

    float water = 2f / arr[0].length;

    float rawHeight(Vec3 position){
        position = Tmp.v33.set(position).scl(scl);
        return (Mathf.pow(Simplex.noise3d(seed, 7, 0.5f, 1f/3f, position.x, position.y, position.z), 2.3f) + waterOffset) / (1f + waterOffset);
    }

    @Override
    public void generateSector(Sector sector){

        //these always have bases
        if(sector.id == 89 || sector.id == 14){
            sector.generateEnemyBase = true;
            return;
        }

        Ptile tile = sector.tile;

        boolean any = false;
        float poles = Math.abs(tile.v.y);
        float noise = Noise.snoise3(tile.v.x, tile.v.y, tile.v.z, 0.001f, 0.58f);

        if(noise + poles/7.1 > 0.12 && poles > 0.25){
            any = true;
        }

        if(noise < 0.17){
            for(Ptile other : tile.tiles){
                var osec = sector.planet.getSector(other);

                //no sectors near start sector!
                if(
                        osec.id == sector.planet.startSector || //near starting sector
                                osec.generateEnemyBase && poles < 0.85 || //near other base
                                (sector.preset != null && noise < 0.14) //near preset
                ){
                    return;
                }
            }
        }

        sector.generateEnemyBase = any;
    }

    @Override
    public float getHeight(Vec3 position){
        float height = rawHeight(position);
        return Math.max(height, water);
    }

    @Override
    public Color getColor(Vec3 position){
        Block block = getBlock(position);
        //replace salt with sand color
        if(block == Blocks.salt) return Blocks.sand.mapColor;
        return Tmp.c1.set(block.mapColor).a(1f - block.albedo);
    }

    @Override
    public void genTile(Vec3 position, TileGen tile){
        tile.floor = getBlock(position);
        tile.block = tile.floor.asFloor().wall;

        if(Ridged.noise3d(seed + 1, position.x, position.y, position.z, 2, 22) > 0.31){
            tile.block = Blocks.air;
        }
    }

    Block getBlock(Vec3 position){
        float height = rawHeight(position);
        Tmp.v31.set(position);
        position = Tmp.v33.set(position).scl(scl);
        float rad = scl;
        float temp = Mathf.clamp(Math.abs(position.y * 2f) / (rad));
        float tnoise = Simplex.noise3d(seed, 7, 0.56, 1f/3f, position.x, position.y + 999f, position.z);
        temp = Mathf.lerp(temp, tnoise, 0.5f);
        height *= 1.2f;
        height = Mathf.clamp(height);

        Block res = arr[Mathf.clamp((int)(temp * arr.length), 0, arr[0].length - 1)][Mathf.clamp((int)(height * arr[0].length), 0, arr[0].length - 1)];
        return res;
    }

    @Override
    protected float noise(float x, float y, double octaves, double falloff, double scl, double mag){
        Vec3 v = sector.rect.project(x, y).scl(5f);
        return Simplex.noise3d(seed, octaves, falloff, 1f / scl, v.x, v.y, v.z) * (float)mag;
    }

    @Override
    protected void generate(){

        class Room{
            final int x;
            final int y;
            final int radius;
            final ObjectSet<Room> connected = new ObjectSet<>();

            Room(int x, int y, int radius){
                this.x = x;
                this.y = y;
                this.radius = radius;
                connected.add(this);
            }

            void join(int x1, int y1, int x2, int y2){
                float nscl = rand.random(100f, 140f) * 6f;
                int stroke = rand.random(3, 9);
                brush(pathfind(x1, y1, x2, y2, tile -> (tile.solid() ? 50f : 0f) + noise(tile.x, tile.y, 2, 0.4f, 1f / nscl) * 500, Astar.manhattan), stroke);
            }

            void connect(Room to){
                if(!connected.add(to) || to == this) return;

                Vec2 midpoint = Tmp.v1.set(to.x, to.y).add(x, y).scl(0.5f);
                rand.nextFloat();

                if(alt){
                    midpoint.add(Tmp.v2.set(1, 0f).setAngle(Angles.angle(to.x, to.y, x, y) + 90f * (rand.chance(0.5) ? 1f : -1f)).scl(Tmp.v1.dst(x, y) * 2f));
                }else{
                    //add randomized offset to avoid straight lines
                    midpoint.add(Tmp.v2.setToRandomDirection(rand).scl(Tmp.v1.dst(x, y)));
                }

                midpoint.sub(width/2f, height/2f).limit(width / 2f / Mathf.sqrt3).add(width/2f, height/2f);

                int mx = (int)midpoint.x, my = (int)midpoint.y;

                join(x, y, mx, my);
                join(mx, my, to.x, to.y);
            }
        }

        cells(5);
        distort(10f, 18f);

        float constraint = 1.3f;
        float radius = width / 2f / Mathf.sqrt3;
        int rooms = rand.random(3, 7);
        Seq<Room> roomseq = new Seq<>();

        for(int i = 0; i < rooms; i++){
            Tmp.v1.trns(rand.random(360f), rand.random(radius / constraint));
            float rx = (width/2f + Tmp.v1.x);
            float ry = (height/2f + Tmp.v1.y);
            float maxrad = radius - Tmp.v1.len();
            float rrad = Math.min(rand.random(9f, maxrad / 2f), 30f);
            roomseq.add(new Room((int)rx, (int)ry, (int)rrad));
        }

        //check positions on the map to place the player spawn. this needs to be in the corner of the map
        Room spawn = null;
        Seq<Room> enemies = new Seq<>();
        int enemySpawns = rand.random(1, Math.max((int)(sector.threat * 5), 1));
        int offset = rand.nextInt(360);
        float length = width/2.55f - rand.random(13, 20);
        int angleStep = 5;
        int waterCheckRad = 5;
        for(int i = 0; i < 360; i+= angleStep){
            int angle = offset + i;
            int cx = (int)(width/2 + Angles.trnsx(angle, length));
            int cy = (int)(height/2 + Angles.trnsy(angle, length));

            int waterTiles = 0;

            //check for water presence
            for(int rx = -waterCheckRad; rx <= waterCheckRad; rx++){
                for(int ry = -waterCheckRad; ry <= waterCheckRad; ry++){
                    Tile tile = tiles.get(cx + rx, cy + ry);
                    if(tile == null || tile.floor().liquidDrop != null){
                        waterTiles ++;
                    }
                }
            }

            if(waterTiles <= 4 || (i + angleStep >= 360)){
                roomseq.add(spawn = new Room(cx, cy, rand.random(8, 15)));

                for(int j = 0; j < enemySpawns; j++){
                    float enemyOffset = rand.range(60f);
                    Tmp.v1.set(cx - width/2, cy - height/2).rotate(180f + enemyOffset).add(width/2, height/2);
                    Room espawn = new Room((int)Tmp.v1.x, (int)Tmp.v1.y, rand.random(8, 16));
                    roomseq.add(espawn);
                    enemies.add(espawn);
                }

                break;
            }
        }

        //clear radius around each room
        for(Room room : roomseq){
            erase(room.x, room.y, room.radius);
        }

        //randomly connect rooms together
        int connections = rand.random(Math.max(rooms - 1, 1), rooms + 3);
        for(int i = 0; i < connections; i++){
            roomseq.random(rand).connect(roomseq.random(rand));
        }

        for(Room room : roomseq){
            spawn.connect(room);
        }

        Room fspawn = spawn;

        cells(1);

        distort(10f, 6f);

        //rivers
        pass((x, y) -> {
            if(block.solid) return;

            Vec3 v = sector.rect.project(x, y);

            float rr = Simplex.noise2d(sector.id, (float)2, 0.6f, 1f / 7f, x, y) * 0.1f;
            float value = Ridged.noise3d(2, v.x, v.y, v.z, 1, 1f / 55f) + rr - rawHeight(v) * 0f;
            float rrscl = rr * 44 - 2;

            if(value > 0.17f && !Mathf.within(x, y, fspawn.x, fspawn.y, 12 + rrscl)){
                boolean deep = value > 0.17f + 0.1f && !Mathf.within(x, y, fspawn.x, fspawn.y, 15 + rrscl);
                boolean spore = floor != IceBlocks.magnetRock && floor != IceBlocks.calamiteFloor;
                //ignore pre-existing liquids
                if(!(floor == dustFloor || floor == IceBlocks.magnetRock || floor == IceBlocks.calamiteFloor || floor == stone || floor.asFloor().isLiquid)){
                    floor = spore ?
                            (IceBlocks.methaneFloor) :
                            (deep ? IceBlocks.methaneFloor :
                                    (IceBlocks.thermalFloor));
                }
            }
        });

        //shoreline setup
        pass((x, y) -> {
            int deepRadius = 3;

            if(floor.asFloor().isLiquid && floor.asFloor().shallow){

                for(int cx = -deepRadius; cx <= deepRadius; cx++){
                    for(int cy = -deepRadius; cy <= deepRadius; cy++){
                        if((cx) * (cx) + (cy) * (cy) <= deepRadius * deepRadius){
                            int wx = cx + x, wy = cy + y;

                            Tile tile = tiles.get(wx, wy);
                            if(tile != null && (!tile.floor().isLiquid || tile.block() != Blocks.air)){
                                //found something solid, skip replacing anything
                                return;
                            }
                        }
                    }
                }

                floor = IceBlocks.thermalFloor;
            }
        });

        Seq<Block> ores = Seq.with(oreThallium, Blocks.oreScrap);
        float poles = Math.abs(sector.tile.v.y);
        float nmag = 0.6f;
        float scl = 1f;
        float addscl = 1.1f;

        if(Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x, sector.tile.v.y, sector.tile.v.z)*nmag + poles > 0.25f*addscl){
            ores.add(oreThalliumUnderground);
        }

        if(Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x + 1, sector.tile.v.y, sector.tile.v.z)*nmag + poles > 0.5f*addscl){
            ores.add(oreSoptin);
        }
        if(Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x + 2, sector.tile.v.y, sector.tile.v.z)*nmag + poles > 0.65f*addscl){
            ores.add(oreChalkUnderground);
        }

        if(Simplex.noise3d(seed, 2, 0.5, scl, sector.tile.v.x + 2, sector.tile.v.y, sector.tile.v.z)*nmag + poles > 0.7f*addscl){
            ores.add(orePoloniumUnderground);
        }

        FloatSeq frequencies = new FloatSeq();
        for(int i = 0; i < ores.size; i++){
            frequencies.add(rand.random(-0.1f, 0.01f) - i * 0.01f + poles * 0.04f);
        }

        pass((x, y) -> {
            if(!floor.asFloor().hasSurface()) return;

            int offsetX = x - 4, offsetY = y + 23;
            for(int i = ores.size - 1; i >= 0; i--){
                Block entry = ores.get(i);
                float freq = frequencies.get(i);
                if(Math.abs(0.5f - noise(offsetX, offsetY + i*999, 2, 0.7, (40 + i * 2))) > 0.22f + i*0.01 &&
                        Math.abs(0.5f - noise(offsetX, offsetY - i*999, 1, 1, (30 + i * 4))) > 0.37f + freq){
                    ore = entry;
                    break;
                }
            }

            if(ore == Blocks.oreScrap && rand.chance(0.33)){
                floor = rkiMetal;
            }
        });

        trimDark();

        int minVents = rand.random(4, 9);
        int ventCount = 0;

        //vents
        outer:
        for(Tile tile : tiles){
            var floor = tile.floor();
            if(floor == dustFloor && rand.chance(0.003)){
                int ventRadius = 2;
                for(int x = -ventRadius; x <= ventRadius; x++){
                    for(int y = -ventRadius; y <= ventRadius; y++){
                        Tile other = tiles.get(x + tile.x, y + tile.y);
                        if(other == null || other.floor() != dustFloor || other.block().solid){
                            continue outer;
                        }
                    }
                }

                ventCount ++;
                for(var pos : LargeCrater.offsets){
                    Tile other = tiles.get(pos.x + tile.x + 2, pos.y + tile.y + 2);
                    other.setFloor(IceBlocks.dustLargeCrater.asFloor());
                }
            }
        }

        int iterations = 0;
        int maxIterations = 5;

        //try to add additional vents, but only several times to prevent infinite loops in bad maps
        while(ventCount < minVents && iterations++ < maxIterations) {
            outer:
            for (Tile tile : tiles) {
                if (rand.chance(0.00018 * (1 + iterations)) && !Mathf.within(tile.x, tile.y, spawn.x, spawn.y, 5f)) {

                    int ventRadius = 2;
                    for (int x = -ventRadius; x <= ventRadius; x++) {
                        for (int y = -ventRadius; y <= ventRadius; y++) {
                            Tile other = tiles.get(x + tile.x, y + tile.y);
                            //skip solids / other vents / arkycite / slag
                            if (other == null || other.block().solid || other.floor().attributes.get(IceAttributes.sun) != 0 || other.floor() == IceBlocks.magnetRock || other.floor() == IceBlocks.calamiteFloor) {
                                continue outer;
                            }
                        }
                    }

                    Block
                            floor = dustFloor,
                            secondFloor = dustFloor,
                            vent = Blocks.rhyoliteVent;

                    int xDir = 1;
                    //set target material depending on what's encountered
                    if (tile.floor() == stone ) {
                        floor = secondFloor = stone;
                        vent = IceBlocks.stoneLargeCrater;
                    }


                    ventCount++;
                    for (var pos : LargeCrater.offsets) {
                        Tile other = tiles.get(pos.x + tile.x + 2, pos.y + tile.y + 1);
                        other.setFloor(vent.asFloor());
                    }

                    //"circle" for blending
                    int crad = rand.random(6, 15), crad2 = crad * crad;
                    for (int cx = -crad; cx <= crad; cx++) {
                        for (int cy = -crad; cy <= crad; cy++) {
                            int rx = cx + tile.x, ry = cy + tile.y;
                            //skew circle Y
                            float rcy = cy + cx * 0.9f;
                            if (cx * cx + rcy * rcy <= crad2 - noise(rx, ry + rx * 2f * xDir, 2, 0.7f, 8f, crad2 * 1.1f)) {
                                Tile dest = tiles.get(rx, ry);
                                if (dest != null && dest.floor().attributes.get(IceAttributes.sun) == 0 && dest.floor() != Blocks.roughRhyolite && dest.floor() != Blocks.arkyciteFloor && dest.floor() != Blocks.slag) {

                                    dest.setFloor(rand.chance(0.08) ? secondFloor.asFloor() : floor.asFloor());

                                    if (dest.block().isStatic()) {
                                        dest.setBlock(floor.asFloor().wall);
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        median(2);

        inverseFloodFill(tiles.getn(spawn.x, spawn.y));

        tech(rkiMetal, rkiMetal, rkiMetalWall);

        pass((x, y) -> {

            if(genLakes && floor != stone && floor != IceBlocks.magnetRock && floor.asFloor().hasSurface()){
                float noise = noise(x + 782, y, 5, 0.75f, 260f, 1f);
                if(noise > 0.67f && !roomseq.contains(e -> Mathf.within(x, y, e.x, e.y, 14))){
                    if(noise > 0.72f){
                        floor = noise > 0.78f ? IceBlocks.methaneFloor : IceBlocks.thermalFloor;
                    }else{
                        floor = IceBlocks.thermalFloor;
                    }
                }
            }
            if(rand.chance(1)){
                //random spore trees
                boolean any = false;
                boolean all = true;
                for(Point2 p : Geometry.d4){
                    Tile other = tiles.get(x + p.x, y + p.y);
                    if(other != null && other.block() == Blocks.air){
                        other.setTeam(Team.malis);
                        any = true;
                    }else{
                        all = false;
                    }
                }
                if(any && ((block == stone || block == dustFloor) || (all && block == Blocks.air && floor == stone && rand.chance(0.012)))){
                    block = IceBlocks.undergroundTree;
                }
                if(any && ((block == dustFloor || block == thermalFloor) || (all && block == Blocks.air && floor == dustFloor && rand.chance(0.01)))){
                    block = IceBlocks.undergroundTree;
                }
                if(any && block == air && rand.chance(0.0012)){
                    block = dustCrusher;
                }
                if(any && block == air && rand.chance(0.0004)){
                    block = dustSpreader;
                }
            }

            //random stuff
            dec: {
                for(int i = 0; i < 4; i++){
                    Tile near = world.tile(x + Geometry.d4[i].x, y + Geometry.d4[i].y);
                    if(near != null && near.block() != Blocks.air){
                        break dec;
                    }
                }

                if(rand.chance(0.01) && floor.asFloor().hasSurface() && block == Blocks.air){
                    block = dec.get(floor, floor.asFloor().decoration);
                }
            }
        });

        float difficulty = sector.threat;
        ints.clear();
        ints.ensureCapacity(width * height / 4);

        int ruinCount = rand.random(-2, 4);
        if(ruinCount > 0){
            int padding = 25;

            //create list of potential positions
            for(int x = padding; x < width - padding; x++){
                for(int y = padding; y < height - padding; y++){
                    Tile tile = tiles.getn(x, y);
                    if(!tile.solid() && (tile.drop() != null || tile.floor().liquidDrop != null)){
                        ints.add(tile.pos());
                    }
                }
            }

            ints.shuffle(rand);

            int placed = 0;
            float diffRange = 0.4f;
            //try each position
            for(int i = 0; i < ints.size && placed < ruinCount; i++){
                int val = ints.items[i];
                int x = Point2.x(val), y = Point2.y(val);

                //do not overwrite player spawn
                if(Mathf.within(x, y, spawn.x, spawn.y, 18f)){
                    continue;
                }

                float range = difficulty + rand.random(diffRange);

                Tile tile = tiles.getn(x, y);
                BasePart part = null;
                if(tile.overlay().itemDrop != null){
                    part = bases.forResource(tile.drop()).getFrac(range);
                }else if(tile.floor().liquidDrop != null && rand.chance(0.05)){
                    part = bases.forResource(tile.floor().liquidDrop).getFrac(range);
                }else if(rand.chance(0.05)){ //ore-less parts are less likely to occur.
                    part = bases.parts.getFrac(range);
                }

                //actually place the part
                if(part != null && BaseGenerator.tryPlace(part, x, y, Team.derelict, (cx, cy) -> {
                    Tile other = tiles.getn(cx, cy);
                    if(other.floor().hasSurface()){
                        other.setOverlay(Blocks.oreScrap);
                        for(int j = 1; j <= 2; j++){
                            for(Point2 p : Geometry.d8){
                                Tile t = tiles.get(cx + p.x*j, cy + p.y*j);
                                if(t != null && t.floor().hasSurface() && rand.chance(j == 1 ? 0.4 : 0.2)){
                                    t.setOverlay(Blocks.oreScrap);
                                }
                            }
                        }
                    }
                })){
                    placed ++;

                    int debrisRadius = Math.max(part.schematic.width, part.schematic.height)/2 + 3;
                    Geometry.circle(x, y, tiles.width, tiles.height, debrisRadius, (cx, cy) -> {
                        float dst = Mathf.dst(cx, cy, x, y);
                        float removeChance = Mathf.lerp(0.05f, 0.5f, dst / debrisRadius);

                        Tile other = tiles.getn(cx, cy);
                        if(other.build != null && other.isCenter()){
                            if(other.team() == Team.derelict && rand.chance(removeChance)){
                                other.remove();
                            }else if(rand.chance(0.5)){
                                other.build.health = other.build.health - rand.random(other.build.health * 0.9f);
                            }
                        }
                    });
                }
            }
        }

        //remove invalid ores
        for(Tile tile : tiles){
            if(tile.overlay().needsSurface && !tile.floor().hasSurface()){
                tile.setOverlay(Blocks.air);
            }
        }

        Schematics.placeLaunchLoadout(spawn.x, spawn.y);

        for(Room espawn : enemies){
            tiles.getn(espawn.x, espawn.y).setOverlay(Blocks.spawn);
            if (Mathf.chance(0.0001f)){
                tiles.getn(espawn.x, espawn.y).setBlock(monsterNest, state.rules.waveTeam);
            }
        }

        if(sector.hasEnemyBase()){
            basegen.generate(tiles, enemies.map(r -> tiles.getn(r.x, r.y)), tiles.get(spawn.x, spawn.y), state.rules.waveTeam, sector, difficulty);

            state.rules.attackMode = sector.info.attack = true;
        }else{
            state.rules.winWave = sector.info.winWave = 10 + 5 * (int)Math.max(difficulty * 10, 1);
        }

        float waveTimeDec = 0.3f;

        state.rules.waveSpacing = Mathf.lerp(60 * 80 * 2, 60f * 80f * 1f, Math.max(difficulty - waveTimeDec, 0f));
        state.rules.waves = true;
        state.rules.env = sector.planet.defaultEnv;
        state.rules.enemyCoreBuildRadius = 600f;
        state.rules.coreDestroyClear = true;

        //spawn air only when spawn is blocked
        state.rules.spawns = RkiWaves.generate(difficulty, new Rand(sector.id), state.rules.attackMode);
    }

    @Override
    public void postGenerate(Tiles tiles){
        if(sector.hasEnemyBase()){
            basegen.postGenerate();

            //spawn air enemies
            if(spawner.countGroundSpawns() == 0){
                state.rules.spawns = RkiWaves.generate(sector.threat, new Rand(sector.id), state.rules.attackMode);
            }
        }
    }
}
