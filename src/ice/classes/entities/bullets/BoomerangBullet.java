package ice.classes.entities.bullets;

import arc.util.Time;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.Bullet;

public class BoomerangBullet extends BasicBulletType {
    public float cut = 2;
    public BoomerangBullet(float speed, float damage) {
        super(speed, damage);
    }
    @Override
    public void update(Bullet b) {
        super.update(b);
        if (b.timer.get(1, b.lifetime / cut)){
            b.vel.setAngle(b.rotation() + 180);
        }
    }
}
