package ice.classes.entities.bullets;

import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.*;

public class ScaledBasicBulletType extends BasicBulletType implements HealthScaledBulletType {
    public float healthScale;
    public ScaledBasicBulletType(float speed, float damage, float healthScale) {
        super(speed, damage);
        this.healthScale = healthScale;
    }
    @Override
    public float healthScale() {
        return healthScale;
    }
    @Override
    public void hitEntity(Bullet b, Hitboxc entity, float health) {
        super.hitEntity(b, entity, health);

        onHit(b, entity);
    }
}
