package ice.classes.blocks.production;

import arc.graphics.*;
import arc.math.*;
import arc.util.*;
import ice.graphics.IcePal;
import mindustry.entities.*;
import mindustry.game.Team;
import mindustry.world.blocks.production.*;


public class BreakDrill extends Drill {
    public float randAngle = Mathf.random(0, 360);
    public float reload = 130;
    public int lengthLight = 5;
    public float damageLight = 10;
    public Color colorLight = IcePal.thalliumMid;

    public BreakDrill(String name) {
        super(name);
        update = true;
    }

    public class BreakDrillBuild extends DrillBuild {
        public float reloadTime = reload;
        @Override
        public void updateTile() {
            if (timer(timerDump, dumpTime)) {
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if (dominantItem == null) {
                return;
            }
            if (reloadTime <= 0) {
                Lightning.create(Team.derelict,colorLight,damageLight ,this.x, this.y, randAngle, lengthLight);
                reloadTime = reload;
                randAngle = Mathf.random(0, 360);
            }

            timeDrilled += warmup * delta();
            reloadTime -= Time.delta;

            float delay = getDrillTime(dominantItem);

            if (items.total() < itemCapacity && dominantItems > 0 && efficiency > 0) {
                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency;

                lastDrillSpeed = (speed * dominantItems * warmup) / delay;
                warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                progress += delta() * dominantItems * speed * warmup;

                if (Mathf.chanceDelta(updateEffectChance * warmup))
                    updateEffect.at(x + Mathf.range(size * 2f), y + Mathf.range(size * 2f));
            } else {
                lastDrillSpeed = 0f;
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                return;
            }

            if (dominantItems > 0 && progress >= delay && items.total() < itemCapacity) {
                offload(dominantItem);

                progress %= delay;

                if (wasVisible && Mathf.chanceDelta(updateEffectChance * warmup))
                    drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }
        }

    }
}