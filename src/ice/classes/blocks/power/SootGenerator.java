package ice.classes.blocks.power;

import arc.*;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import ice.IcicleVars;
import ice.content.IceStatuses;
import ice.graphics.IcePal;
import mindustry.entities.Units;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.type.StatusEffect;
import mindustry.world.Block;
import mindustry.world.blocks.power.*;

public class SootGenerator extends ConsumeGenerator {
    public float range = 50f;
    public StatusEffect status = IceStatuses.inSoot;
    public float statusTime = 300f;
    public SootGenerator(String name) {
        super(name);
        buildType = SootGeneratorBuild::new;
    }
    public class SootGeneratorBuild extends ConsumeGeneratorBuild {
        @Override
        public void updateTile(){
            boolean valid = efficiency > 0;

            warmup = Mathf.lerpDelta(warmup, valid ? 1f : 0f, warmupSpeed);

            productionEfficiency = efficiency * efficiencyMultiplier;
            totalTime += warmup * Time.delta;

            //randomly produce the effect
            if(valid && Mathf.chanceDelta(effectChance)){
                generateEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
            }

            //take in items periodically
            if(hasItems && valid && generateTime <= 0f){
                consume();
                consumeEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
                generateTime = 1f;
                Units.nearbyEnemies(Team.derelict, x, y, range, other -> {
                    other.apply(status, statusTime);
                });
            }

            if(outputLiquid != null){
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);

                if(explodeOnFull && liquids.get(outputLiquid.liquid) >= liquidCapacity - 0.01f){
                    kill();
                    Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                }
            }
            

            //generation time always goes down, but only at the end so consumeTriggerValid doesn't assume fake items
            generateTime -= delta() / itemDuration;
        }
        @Override
        public void draw(){
            drawer.draw(this);
            if (IcicleVars.debug) {
                Draw.color(IcePal.sootColor);
                Lines.stroke(1f);
                Lines.poly(x, y, 360, range);
                Draw.reset();
            }
        }
    }
}
