package ice.classes.type.meta;

import arc.math.Mathf;
import arc.math.geom.Position;
import arc.struct.Seq;
import arc.util.Nullable;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.game.Team;
import mindustry.gen.Building;

import static mindustry.Vars.*;
import static mindustry.Vars.tilesize;

public class IceDamage {
    private static final Seq<Building> builds = new Seq<>();
    public static void applyEditableSuppression(Team team, float x, float y, float range, float reload, float maxDelay, float applyParticleChance, Effect effect, @Nullable Position source){
        builds.clear();
        indexer.eachBlock(null, x, y, range, build -> build.team != team, build -> {
            float prev = build.healSuppressionTime;
            build.applyHealSuppression(reload + 1f);

            if(build.wasRecentlyHealed(60f * 12f) || build.block.suppressable){

                //add prev check so ability spam doesn't lead to particle spam (essentially, recently suppressed blocks don't get new particles)
                if(!headless && prev - Time.time <= reload/2f){
                    builds.add(build);
                }
            }
        });

        //to prevent particle spam, the amount of particles is to remain constant (scales with number of buildings)
        float scaledChance = applyParticleChance / builds.size;
        for(var build : builds){
            if(Mathf.chance(scaledChance)){
                Time.run(Mathf.random(maxDelay), () -> {
                    effect.at(build.x + Mathf.range(build.block.size * tilesize / 2f), build.y + Mathf.range(build.block.size * tilesize / 2f), 0f, source);
                });
            }
        }
    }
}
