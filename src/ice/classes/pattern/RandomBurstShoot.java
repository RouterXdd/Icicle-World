package ice.classes.pattern;

import arc.math.Mathf;
import arc.util.Nullable;
import mindustry.entities.pattern.ShootPattern;

public class RandomBurstShoot extends ShootPattern {
    public int minShots = 3;
    public int maxShots = 3;
    public float minDelay = 2;
    public float maxDelay = 5;

    public RandomBurstShoot(int minShots, int maxShots, float minDelay, float maxDelay) {
        this.minShots = minShots;
        this.maxShots = maxShots;
        this.minDelay = minDelay;
        this.maxDelay = maxDelay;
    }

    public RandomBurstShoot(){}

    public void shoot(int totalShots, ShootPattern.BulletHandler handler, @Nullable Runnable barrelIncrementer) {
        for(int i = 0; i < Mathf.random(minShots, maxShots); ++i) {
            handler.shoot(0.0F, 0.0F, 0, this.firstShotDelay + this.shotDelay + Mathf.random(minDelay, maxDelay) * (float)i);
        }

    }
}
