package ice.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import ice.classes.entities.bullets.BoomerangBullet;
import ice.graphics.IcePal;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
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
    pointBullet, pointCoreBullet,
    vesselBullet, basisBullet, stemBullet, yellowBlast, yellowPlasma, xylemFlame, ewerBullet, fundamentLightningBall,
    scrapMissile, sporeSkimmerBullet, siliconSkimmerBullet, methaneSpike, ceramicChunk, radBlast, poloniumBlast, chargedBlast,
    //funny bullets
    basicBullet;
    public static void load(){
        pointBullet = new BasicBulletType(20f, 8){{
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
        stemBullet = new BasicBulletType(4f, 2f){{
            width = 4f;
            height = 9f;
            lifetime = 20f;
            hitColor = backColor = IcePal.sporeMid;
            frontColor = IcePal.sporeLight;
            collideTerrain = true;
        }};
        ewerBullet = new BasicBulletType(8f, 40){{
            width = 11f;
            height = 21f;
            hitSize = 7f;
            shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
            smokeEffect = Fx.shootBigSmoke;
            ammoMultiplier = 1;
            reloadMultiplier = 1f;
            lifetime = 18f;
            pierceCap = 4;
            pierce = true;
            pierceBuilding = true;
            hitColor = backColor = trailColor = Pal.metalGrayDark;
            frontColor = Color.white;
            trailWidth = 2.5f;
            trailLength = 9;
            hitEffect = despawnEffect = Fx.hitBulletColor;
            collideTerrain = true;
        }};
        xylemFlame = new BulletType(5f, 45f){{
            hitSize = 7f;
            lifetime = 15f;
            pierce = true;
            pierceBuilding = true;
            pierceCap = 2;
            statusDuration = 60f * 4;
            shootEffect = IceFx.shootWoodFlame;
            hitEffect = Fx.hitFlameSmall;
            despawnEffect = Fx.none;
            status = StatusEffects.burning;
            collideTerrain = true;
            keepVelocity = false;
            hittable = false;
        }};
        fundamentLightningBall = new EmpBulletType(){{
            float rad = 30f;

            homingRange = 30;
            homingPower = 0.6f;
            homingDelay = 4;
            scaleLife = false;
            lightOpacity = 0.6f;
            unitDamageScl = 0.8f;
            timeIncrease = 1.88f;
            timeDuration = 60f * 8f;
            powerDamageScl = 0.75f;
            damage = 50;
            hitColor = lightColor = IcePal.thalliumLightish;
            lightRadius = 50f;
            shootEffect = IceFx.lightningThalliumShoot;
            smokeEffect = Fx.shootBigSmoke2;
            lifetime = 15f;
            sprite = "circle-bullet";
            backColor = IcePal.thalliumLightish;
            frontColor = Color.white;
            width = height = 9f;
            shrinkY = 0f;
            speed = 6f;
            trailLength = 20;
            trailWidth = 6f;
            trailColor = IcePal.thalliumLightish;
            trailInterval = 3f;
            splashDamage = 30f;
            splashDamageRadius = rad;
            hitShake = 4f;
            trailRotation = collideTerrain = true;
            hitSound = Sounds.plasmaboom;

            trailEffect = new Effect(12f, e -> {
                color(IcePal.thalliumLightish);
                for(int s : Mathf.signs){
                    Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                }
            });

            hitEffect = new Effect(50f, 100f, e -> {
                e.scaled(7f, b -> {
                    color(IcePal.thalliumLightish, b.fout());
                    Fill.circle(e.x, e.y, rad);
                });

                color(IcePal.thalliumLightish);
                stroke(e.fout() * 3f);
                Lines.circle(e.x, e.y, rad);

                int points = 6;
                float offset = Mathf.randomSeed(e.id, 360f);
                for(int i = 0; i < points; i++){
                    float angle = i* 360f / points + offset;
                    //for(int s : Mathf.zeroOne){
                    Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle/* + s*180f*/);
                    //}
                }

                Fill.circle(e.x, e.y, 7f * e.fout());
                color();
                Fill.circle(e.x, e.y, 4f * e.fout());
                Drawf.light(e.x, e.y, rad * 1.6f, IcePal.thalliumLightish, e.fout());
            });
        }};
        scrapMissile = new MissileBulletType(5f, 20){{
            smokeEffect = Fx.shootBigSmoke;
            shootEffect = Fx.shootBigColor;
            width = 6.5f;
            height = 8f;
            lifetime = 22f;
            hitSize = 4f;
            hitColor = backColor = trailColor = Pal.metalGrayDark;
            frontColor = Color.white;
            trailWidth = 1.9f;
            homingDelay = 5;
            trailLength = 7;
            despawnEffect = hitEffect = Fx.hitBulletColor;
            fragBullets = 5;
            collideTerrain = true;
            fragBullet = new ArtilleryBulletType(){{
                damage = 0;
                splashDamage = 8;
                splashDamageRadius = 17;
                speed = 3;
                lifetime = 10;
                width = 9f;
                height = 9f;
            }};
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
                lifetime = 30f;
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
                lifetime = 30f;
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
            damage = 30;
            splashDamage = 20;
            splashDamageRadius = 24;
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
        ceramicChunk = new BasicBulletType(8f, 70){{
            ammoMultiplier = 2f;
            shootEffect = Fx.shootSmall;
            lifetime = 18.75f;
            width = 8f;
            height = 11f;
            hitEffect = Fx.flakExplosion;
            fragBullet = new BasicBulletType(4f, 20, "shell"){{
                width = 6f;
                height = 8f;
                lifetime = 25f;
                homingPower = 0.3f;
                backColor = Pal.gray;
                frontColor = Color.white;
                despawnEffect = Fx.none;
            }};
            fragBullets = 6;
            collideTerrain = true;
        }};
        radBlast = new BasicBulletType(6f, 30f){{
            sprite = "missile-large";

            lifetime = 200f / 6f;
            width = 9f;
            height = 18f;

            hitSize = 7f;
            shootEffect = IceFx.radLaserShoot;
            smokeEffect = Fx.none;
            ammoMultiplier = 1;
            hitColor = backColor = trailColor = lightningColor;
            frontColor = Color.white;
            trailWidth = 2.5f;
            trailLength = 9;
            hitEffect = despawnEffect = Fx.hitBulletColor;
            buildingDamageMultiplier = 0.3f;

            trailEffect = Fx.colorSpark;
            trailRotation = true;
            trailInterval = 3f;
            lightning = 1;
            lightningColor = IcePal.poloniumLight;
            lightningCone = 15f;
            lightningLength = 5;
            lightningLengthRand = 10;
            lightningDamage = 5f;

            homingPower = 0.3f;
            homingDelay = 5f;
            homingRange = 160f;
            despawnShake = 3f;

            fragBullet = new LaserBulletType(10f){{
                colors = new Color[]{IcePal.poloniumLight.cpy().a(0.4f), IcePal.poloniumMid, Color.white};
                buildingDamageMultiplier = 0.25f;
                width = 10f;
                hitEffect = Fx.hitLancer;
                sideAngle = 175f;
                sideWidth = 1f;
                sideLength = 40f;
                lifetime = 22f;
                drawSize = 200f;
                length = 80f;
                pierceCap = 2;
            }};

            fragSpread = fragRandomSpread = 0f;

            splashDamage = 0f;
            hitEffect = Fx.hitSquaresColor;
            collideTerrain = true;
        }};
        poloniumBlast = new ArtilleryBulletType(4f, 80){{
            hitEffect = new MultiEffect(Fx.titanSmoke);
            knockback = 0.8f;
            hitColor = IcePal.poloniumLight;
            lifetime = 80f;
            width = height = 16f;
            splashDamageRadius = 7f * 8f;
            splashDamage = 70f;
            status = IceStatuses.radiation;
            statusDuration = 60f * 15f;
            frontColor = IcePal.poloniumLight;
            backColor = IcePal.poloniumMid;
            makeFire = true;
            trailEffect = IceFx.poloniumSteam;
            trailChance = 0.35f;
            ammoMultiplier = 4f;
            collidesTiles = true;
            collideTerrain = true;
        }};
        chargedBlast = new ArtilleryBulletType(4f, 120){{
            hitEffect = new MultiEffect(Fx.titanSmoke);
            knockback = 0.8f;
            hitColor = IcePal.poloniumMid;
            lifetime = 68f;
            width = height = 16f;
            splashDamageRadius = 10f * 8f;
            splashDamage = 110f;
            status = IceStatuses.radiation;
            statusDuration = 60f * 28f;
            frontColor = IcePal.poloniumMid;
            backColor = IcePal.poloniumDark;
            makeFire = true;
            trailEffect = IceFx.poloniumSteam;
            trailChance = 0.35f;
            ammoMultiplier = 4f;
            collidesTiles = true;
            collideTerrain = true;
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
            collideTerrain = true;
        }};
        basicBullet = new BasicBulletType(5, 50){{
            width = 5f;
            height = 5f;
            lifetime = 30f;
            sprite = "circle-bullet";
            shootEffect = Fx.sparkShoot;
            smokeEffect = Fx.shootBigSmoke;
            hitColor = backColor = Color.valueOf("3e82a3");
            frontColor = Color.valueOf("64c5d8");
            hitEffect = despawnEffect = Fx.hitBulletColor;
        }};
    }
}
