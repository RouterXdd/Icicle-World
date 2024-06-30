package ice.classes.blocks.environment;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import ice.IcicleVars;
import ice.content.*;
import mindustry.Vars;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.type.UnitType;
import mindustry.world.Tile;

import static mindustry.Vars.world;

public class EnemyNest extends EditorBlock {
    public float minInterval = 500;
    public float maxInterval = 700;
    public int startAmount = 3;

    public EnemyNest(String name) {
        super(name);
        health = 3000;
        solid = false;
        update = configurable = true;
        buildType = EnemyNestBuild::new;
        targetable = true;
        destructible = true;
        drawTeamOverlay = false;
        config(Float.class,(EnemyNestBuild build, Float val)->{build.statRising = val;});
    }
    public class EnemyNestBuild extends Building {
        float progress;
        float interval = minInterval;
        int armorRise = 0;
        int healthRise = 0;
        public float statRising;
        @Override
        public void drawCracks(){}

        public void drawDefaultCracks(){
            super.drawCracks();
        }
        @Override
        public void draw(){
            Draw.z(Layer.groundUnit + 1f);
            Draw.rect(region, x, y);
            Draw.z(Layer.groundUnit + 2f);
            if (statRising <= 0 && IcicleVars.debug){
                Icon.lock.draw(x,y,1,1);
            }
            drawDefaultCracks();
        }
        @Override
        public void updateTile(){
            progress += delta();
            if (progress >= interval){
                interval = Mathf.random(minInterval, maxInterval);
                if (Mathf.chance(statRising)) {
                    armorRise += Mathf.random(0, 4);
                    healthRise += Mathf.random(0, 100);
                }
                for(int i = 0; i < Mathf.random(startAmount, startAmount + Vars.state.wave); i++) {
                    Unit unit = getEnemy().create(team);
                    unit.armor += armorRise;
                    unit.health += healthRise;
                    unit.set(x + Mathf.random(-10, 10), y + Mathf.random(-10, 10));
                    unit.rotation = 90f;
                    unit.add();
                }
                progress = 0;
            }
        }
        public UnitType getEnemy(){
            UnitType[][] monster = {
                    {IceUnitTypes.swarmling, IceUnitTypes.swarmling, IceUnitTypes.reaver,IceUnitTypes.giant}
            };
            return monster[Mathf.random(monster.length - 1)][Mathf.random(0, 3)];
        }
        @Override
        public void buildConfiguration(Table table) {
            super.buildConfiguration(table);
            table.label(()-> Core.bundle.format("bar.stat-rise",
                    statRising));
            table.row();
            table.slider(0, 1, 0.1f, statRising, true, this::configure);
        }
        @Override
        public void afterDestroyed() {
            super.onDestroyed();
            Tile tile = world.tileWorld(x, y);
            tile.setBlock(IceBlocks.destroyedMonsterNest, team);
        }
        @Override
        public void write(Writes write){
            super.write(write);

            write.f(statRising);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);

            statRising = read.f();
        }
    }
}
