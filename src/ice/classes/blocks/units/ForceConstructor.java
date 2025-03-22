package ice.classes.blocks.units;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.IntSeq;
import arc.struct.Seq;
import arc.util.*;
import arc.util.io.*;
import ice.classes.blocks.production.BurstPump;
import ice.classes.type.IceStats;
import ice.content.IceUnitTypes;
import ice.graphics.IcePal;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;
import mindustry.world.meta.Stats;

import static mindustry.Vars.*;

public class ForceConstructor extends Block{
    public UnitType unitType = IceUnitTypes.shieldDrone;
    public float buildTime = 60f * 8f;
    public int amount = 4;

    public float polyStroke = 1.8f, polyRadius = 6.5f;
    public float polyScale = 1.2f;
    public int polySides = 6;
    public float polyRotateSpeed = 1f;
    public float noBuildRange = 75;
    public Color polyColor = IcePal.poloniumLightish;

    public ForceConstructor(String name){
        super(name);

        solid = true;
        update = true;
        ambientSound = Sounds.respawning;
    }

    @Override
    public boolean outputsItems(){
        return false;
    }
    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.unitType, table -> {
            table.row();
            table.table(Styles.grayPanel, b -> {
                b.image(unitType.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit);
                b.table(info -> {
                    info.add(unitType.localizedName).left();
                    if(Core.settings.getBool("console")){
                        info.row();
                        info.add(unitType.name).left().color(Color.lightGray);
                    }
                });
                b.button("?", Styles.flatBordert, () -> ui.content.show(unitType)).size(40f).pad(10).right().grow().visible(() -> unitType.unlockedNow());
            }).growX().pad(5).row();

        });
        stats.add(IceStats.droneAmount, amount);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("units", (UnitTransportSourceBuild e) ->
                new Bar(
                        () ->
                                Core.bundle.format("bar.unitcap",
                                        Fonts.getUnicodeStr(unitType.name),
                                        e.team.data().countType(unitType),
                                        "∞"
                                ),
                        () -> Pal.power,
                        () -> unitType.useUnitCap ? (float)e.team.data().countType(unitType) : 1f
                ));
        addBar("progress", (UnitTransportSourceBuild r) -> new Bar("bar.progress", Pal.ammo, r::progress));
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        boolean tooClose = false;
        if (tile != null) {
        tooClose = indexer.eachBlock(team, tile.worldx(), tile.worldy(), noBuildRange, b -> {
            if (b.block() instanceof ForceConstructor) return true;
            if (b.block() instanceof ConstructBlock) {
                if (b instanceof ConstructBlock.ConstructBuild g) {
                    return (g.current instanceof ForceConstructor) && (g.previous instanceof ForceConstructor);
                }
            }
            return false;
        }, b -> {
            Drawf.dashLine(Pal.remove, tile.worldx(), tile.worldy(), b.x, b.y);
        });
        }
        return super.canPlaceOn(tile, team, rotation) && !tooClose;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        if(!canPlaceOn(world.tile(x, y), player.team(), rotation)  ){
            drawPlaceText(Core.bundle.get("message.nobuild"), x, y, valid);
        }
    }

    public class UnitTransportSourceBuild extends Building implements UnitTetherBlock{
        //needs to be "unboxed" after reading, since units are read after buildings.
        public int readUnitId = -1;
        protected IntSeq readUnits = new IntSeq();
        public float buildProgress, totalProgress;
        public float warmup, readyness;
        public Seq<Unit> units = new Seq<>();
        public @Nullable Unit unitT, unit;

        @Override
        public void updateTile(){
            if(!readUnits.isEmpty()){
                units.clear();
                readUnits.each(i -> {
                    var unit = Groups.unit.getByID(i);
                    if(unit != null){
                        units.add(unit);
                    }
                });
                readUnits.clear();
            }

            units.removeAll(u -> !u.isAdded() || u.dead);
            //unit was lost/destroyed
            if(unitT != null && (unitT.dead || !unitT.isAdded())){
                unitT = null;
            }

            if(readUnitId != 0){
                unitT = Groups.unit.getByID(readUnitId);
                    readUnitId = 0;
            }
            totalProgress += warmup;

            warmup = Mathf.approachDelta(warmup, units.size < amount ? efficiency : 0f, 1f / 60f);
            readyness = Mathf.approachDelta(readyness, unitT != null ? 1f : 0f, 1f / 60f);

            if(units.size == 0 && (buildProgress += edelta() / buildTime) >= 1f){
                    for(int i = 0; i < amount; i++) {
                    if(!net.client()) {
                        unit = unitType.create(team);
                        if (unit instanceof BuildingTetherc bt) {
                            bt.building(this);
                        }
                        unit.set(x, y);
                        unit.rotation = 90f * i;
                        unit.add();
                        units.add(unit);
                        Call.unitTetherBlockSpawned(tile, unit.id);
                    }
                    }

                    consume();
            }
        }

        public void spawned(int id){
            Fx.spawn.at(x, y);
            buildProgress = 0f;
            if(net.client()){
                readUnitId = id;
            }
        }

        @Override
        public boolean shouldConsume(){
            return unitT == null;
        }

        @Override
        public boolean shouldActiveSound(){
            return shouldConsume() && warmup > 0.01f;
        }

        @Override
        public void draw(){
            Draw.rect(block.region, x, y);
            if(units.size == 0){
                Draw.draw(Layer.blockOver, () -> {
                    Drawf.construct(this, unitType.fullIcon, 0f, buildProgress, warmup, totalProgress);
                });
            }else{
                Draw.z(Layer.bullet - 0.01f);
                Draw.color(polyColor);
                Lines.stroke(polyStroke * readyness);
                Lines.poly(x, y, polySides, polyRadius + Mathf.absin(Time.time, 2f, polyRadius / 4f) * polyScale, Time.time * polyRotateSpeed);
                Draw.reset();
                Draw.z(Layer.block);
            }
        }

        @Override
        public float totalProgress(){
            return totalProgress;
        }

        @Override
        public float progress(){
            return buildProgress;
        }

        @Override
        public void write(Writes write){
            super.write(write);

            write.i(unitT == null ? -1 : unitT.id);
            write.s(units.size);
            for(var unit : units){
                write.i(unit.id);
            }
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            readUnitId = read.i();
            int count = read.s();
            readUnits.clear();
            for(int i = 0; i < count; i++){
                readUnits.add(read.i());
            }
        }
    }
}
