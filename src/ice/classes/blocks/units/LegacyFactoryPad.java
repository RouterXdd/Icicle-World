package ice.classes.blocks.units;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.util.*;
import mindustry.Vars;
import mindustry.ai.UnitCommand;
import mindustry.content.Fx;
import mindustry.entities.Units;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.world.blocks.units.UnitFactory;

public class LegacyFactoryPad extends UnitFactory {
    public int[] capacities = {};
    public TextureRegion topRegion;
    public boolean drawTop = true;

    public LegacyFactoryPad(String name) {
        super(name);
        rotate = false;
        update = true;
        hasPower = true;
        hasItems = true;
        solid = false;
        configurable = true;
        clearOnDoubleTap = true;
        commandable = true;
        ambientSound = Sounds.respawning;
    }
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        if (drawTop) {
            Draw.rect(topRegion, plan.drawx(), plan.drawy());
        }
    }
    @Override
    public TextureRegion[] icons(){
        if (drawTop) {
            return new TextureRegion[]{region, topRegion};
        } else {
            return new TextureRegion[]{region};
        }
    }
    @Override
    public void load(){
        region = Core.atlas.find(this.name);
        topRegion = Core.atlas.find(this.name + "-top");
    }

    @Override
    public boolean outputsItems(){
        return false;
    }
    public class LegacyFactoryPadBuild extends UnitFactoryBuild {
        public @Nullable UnitCommand command;

        @Override
        public Object config(){
            return currentPlan;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);

            if(currentPlan != -1){
                UnitPlan plan = plans.get(currentPlan);
                Draw.draw(Layer.blockOver, () -> Drawf.construct(this, plan.unit, 0f, progress / plan.time, speedScl, time));
            }
            if (drawTop) {
                Draw.rect(topRegion, x, y);
            }
        }

        @Override
        public void updateTile(){
            if(!configurable){
                currentPlan = 0;
            }

            if(currentPlan < 0 || currentPlan >= plans.size){
                currentPlan = -1;
            }

            if(efficiency > 0 && currentPlan != -1 && Units.canCreate(team, unit())){
                time += edelta() * speedScl * Vars.state.rules.unitBuildSpeed(team);
                progress += edelta() * Vars.state.rules.unitBuildSpeed(team);
                speedScl = Mathf.lerpDelta(speedScl, 1f, 0.05f);
            }else{
                speedScl = Mathf.lerpDelta(speedScl, 0f, 0.05f);
            }

            moveOutPayload();

            if(currentPlan != -1 && payload == null){
                UnitPlan plan = plans.get(currentPlan);

                //make sure to reset plan when the unit got banned after placement
                if(plan.unit.isBanned()){
                    currentPlan = -1;
                    return;
                }

                if(progress >= plan.time ){
                    progress %= 1f;

                    Unit unit = plan.unit.create(team);
                    unit.set(x + Mathf.random(-0.001f,0.001f), y + Mathf.random(-0.001f,0.001f));
                    unit.rotation = 90f;
                    if (unit.hitSize < 26f) {
                        Fx.spawn.at(x, y);
                    }
                    unit.add();
                    if(unit.isCommandable()){
                        if(commandPos != null){
                            unit.command().commandPosition(commandPos);
                        }

                        unit.command().command(command == null && unit.type.defaultCommand != null ? unit.type.defaultCommand : command);
                    }

                    consume();
                }

                progress = Mathf.clamp(progress, 0, plan.time);
            }else{
                progress = 0f;
            }
        }
    }
}
