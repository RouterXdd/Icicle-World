package ice.classes.blocks.defence;

import arc.math.Mathf;
import arc.util.Time;
import ice.classes.type.IceStats;
import ice.content.IceStatuses;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.*;
import mindustry.gen.Bullet;
import mindustry.type.StatusEffect;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.meta.*;

import static mindustry.Vars.tilesize;

public class IceWall extends Wall {
    public float inflictChance = 0f;
    public StatusEffect inflictStatus = IceStatuses.radiation;
    public float inflictTime = 60 * 40f;
    public float inflictRange = 10f * 8f;
    public float regenSpeed = 0.25f;
    public Effect regenEffect = Fx.none;
    public float regenChance = 0.1f;
    public boolean regenerate = true;
    public int onlyDamage = 0;
    public IceWall(String name) {
        super(name);
        update = true;
        suppressable = true;
    }
    @Override
    public void setStats(){
        super.setStats();
        if(regenerate)stats.add(Stat.repairSpeed, (int)(regenSpeed * 60f), StatUnit.perSecond);
        if(onlyDamage > 0f) stats.add(IceStats.onlyDamage, onlyDamage, StatUnit.none);
        if(inflictChance > 0f){
            stats.add(IceStats.inflictChance, inflictChance * 100f, StatUnit.percent);
            stats.add(IceStats.inflictRange, inflictRange / tilesize, StatUnit.blocks);
            stats.add(IceStats.inflictStatus,inflictStatus.emoji() + "" + inflictStatus.localizedName);
        }
    }
    public class IceWallBuild extends WallBuild {
        public void update(){
            if (regenerate) {
                boolean canHeal = !checkSuppression();
                if (damaged() && canHeal) {
                    if (Mathf.chance(regenChance))
                        regenEffect.at(x + Mathf.range(size * tilesize / 2f - 1f), y + Mathf.range(size * tilesize / 2f - 1f));
                    heal(regenSpeed * Time.delta);
                }
            }
        }
        @Override
        public boolean collision(Bullet bullet) {
                if (onlyDamage > 0){
                    damage(onlyDamage);
                    bullet.collided().add(100);
                } else {
                    super.collision(bullet);
                }
                if (inflictChance > 0 && Mathf.chance(inflictChance)) {
                    Units.closestEnemy(this.team, x, y, inflictRange, other -> {
                                other.apply(inflictStatus, inflictTime);
                                return true;
                    });
                }
            return true;
        }
    }
}
