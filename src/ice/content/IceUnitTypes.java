package ice.content;

import arc.Core;
import arc.graphics.*;
import arc.math.*;
import arc.math.geom.Rect;
import arc.util.Time;
import arc.util.Tmp;
import ice.classes.entities.abilities.*;
import ice.classes.entities.ai.ShielderAI;
import ice.classes.entities.types.*;
import ice.classes.pattern.*;
import ice.graphics.IcePal;
import mindustry.ai.UnitCommand;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.type.weapons.*;
import mindustry.world.meta.BlockFlag;
import mindustry.world.meta.Stat;

import static ice.content.IceBullets.*;
import static mindustry.content.Items.*;
import static mindustry.content.StatusEffects.*;

public class IceUnitTypes {
    public static UnitType
    //core
    malice, charity, censure,
    //main
    vessel, stem, basis, ewer, xylem, fundament, decanter, stalk, groundwork,
        //sub units
    quant, sin, chronon, zen, blaze, sunLight,
        //executioners (Mini bosses)
        yellow, purple, remnant, red, blue, green, warden,
    //utility
    shieldDrone, vesselPoint,
    //methanite
    born, haste, angle, moto, queen, emperor, collector,
    //other
    basic, pounder, destroyer, annihilator, octo,
    swarmling, reaver, giant, unknown;
    public static void load(){
        malice = new RkiUnitType("malice"){{
            aiController = BuilderAI::new;
            defaultCommand = UnitCommand.rebuildCommand;
            isEnemy = false;

            lowAltitude = false;
            mineSpeed = 5f;
            mineTier = 2;
            buildSpeed = 1f;
            drag = 0.06f;
            speed = 2.3f;
            rotateSpeed = 15f;
            accel = 0.11f;
            fogRadius = 5f;
            itemCapacity = 40;
            health = 330f;
            hitSize = 14f;
            constructor = LegsUnit::create;
            mineWalls = true;
            legLength = 12f;
            hovering = true;
            lockLegBase = true;
            legContinuousMove = false;
            legExtension = -3f;
            legBaseOffset = 4f;
            legMaxLength = 1f;
            legMinLength = 0.2f;
            legLengthScl = 0.95f;
            legForwardScl = 0.5f;

            legMoveSpace = 2f;
            groundLayer = Layer.legUnit - 1f;

            weapons.add(new Weapon("icicle-world-malice-gun"){{
                top = false;
                reload = 24f;
                rotate = false;
                shootCone = 20;
                mirror = false;
                x = 0f;
                y = 3f;

                inaccuracy = 5f;
                ejectEffect = IceFx.pointEject;

                bullet = pointCoreBullet;
            }},
                    new RepairBeamWeapon("icicle-world-malice-repair-gun"){{
                        widthSinMag = 0.065f;
                        reload = 20f;
                        x = 0f;
                        y = -2f;
                        rotate = true;
                        shootY = 2f;
                        beamWidth = 0.5f;
                        repairSpeed = 1.25f;
                        fractionRepairSpeed = 0.2f;
                        aimDst = 0f;
                        shootCone = 20f;
                        mirror = false;
                        top = true;

                        targetUnits = false;
                        targetBuildings = true;
                        autoTarget = true;
                        aiControllable = true;
                        controllable = false;
                        laserColor = IcePal.thalliumMid;
                        healColor = IcePal.thalliumMid;

                        bullet = new BulletType(){{
                            maxRange = 35f;
                        }};
                    }});
        }};
        charity = new RkiUnitType("charity"){{
            aiController = BuilderAI::new;
            defaultCommand = UnitCommand.rebuildCommand;
            isEnemy = false;

            lowAltitude = false;
            mineSpeed = 7.5f;
            mineTier = 3;
            buildSpeed = 1.6f;
            drag = 0.08f;
            speed = 2.55f;
            rotateSpeed = 17f;
            accel = 0.13f;
            fogRadius = 7f;
            itemCapacity = 40;
            health = 520f;
            armor = 3;
            hitSize = 14f;
            constructor = LegsUnit::create;
            mineWalls = true;
            hovering = true;
            lockLegBase = true;
            legContinuousMove = false;
            legCount = 6;
            legExtension = -6.5f;
            legBaseOffset = 5f;
            legMaxLength = 0.9f;
            legLength = 19f;
            legMinLength = 0.38f;
            legLengthScl = 1f;
            legForwardScl = 0.75f;

            legMoveSpace = 1.8f;
            groundLayer = Layer.legUnit - 1f;

            weapons.add(new Weapon("icicle-world-malice-gun"){{
                            top = false;
                            reload = 13.5f;
                            rotate = false;
                            shootCone = 20;
                            mirror = true;
                            alternate = false;
                            x = 3f;
                            y = 4.5f;

                            inaccuracy = 5f;
                            ejectEffect = IceFx.pointEject;

                            bullet = pointCoreBullet;
                        }},
                    new RepairBeamWeapon("icicle-world-malice-repair-gun"){{
                        widthSinMag = 0.07f;
                        reload = 20f;
                        x = 5f;
                        y = -7f;
                        rotate = true;
                        shootY = 2f;
                        beamWidth = 0.535f;
                        repairSpeed = 1.25f;
                        fractionRepairSpeed = 0.2f;
                        aimDst = 0f;
                        shootCone = 25f;
                        mirror = true;
                        top = true;

                        targetUnits = false;
                        targetBuildings = true;
                        autoTarget = true;
                        aiControllable = true;
                        controllable = false;
                        laserColor = IcePal.thalliumMid;
                        healColor = IcePal.thalliumMid;

                        bullet = new BulletType(){{
                            maxRange = 46.5f;
                        }};
                    }});
        }
            @Override
            public void load() {
                super.load();
                legRegion = Core.atlas.find("icicle-world-malice-leg");
                legBaseRegion = Core.atlas.find("icicle-world-malice-leg-base");
            }
        };
        censure = new RkiUnitType("censure"){{
            aiController = BuilderAI::new;
            defaultCommand = UnitCommand.rebuildCommand;
            isEnemy = false;

            lowAltitude = false;
            mineSpeed = 15f;
            mineTier = 3;
            buildSpeed = 2f;
            drag = 0.1f;
            speed = 3f;
            rotateSpeed = 20f;
            accel = 0.13f;
            fogRadius = 10f;
            itemCapacity = 70;
            health = 1210f;
            armor = 5;
            hitSize = 19f;
            constructor = LegsUnit::create;
            mineWalls = true;
            hovering = true;
            lockLegBase = true;
            legContinuousMove = false;
            legCount = 8;
            legExtension = -7f;
            legBaseOffset = 8f;
            legMaxLength = 0.9f;
            legLength = 25f;
            legMinLength = 0.7f;
            legLengthScl = 1f;
            legForwardScl = 0.6f;

            legMoveSpace = 4f;
            groundLayer = Layer.legUnit - 1f;
            abilities.add(new NerfedEnergyFieldAbility(30f, 40f, 100f){{
                color = IcePal.thalliumLightish;
                status = none;
                healEffect = Fx.none;
                maxTargets = 10;
                healPercent = 1f;
                sameTypeHealMult = 1f;
                y = -11;
            }});
        }
            @Override
            public void load() {
                super.load();
                legRegion = Core.atlas.find("icicle-world-malice-leg");
                legBaseRegion = Core.atlas.find("icicle-world-malice-leg-base");
            }
        };
        vessel = new RkiUnitType("vessel"){{
            drag = 0.08f;
            speed = 1.45f;
            rotateSpeed = 12f;
            accel = 0.13f;
            fogRadius = 9f;
            itemCapacity = 10;
            health = 290f;
            hitSize = 12f;
            constructor = LegsUnit::create;
            legLength = 10f;
            legMaxLength = 1.2f;
            legMinLength = 0.2f;
            legLengthScl = 0.8f;
            legForwardScl = 0.65f;
            hovering = true;

            legMoveSpace = 1f;
            groundLayer = Layer.legUnit - 1f;
            weapons.add(new Weapon("icicle-world-vessel-weapon"){{
                top = true;
                reload = 42f;
                rotate = false;
                shootCone = 32;
                mirror = false;
                x = 0f;
                y = -2f;

                inaccuracy = 14f;
                ejectEffect = Fx.casing2;

                bullet = vesselBullet;
            }});
        }};
        stem = new RkiUnitType("stem"){{
            speed = 1.05f;
            rotateSpeed = 12f;
            armor = 3;
            itemCapacity = 15;
            health = 360f;
            hitSize = 10f;
            constructor = MechUnit::create;
            weapons.add(new Weapon("icicle-world-stem-weapon"){{
                top = false;
                reload = 4f;
                rotate = false;
                shootCone = 40;
                mirror = true;
                shootSound = Sounds.pew;
                x = 6f;
                y = 0.5f;

                inaccuracy = 20f;
                ejectEffect = Fx.casing2;

                bullet = stemBullet;
            }});
        }};
        basis = new RkiTankUnitType("basis"){{
            hitSize = 12f;
            treadPullOffset = -3;
            speed = 0.75f;
            rotateSpeed = 3.5f;
            health = 480;
            armor = 6f;
            itemCapacity = 0;
            researchCostMultiplier = 0f;
            constructor = TankUnit::create;
            treadRects = new Rect[]{new Rect(11 - 32f, 8 - 32f, 9, 53)};

            weapons.add(new Weapon("icicle-world-basis-weapon"){{
                layerOffset = 0.0001f;
                reload = 60f;
                shootY = 4.5f;
                recoil = 2.4f;
                rotate = true;
                rotateSpeed = 2.4f;
                mirror = true;
                x = 3f;
                y = -1f;
                heatColor = IcePal.thalliumLightish;
                cooldownTime = 32f;

                bullet = basisBullet;
            }});
        }};
        ewer = new RkiUnitType("ewer"){{
            drag = 0.1f;
            speed = 1;
            rotateSpeed = 9f;
            accel = 0.11f;
            fogRadius = 11f;
            itemCapacity = 25;
            health = 1360f;
            hitSize = 17f;
            constructor = LegsUnit::create;
            legLength = 17f;
            legMaxLength = 1.5f;
            legMinLength = 0.6f;
            legLengthScl = 1f;
            legForwardScl = 0.7f;
            hovering = true;

            legMoveSpace = 1f;
            groundLayer = Layer.legUnit - 1f;
            weapons.add(new Weapon("icicle-world-ewer-weapon"){{
                top = true;
                reload = 30f;
                rotate = false;
                shootCone = 40;
                mirror = false;
                x = 0f;
                y = 0f;

                inaccuracy = 8f;
                ejectEffect = Fx.casing2;

                bullet = ewerBullet;
            }});
        }};
        xylem = new RkiUnitType("xylem"){{
            speed = 0.65f;
            rotateSpeed = 7f;
            armor = 7;
            itemCapacity = 15;
            health = 2420f;
            hitSize = 14f;
            constructor = MechUnit::create;
            weapons.add(new Weapon("icicle-world-xylem-weapon"){{
                top = false;
                reload = 8f;
                rotate = false;
                shootCone = 45;
                mirror = true;
                shootSound = Sounds.flame2;
                x = 10f;
                y = 1f;
                shootY = 2f;

                ejectEffect = Fx.none;

                bullet = xylemFlame;
            }});
        }};
        fundament = new RkiTankUnitType("fundament"){{
            hitSize = 27f;
            treadPullOffset = 0;
            speed = 0.49f;
            rotateSpeed = 1.95f;
            health = 3260;
            armor = 12f;
            itemCapacity = 0;
            researchCostMultiplier = 0f;
            constructor = TankUnit::create;
            treadRects = new Rect[]{new Rect(15 - 64f, 15 - 64f, 10, 98)};

            weapons.add(new Weapon("icicle-world-fundament-weapon"){{
                layerOffset = 0.0001f;
                reload = 90f;
                shootY = 0f;
                recoil = 0f;
                rotate = true;
                rotateSpeed = 0.5f;
                rotationLimit = 30;
                shootCone = 340;
                inaccuracy = 360;
                mirror = false;
                x = 0f;
                y = 0f;
                shoot.shots = 4;

                heatColor = IcePal.thalliumLightish;
                cooldownTime = 40f;

                bullet = fundamentLightningBall;
            }});
        }};
        decanter = new RkiUnitType("decanter"){{
            drag = 0.1f;
            speed = 0.54f;
            hitSize = 44f;
            health = 16500;
            armor = 8f;
            lightRadius = 140f;
            rotateSpeed = 1.6f;
            legStraightness = 0.6f;
            baseLegStraightness = 0.5f;
            lockLegBase = true;
            legContinuousMove = true;
            constructor = LegsUnit::create;

            legCount = 6;
            legLength = 30f;
            legForwardScl = 2.1f;
            legMoveSpace = 1.2f;
            rippleScale = 1.2f;
            stepShake = 0.5f;
            legGroupSize = 3;
            legExtension = -7f;
            legBaseOffset = 18f;
            legStraightLength = 0.9f;
            legMaxLength = 1.2f;

            ammoType = new ItemAmmoType(scrap, 30);

            legSplashDamage = 32;
            legSplashRange = 32;
            drownTimeMultiplier = 2f;

            hovering = true;
            shadowElevation = 0.4f;
            groundLayer = Layer.legUnit;

            alwaysShootWhenMoving = true;
            abilities.add(new EditableSuppressionFieldAbility(){{
                orbRadius = 9;
                particleSize = 4;
                layer = Layer.legUnit - 0.001f;
                        y = 0f;
                particles = 14;
                particleColor = IcePal.sootSuppressColor;
                color = IcePal.sootColor;

            }});
            weapons.add(new Weapon("icicle-world-decanter-sub-weapon"){{
                shootSound = Sounds.pulseBlast;
                mirror = true;
                rotationLimit = 30f;
                rotateSpeed = 0.4f;
                rotate = true;
                alternate = false;

                x = 10f;
                y = -13f;
                shootY = 9f;
                recoil = 4f;
                reload = 210f;
                cooldownTime = reload * 1.2f;
                shake = 7f;
                layerOffset = -0.01f;
                shadow = 10f;

                shootStatus = StatusEffects.slow;
                shootStatusDuration = reload + 1f;

                shoot.shots = 24;
                shoot.firstShotDelay = 37.5f;
                shoot.shotDelay = 6;
                heatColor = Color.red;


                for(int i = 0; i < 3; i++){
                    int fi = i;
                    parts.add(new RegionPart("-blade"){{
                        under = true;
                        layerOffset = -0.001f;
                        heatColor = Pal.techBlue;
                        heatProgress = PartProgress.heat.add(0.2f).min(PartProgress.warmup);
                        progress = PartProgress.warmup.blend(PartProgress.reload, 0.1f);
                        x = 13.5f / 4f;
                        y = 10f / 4f - fi * 2f;
                        moveY = 1f - fi * 1f;
                        moveX = fi * 0.3f;
                        moveRot = -45f - fi * 12f;

                        moves.add(new PartMove(PartProgress.reload.inv().mul(1.8f).inv().curve(fi / 5f, 0.25f), 0f, 0f, 36f));
                    }});
                }

                bullet = ewerBullet;
            }}, new Weapon("icicle-world-decanter-main-weapon"){{
                shootSound = Sounds.pulseBlast;
                mirror = false;
                rotationLimit = 30f;
                rotateSpeed = 0.25f;
                rotate = true;
                alternate = false;

                x = 0f;
                y = -22f;
                shootY = 13f;
                recoil = 4f;
                reload = 230f;
                cooldownTime = reload * 1.2f;
                shake = 7f;
                layerOffset = 0.01f;
                shadow = 10f;

                shootStatus = StatusEffects.slow;
                shootStatusDuration = reload + 1f;

                shoot.shots = 30;
                shoot.firstShotDelay = 30;
                shoot.shotDelay = 8;
                heatColor = Color.red;
                bullet = new BasicBulletType(10f, 55){{
                    width = 18f;
                    height = 34f;
                    hitSize = 7f;
                    shootEffect = new MultiEffect(Fx.shootBigColor, Fx.colorSparkBig);
                    smokeEffect = Fx.shootBigSmoke;
                    ammoMultiplier = 1;
                    reloadMultiplier = 1f;
                    lifetime = 14.4f;
                    pierceCap = 6;
                    pierce = true;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = Pal.darkerMetal;
                    frontColor = Color.white;
                    trailWidth = 3f;
                    trailLength = 12;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    collideTerrain = true;
                }};
                    }});
        }};
        stalk = new RkiUnitType("stalk"){{
            speed = 0.42f;
            rotateSpeed = 3.6f;
            armor = 14;
            itemCapacity = 30;
            health = 19650f;
            hitSize = 26f;
            constructor = MechUnit::create;
            immunities.add(burning);
            weapons.add(new Weapon("icicle-world-stalk-weapon"){{
                top = false;
                reload = 70f;
                rotate = false;
                shootCone = 50;
                mirror = true;
                shootSound = Sounds.cannon;
                inaccuracy = 8;
                x = 25f;
                y = 0f;
                shootY = 13.5f;

                ejectEffect = Fx.none;
                shoot = new ShootSpread(){{
                    shots = 8;
                    spread = 2;
                }};

                bullet = new BasicBulletType(9, 40){{
                    lifetime = 25;
                    width = 12f;
                    height = 21f;
                    splashDamage = 15;
                    splashDamageRadius = 2.5f * 8;
                    splashDamagePierce = true;
                    hitColor = backColor = IcePal.sporeMid;
                    frontColor = trailColor = IcePal.sporeLight;
                    velocityRnd = 0.2f;
                    incendChance = 1;
                    incendSpread = 11;
                    incendAmount = 8;
                    collideTerrain = true;
                    trailLength = 11;
                    trailWidth = 2.75f;
                    parentizeEffects = false;
                    shootEffect = new MultiEffect(new ParticleEffect(){{
                        line = true;
                        particles = 5;
                        colorFrom = IcePal.sporeLight;
                        colorTo = Color.black;
                        sizeFrom = 5;
                        sizeTo = 0;
                        lenFrom = 7;
                        lenTo = 0;
                        length = 75;
                        cone = 55;
                        lifetime = 75;
                    }});
                }};
            }});
        }};

        groundwork = new RkiTankUnitType("groundwork"){{
            hitSize = 37.5f;
            treadPullOffset = 0;
            speed = 0.285f;
            rotateSpeed = 1.12f;
            health = 21600;
            armor = 21f;
            crushDamage = 5f;
            itemCapacity = 0;
            drawCell = false;
            researchCostMultiplier = 0f;
            constructor = TankUnit::create;
            treadRects = new Rect[]{new Rect(44f - 150f, 25f - 150f, 36, 253),new Rect(94f - 150f, 241f - 150f, 56, 44)};
            abilities.add(new RotatingShieldsAbility(){{
                radius = 47;
            }});
            abilities.add(new RotatingShieldsAbility(){{
                angleOffset = 180;
                radius = 47;
            }});
            weapons.add(new Weapon("icicle-world-groundwork-main-weapon"){{
                    shootSound = Sounds.largeCannon;
                    layerOffset = 0.1f;
                    reload = 210f;
                    shootY = 29f;
                    shake = 6f;
                    recoil = 6f;
                    rotate = true;
                    rotateSpeed = 0.5f;
                    mirror = false;
                    x = 0f;
                    y = -11f;
                    shadow = 22f;
                    heatColor = IcePal.thalliumLightish;
                    shootWarmupSpeed = 0.06f;
                    cooldownTime = 110f;
                    minWarmup = 0.9f;

                    bullet = new BasicBulletType(10f, 200f) {{
                        sprite = "missile-large";
                        width = 10f;
                        height = 24f;
                        lifetime = 26f;
                        hitSize = 7f;
                        buildingDamageMultiplier = 0.45f;

                        smokeEffect = Fx.shootSmokeTitan;
                        splashDamage = 270;
                        splashDamageRadius = 70;
                        pierceCap = 5;
                        pierce = true;
                        pierceBuilding = collideTerrain = true;
                        hitColor = backColor = trailColor = IcePal.thalliumLight;
                        frontColor = Color.white;
                        trailWidth = 3.75f;
                        trailLength = 12;
                        hitEffect = despawnEffect = Fx.massiveExplosion;


                        shootEffect = new ExplosionEffect() {{
                            lifetime = 75f;
                            waveStroke = 5f;
                            waveColor = sparkColor = trailColor;
                            waveRad = 15f;
                            smokeSize = 5f;
                            smokes = 8;
                            smokeSizeBase = 0f;
                            smokeColor = trailColor;
                            sparks = 8;
                            sparkRad = 40f;
                            sparkLen = 6f;
                            sparkStroke = 3f;
                        }};
                    }};
                }},new Weapon("icicle-world-groundwork-weapon"){{
                shootSound = Sounds.blaster;
                y = 14f;
                shootY = 3;
                x = 21.5f;
                top = true;
                mirror = true;
                alternate = false;
                rotate = true;
                reload = 20f;
                shootCone = 55f;
                recoil = 1;

                bullet = new LaserBoltBulletType(6f, 25){{
                    width = 2f;
                    height = 5f;
                    recoil = 0f;
                    trailWidth = 2f;
                    trailLength = 7;
                    collidesAir = false;
                    collidesGround = true;
                    collidesTeam = true;
                    pierce = pierceBuilding = true;
                    ammoMultiplier = 1f;
                    lifetime = 13f;
                    healAmount = 30;
                    shootEffect = IceFx.lightningThalliumShoot;
                    heatColor = trailColor = IcePal.thalliumLight;
                    smokeEffect = hitEffect = despawnEffect = IceFx.hitThalliumLaser;
                    backColor = frontColor = hitColor = healColor = lightColor = IcePal.thalliumMid;
                    collideTerrain = true;
                }};
            }});
        }};
        quant = new RkiUnitType("quant"){{
            hovering = true;
            shadowElevation = 0.1f;

            drag = 0.07f;
            speed = 1.65f;
            rotateSpeed = 5f;
            constructor = ElevationMoveUnit::create;

            accel = 0.09f;
            health = 340f;
            armor = 2f;
            hitSize = 13f;
            engineOffset = 6f;
            engineSize = 2.4f;
            itemCapacity = 0;
            useEngineElevation = false;
            researchCostMultiplier = 0f;

            abilities.add(new MoveEffectAbility(0f, -6f, Pal.sapBulletBack, Fx.missileTrailShort, 5.5f){{
                teamColor = true;
            }});

                parts.add(new HoverPart(){{
                    x = 0f;
                    y = 0;
                    mirror = true;
                    radius = 9f;
                    phase = 90f;
                    stroke = 2f;
                    layerOffset = -0.001f;
                    color = IcePal.methaneMid;
                }});

            weapons.add(new Weapon("icicle-world-quant-weapon"){{
                shootSound = Sounds.blaster;
                y = 0f;
                shootY = 3;
                x = 0f;
                top = true;
                mirror = true;
                reload = 55f;
                shootCone = 80f;
                recoil = 0;

                shoot = new RandomShootSpread(2, 4, 9.5f, 12f);

                bullet = new BasicBulletType(6f, 16){{
                    width = 8f;
                    height = 14.5f;
                    lifetime = 22f;
                    weaveRandom = true;
                    weaveMag = 2.8f;
                    weaveScale = 4.2f;
                    recoil = 0.45f;
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = IcePal.methaneLight;
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 5;
                    collideTerrain = true;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        chronon = new RkiUnitType("chronon"){{
            hovering = true;
            shadowElevation = 0.1f;

            drag = 0.07f;
            speed = 1.48f;
            rotateSpeed = 5f;
            constructor = ElevationMoveUnit::create;

            accel = 0.09f;
            health = 1140f;
            armor = 4f;
            hitSize = 17f;
            engineOffset = 8f;
            engineSize = 3.6f;
            itemCapacity = 0;
            useEngineElevation = false;
            researchCostMultiplier = 0f;
            setEnginesMirror(new UnitEngine(5f, 0, 2.5f, 315f));

            abilities.add(new MoveEffectAbility(0f, -8f, Pal.sapBulletBack, Fx.missileTrail, 4.8f){{
                teamColor = true;
            }});

            parts.add(new HoverPart(){{
                x = 0f;
                y = 0;
                mirror = true;
                radius = 12f;
                phase = 110f;
                stroke = 2.5f;
                layerOffset = -0.001f;
                sides = 6;
                color = IcePal.methaneMid;
            }});

            weapons.add(new Weapon("icicle-world-chronon-weapon"){{
                shootSound = Sounds.blaster;
                y = 0f;
                shootY = 5;
                shootX = 3.5f;
                x = 0f;
                top = true;
                mirror = true;
                alternate = false;
                reload = 70f;
                shootCone = 70f;
                recoil = 0;
                shoot = new RandomBurstShoot(3, 6, 2f, 4f);
                shoot.shotDelay = 1;
                bullet = new BasicBulletType(8f, 20){{
                    width = 8.5f;
                    height = 16f;
                    lifetime = 18f;
                    recoil = 0.2f;
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = IcePal.methaneLight;
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 5;
                    collideTerrain = true;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        sin = new RkiUnitType("sin"){{
            speed = 0.95f;
            drag = 0.16f;
            hitSize = 13f;
            health = 430;
            accel = 0.45f;
            range = 70;
            rotateSpeed = 2.95f;
            faceTarget = false;
            constructor = UnitWaterMove::create;

            armor = 3f;

            weapons.add(new Weapon("icicle-world-sin-weapon"){{
                reload = 20f;
                x = 3.5f;
                shootY = 2f;
                y = -1f;
                rotate = true;
                ejectEffect = Fx.casing1;
                bullet = new MissileBulletType(2.5f, 20){{
                    keepVelocity = true;
                    width = 5.5f;
                    height = 9f;
                    shrinkY = 0f;
                    drag = -0.004f;
                    homingRange = 55f;
                    splashDamageRadius = 20f;
                    splashDamage = 8f;
                    lifetime = 40f;
                    trailColor = Color.gray;
                    backColor = Pal.bulletYellowBack;
                    frontColor = Pal.bulletYellow;
                    hitEffect = Fx.blastExplosion;
                    despawnEffect = Fx.blastExplosion;
                    weaveScale = 9f;
                    weaveMag = 1.85f;
                    collideTerrain = true;
                }};
            }});

            weapons.add(new Weapon("icicle-world-sin-launcher"){{
                mirror = false;
                reload = 40f;
                x = 0f;
                y = -4.5f;
                rotate = true;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.missile;
                bullet = new BasicBulletType(3f, 30, "circle-bullet"){{
                    width = 8f;
                    height = 8f;
                    shrinkY = 0f;
                    lifetime = 45f;
                    trailColor = Color.gray;
                    backColor = IcePal.methaneLight;
                    frontColor = IcePal.methaneMid;
                    hitEffect = Fx.colorSpark;
                    despawnEffect = Fx.colorSpark;
                    weaveScale = 8f;
                    weaveMag = 2f;
                    fragBullets = 3;
                    collideTerrain = true;
                    fragBullet = new ShrapnelBulletType(){{
                        length = 20;
                        damage = 10f;
                        width = 8f;
                        toColor = IcePal.methaneLight;
                        shootEffect = smokeEffect = Fx.shootBigColor;
                    }};
                }};
            }});
        }};
        zen = new RkiUnitType("zen"){{
            speed = 0.65f;
            drag = 0.2f;
            hitSize = 17f;
            health = 2740;
            accel = 0.55f;
            range = 70;
            rotateSpeed = 1.95f;
            faceTarget = false;
            constructor = UnitWaterMove::create;

            armor = 7f;

            weapons.add(new Weapon("icicle-world-zen-weapon"){{
                reload = 30f;
                x = 4f;
                y = 9.75f;
                rotate = true;
                ejectEffect = Fx.casing1;
                bullet = new MissileBulletType(2.5f, 30){{
                    sprite = "mine-bullet";
                    width = height = 8f;
                    layer = Layer.scorch;
                    shootEffect = smokeEffect = Fx.none;

                    maxRange = 50f;
                    ignoreRotation = true;

                    backColor = IcePal.methaneLight;
                    frontColor = Color.white;
                    mixColorTo = Color.white;

                    hitSound = Sounds.plasmaboom;

                    ejectEffect = Fx.none;
                    hitSize = 22f;

                    collidesAir = false;

                    lifetime = 70f;

                    hitEffect = new MultiEffect(Fx.blastExplosion, IceFx.methaneCloud);
                    keepVelocity = false;

                    shrinkX = shrinkY = 0f;

                    weaveMag = 7f;
                    weaveScale = 3f;
                    speed = 0.7f;
                    drag = -0.017f;
                    homingPower = 0.05f;
                    collideFloor = collideTerrain = true;
                    trailColor = IcePal.methaneLight;
                    trailWidth = 3f;
                    trailLength = 8;

                    splashDamage = 30f;
                    splashDamageRadius = 45f;
                }};
            }});

            weapons.add(new Weapon("icicle-world-zen-launcher"){{
                mirror = false;
                reload = 20f;
                x = 0f;
                y = -3f;
                shootY = 3;
                rotate = true;
                ejectEffect = Fx.casing1;
                shootSound = Sounds.blaster;
                shoot = new ShootAlternate(9.5f);
                bullet = new BasicBulletType(5f, 20, "circle-bullet"){{
                    width = 8f;
                    height = 8f;
                    shrinkY = 0f;
                    lifetime = 25f;
                    trailColor = IcePal.methaneLight;
                    backColor = IcePal.methaneLight;
                    frontColor = IcePal.methaneMid;
                    hitEffect = Fx.colorSpark;
                    despawnEffect = Fx.colorSpark;
                    trailWidth = 3f;
                    trailLength = 5;
                    fragBullets = 1;
                    fragRandomSpread = 0;
                    collideTerrain = true;
                    fragBullet = new ShrapnelBulletType(){{
                        length = 32;
                        damage = 15f;
                        width = 10f;
                        toColor = IcePal.methaneLight;
                        shootEffect = smokeEffect = Fx.shootBigColor;
                    }};
                }};
            }});
        }};
        blaze = new RkiUnitType("blaze"){{

            speed = 0.85f;
            hitSize = 9f;
            health = 290;
            mechSideSway = 0.25f;
            range = 30f;
            constructor = LegsUnit::create;
            legCount = 3;
            legLength = 8.5f;
            lightRadius = 130f;
            groundLayer = Layer.legUnit;
            weapons.add(new Weapon(){{
                shootOnDeath = true;
                reload = 30f;
                noAttack = true;
                shootCone = 180f;
                controllable = false;
                alwaysShooting = false;
                ejectEffect = Fx.none;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType(){{
                    collidesTiles = false;
                    collides = false;
                    hitSound = Sounds.explosion;

                    rangeOverride = 35f;
                    hitEffect = Fx.pulverize;
                    speed = 0f;
                    splashDamageRadius = 70f;
                    instantDisappear = true;
                    splashDamage = 40f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
        }};
        sunLight = new RkiUnitType("sun-light"){{

            speed = 0.85f;
            hitSize = 9f;
            health = 290;
            mechSideSway = 0.25f;
            range = 30f;
            constructor = LegsUnit::create;
            legCount = 3;
            legLength = 8.5f;
            lightRadius = 130f;
            groundLayer = Layer.legUnit;
            weapons.add(new Weapon(){{
                shootOnDeath = true;
                reload = 30f;
                noAttack = true;
                shootCone = 180f;
                controllable = false;
                alwaysShooting = false;
                ejectEffect = Fx.none;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType(){{
                    collidesTiles = false;
                    collides = false;
                    hitSound = Sounds.explosion;

                    rangeOverride = 35f;
                    hitEffect = Fx.pulverize;
                    speed = 0f;
                    splashDamageRadius = 70f;
                    instantDisappear = true;
                    splashDamage = 40f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
        }};

        vesselPoint = new RkiUnitType("vessel-point"){{
            hidden = true;
            drag = 0.08f;
            speed = 1.45f;
            rotateSpeed = 12f;
            accel = 0.13f;
            fogRadius = 9f;
            itemCapacity = 10;
            health = 290f;
            hitSize = 12f;
            constructor = LegsUnit::create;
            legLength = 10f;
            legMaxLength = 1.2f;
            legMinLength = 0.2f;
            legLengthScl = 0.8f;
            legForwardScl = 0.65f;
            hovering = true;

            legMoveSpace = 1f;
            groundLayer = Layer.legUnit - 1f;
            weapons.add(new Weapon("icicle-world-malice-repair-gun"){{
                top = false;
                reload = 35f;
                rotate = false;
                shootCone = 20;
                mirror = false;
                x = 0f;
                y = 2f;

                inaccuracy = 8f;
                ejectEffect = IceFx.pointEject;

                bullet = pointBullet;
            }});
        }};
        yellow = new RkiUnitType("yellow"){{
            drag = 0.08f;
            speed = 0.9f;
            rotateSpeed = 7f;
            accel = 0.18f;
            fogRadius = 15f;
            itemCapacity = 30;
            health = 18500f;
            hitSize = 25f;
            constructor = LegsUnit::create;
            legLength = 16f;
            legMaxLength = 2f;
            legMinLength = 1.15f;
            legLengthScl = 1.1f;
            legForwardScl = 0.85f;
            stepShake = 0.6f;
            legCount = 6;
            hovering = true;

            legMoveSpace = 1f;
            groundLayer = Layer.legUnit - 1f;
            abilities.add(new RepairPillAbility(30f,45f){{
                y = -9;
            }});
            weapons.add(new Weapon("icicle-world-yellow-side-cannon"){{
                top = false;
                reload = 65f;
                rotate = false;
                shootCone = 35;
                mirror = true;
                recoil = 1.5f;
                layerOffset = -0.00001f;
                x = 4.5f;
                y = 6f;

                inaccuracy = 8f;
                ejectEffect = IceFx.pointEject;

                bullet = yellowBlast;
            }},
                    new Weapon("icicle-world-yellow-back-cannon"){{
                        top = true;
                        reload = 110f;
                        rotate = false;
                        shootCone = 50;
                        mirror = true;
                        recoil = 1.5f;
                        layerOffset = 0.00001f;
                        x = 4f;
                        y = -3.5f;
                        shoot.shots = 3;
                        shoot.shotDelay = 6;

                        inaccuracy = 12f;
                        ejectEffect = IceFx.pointEject;

                        bullet = yellowPlasma;
                    }});
        }};
        purple = new RkiTankUnitType("purple"){{
            hitSize = 28f;
            speed = 0.6f;
            rotateSpeed = 1.75f;
            health = 19500;
            armor = 12f;
            itemCapacity = 0;
            constructor = TankUnit::create;
            treadRects = new Rect[]{new Rect(38 - 70f, 14 - 62f, 12, 105)};
            researchCostMultiplier = 0f;

            weapons.add(new Weapon("icicle-world-purple-weapon"){{
                shootSound = Sounds.dullExplosion;
                layerOffset = 0.0001f;
                reload = 110f;
                shootY = 12f;
                recoil = 3.5f;
                rotate = true;
                rotateSpeed = 1.55f;
                mirror = false;
                shootCone = 10f;
                x = 0f;
                y = 0f;
                heatColor = IcePal.sporeLightish;
                cooldownTime = 35f;
                bullet = new BasicBulletType(7f, 180){{
                    sprite = "missile-large";
                    width = 9f;
                    height = 17f;
                    lifetime = 30f;
                    hitSize = 8f;
                    pierceCap = 4;
                    pierce = true;
                    collideTerrain = true;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = lightningColor = IcePal.sporeLight;
                    frontColor = Color.white;
                    trailWidth = 3f;
                    trailLength = 8;
                    hitEffect = despawnEffect = Fx.blastExplosion;
                    shootEffect = Fx.shootTitan;
                    smokeEffect = Fx.shootSmokeTitan;
                    splashDamageRadius = 40f;
                    splashDamage = 40f;
                    lightningLength = 8;
                    lightningDamage = 20;
                    lightning = 4;

                    trailEffect = Fx.hitSquaresColor;
                    trailRotation = true;
                    trailInterval = 3f;
                }};
            }});
        }
        public void killed(Unit unit) {
                    Unit u = remnant.create(unit.team);
                    u.set(unit.x, unit.y);
                    u.rotation = Tmp.v1.angle();
                    u.add();
        }};
        remnant = new RkiUnitType("remnant"){{
            health = 1000;
            speed = 1.2f;
            accel = 0.08f;
            drag = 0.016f;

            flying = true;
            hitSize = 10f;
            targetAir = false;
            engineSize = 0f;
            faceTarget = false;
            hittable = false;
            targetable = false;
            playerControllable = false;
            createWreck = createScorch = false;
            constructor = TimedKillUnit::create;
            lifetime = 30 * 60;
            armor = 5f;
            itemCapacity = 0;
            targetFlags = new BlockFlag[]{BlockFlag.turret, null};
            circleTarget = true;

            weapons.add(new Weapon(){{
                x = 0f;
                shootY = 0f;
                reload = 40;
                mirror = false;
                shootCone = 180f;
                ejectEffect = Fx.none;
                shootSound = Sounds.lasershoot;
                shoot.shots = 3;
                shoot.shotDelay = 5;
                bullet = new LaserBulletType(){{
                    damage = 40f;
                    recoil = 0f;
                    sideAngle = 45f;
                    sideWidth = 1.5f;
                    sideLength = 80f;
                    length = 170f;
                    colors = new Color[]{Color.white.cpy().a(0.4f), Color.white, Color.white};
                }};
            }});
        }};
        red = new RkiUnitType("red"){{
            speed = 0.56f;
            hitSize = 22f;
            rotateSpeed = 1.75f;
            health = 21050;
            armor = 8f;
            mechFrontSway = 1f;
            ammoType = new ItemAmmoType(IceItems.prinute);
            constructor = MechUnit::create;

            mechStepParticles = true;
            stepShake = 0.15f;
            singleTarget = true;
            drownTimeMultiplier = 3.2f;
            abilities.add(new DeathExplosionAbility(1200, 15 * 8){{
                exploEffect = new MultiEffect(
                        new WaveEffect(){{
                            sizeFrom = 0;
                            sizeTo = exploRange;
                            strokeFrom = 0;
                            strokeTo = 9f;
                            lifetime = 150;
                            colorFrom = IcePal.cruxDark;
                            colorTo = Color.valueOf("00000000");
                        }},
                        new WaveEffect(){{
                            startDelay = 9;
                            sizeFrom = 0;
                            sizeTo = exploRange;
                            strokeFrom = 0;
                            strokeTo = 9f;
                            lifetime = 150;
                            colorFrom = IcePal.cruxDark;
                            colorTo = Color.valueOf("00000000");
                        }},
                        new WaveEffect(){{
                            startDelay = 18;
                            sizeFrom = 0;
                            sizeTo = exploRange;
                            strokeFrom = 0;
                            strokeTo = 9f;
                            lifetime = 150;
                            colorFrom = IcePal.cruxDark;
                            colorTo = Color.valueOf("00000000");
                        }},
                        new ParticleEffect(){{
                            particles = 10;
                            cone = 360;
                            baseLength = 5;
                            length = exploRange;
                            lifetime = 230;
                            sizeFrom = 0;
                            sizeTo = 25;
                            colorFrom = IcePal.cruxMid;
                            colorTo = Color.valueOf("00000000");
                        }});
                exploBullets = 5;
                exploBullet = new BasicBulletType(4f, 0, "circle-bullet"){{
                        width = 32f;
                        height = 32f;
                        lifetime = 200f;
                        drag = 0.05f;
                        shootEffect = Fx.shootBig;
                        frontColor = IcePal.cruxMid;
                        backColor = IcePal.cruxDark;
                        collides = collidesGround = collidesAir = false;
                        intervalBullets = 2;
                        intervalRandomSpread = 360;
                        intervalAngle = 0;
                        bulletInterval = 12;
                        intervalBullet = new BasicBulletType(1.5f, 30) {{
                            width = 11f;
                            height = 20f;
                            lifetime = 70f;
                            shootEffect = Fx.shootBig;
                            collideTerrain = true;
                            frontColor = Pal.redDust;
                            backColor = Pal.redderDust;
                            splashDamage = 10;
                            splashDamageRadius = 10;
                            pierceCap = 2;
                            pierceBuilding = true;
                            intervalBullets = 2;
                            intervalRandomSpread = 30;
                            intervalAngle = 0;
                            bulletInterval = 5;
                            intervalBullet = new LightningBulletType() {{
                                damage = 4;
                                lightningLength = 8;
                                collidesAir = false;
                                ammoMultiplier = 1f;
                                lightningColor = Pal.redderDust;

                                lightningType = new BulletType(0.0001f, 0f) {{
                                    lifetime = Fx.lightning.lifetime;
                                    hitEffect = Fx.hitLancer;
                                    despawnEffect = Fx.none;
                                    status = StatusEffects.shocked;
                                    statusDuration = 15f;
                                    hittable = false;
                                    lightColor = Pal.redderDust;
                                    collidesAir = false;
                                }};
                            }};
                        }};
                    }};
            }});

            weapons.add(
                    new Weapon("icicle-world-red-weapon"){{
                        top = false;
                        y = 0;
                        x = 13.5f;
                        shootY = 8.75f;
                        reload = 65f;
                        recoil = 3f;
                        shake = 1f;
                        ejectEffect = Fx.casing3;
                        shootSound = Sounds.bang;
                        inaccuracy = 3f;

                        bullet = new BasicBulletType(8f, 130){{
                            width = 11f;
                            height = 20f;
                            lifetime = 20f;
                            shootEffect = Fx.shootBig;
                            collideTerrain = true;
                            frontColor = Pal.redDust;
                            backColor = Pal.redderDust;
                            splashDamage = 45;
                            splashDamageRadius = 25;
                            pierceCap = 2;
                            pierceBuilding = true;
                            intervalBullets = 1;
                            intervalRandomSpread = 0;
                            intervalAngle = 0;
                            bulletInterval = 4;
                            intervalBullet = new LightningBulletType(){{
                                damage = 10;
                                lightningLength = 8;
                                collidesAir = false;
                                ammoMultiplier = 1f;
                                lightningColor = Pal.redderDust;


                                lightningType = new BulletType(0.0001f, 0f){{
                                    lifetime = Fx.lightning.lifetime;
                                    hitEffect = Fx.hitLancer;
                                    despawnEffect = Fx.none;
                                    status = StatusEffects.shocked;
                                    statusDuration = 15f;
                                    hittable = false;
                                    lightColor = Pal.redderDust;
                                    collidesAir = false;
                                }};
                            }};
                        }};
                    }}
            );
        }};
        blue = new RkiUnitType("blue"){{
            hovering = true;
            shadowElevation = 0.15f;

            drag = 0.07f;
            speed = 1.3f;
            rotateSpeed = 4f;
            constructor = ElevationMoveUnit::create;

            accel = 0.09f;
            health = 19000f;
            armor = 5f;
            hitSize = 19f;
            engineOffset = 10f;
            engineSize = 2.5f;
            itemCapacity = 0;
            useEngineElevation = false;
            researchCostMultiplier = 0f;

            abilities.add(new MoveEffectAbility(0f, -10f, Pal.techBlue, Fx.missileTrailShort, 4f){{
                teamColor = true;
            }});
            abilities.add(new WraithAbility(55, 10, 180){{
                shockBullet = new MissileBulletType(6f, 75){{
                    width = 8f;
                    height = 15f;
                    lifetime = 25f;
                    splashDamage = 35;
                    splashDamageRadius = 30;

                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = Pal.techBlue;
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 7;
                    collideTerrain = true;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
                activeEffect = new WaveEffect(){{
                    sides = 8;
                    sizeFrom = 0;
                    sizeTo = 6;
                    lifetime = 30;
                    colorFrom = Pal.techBlue;
                }};
            }});

            parts.add(new HoverPart(){{
                x = 8.5f;
                y = -6.5f;
                mirror = true;
                radius = 7f;
                phase = 90f;
                stroke = 1.5f;
                layerOffset = -0.001f;
                color = Pal.techBlue;
            }},
            new HoverPart(){{
                x = 0;
                y = 0;
                mirror = false;
                radius = 18f;
                phase = 60f;
                stroke = 2f;
                layerOffset = -0.001f;
                color = Pal.techBlue;
            }});

            weapons.add(new Weapon("icicle-world-blue-weapon"){{
                shootSound = Sounds.blaster;
                y = 10.5f;
                shootY = 0;
                x = 5f;
                top = true;
                mirror = true;
                reload = 58f;
                shootCone = 50f;
                recoil = 0;
                shoot.shots = 3;
                shoot.shotDelay = 6;

                bullet = new MissileBulletType(6f, 80){{
                    width = 8f;
                    height = 15f;
                    lifetime = 25f;
                    splashDamage = 20;
                    splashDamageRadius = 45;
                    shootEffect = new WaveEffect(){{
                        sides = 4;
                        sizeFrom = 0;
                        sizeTo = 5;
                        colorFrom = Pal.techBlue;
                    }};
                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = Pal.techBlue;
                    frontColor = Color.white;
                    trailWidth = 2f;
                    trailLength = 7;
                    collideTerrain = true;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        shieldDrone = new RkiUnitType("shield-drone"){{
            controller = u -> new ShielderAI();

            defaultCommand = UnitCommand.mineCommand;
            constructor = BuildingTetherPayloadUnit::create;

            flying = true;
            drag = 0.06f;
            hitSize = 12;
            accel = 0.12f;
            speed = 1.5f;
            health = 100;
            engineSize = 2.9f;
            engineOffset = 6.55f;
            range = 50f;
            isEnemy = playerControllable = logicControllable = useUnitCap = false;

            ammoType = new PowerAmmoType(500);

            abilities.add(new ForceFieldAbility(55f, 0.5f, 1000f, 55f * 6, 8, 0));
            weapons.add(new Weapon(){{
                x = 0f;
                shootY = 0f;
                reload = 100;
                mirror = false;
                shootCone = 70f;
                ejectEffect = Fx.none;
                shootSound = Sounds.pulseBlast;
                bullet = methaneSpike;
            }});
        }};
        born = new MethaniteUnitType("born"){{
            drag = 0.1f;
            speed = 2.1f;
            rotateSpeed = 20f;
            accel = 0.15f;
            fogRadius = 8f;
            itemCapacity = 0;
            health = 320f;
            armor = 1;
            hitSize = 11f;
            constructor = LegsUnit::create;
            mineWalls = true;
            hovering = true;
            lockLegBase = true;
            legContinuousMove = false;
            legCount = 4;
            legExtension = -7f;
            legBaseOffset = 4f;
            legMaxLength = 0.85f;
            legLength = 14f;
            legMinLength = 0.32f;
            legLengthScl = 1f;
            legForwardScl = 0.7f;
            bloodAmount = 15;

            legMoveSpace = 2f;
            groundLayer = Layer.legUnit;

            weapons.add(new Weapon("icicle-world-born-attack"){{
                top = false;
                reload = 15f;
                rotate = false;
                shootCone = 50;
                mirror = false;
                x = 0f;
                y = 6.75f;

                ejectEffect = Fx.none;
                parts.add(new RegionPart("-mouth"){{
                    y = -6.75f;
                    under = mirror = true;
                    layerOffset = -0.00001f;
                    heatColor = Pal.techBlue;
                    progress = PartProgress.reload;
                    moveRot = 8f;
                }});

                bullet = new BasicBulletType(4f, 5){{
                    width = 0.2f;
                    height = 0.2f;
                    lifetime = 5f;
                    recoil = -1;
                    hitEffect = despawnEffect = shootEffect = smokeEffect = Fx.none;
                }};
            }});
        }};
        haste = new MethaniteUnitType("haste"){{
            drag = 0.1f;
            speed = 2.55f;
            rotateSpeed = 20f;
            accel = 0.15f;
            fogRadius = 8f;
            itemCapacity = 0;
            health = 760f;
            armor = 3;
            hitSize = 17f;
            constructor = LegsUnit::create;
            mineWalls = true;
            hovering = true;
            lockLegBase = true;
            legContinuousMove = false;
            legCount = 6;
            legExtension = -7f;
            legBaseOffset = 5f;
            legMaxLength = 1f;
            legLength = 22f;
            legMinLength = 0.64f;
            legLengthScl = 1f;
            legForwardScl = 0.75f;

            legMoveSpace = 2f;
            groundLayer = Layer.legUnit;

            weapons.add(new Weapon("icicle-world-haste-attack"){{
                            top = false;
                            reload = 20f;
                            rotate = false;
                            shootCone = 50;
                            mirror = false;
                            x = 0f;
                            y = 9.25f;

                            ejectEffect = Fx.none;
                            parts.add(new RegionPart("-mouth"){{
                                y = -6.25f;
                                under = mirror = true;
                                layerOffset = -0.000001f;
                                heatColor = Pal.techBlue;
                                progress = PartProgress.reload;
                                moveRot = 20f;
                            }});
                            parts.add(new RegionPart("-back"){{
                                y = -14.75f;
                                under = mirror = true;
                                heatColor = Pal.techBlue;
                                progress = p -> Interp.pow2.apply(Mathf.sinDeg(Time.time * 4f)) * 0.6f;
                                moveRot = 12f;
                            }});

                            bullet = new BasicBulletType(6f, 15){{
                                width = 0.2f;
                                height = 0.2f;
                                lifetime = 6f;
                                recoil = -3;
                                hitEffect = despawnEffect = shootEffect = smokeEffect = Fx.none;
                            }};
                        }});
        }};
        basic = new PolygonUnitType("basic") {{
            health = 1000;
            speed = 0.9f;
            hitSize = 12f;
            weapons.add(
                    new Weapon("icicle-world-basic-gun") {{
                        shootSound = Sounds.blaster;
                        reload = 20f;
                        x = 0f;
                        y = 7.75f;
                        layerOffset = -0.0001f;
                        shootY = 3;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = basicBullet;
                    }});
        }};
        pounder = new PolygonUnitType("pounder") {{
            health = 1200;
            speed = 0.8f;
            hitSize = 12f;
            weapons.add(
                    new Weapon("icicle-world-pounder-gun") {{
                        shootSound = Sounds.blaster;
                        reload = 35f;
                        x = 0f;
                        y = 7.75f;
                        layerOffset = -0.0001f;
                        shootY = 3;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = new BasicBulletType(4f, 150){{
                            width = 6.5f;
                            height = 6.5f;
                            lifetime = 30f;
                            sprite = "circle-bullet";
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootBigSmoke;
                            hitColor = backColor = Color.valueOf("3e82a3");
                            frontColor = Color.valueOf("64c5d8");
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }});
        }};
        destroyer = new PolygonUnitType("destroyer") {{
            health = 1500;
            speed = 0.75f;
            hitSize = 12f;
            weapons.add(
                    new Weapon("icicle-world-destroyer-gun") {{
                        shootSound = Sounds.blaster;
                        reload = 65f;
                        x = 0f;
                        y = 7.75f;
                        layerOffset = -0.0001f;
                        shootY = 3;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = new BasicBulletType(3.75f, 300){{
                            width = 7.5f;
                            height = 7.5f;
                            lifetime = 30f;
                            recoil = 1.5f;
                            sprite = "circle-bullet";
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootBigSmoke;
                            hitColor = backColor = Color.valueOf("3e82a3");
                            frontColor = Color.valueOf("64c5d8");
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }});
        }};
        annihilator = new PolygonUnitType("annihilator") {{
            health = 1800;
            speed = 0.7f;
            hitSize = 12f;
            weapons.add(
                    new Weapon("icicle-world-annihilator-gun") {{
                        shootSound = Sounds.blaster;
                        reload = 105f;
                        x = 0f;
                        y = 7.5f;
                        layerOffset = -0.0001f;
                        shootY = 3;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = new BasicBulletType(2.5f, 700){{
                            width = 9f;
                            height = 9f;
                            lifetime = 30f;
                            recoil = 4f;
                            sprite = "circle-bullet";
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootBigSmoke;
                            hitColor = backColor = Color.valueOf("3e82a3");
                            frontColor = Color.valueOf("64c5d8");
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }});
        }};
        octo = new UnitType("octo"){{
            health = 1000;
            speed = 7f;
            hitSize = 11f;
            drag = 1f;
            flying = true;
            drawBody = false;
            range = 60f;
            constructor = UnitEntity::create;
            engineSize = 0;
            parts.add(new ShapePart(){{
                          progress = PartProgress.life;
                          color = Color.valueOf("1d54a1");
                          hollow = false;
                          circle = true;
                          radius = 6f;
                      }},
                    new ShapePart(){{
                        progress = PartProgress.life;
                        color = Color.valueOf("3873c5");
                        hollow = false;
                        radius = 4.8f;
                        circle = true;
                    }});
            weapons.add(
                    new Weapon("barrel"){{
                        shootSound = Sounds.blaster;
                        reload = 20f;
                        x = 0f;
                        y = 5f;
                        shootCone = 360;
                        shootY = 3;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = basicBullet;
                        parts.add(new ShapePart(){{
                                      progress = PartProgress.life;
                                      color = Color.valueOf("5b5b5bff");
                                      hollow = false;
                                      sides = 4;
                                      rotation = 45;
                                      radius = 4f;
                                  }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("5b5b5bff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 4f;
                                    y = 2;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                    y = 2;
                                }});
                    }},
                    new Weapon("barrel"){{
                        shootSound = Sounds.blaster;
                        reload = 20f;
                        x = 0f;
                        y = -5f;
                        baseRotation = 180;
                        shootCone = 360;
                        shootY = 3;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = basicBullet;
                        parts.add(new ShapePart(){{
                                      progress = PartProgress.life;
                                      color = Color.valueOf("5b5b5bff");
                                      hollow = false;
                                      sides = 4;
                                      rotation = 45;
                                      radius = 4f;
                                  }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("5b5b5bff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 4f;
                                    y = 2;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                    y = 2;
                                }});
                    }},
                    new Weapon("barrel"){{
                        shootSound = Sounds.blaster;
                        reload = 20f;
                        x = 5f;
                        y = 0f;
                        shootY = 3;
                        shootCone = 360;
                        baseRotation = -90;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = basicBullet;
                        parts.add(new ShapePart(){{
                                      progress = PartProgress.life;
                                      color = Color.valueOf("5b5b5bff");
                                      hollow = false;
                                      sides = 4;
                                      rotation = 45;
                                      radius = 4f;
                                  }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("5b5b5bff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 4f;
                                    y = 2;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                    y = 2;
                                }});
                    }},
                    new Weapon("barrel"){{
                        shootSound = Sounds.blaster;
                        reload = 20f;
                        x = -5f;
                        y = 0f;
                        shootY = 3;
                        shootCone = 360;
                        baseRotation = 90;
                        mirror = false;
                        top = false;
                        rotate = false;
                        bullet = basicBullet;
                        parts.add(new ShapePart(){{
                                      progress = PartProgress.life;
                                      color = Color.valueOf("5b5b5bff");
                                      hollow = false;
                                      sides = 4;
                                      rotation = 45;
                                      radius = 4f;
                                  }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("5b5b5bff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 4f;
                                    y = 2;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                }},
                                new ShapePart(){{
                                    progress = PartProgress.life;
                                    color = Color.valueOf("7e7e7eff");
                                    hollow = false;
                                    sides = 4;
                                    rotation = 45;
                                    radius = 2.8f;
                                    y = 2;
                                }});
                    }});
        }};
        giant = new MonsterUnitType("giant"){{
            speed = 0.38f;
            hitSize = 16f;
            health = 1480;
            armor = 2;
            bloodAmount = 60f;
            constructor = MechUnit::create;
            weapons.add(new Weapon("icicle-world-giant-hand"){{
                            reload = Float.MAX_VALUE;
                            x = 0f;
                            y = 0f;
                            top = false;
                            mirror = false;
                            layerOffset = -0.0001f;
                            ejectEffect = Fx.casing1;
                            rotationLimit = 25;
                            recoil = 0;
                            bullet = new BasicBulletType(0f, 0){{
                                width = 0f;
                                height = 0f;
                                lifetime = 0f;
                                hitEffect = despawnEffect = Fx.none;
                            }};
                        }},
                    (new Weapon("icicle-world-giant-head"){{
                        reload = 40;
                        x = 0f;
                        y = 0f;
                        top = true;
                        mirror = false;
                        recoil = 0;
                        ejectEffect = Fx.none;
                        bullet = new BasicBulletType(6f, 25){{
                            width = 0.2f;
                            height = 0.2f;
                            lifetime = 1.5f;
                            hitEffect = despawnEffect = Fx.none;
                        }};
                    }})
            );
        }};
        reaver = new MonsterUnitType("reaver"){{
            speed = 0.7f;
            hitSize = 9f;
            health = 320;
            constructor = MechUnit::create;
            bloodAmount = 40;
            weapons.add(new Weapon("icicle-world-reaver-arm"){{
                reload = Float.MAX_VALUE;
                x = 0f;
                y = 0f;
                top = false;
                rotate = true;
                mirror = false;
                layerOffset = -0.0001f;
                ejectEffect = Fx.casing1;
                rotationLimit = 25;
                recoil = 0;
                bullet = new BasicBulletType(0f, 0){{
                    width = 0f;
                    height = 0f;
                    lifetime = 0f;
                    hitEffect = despawnEffect = Fx.none;
                }};
            }},
                    (new Weapon("icicle-world-reaver-head"){{
                        reload = 25;
                        x = 0f;
                        y = 0f;
                        top = true;
                        mirror = false;
                        recoil = 0;
                        ejectEffect = Fx.none;
                        bullet = new BasicBulletType(5f, 10){{
                            width = 0.2f;
                            height = 0.2f;
                            lifetime = 2f;
                            hitEffect = despawnEffect = Fx.none;
                        }};
                    }})
            );
        }};
        swarmling = new MonsterUnitType("swarmling"){{
            speed = 1.65f;
            hitSize = 5f;
            health = 70;
            constructor = MechUnit::create;
            bloodAmount = 15;
            weapons.add((new Weapon("kus"){{
                        reload = 18;
                        x = 0f;
                        y = 0f;
                        top = true;
                        mirror = false;
                        recoil = 0;
                        ejectEffect = Fx.none;
                        bullet = new BasicBulletType(5f, 3){{
                            width = 0.2f;
                            height = 0.2f;
                            lifetime = 2f;
                            hitEffect = despawnEffect = Fx.none;
                        }};
                    }})
            );
        }};
        unknown = new UnitType("unknown"){{
            outlines = false;
            constructor = UnitEntity::create;
        }
        @Override
        public void setStats(){
            stats.remove(Stat.health);
            stats.remove(Stat.range);
            stats.remove(Stat.speed);
            stats.remove(Stat.flying);
            stats.remove(Stat.armor);
            stats.remove(Stat.itemCapacity);
            stats.remove(Stat.canBoost);
        }
        };
    }
}
