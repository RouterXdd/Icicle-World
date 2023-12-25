package ice.content;

import arc.graphics.Color;
import arc.graphics.g2d.Lines;
import ice.graphics.IcePal;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.Sounds;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;

public class IceBullets {
    public static BulletType
    //main bullets
    pointBullet, pointCoreBullet, vesselBullet, basisBullet, stemBullet, sporeSkimmerBullet, siliconSkimmerBullet, methaneSpike, yellowBlast, yellowPlasma,
    //funny bullets
    basicBullet;
    public static void load(){
        pointBullet = new BasicBulletType(20f, 6){{
            width = 8f;
            hitSize = 8f;
            height = 16f;
            shootEffect = IceFx.lightningThalliumShoot;
            smokeEffect = Fx.shootBigSmoke;
            ammoMultiplier = 1;
            lifetime = 4;
            pierce = true;
            pierceBuilding = true;
            hitColor = backColor = trailColor = IcePal.thalliumMid;
            frontColor = Color.white;
            trailWidth = 1.8f;
            trailLength = 100;
            hitEffect = despawnEffect = Fx.hitBulletColor;
            collideTerrain = true;
        }};
        pointCoreBullet = new BasicBulletType(20f, 10){{
            width = 8f;
            hitSize = 8f;
            height = 16f;
            shootEffect = IceFx.lightningThalliumShoot;
            smokeEffect = Fx.shootBigSmoke;
            ammoMultiplier = 1;
            lifetime = 4;
            pierce = true;
            pierceBuilding = true;
            hitColor = backColor = trailColor = IcePal.thalliumMid;
            frontColor = Color.white;
            trailWidth = 1.8f;
            trailLength = 100;
            hitEffect = despawnEffect = Fx.hitBulletColor;
            collideTerrain = true;
            buildingDamageMultiplier = 0.0001f;
        }};
        vesselBullet = new BasicBulletType(6f, 15){{
            width = 7f;
            height = 14f;
            hitSize = 5f;
            shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
            smokeEffect = Fx.shootBigSmoke;
            ammoMultiplier = 1;
            reloadMultiplier = 1f;
            lifetime = 15f;
            pierceCap = 3;
            pierce = true;
            pierceBuilding = true;
            hitColor = backColor = trailColor = IcePal.thalliumLight;
            frontColor = Color.white;
            trailWidth = 2.2f;
            trailLength = 7;
            hitEffect = despawnEffect = Fx.hitBulletColor;
            collideTerrain = true;
        }};
        basisBullet = new ShrapnelBulletType(){{
            length = 34;
            damage = 25f;
            width = 14f;
            toColor = IcePal.thalliumMid;
            shootEffect = smokeEffect = Fx.shootBigColor;
        }};
        stemBullet = new BasicBulletType(4f, 2.5f){{
            width = 4f;
            height = 9f;
            lifetime = 20f;
            hitColor = backColor = IcePal.sporeMid;
            frontColor = IcePal.sporeLight;
        }};
        sporeSkimmerBullet = new BasicBulletType(4, 30){{
            shootEffect = new MultiEffect(Fx.shootTitan, new WaveEffect(){{
                colorTo = IcePal.sporeLight;
                sizeTo = 26f;
                lifetime = 14f;
                strokeFrom = 4f;
            }});
            smokeEffect = Fx.shootSmokeTitan;
            hitColor = IcePal.sporeLight;

            trailEffect = Fx.missileTrail;
            trailInterval = 3f;
            trailParam = 6f;
            pierceCap = 3;
            lifetime = 32.5f;
            width = 7f;
            height = 14f;
            backColor = IcePal.sporeLight;
            frontColor = Color.white;
            shrinkX = shrinkY = 0f;
            trailColor = IcePal.sporeLight;
            trailLength = 7;
            trailWidth = 2.2f;
            despawnEffect = hitEffect = new ExplosionEffect(){{
                waveColor = IcePal.sporeLight;
                smokeColor = Color.gray;
                sparkColor = IcePal.sporeMid;
                waveStroke = 5f;
                waveRad = 20f;
            }};
            collideTerrain = true;
            despawnSound = Sounds.dullExplosion;

            intervalBullet = new BasicBulletType(2f, 5){{
                width = 6f;
                hitSize = 5f;
                height = 12f;
                lifetime = 18f;
                hitColor = trailColor = IcePal.sporeLight;
                frontColor = Color.white;
                trailWidth = 2.1f;
                trailLength = 5;
                hitEffect = despawnEffect = new WaveEffect(){{
                    colorFrom = colorTo = IcePal.sporeLight;
                    sizeTo = 4f;
                    strokeFrom = 4f;
                    lifetime = 10f;
                }};
                collideTerrain = true;
                buildingDamageMultiplier = 0.3f;
            }};

            bulletInterval = 5f;
            intervalRandomSpread = 0f;
            intervalBullets = 2;
            intervalAngle = 180f;
            intervalSpread = 300f;
        }};
        siliconSkimmerBullet = new BasicBulletType(4, 25){{
            shootEffect = new MultiEffect(Fx.shootTitan, new WaveEffect(){{
                colorTo = Pal.darkishGray;
                sizeTo = 26f;
                lifetime = 14f;
                strokeFrom = 4f;
            }});
            collideTerrain = true;
            smokeEffect = Fx.shootSmokeTitan;
            hitColor = Pal.darkishGray;

            trailEffect = Fx.missileTrail;
            trailInterval = 3f;
            trailParam = 6f;
            pierceCap = 5;
            lifetime = 32.5f;
            width = 7f;
            height = 14f;
            backColor = Pal.darkishGray;
            frontColor = Color.white;
            shrinkX = shrinkY = 0f;
            trailColor = Pal.darkishGray;
            trailLength = 7;
            trailWidth = 2.2f;
            homingPower = 0.4f;
            despawnEffect = hitEffect = new ExplosionEffect(){{
                waveColor = Pal.darkishGray;
                smokeColor = Color.gray;
                sparkColor = Pal.darkerGray;
                waveStroke = 5f;
                waveRad = 20f;
            }};
            despawnSound = Sounds.dullExplosion;

            intervalBullet = new BasicBulletType(2f, 3){{
                width = 6f;
                hitSize = 5f;
                height = 12f;
                lifetime = 18f;
                hitColor = trailColor = Pal.darkishGray;
                frontColor = Color.white;
                trailWidth = 2.1f;
                trailLength = 5;
                collideTerrain = true;
                hitEffect = despawnEffect = new WaveEffect(){{
                    colorFrom = colorTo = Pal.darkishGray;
                    sizeTo = 4f;
                    strokeFrom = 4f;
                    lifetime = 10f;
                }};
                buildingDamageMultiplier = 0.3f;
                homingPower = 0.2f;
            }};

            bulletInterval = 5f;
            intervalRandomSpread = 0f;
            intervalBullets = 2;
            intervalAngle = 180f;
            intervalSpread = 300f;
        }};
        methaneSpike = new BasicBulletType(){{
            damage = 70;
            splashDamage = 50;
            splashDamageRadius = 48;
            splashDamagePierce = true;
            speed = 9f;
            width = height = 18;
            shrinkY = 0.3f;
            backSprite = "large-bomb-back";
            sprite = "mine-bullet";
            collidesTiles = true;
            collideTerrain = true;
            shootEffect = Fx.shootBig2;
            smokeEffect = Fx.shootSmokeDisperse;
            frontColor = IcePal.methaneLightish;
            backColor = trailColor = hitColor = IcePal.methaneMid;
            trailChance = 1f;
            ammoMultiplier = 3f;

            lifetime = 170f / 9f;
            rotationOffset = 90f;
            trailRotation = true;
            trailEffect = Fx.disperseTrail;

            hitEffect = despawnEffect = IceFx.hitSplashBulletColor;
        }};
        yellowBlast = new BasicBulletType(4f, 50){{
            backColor = trailColor = hitColor = Pal.accent;
            frontColor = Color.white;
            width = 9f;
            height = 13f;
            lifetime = 35f;
            trailWidth = 2f;
            trailLength = 4;
            collideTerrain = true;

            trailEffect = Fx.missileTrail;
            trailParam = 1.8f;
            trailInterval = 5f;

            splashDamageRadius = 40f;
            splashDamage = 28f;

            hitEffect = despawnEffect = new MultiEffect(Fx.hitBulletColor, new WaveEffect(){{
                colorFrom = colorTo = Pal.accent;
                sizeTo = splashDamageRadius + 3f;
                lifetime = 9f;
                strokeFrom = 3f;
            }});

            shootEffect = new MultiEffect(Fx.shootBigColor, new Effect(12, e -> {
                color(Color.white, e.color, e.fin());
                stroke(0.7f + e.fout());
                Lines.square(e.x, e.y, e.fin() * 5.5f, e.rotation + 45f);

                Drawf.light(e.x, e.y, 23f, e.color, e.fout() * 0.7f);
            }));
            smokeEffect = Fx.shootSmokeSquare;
        }};
        yellowPlasma = new BasicBulletType(6f, 30){{
            sprite = "missile-large";
            smokeEffect = Fx.shootBigSmoke;
            shootEffect = Fx.shootBigColor;
            width = 6f;
            height = 9f;
            lifetime = 25f;
            hitSize = 5f;
            hitColor = backColor = trailColor = Pal.accent;
            frontColor = Color.white;
            trailWidth = 1.7f;
            trailLength = 7;
            despawnEffect = hitEffect = Fx.hitBulletColor;
            lightning = 3;
            lightningDamage = 8;
            lightningLength = 5;
        }};
        basicBullet = new BasicBulletType(5, 50){{
            width = height = 7f;
            lifetime = 30f;
            sprite = "circle-bullet";
            shootEffect = Fx.sparkShoot;
            smokeEffect = Fx.shootBigSmoke;
            hitColor = backColor = Color.valueOf("1d54a1");
            frontColor = Color.valueOf("3873c5");
            hitEffect = despawnEffect = Fx.hitBulletColor;
        }};
    }
}
