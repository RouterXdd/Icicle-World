package ice.classes.blocks.production;

import arc.Core;
import arc.func.Cons;
import arc.func.Intc2;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.struct.EnumSet;
import arc.struct.ObjectFloatMap;
import arc.util.Eachable;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.Tmp;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.units.BuildPlan;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.consumers.ConsumeLiquid;
import mindustry.world.consumers.ConsumeLiquidBase;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
//Persea's MountMiner class, but I fixed this in one day, when author spent a few days :trollege:
public class NearMiner extends Block{
    public TextureRegion topRegion;
    public TextureRegion rotatorBottomRegion;
    public TextureRegion rotatorRegion;
    @Override
    public void load() {
        super.load();
        topRegion = Core.atlas.find(name + "-top");
        rotatorBottomRegion = Core.atlas.find(name + "-rotator-bottom");
        rotatorRegion = Core.atlas.find(name + "-rotator");
    }
    static int idx = 0;
    public int range = 1;
    public float rotateSpeed = 2f;
    public int tier = 2;
    public float drillTime = 200f;
    public float optionalBoostIntensity = 2.5f;
    public ObjectFloatMap<Item> drillMultipliers = new ObjectFloatMap<>();
    public Effect updateEffect = Fx.mineWallSmall;
    public float updateEffectChance = 0.02f;

    public NearMiner(String name){
        super(name);

        hasItems = true;
        rotate = true;
        update = true;
        solid = true;
        regionRotated1 = 1;

        envEnabled |= Env.space;
        flags = EnumSet.of(BlockFlag.drill);
    }

    @Override
    public void init(){
        updateClipRadius(range * tilesize);
        super.init();
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("drillspeed", (MountMinerBuild e) ->
                new Bar(() -> Core.bundle.format("bar.drillspeed", Strings.fixed(e.lastDrillSpeed * 60, 2)), () -> Pal.ammo, () -> e.warmup));
    }

    @Override
    public boolean outputsItems(){
        return true;
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion};
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.drillTier, StatValues.drillables(drillTime, 0f, size, drillMultipliers, b -> (b instanceof Floor f && f.wallOre && f.itemDrop != null && f.itemDrop.hardness <= tier) || (b instanceof StaticWall w && w.itemDrop != null && w.itemDrop.hardness <= tier)));
        stats.add(Stat.drillSpeed, 60f / drillTime * size, StatUnit.itemsSecond);
        if(optionalBoostIntensity != 1 && findConsumer(f -> f instanceof ConsumeLiquidBase && f.booster) instanceof ConsumeLiquidBase consBase){
            stats.remove(Stat.booster);
            stats.add(Stat.booster,
                    StatValues.speedBoosters("{0}" + StatUnit.timesSpeed.localized(),
                            consBase.amount, optionalBoostIntensity, false,
                            l -> (consumesLiquid(l) && (findConsumer(f -> f instanceof ConsumeLiquid).booster || ((ConsumeLiquid)findConsumer(f -> f instanceof ConsumeLiquid)).liquid != l)))
            );
        }
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        for(int i = 0; i < size; i++){
            nearbySide(tile.x, tile.y, rotation, i, Tmp.p1);
            for(int j = 0; j < range; j++){
                Tile other = world.tile(Tmp.p1.x + Geometry.d4x(rotation)*j, Tmp.p1.y + Geometry.d4y(rotation)*j);
                if(other != null && other.solid()){
                    Item drop = other.wallDrop();
                    if(drop != null && drop.hardness <= tier){
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        Item item = null, invalidItem = null;
        boolean multiple = false;
        int count = 0;

        for(int i = 0; i < size; i++){
            nearbySide(x, y, rotation, i, Tmp.p1);

            int j = 0;
            Item found = null;
            for(; j < range; j++){
                int rx = Tmp.p1.x + Geometry.d4x(rotation)*j, ry = Tmp.p1.y + Geometry.d4y(rotation)*j;
                Tile other = world.tile(rx, ry);
                if(other != null && other.solid()){
                    Item drop = other.wallDrop();
                    if(drop != null){
                        if(drop.hardness <= tier){
                            found = drop;
                            count++;
                        }else{
                            invalidItem = drop;
                        }
                    }
                    break;
                }
            }

            if(found != null){
                //check if multiple items will be drilled
                if(item != found && item != null){
                    multiple = true;
                }
                item = found;
            }

            int len = Math.min(j, range - 1);
            Drawf.dashLine(found == null ? Pal.remove : Pal.placing,
                    Tmp.p1.x * tilesize,
                    Tmp.p1.y *tilesize,
                    (Tmp.p1.x + Geometry.d4x(rotation)*len) * tilesize,
                    (Tmp.p1.y + Geometry.d4y(rotation)*len) * tilesize
            );
        }

        if(item != null){
            float width = drawPlaceText(Core.bundle.formatFloat("bar.drillspeed", 60f / getDrillTime(item) * count, 2), x, y, valid);
            if(!multiple){
                float dx = x * tilesize + offset - width/2f - 4f, dy = y * tilesize + offset + size * tilesize / 2f + 5, s = iconSmall / 4f;
                Draw.mixcol(Color.darkGray, 1f);
                Draw.rect(item.fullIcon, dx, dy - 1, s, s);
                Draw.reset();
                Draw.rect(item.fullIcon, dx, dy, s, s);
            }
        }else if(invalidItem != null){
            drawPlaceText(Core.bundle.get("bar.drilltierreq"), x, y, false);
        }

    }


    public float getDrillTime(Item item){
        return drillTime / drillMultipliers.get(item, 1f);
    }

    public class MountMinerBuild extends Building{

        public Tile[] facing = new Tile[size];
        public Point2[] lasers = new Point2[size];
        public @Nullable Item lastItem;

        public float time;
        public float warmup, totalTime, boostWarmup;
        public float lastDrillSpeed;
        public int facingAmount;

        @Override
        public void drawSelect(){

            if(lastItem != null){
                float dx = x - size * tilesize/2f, dy = y + size * tilesize/2f, s = iconSmall / 4f;
                Draw.mixcol(Color.darkGray, 1f);
                Draw.rect(lastItem.fullIcon, dx, dy - 1, s, s);
                Draw.reset();
                Draw.rect(lastItem.fullIcon, dx, dy, s, s);
            }
        }

        @Override
        public void updateTile(){
            super.updateTile();

            warmup = Mathf.approachDelta(warmup, Mathf.num(efficiency > 0), 1f / 60f);
            float dx = Geometry.d4x(rotation) * 0.5f, dy = Geometry.d4y(rotation) * 0.5f;

            totalTime += warmup * delta();

            updateFacing();
            boolean cons = shouldConsume();

            float multiplier = Mathf.lerp(1f, optionalBoostIntensity, optionalEfficiency);
            float drillTime = getDrillTime(lastItem);
            boostWarmup = Mathf.lerpDelta(boostWarmup, optionalEfficiency, 0.1f);
            lastDrillSpeed = (facingAmount * multiplier * timeScale) / drillTime;

            time += edelta() * multiplier;

            if(time >= drillTime){
                for(Tile tile : facing){
                    Item drop = tile == null ? null : tile.wallDrop();
                    if(items.total() < itemCapacity && drop != null){
                        items.add(drop, 1);
                        produced(drop);
                    }
                }
                time %= drillTime;
            }

            if(timer(timerDump, dumpTime)){
                dump();
            }
        }

        @Override
        public boolean shouldConsume(){
            return items.total() < itemCapacity && lastItem != null && enabled;
        }

        @Override
        public void draw(){
            super.draw();
            Draw.rect(block.region, x, y);
            Draw.rect(topRegion, x, y, rotdeg());
            var dir = Geometry.d4(rotation);
            idx = 0;
            for(int i = 0; i < size; i++) {
                Tile face = facing[i];
                if (face != null) {
                    float lx = face.worldx() - (dir.x / 2f) * tilesize, ly = face.worldy() - (dir.y / 2f) * tilesize;
                    Drawf.spinSprite(rotatorBottomRegion, lx, ly, totalTime * rotateSpeed);
                    Draw.rect(rotatorRegion, lx, ly);
                }
            }
        }

        @Override
        public void onProximityUpdate(){
            //when rotated.
            updateLasers();
            updateFacing();
        }

        protected void updateLasers(){
            for(int i = 0; i < size; i++){
                if(lasers[i] == null) lasers[i] = new Point2();
                nearbySide(tileX(), tileY(), rotation, i, lasers[i]);
            }
        }

        protected void updateFacing(){
            lastItem = null;
            boolean multiple = false;
            int dx = Geometry.d4x(rotation), dy = Geometry.d4y(rotation);
            facingAmount = 0;

            //update facing tiles
            for(int p = 0; p < size; p++){
                Point2 l = lasers[p];
                Tile dest = null;
                for(int i = 0; i < range; i++){
                    int rx = l.x + dx*i, ry = l.y + dy*i;
                    Tile other = world.tile(rx, ry);
                    if(other != null){
                        if(other.solid()){
                            Item drop = other.wallDrop();
                            if(drop != null && drop.hardness <= tier){
                                facingAmount ++;
                                if(lastItem != drop && lastItem != null){
                                    multiple = true;
                                }
                                lastItem = drop;
                                dest = other;
                            }
                            break;
                        }
                    }
                }

                facing[p] = dest;
            }

            //when multiple items are present, count that as no item
            if(multiple){
                lastItem = null;
            }
        }

        @Override
        public byte version(){
            return 1;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(time);
            write.f(warmup);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            if(revision >= 1){
                time = read.f();
                warmup = read.f();
            }
        }
    }
}
