package ice.classes.blocks.production;

import arc.graphics.*;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.*;
import arc.util.*;
import ice.graphics.IcePal;
import mindustry.entities.*;
import mindustry.game.Team;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.world.blocks.production.*;


public class BreakDrill extends Drill {
    public float reload = 130;
    public int lengthLight = 5;
    public float damageLight = 10;
    public Color colorLight = IcePal.thalliumMid;
    public boolean blockTeam, drawEngine, teamColorLight = false;
    public float engineSize, engineScale;
    public float minHealth = 0.2f;

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
            if (reloadTime <= 0 && health > maxHealth * minHealth) {
                if (teamColorLight){
                    if (blockTeam){
                        Lightning.create(this.team,team.color,damageLight ,this.x, this.y, Mathf.random(0, 360), lengthLight);
                    } else {
                        Lightning.create(Team.derelict, team.color, damageLight, this.x, this.y, Mathf.random(0, 360), lengthLight);
                    }
                } else {
                    if (blockTeam) {
                            Lightning.create(this.team, colorLight, damageLight, this.x, this.y, Mathf.random(0, 360), lengthLight);
                    } else {
                        Lightning.create(Team.derelict, colorLight, damageLight, this.x, this.y, Mathf.random(0, 360), lengthLight);
                    }
                }
                reloadTime = reload;
            }
            if (health < maxHealth * minHealth){
                efficiency = efficiency / 2;
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
        public void draw(){
            super.draw();
            /*

            Draw.rect(region, x, y);
            Draw.z(Layer.blockCracks);
            drawDefaultCracks();

            Draw.z(Layer.blockAfterCracks);
            if(drawRim){
                Draw.color(heatColor);
                Draw.alpha(warmup * ts * (1f - s + Mathf.absin(Time.time, 3f, s)));
                Draw.blend(Blending.additive);
                Draw.rect(rimRegion, x, y);
                Draw.blend();
                Draw.color();
            }*/
            if (drawEngine){
                Draw.color(team.color);
                Draw.alpha(warmup);
                Fill.circle(
                        x,
                        y,
                        (engineSize + Mathf.absin(Time.time, 2f, engineSize / 4f)) * engineScale
                );
                Draw.color(Color.white);
                Draw.alpha(warmup);
                Fill.circle(
                        x,
                        y,
                        (engineSize + Mathf.absin(Time.time, 2f, engineSize / 4f)) / 2f  * engineScale
                );
            }/*

            if(drawSpinSprite){
                Drawf.spinSprite(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }else{
                Draw.rect(rotatorRegion, x, y, timeDrilled * rotateSpeed);
            }

            Draw.rect(topRegion, x, y);

            if(dominantItem != null && drawMineItem){
                Draw.color(dominantItem.color);
                Draw.rect(itemRegion, x, y);
                Draw.color();
            }*/
        }

    }
}