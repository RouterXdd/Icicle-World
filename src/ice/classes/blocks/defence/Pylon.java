package ice.classes.blocks.defence;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import ice.graphics.IcePal;
import mindustry.content.*;
import mindustry.entities.Units;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class Pylon extends Block{
    public final int timerUse = timers++;
    public Color baseColor = IcePal.thalliumLight;
    public Color phaseColor = baseColor;
    public float reload = 250f;
    public float range = 60f;
    public float healAmount = 12f;
    public float phaseBoost = 12f;
    public float phaseRangeBoost = 50f;
    public float useTime = 400f;
    public float layer = Layer.bullet - 0.001f, blinkScl = 20f, blinkSize = 0.1f;
    public float effectRadius = 5f, sectorRad = 0.14f, rotateSpeed = 0.5f;
    public int sectors = 5;

    public Pylon(String name){
        super(name);
        solid = true;
        update = true;
        group = BlockGroup.projectors;
        hasPower = true;
        hasItems = true;
        emitLight = true;
        lightRadius = 50f;
        suppressable = true;
        envEnabled |= Env.space;
    }

    @Override
    public boolean outputsItems(){
        return false;
    }

    @Override
    public void setStats(){
        stats.timePeriod = useTime;
        super.setStats();

        stats.add(Stat.repairTime, healAmount, StatUnit.none);
        stats.add(Stat.range, range / tilesize, StatUnit.blocks);

        if(findConsumer(c -> c instanceof ConsumeItems) instanceof ConsumeItems cons){
            stats.remove(Stat.booster);
            stats.add(Stat.booster, StatValues.itemBoosters(
                    "{0}" + StatUnit.timesSpeed.localized(),
                    stats.timePeriod, (phaseBoost + healAmount) / healAmount, phaseRangeBoost,
                    cons.items)
            );
        }
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, baseColor);

        indexer.eachBlock(player.team(), x * tilesize + offset, y * tilesize + offset, range, other -> true, other -> Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f))));
    }

    public class PylonBuild extends Building implements Ranged{
        public float heat, charge = Mathf.random(reload), phaseHeat, smoothEfficiency;

        @Override
        public float range(){
            return range;
        }

        @Override
        public void updateTile(){
            boolean canHeal = !checkSuppression();

            smoothEfficiency = Mathf.lerpDelta(smoothEfficiency, efficiency, 0.08f);
            heat = Mathf.lerpDelta(heat, efficiency > 0 && canHeal ? 1f : 0f, 0.08f);
            charge += heat * delta();

            phaseHeat = Mathf.lerpDelta(phaseHeat, optionalEfficiency, 0.1f);

            if(optionalEfficiency > 0 && timer(timerUse, useTime) && canHeal){
                consume();
            }

            if(charge >= reload && canHeal){
                float realRange = range + phaseHeat * phaseRangeBoost;
                charge = 0f;

                indexer.eachBlock(this, realRange, b -> b.damaged() && !b.isHealSuppressed(), other -> {
                    other.heal((healAmount + phaseHeat * phaseBoost) * efficiency);
                    other.recentlyHealed();
                    Fx.chainLightning.at(x, y, 0f, baseColor, other);
                    Fx.healBlockFull.at(other.x, other.y, other.block.size, baseColor, other.block);
                });
                Units.nearby(this.team, x, y, realRange, otherUnit -> {
                    if(otherUnit.damaged()) {
                        Fx.chainLightning.at(x, y, 0f, baseColor, otherUnit);
                        otherUnit.heal((healAmount + phaseHeat * phaseBoost) * efficiency);
                    }
                });
            }
        }

        @Override
        public double sense(LAccess sensor){
            if(sensor == LAccess.progress) return Mathf.clamp(charge / reload);
            return super.sense(sensor);
        }

        @Override
        public void drawSelect(){
            float realRange = range + phaseHeat * phaseRangeBoost;

            indexer.eachBlock(this, realRange, other -> true, other -> Drawf.selected(other, Tmp.c1.set(baseColor).a(Mathf.absin(4f, 1f))));
            Units.nearby(this.team, x, y, realRange, otherUnit -> {
                Draw.color(baseColor, phaseColor, phaseHeat);
                for(int i = 0; i < sectors; i++){
                    float rot = i * 360f/sectors - Time.time * rotateSpeed;
                    Lines.arc(otherUnit.x, otherUnit.y, otherUnit.hitSize + 3f, sectorRad, rot);
                }
            });

            Drawf.dashCircle(x, y, realRange, baseColor);
        }

        @Override
        public void draw(){
            super.draw();
            float realRange = range + phaseHeat * phaseRangeBoost;

            Draw.z(layer);
            Draw.color(baseColor, phaseColor, phaseHeat);
            Draw.alpha(heat * Mathf.absin(Time.time, 50f / Mathf.PI2, 1f) * 0.5f);
            float orbRadius = effectRadius * (1f + Mathf.absin(blinkScl, blinkSize));

            Fill.circle(x, y, orbRadius);
            Draw.color();
            Fill.circle(x, y, orbRadius / 2f);

            Lines.stroke((0.7f + Mathf.absin(blinkScl, 0.7f)), phaseColor);

            for(int i = 0; i < sectors; i++){
                float rot = i * 360f/sectors - Time.time * rotateSpeed;
                Lines.arc(x, y, orbRadius + 3f, sectorRad, rot);
            }

            Lines.stroke(Lines.getStroke() * heat);

            if(heat > 0){
                for(int i = 0; i < sectors; i++){
                    float rot = i * 360f/sectors + Time.time * rotateSpeed;
                    Lines.arc(x, y, realRange, sectorRad, rot);
                }
            }

            Drawf.light(x, y, realRange * 1.5f, phaseColor, heat * 0.8f);

            Draw.reset();
        }

        @Override
        public void drawLight(){
            Drawf.light(x, y, lightRadius * smoothEfficiency, baseColor, 0.7f * smoothEfficiency);
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
            write.f(phaseHeat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
            phaseHeat = read.f();
        }
    }
}
