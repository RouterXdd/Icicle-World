package ice.classes.blocks.units;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
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
        boolean tooClose = indexer.eachBlock(team, tile.worldx(), tile.worldy(), noBuildRange, b -> b.block() instanceof ForceConstructor, b -> {
            Drawf.dashLine(Pal.remove, tile.worldx(), tile.worldy(), b.x, b.y);
        });
        return super.canPlaceOn(tile, team, rotation) && !tooClose;
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        if(!canPlaceOn(world.tile(x, y), player.team(), rotation)){
            drawPlaceText(Core.bundle.get("message.nobuild"), x, y, valid);
        }
    }

    public class UnitTransportSourceBuild extends Building implements UnitTetherBlock{
        //needs to be "unboxed" after reading, since units are read after buildings.
        public int readUnitId = -1;
        public float buildProgress, totalProgress;
        public float warmup, readyness;
        public @Nullable Unit unit;

        @Override
        public void updateTile(){
            //unit was lost/destroyed
            if(unit != null && (unit.dead || !unit.isAdded())){
                unit = null;
            }

            if(readUnitId != -1){
                unit = Groups.unit.getByID(readUnitId);
                if(unit != null || !net.client()){
                    readUnitId = -1;
                }
            }

            warmup = Mathf.approachDelta(warmup, efficiency, 1f / 60f);
            readyness = Mathf.approachDelta(readyness, unit != null ? 1f : 0f, 1f / 60f);

            if(unit == null){
                buildProgress += edelta() / buildTime;
                totalProgress += edelta();

                if(buildProgress >= 1f ){
                    for(int i = 0; i < amount; i++) {
                    if(!net.client()) {
                        unit = unitType.create(team);
                        if (unit instanceof BuildingTetherc bt) {
                            bt.building(this);
                        }
                        unit.set(x, y);
                        unit.rotation = 90f * i;
                        unit.add();
                        Call.unitTetherBlockSpawned(tile, unit.id);
                    }
                    }
                    consume();
                }
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
            return unit == null;
        }

        @Override
        public boolean shouldActiveSound(){
            return shouldConsume() && warmup > 0.01f;
        }

        @Override
        public void draw(){
            Draw.rect(block.region, x, y);
            if(unit == null){
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

            write.i(unit == null ? -1 : unit.id);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            readUnitId = read.i();
        }
    }
}
