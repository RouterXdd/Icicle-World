package ice.classes.pattern;

import arc.math.Mathf;
import arc.util.Nullable;
import mindustry.entities.pattern.ShootPattern;

public class RandomShootSpread extends ShootPattern{
    public int minShots = 3;
    public int maxShots = 3;
    public float minSpread = 5;
    public float maxSpread = 10;

    public RandomShootSpread(int minShots, int maxShots, float minSpread, float maxSpread) {
        this.minShots = minShots;
        this.maxShots = maxShots;
        this.minSpread = minSpread;
        this.maxSpread = maxSpread;
    }

    public RandomShootSpread(){}

    public void shoot(int totalShots, ShootPattern.BulletHandler handler, @Nullable Runnable barrelIncrementer) {
        for(int i = 0; i < Mathf.random(minShots, maxShots); ++i) {
            float angleOffset = (float)i * Mathf.random(minShots, maxShots) - (float)(this.shots - 1) * Mathf.random(minSpread, maxSpread) / 2.0F;
            handler.shoot(0.0F, 0.0F, angleOffset, this.firstShotDelay + this.shotDelay * (float)i);
        }

    }
}
