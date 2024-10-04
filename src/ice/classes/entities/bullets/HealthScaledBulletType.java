package ice.classes.entities.bullets;

import mindustry.entities.Damage;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Healthc;
import mindustry.gen.Hitboxc;
import mindustry.gen.Unit;

public interface HealthScaledBulletType{
    float healthScale();

    default void onHit(Bullet b, Hitboxc entity){
        if (entity instanceof Unit u) {
                u.damage(((Healthc) entity).health() * healthScale() / 100f);
            }
        }
    }
