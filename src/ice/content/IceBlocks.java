package ice.content;


import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.struct.*;
import ice.classes.blocks.defence.Pylon;
import ice.classes.blocks.defence.RegenWall;
import ice.classes.blocks.distribution.*;
import ice.classes.blocks.environment.*;
import ice.classes.blocks.power.*;
import ice.classes.blocks.production.*;
import ice.classes.blocks.units.LegacyFactoryPad;
import ice.graphics.IcePal;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.*;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.type.ItemStack.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static ice.content.IceItems.*;
import static ice.content.IceLiquids.*;
import static ice.content.IceBullets.*;
import static ice.content.IceAttributes.*;
import static ice.content.IceUnitTypes.*;
public class IceBlocks {
    public static Block
    //environment
     //main
    dustCrusher, dustSpreader, undergroundTree, destroyedMonsterNest, ruinBlock,
            //calamite pikes
            calamiteFloor, calamiteWall, calamiteBoulder,
            //dust valley
            dustFloor, dustWall, dustSpit,
            //methane ocean
            methaneFloor, methaneFloorShallow, thermalFloor, solidMethane, thermalBoulder,
            //magnet cavern
            magnetRock, magnetChunk, magnetPositiveRock, magnetNegativeRock, positiveCrystal, negativeCrystal, magnetBoulder,
            //other
            stoneLargeCrater, dustLargeCrater, rkiMetal, rkiMetalWall,
     //ores
    oreDust, oreThallium, oreSoptin, orePolonium, orePoloniumUnderground, oreThalliumUnderground,
    //crafting
    prinuteMerger, prinuteFabricator, siliconDissembler, distiller, metalIncubator, poloniumCrucible, denseStructurer,
    //production
    protoDrill, advancedDrill, engineDrill, nuclearDrill, mechanicalCutter, laserCutter, oreFinder, methaneDigger,
    //distribution
    thalliumConveyor, thalliumJunction, splitter, thalliumTunnel,
    //liquid
    burstPump, pulsePump, soptinTube, soptinRouter, soptinTunnel,
    //defence
    woodWall, ceramicWall, aliveWall, bleak, shine, repairPylon, flameDome,
    //effect
    lamp,
    //power
    oldNode, armoredNode, recallNode, scrapSolar, siliconSolar, decomposer, methaneBurner, nuclearReactor,
    //storage
    coreAngry, coreHate, coreFury,
    //turrets
    bail, clockwise, skimmer, perfection, shatter, crypt, demonCore, burnout, discharge,
    //units
    simpleConstructor, forcedConstructor, aquaConstructor, monsterNest
    ;
    public static void load(){
        undergroundTree = new TreeBlock("underground-tree"){{
            itemDrop = sporeWood;
        }};
        destroyedMonsterNest = new EditorBlock("destroyed-monster-nest"){{
            size = 5;
            customShadow = true;
        }};
        ruinBlock = new EditorBlock("ruin-block"){{
            buildVisibility = BuildVisibility.sandboxOnly;
            health = 5500;
            targetable = true;
            destructible = true;
            drawCracks = false;
            solid = true;
            forceDark = true;
        }};
        calamiteFloor = new Floor("calamite-floor", 2);
        calamiteWall = new StaticWall("calamite-wall"){{
            variants = 2;
            calamiteFloor.asFloor().wall = this;
        }};
        calamiteBoulder = new Prop("calamite-boulder"){{
            variants = 2;
            calamiteFloor.asFloor().decoration = this;
        }};
        dustFloor = new Floor("dust-stone");
        dustWall = new StaticWall("dust-wall"){{
            dustFloor.asFloor().wall = this;
        }};
        dustSpit = new Prop("dust-spit"){{
            variants = 2;
            hasShadow = false;
            dustFloor.asFloor().decoration = this;
        }};
        methaneFloor = new Floor("methanum-floor"){{
            drownTime = 140f;
            speedMultiplier = 0.35f;
            variants = 0;
            status = StatusEffects.wet;
            statusDuration = 120f;
            liquidDrop = methanum;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
        }};
        methaneFloorShallow = new Floor("methanum-floor-shallow"){{
            speedMultiplier = 0.55f;
            variants = 3;
            status = StatusEffects.wet;
            statusDuration = 120f;
            liquidDrop = methanum;
            liquidMultiplier = 0.75f;
            isLiquid = true;
            shallow = true;
            cacheLayer = CacheLayer.water;
        }};
        thermalFloor = new Floor("thermal-floor");
        solidMethane = new StaticWall("solid-methane"){{
            variants = 2;
            thermalFloor.asFloor().wall = this;
        }};
        thermalBoulder = new Prop("thermal-boulder"){{
            variants = 1;
            thermalFloor.asFloor().decoration = this;
        }};
        magnetRock = new Floor("magnet-rock");
        magnetPositiveRock = new Floor("magnet-positive-rock", 2);
        magnetNegativeRock = new Floor("magnet-negative-rock", 2);
        magnetChunk = new StaticWall("magnet-chunk"){{
            variants = 2;
            magnetRock.asFloor().wall = this;
            magnetNegativeRock.asFloor().wall = this;
            magnetPositiveRock.asFloor().wall = this;
        }};
        positiveCrystal = new TallBlock("positive-crystal"){{
            variants = 2;
            clipSize = 64f;
            magnetPositiveRock.asFloor().decoration = this;
        }};
        negativeCrystal = new TallBlock("negative-crystal"){{
            variants = 2;
            clipSize = 64f;
            magnetNegativeRock.asFloor().decoration = this;
        }};
        magnetBoulder = new Prop("magnet-boulder"){{
            variants = 1;
            magnetRock.asFloor().decoration = this;
        }};
        oreDust = new OreBlock(ceramicalDust);
        oreThallium = new OreBlock(thallium);
        oreSoptin = new OreBlock(soptin);
        orePolonium = new OreBlock(polonium);
        orePoloniumUnderground = new UndergroundOre("polonium-under");
        oreThalliumUnderground = new UndergroundOre("thallium-under"){{
            outputOre = oreThallium;
        }};
        stoneLargeCrater = new LargeCrater("stone-large-crater", 1){{
            attributes.set(sun, 1);
            emitLight = true;
            lightRadius = 26f;
            blendGroup = parent = Blocks.stone;
        }};
        dustLargeCrater = new LargeCrater("dust-large-crater", 1){{
            attributes.set(sun, 1);
            emitLight = true;
            lightRadius = 26f;
            blendGroup = parent = dustFloor;
        }};
        rkiMetal = new Floor("rki-metal", 0);
        rkiMetalWall = new StaticWall("rki-metal-wall"){{
            variants = 2;
            rkiMetal.asFloor().wall = this;
        }};
        dustCrusher = new WorldDuster("dust-crusher"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 5));
        }};
        dustSpreader = new WorldDuster("dust-spreader"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 30));
            maxPos = 3;
            minPos = -maxPos;
            displayRange = 24;
            amount = 3;
            reload = 600;
        }};
        prinuteMerger = new GenericCrafter("prinute-merger"){{
            requirements(Category.crafting, with(thallium, 40, scrap, 25, sporeWood, 20));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(prinute, 1);
            craftTime = 60f;
            size = 2;
            hasPower = true;
            hasLiquids = false;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(IcePal.thalliumLightish));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            consumeItems(with(thallium, 2, scrap, 2));
            consumePower(1.5f);
        }};
        prinuteFabricator = new GenericCrafter("prinute-fabricator"){{
            requirements(Category.crafting, with(thallium, 240, sporeWood, 175, silicon, 140, prinute, 80, soptin, 110));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(prinute, 3);
            craftTime = 95f;
            size = 3;
            hasPower = true;
            hasLiquids = false;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(IcePal.thalliumLightish));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;

            consumeItems(with(thallium, 4, scrap, 4, ceramic, 1));
            consumePower(4.75f);
        }};
        siliconDissembler = new GenericCrafter("silicon-dissembler"){{
            requirements(Category.crafting, with(thallium, 210, sporeWood, 140, scrap, 110, prinute, 65));
            craftEffect = Fx.none;
            outputItems = with(silicon, 3, ceramic, 1);
            craftTime = 70f;
            size = 3;
            hasPower = true;
            hasLiquids = squareSprite = false;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawArcSmelt(){{
                flameColor = IcePal.thalliumLight;
                midColor = IcePal.thalliumLightish;
                particleLen = 8.5f;
                particleLife = 50f;
            }}, new DrawDefault());
            fogRadius = 3;
            researchCost = with(thallium, 1000, sporeWood, 750, scrap, 600, prinute, 300);
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.16f;

            consumeItems(with(ceramicalDust, 6));
            consumeLiquid(methanum, 4f / 60f);
            consumePower(5f);
        }};
        distiller = new GenericCrafter("distiller"){{
            requirements(Category.crafting, with(thallium, 140, soptin, 85, silicon, 105));
            outputLiquid = new LiquidStack(water, 8f / 60f);
            craftTime = 65;
            size = 2;
            hasLiquids = true;
            hasPower = true;
            hasItems = true;

            craftEffect = Fx.none;
            legacyReadWarmup = true;
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawLiquidTile(methanum),
                    new DrawBubbles(Color.valueOf("7693e3")){{
                        sides = 8;
                        recurrence = 3f;
                        spread = 4;
                        radius = 1f;
                        amount = 16;
                    }},
                    new DrawDefault()
            );

            consumePower(190f / 60f);
            consumeLiquid(methanum, 8f / 60f);
        }};
        metalIncubator = new GenericCrafter("metal-incubator"){{
            requirements(Category.crafting, with(thallium, 260, sporeWood, 165, silicon, 140, prinute, 115));
            craftEffect = Fx.none;
            outputItem = new ItemStack(livesteel, 1);
            craftTime = 60f * 3.4f;
            size = 3;
            itemCapacity = 20;
            hasPower = hasItems = true;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(){{
                flameColor = IcePal.sporeMid;
                midColor = IcePal.sporeLightish;
                particleInterp = new Interp.PowOut(1.6f);
                flameRadiusScl = 12f;
            }}, new DrawDefault(), new DrawRegion("-top"));
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.11f;

            consumeItems(with(scrap, 2, sporeWood, 4, prinute, 1));
            consumePower(2.8f);
        }};
        poloniumCrucible = new GenericCrafter("polonium-crucible"){{
            requirements(Category.crafting, with(thallium, 275, sporeWood, 190, silicon, 130, prinute, 110, polonium, 85));
            craftEffect = Fx.none;
            outputItems = with(poloniumCharge, 1);
            craftTime = 150f;
            size = 3;
            hasPower = true;
            hasLiquids = squareSprite = false;
            itemCapacity = 12;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(methanum, 2) ,new DrawArcSmelt(){{
                flameColor = IcePal.poloniumLight;
                midColor = IcePal.poloniumLightish;
                circleSpace = 1.25f;
                particleLen = 7f;
                particleLife = 80f;
            }}, new DrawDefault());
            fogRadius = 3;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.16f;

            consumeItems(with(polonium, 3, prinute, 2));
            consumeLiquid(methanum, 18f / 60f);
            consumePower(8f);
        }};
        denseStructurer = new GenericCrafter("dense-structurer"){{
            requirements(Category.crafting, with(thallium, 260, sporeWood, 165, silicon, 140, prinute, 115));
            scaledHealth = 30;
            craftEffect = Fx.none;
            outputItem = new ItemStack(denseAlloy, 1);
            craftTime = 60f * 2.95f;
            size = 4;
            itemCapacity = 20;
            hasPower = hasItems = true;
            drawer = new DrawMulti(new DrawDefault(), new DrawCrucibleFlame(){{
                flameColor = IcePal.wasteMid;
                midColor = IcePal.wasteLight;
                flameRad = 4;
                circleSpace = 5.5f;
                particleInterp = new Interp.PowIn(2.3f);
                flameRadiusScl = 15f;
                particles = 45;
                particleRad = 10;
            }},new DrawFlame(IcePal.wasteLightish){{
                lightRadius = 90f;
            }});
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.18f;

            consumeItems(with(scrap, 4, sporeWood, 3, prinute, 2, thallium, 4, polonium, 2));
            consumePower(6.8f);
        }};
        protoDrill = new BreakDrill("proto-drill"){{
            requirements(Category.production, with(thallium, 20));
            health = 200;
            tier = 2;
            drillTime = 600;
            size = 2;
            squareSprite = false;
            damageLight = 6;
            reload = 135;
            minHealth = 0.26f;

            consumeLiquid(water, 0.05f).boost();
            researchCost = with(thallium, 25);
        }};
        advancedDrill = new BreakDrill("advanced-drill"){{
            requirements(Category.production, with(thallium, 45, prinute, 15));
            health = 450;
            tier = 3;
            drillTime = 500;
            size = 2;
            squareSprite = false;
            damageLight = 5;
            lengthLight = 4;
            reload = 160;
            minHealth = 0.32f;

            consumeLiquid(water, 0.05f).boost();
        }};
        engineDrill = new BreakDrill("engine-drill"){{
            requirements(Category.production, with(thallium, 110, prinute, 40, silicon, 70, scrap, 55));
            health = 800;
            tier = 4;
            drillTime = 440;
            size = 3;
            damageLight = 15;
            lengthLight = 11;
            blockTeam = true;
            drawEngine = true;
            teamColorLight = true;
            reload = 120;
            engineSize = 2.7f;
            engineScale = 1.5f;
            consumePower(3.5f);
            minHealth = 0;

            consumeLiquid(water, 0.1f).boost();
        }};
        nuclearDrill = new BreakDrill("nuclear-drill"){{
            requirements(Category.production, with(thallium, 190, prinute, 75, silicon, 90, ceramic, 60, polonium, 50));
            health = 1200;
            tier = 5;
            drillTime = 190;
            rotateSpeed = 5f;
            size = 3;
            colorLight = IcePal.poloniumLight;
            damageLight = 5;
            lengthLight = 4;
            reload = 22;
            consumePower(4f);
            minHealth = 0;

            consumeLiquid(water, 0.125f).boost();
        }};
        mechanicalCutter = new NearMiner("mechanical-cutter"){{
            requirements(Category.production, with(thallium, 40));

            drillTime = 280f;
            scaledHealth = 10f;
            tier = 3;
            size = 1;
            range = 1;
            fogRadius = 3;
            researchCost = with(thallium, 80);
            squareSprite = false;
            optionalBoostIntensity = 1;
        }};
        laserCutter = new BeamDrill("laser-cutter"){{
            requirements(Category.production, with(thallium, 30, prinute, 10));
            consumePower(0.2f);

            drillTime = 180f;
            scaledHealth = 20f;
            tier = 3;
            size = 1;
            range = 1;
            fogRadius = 3;
            researchCost = with(thallium, 150, prinute, 40);
            optionalBoostIntensity = 1;
        }};
        oreFinder = new OreFinder("ore-finder"){{
            requirements(Category.production, with(thallium, 140, prinute, 90, silicon, 75, ceramic, 40));
            scaledHealth = 10;
            size = 3;
            range = 10 * 8;
            consumePower(1.6f);
        }};
        methaneDigger = new OreUpper("methane-digger"){{
            requirements(Category.production, with(thallium, 220, soptin, 160, prinute, 110, silicon, 120, ceramic, 90));
            scaledHealth = 20;
            size = 3;
            consumePower(8f);
            consumeLiquid(methanum, 0.2f);
        }};
        thalliumConveyor = new RegenConveyor("thallium-conveyor"){{
            requirements(Category.distribution, with(thallium, 2));
            health = 90;
            speed = 0.04f;
            displayedSpeed = 5.6f;
            researchCost = with(thallium, 10);
            junctionReplacement = thalliumJunction;
            bridgeReplacement = thalliumTunnel;
        }};
        thalliumJunction = new Junction("thallium-junction"){{
            requirements(Category.distribution, with(thallium, 14, sporeWood, 4));
            researchCost = with(thallium, 30, sporeWood, 15);
            squareSprite = false;
        }};
        splitter = new Splitter("splitter"){{
            requirements(Category.distribution, with(thallium, 10));
            researchCost = with(thallium, 30);
        }};
        thalliumTunnel = new DuctBridge("thallium-tunnel"){{
            requirements(Category.distribution, with(thallium, 15, prinute, 3));
            health = 130;
            range = 6;
            researchCostMultiplier = 0.3f;
        }};
        //TODO effects
        burstPump = new BurstPump("burst-pump"){{
            requirements(Category.liquid, with(thallium, 60, prinute, 25, soptin, 40));
            pumpAmount = 1f;
            consumePower(1f);
            consumeItem(ceramicalDust, 2);
            consumeTime = 60 * 2;
            liquidCapacity = 200f;
            hasPower = true;
            hasItems = true;
            size = 2;
            squareSprite = false;
        }};
        //TODO effects
        pulsePump = new BurstPump("pulse-pump"){{
            requirements(Category.liquid, with(silicon, 85, ceramic, 68, soptin, 40));
            pumpAmount = 1f;
            reload = 265f;
            consumePower(3f);
            liquidCapacity = 200f;
            hasPower = true;
            size = 3;
            squareSprite = false;
            pumpEffect = new MultiEffect(
                    new ParticleEffect(){{
                        particles = 12;
                        cone = 25;
                        length = 30;
                    }},
                    new ParticleEffect(){{
                        particles = 12;
                        cone = 25;
                        length = 30;
                        baseRotation = 90;
                    }},
                    new ParticleEffect(){{
                        particles = 12;
                        cone = 25;
                        length = 30;
                        baseRotation = -90;
                    }},
                    new ParticleEffect(){{
                        particles = 12;
                        cone = 25;
                        length = 30;
                        baseRotation = 180;
                    }});
        }};
        soptinTube = new Conduit("soptin-tube"){{
            requirements(Category.liquid, with(soptin, 2));
            health = 50;
            bridgeReplacement = soptinTunnel;
        }};
        soptinRouter = new LiquidRouter("soptin-router"){{
            requirements(Category.liquid, with(soptin, 5, sporeWood, 2));
            liquidCapacity = 12f;
            solid = true;
        }};
        soptinTunnel = new DirectionLiquidBridge("soptin-tunnel"){{
            requirements(Category.liquid, with(soptin, 12, prinute, 5));
            health = 130;
            range = 6;
            researchCostMultiplier = 0.3f;
        }};
        woodWall = new Wall("wood-wall"){{
            requirements(Category.defense, with(sporeWood, 6));
            health = 100 * 4;
            envDisabled |= Env.scorching;
        }};
        ceramicWall = new Wall("ceramic-wall"){{
            requirements(Category.defense, with(ceramic, 6));
            health = 140 * 4;
            envDisabled |= Env.scorching;
        }};
        aliveWall = new RegenWall("alive-wall"){{
            requirements(Category.defense, with(prinute, 2, livesteel, 6));
            health = 155 * 4;
            envDisabled |= Env.scorching;
            regenEffect = IceFx.sporeRegen;
        }};
        bleak = new PowerTurret("bleak"){{
            requirements(Category.effect, with(thallium, 100, sporeWood, 70));
            shootType = new LaserBoltBulletType(){{
                lightningLength = 10;
                collidesAir = false;
                collidesGround = true;
                collidesTeam = true;
                pierce = pierceBuilding = true;
                ammoMultiplier = 1f;
                speed = 5;
                lifetime = 6;
                damage = 0;
                healAmount = 15;
                smokeEffect = hitEffect = despawnEffect = IceFx.hitThalliumLaser;
                backColor = frontColor = hitColor = healColor = lightColor = IcePal.thalliumMid;
                collideTerrain = true;
            }};
            squareSprite = false;
            outlineColor = IcePal.rkiOutline;
            drawer = new DrawTurret("rik-");
            reload = 30f;
            shootCone = 40f;
            rotateSpeed = 8f;
            targetAir = false;
            targetGround = false;
            targetHealing = true;
            range = 30f;
            shootEffect = IceFx.lightningThalliumShoot;
            heatColor = IcePal.thalliumMid;
            recoil = 1f;
            size = 1;
            health = 260;
            shootSound = Sounds.spark;
            consumePower(2f);
            researchCost = with(thallium, 30, sporeWood, 20);
            coolant = consumeCoolant(0.1f);
        }};
        shine = new PowerTurret("shine"){{
            requirements(Category.effect, with(thallium, 140, prinute, 85, silicon, 60));
            shootType = new LaserBoltBulletType(){{
                collidesAir = false;
                collidesGround = true;
                collidesTeam = true;
                pierce = pierceBuilding = true;
                ammoMultiplier = 1f;
                speed = 5;
                lifetime = 11;
                damage = 0;
                healAmount = 12;
                smokeEffect = hitEffect = despawnEffect = IceFx.hitThalliumLaser;
                backColor = frontColor = hitColor = healColor = lightColor = IcePal.thalliumMid;
                collideTerrain = true;
            }};
            shoot.shots = 3;
            shoot.shotDelay = 6f;
            squareSprite = false;
            outlineColor = IcePal.rkiOutline;
            drawer = new DrawTurret("rik-");
            reload = 38f;
            shootCone = 48f;
            rotateSpeed = 9f;
            targetAir = false;
            targetGround = false;
            targetHealing = true;
            range = 55f;
            shootEffect = IceFx.lightningThalliumShoot;
            heatColor = IcePal.thalliumMid;
            recoil = 1.5f;
            size = 2;
            scaledHealth = 140;
            shootSound = Sounds.spark;
            consumePower(4.5f);
            coolant = consumeCoolant(0.2f);
        }};
        repairPylon = new Pylon("repair-pylon"){{
            requirements(Category.effect, with(thallium, 330, prinute, 145, silicon, 110, polonium, 75));
            consumePower(5.3f);
            size = 3;
            reload = 190f;
            range = 85f;
            healAmount = 40f;
            phaseBoost = 30f;
            useTime = 900;
            scaledHealth = 110;
            squareSprite = false;
            baseColor = phaseColor = IcePal.thalliumLight;
            consumeItem(poloniumCharge).boost();
        }};
        flameDome = new PowerTurret("flame-dome"){{
            requirements(Category.effect, with(thallium, 140, sporeWood, 90, prinute, 75, soptin, 55));
            shootType = new LiquidBulletType(Liquids.water){{
                lifetime = 20f;
                speed = 10f;
                knockback = 0;
                puddleSize = 10f;
                orbSize = 4f;
                drag = 0.0005f;
                statusDuration = 60f * 3f;
                collideTerrain = true;
                damage = 0;
                layer = Layer.bullet - 2f;
            }};
            shoot = new ShootSpread(){{
                spread = 1;
                shots = 360;
            }};
            squareSprite = false;
            outlineColor = IcePal.rkiOutline;
            drawer = new DrawTurret("rik-");
            reload = 40f;
            shootCone = 360f;
            rotateSpeed = 0f;
            targetAir = false;
            targetGround = true;
            range = 200f;
            shootY = 0;
            shootEffect = Fx.shootLiquid;
            heatColor = IcePal.thalliumMid;
            recoil = 0f;
            size = 2;
            scaledHealth = 180;
            alwaysShooting = true;
            flags = EnumSet.of(BlockFlag.extinguisher);
            shootSound = Sounds.spark;
            consumePower(5f);
            coolant = consumeCoolant(0.1f);
        }};
        lamp = new LightBlock("lamp"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(thallium, 20, prinute, 5));
            brightness = 0.4f;
            radius = 70f;
            consumePower(0.2f);
        }};
        oldNode = new PowerNode("old-node"){{
            requirements(Category.power, with(thallium, 4, sporeWood, 6));
            consumesPower = outputsPower = true;
            health = 120;
            laserRange = 6;
            maxNodes = 2;
            fogRadius = 1;
            laserColor2 = IcePal.thalliumLight;
            consumePowerBuffered(5f);
            researchCost = with(thallium, 20, sporeWood, 20);
        }};
        armoredNode = new AggressiveNode("armored-node"){{
            requirements(Category.power, with(prinute, 8, silicon, 15));
            consumesPower = outputsPower = true;
            health = 470;
            armor = 3;
            range = 66;
            size = 2;
            laserRange = 7.5f;
            maxNodes = 2;
            fogRadius = 3;
            laserColor2 = IcePal.thalliumLight;
            consumePowerBuffered(50f);
        }};
        recallNode = new PowerNode("recall-node"){{
            requirements(Category.power, with(silicon, 18, ceramic, 10));
            consumesPower = outputsPower = true;
            health = 220;
            armor = 3;
            size = 2;
            laserRange = 9.5f;
            maxNodes = 3;
            fogRadius = 3;
            laserColor2 = IcePal.thalliumLight;
            consumePowerBuffered(20f);
        }};
        scrapSolar = new UndergroundPanels("scrap-solar"){{
            requirements(Category.power, with(thallium, 25, scrap, 25));
            powerProduction = 0.5f;
            generateEffect = Fx.generatespark;
            effectChance = 0.008f;
            size = 1;
            attribute = sun;
            displayEfficiency = false;
            floating = false;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.02f;
            researchCost = with(thallium, 20, scrap, 20);
        }};
        siliconSolar = new UndergroundPanels("silicon-solar"){{
            requirements(Category.power, with(thallium, 40, prinute, 10, silicon, 10));
            powerProduction = 0.8f;
            generateEffect = Fx.generatespark;
            effectChance = 0.008f;
            radius = 18f;
            size = 2;
            attribute = sun;
            displayEfficiency = false;
            minEfficiency = 4f - 0.00001f;
            displayEfficiencyScale = 1f / 4f;
            floating = false;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.02f;
        }};
        decomposer = new SootGenerator("decomposer"){{
            requirements(Category.power, with(thallium, 40, scrap, 25));
            scaledHealth = 20f;
            powerProduction = 3f;
            itemDuration = 80f;
            size = 2;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.03f;
            generateEffect = Fx.generatespark;

            consumeItem(sporeWood, 2);

            drawer = new DrawMulti(new DrawDefault(), new DrawWarmupRegion());
        }};
        methaneBurner = new ConsumeGenerator("methane-burner"){{
            requirements(Category.power, with(thallium, 140, prinute, 60, silicon, 75));
            scaledHealth = 20f;
            powerProduction = 8f;
            itemDuration = 70f;
            size = 3;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.03f;
            generateEffect = Fx.fuelburn;

            consumeLiquid(methanum, 6f / 60f);
            outputLiquid = new LiquidStack(waste, 1.5f / 60f);

            drawer = new DrawMulti(new DrawDefault(), new DrawCrucibleFlame(){{
                flameColor = IcePal.methaneMid;
                midColor = IcePal.methaneLightish;
                particleInterp = new Interp.PowIn(2f);
                flameRadiusScl = 14f;
            }},new DrawWarmupRegion());
        }};
        nuclearReactor = new RuinReactor("nuclear-reactor"){{
            requirements(Category.power, with(thallium, 270, prinute, 170, silicon, 190, ceramic, 135, polonium, 98));
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.24f;
            size = 4;
            health = 700;
            itemDuration = 380f;
            powerProduction = 16f;
            heating = 0.04f;
            fuelItem = poloniumCharge;
            lightColor = IcePal.poloniumLightish;
            hotColor = IcePal.poloniumLight;
            explodeEffect = IceFx.nuclearReactorExplosion;
            consumeItem(poloniumCharge, 1);
            consumeLiquid(water, heating / coolantPower).update(false);
        }};
        coreAngry = new CoreBlock("core-angry"){{
            requirements(Category.effect, with(thallium, 860, sporeWood, 600));
            alwaysUnlocked = true;

            isFirstTier = true;
            unitType = malice;
            health = 700;
            itemCapacity = 1600;
            squareSprite = false;
            size = 2;

            unitCapModifier = 5;
            drawTeamOverlay = false;

        }
            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
            }
        };
        coreHate = new CoreBlock("core-hate"){{
            requirements(Category.effect, with(thallium, 1360, sporeWood, 980, prinute, 640, silicon, 575));
            unitType = charity;
            health = 2600;
            itemCapacity = 3400;
            squareSprite = false;
            size = 3;

            unitCapModifier = 15;
            drawTeamOverlay = false;
        }
            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
            }
        };
        coreFury = new CoreBlock("core-fury"){{
            requirements(Category.effect, with(thallium, 3250, sporeWood, 2600, prinute, 1900, silicon, 1300, polonium, 930));
            unitType = charity;
            health = 5600;
            armor = 5;
            itemCapacity = 7000;
            squareSprite = false;
            size = 4;

            unitCapModifier = 30;
            drawTeamOverlay = false;
        }
            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
            }
        };
        bail = new ItemTurret("bail"){{
            requirements(Category.turret, with(thallium, 90, sporeWood, 35, scrap, 40));
            ammo(
                    thallium, pointBullet
            );

            shoot = new ShootSpread(){{
                shots = 5;
                spread = 10;
            }};
            outlineColor = IcePal.rkiOutline;
            squareSprite = false;
            reload = 40f;
            range = 80f;
            size = 2;
            scaledHealth = 270;
            shootSound = Sounds.lasershoot;
            coolant = consumeCoolant(0.3f);
            drawer = new DrawTurret("rik-");
            researchCost = with(thallium, 100, sporeWood, 50, scrap, 30);
        }};
        clockwise = new ItemTurret("clockwise"){{
            requirements(Category.turret, with(thallium, 75, sporeWood, 50, scrap, 68));
            ammo(
                    scrap, scrapMissile
            );
            outlineColor = IcePal.rkiOutline;
            squareSprite = false;
            reload = 55f;
            range = 115f;
            size = 2;
            scaledHealth = 260;
            shootSound = Sounds.shootAlt;
            coolant = consumeCoolant(0.3f);
            drawer = new DrawTurret("rik-");
            researchCost = with(thallium, 150, sporeWood, 100, scrap, 136);
        }};
        skimmer = new ItemTurret("skimmer"){{
            requirements(Category.turret, with(thallium, 130, sporeWood, 78, prinute, 58));

            ammo(
                    sporeWood, sporeSkimmerBullet,
                    silicon, siliconSkimmerBullet
            );

            drawer = new DrawTurret("rik-");

            inaccuracy = 5f;
            shake = 2f;
            outlineColor = IcePal.rkiOutline;
            size = 2;
            shootSound = Sounds.artillery;
            envEnabled |= Env.space;
            reload = 110f;
            recoil = 3f;
            range = 130;
            shootCone = 30f;
            scaledHealth = 230;
            rotateSpeed = 2.8f;

            minRange = 20f;
            coolant = consumeCoolant(0.3f);
        }};
        perfection = new LiquidTurret("perfection"){{
            requirements(Category.turret, with(thallium, 140, sporeWood, 80, prinute, 65, soptin, 40));
            ammo(
                    methanum, methaneSpike
            );

            outlineColor = IcePal.rkiOutline;
            squareSprite = false;
            reload = 80f;
            range = 170f;
            size = 3;
            scaledHealth = 230;
            shootSound = Sounds.pulseBlast;
            drawer = new DrawTurret("rik-");
        }};
        shatter = new ItemTurret("shatter"){{
            requirements(Category.turret, with(thallium, 190, sporeWood, 110, prinute, 95, ceramic, 70));

            ammo(
                    ceramic, ceramicChunk
            );

            drawer = new DrawTurret("rik-");

            inaccuracy = 10f;
            shake = 2f;
            outlineColor = IcePal.rkiOutline;
            shootSound = Sounds.missileTrail;
            size = 3;
            envEnabled |= Env.space;
            ammoPerShot = 2;
            reload = 40f;
            recoil = 3f;
            range = 150;
            shootCone = 36f;
            scaledHealth = 205;
            rotateSpeed = 3.4f;

            minRange = 30;
            limitRange(5);
            coolant = consumeCoolant(0.6f);
        }};
        crypt = new LiquidTurret("crypt"){{
            requirements(Category.turret, with(thallium, 260, prinute, 110, silicon, 130, soptin, 90, ceramic, 75));
            ammo(
                    waste, new BasicBulletType(6, 55){{
                        smokeEffect = Fx.shootBigSmoke;
                        shootEffect = Fx.shootBigColor;
                        width = 7f;
                        height = 10.5f;
                        lifetime = 30f;
                        hitSize = 4f;
                        hitColor = backColor = trailColor = IcePal.wasteLight;
                        frontColor = Color.white;
                        pierce = pierceBuilding = pierceArmor = true;
                        pierceCap = 3;
                        trailWidth = 2f;
                        homingDelay = 8;
                        homingPower = 0.5f;
                        trailLength = 10;
                        despawnEffect = hitEffect = Fx.hitBulletColor;
                        status = IceStatuses.rusting;
                        statusDuration = 90;
                        collideTerrain = true;
                    }}
            );

            outlineColor = IcePal.rkiOutline;
            squareSprite = false;
            reload = 60f;
            range = 180f;
            shootY = 0;
            size = 3;
            scaledHealth = 210;
            shootSound = Sounds.pulseBlast;
            drawer = new DrawTurret("rik-"){{
                parts.addAll(
                        new RegionPart("-side"){{
                            heatProgress = PartProgress.reload;
                            mirror = under = true;
                            moveX = 1f;
                            moveY = -1.5f;
                            moveRot = -22;
                        }}
                );
            }};
        }};
        demonCore = new PowerTurret("demon-core"){{
            requirements(Category.turret, with(thallium, 250, sporeWood, 160, silicon, 100, prinute, 120, polonium, 80));

            shootType = radBlast;

            drawer = new DrawTurret("rik-"){{
                parts.addAll(
                        new RegionPart("-side"){{
                            heatProgress = PartProgress.warmup;
                            x = 2;
                            mirror = true;
                            moveX = -2f;
                        }}
                );
            }};
            consumePower(5f);

            inaccuracy = 360f;
            outlineColor = IcePal.rkiOutline;
            shootSound = Sounds.missileTrail;
            heatColor = IcePal.poloniumLight;
            shoot.firstShotDelay = 20;
            size = 3;
            envEnabled |= Env.space;
            reload = 15f;
            shootY = 0;
            recoil = 0f;
            range = 200;
            shootCone = 360f;
            scaledHealth = 195;
            rotateSpeed = 0f;
        }};
        burnout = new ItemTurret("burnout"){{
            requirements(Category.turret, with(thallium, 340, silicon, 240, ceramic, 95,prinute, 110, polonium, 130));
            ammo(
                    polonium, poloniumBlast,
                    poloniumCharge, chargedBlast
            );
            targetAir = false;
            reload = 150f;
            recoil = 3.2f;
            range = 260f;
            size = 3;
            inaccuracy = 1f;
            shootCone = 28f;
            outlineColor = IcePal.rkiOutline;
            heatColor = IcePal.poloniumLight;
            scaledHealth = 190;
            shootSound = Sounds.mediumCannon;
            coolant = consumeCoolant(0.4f);
            minRange = 30f;
            limitRange(0f);
            drawer = new DrawTurret("rik-");
        }};
        discharge = new PowerTurret("discharge"){{
            requirements(Category.turret, with(thallium, 430, sporeWood, 310, silicon, 390, prinute, 260, polonium, 190, denseAlloy, 110));

            shootType = new RailBulletType(){{
                length = 240f;
                damage = 340f;
                pierceArmor = true;
                pierce = false;
                lineEffect = IceFx.thalliumChainLightning;
                hitEffect = IceFx.thalliumBlastExplosion;
                collideTerrain = true;
            }};
            consumePower(9.7f);

            inaccuracy = 8f;
            outlineColor = IcePal.rkiOutline;
            shootSound = Sounds.missileTrail;
            heatColor = IcePal.thalliumLight;
            shoot.shots = 4;
            shoot.shotDelay = 15;
            size = 4;
            envEnabled |= Env.space;
            reload = 175f;
            shootY = -2.5f;
            recoil = 3.8f;
            range = 240;
            shootCone = 70f;
            scaledHealth = 195;
            rotateSpeed = 4f;
            drawer = new DrawTurret("rik-");
            coolant = consumeCoolant(0.4f);
        }};
        simpleConstructor = new LegacyFactoryPad("simple-constructor"){{
            requirements(Category.units, with(thallium, 120, sporeWood, 75, prinute, 50));
            plans = Seq.with(
                    new UnitLeagcyPlan(vessel, 60f * 18, with(thallium, 25, prinute, 10)),
                    new UnitLeagcyPlan(stem, 60f * 19.5f, with(thallium, 15, prinute, 10, sporeWood, 20)),
                    new UnitLeagcyPlan(basis, 60f * 21, with(thallium, 45, scrap, 20, prinute, 15))
            );
            size = 3;
            consumePower(1.8f);
            researchCost = with(thallium, 200, sporeWood, 100, scrap, 50);
        }};
        forcedConstructor = new LegacyFactoryPad("force-constructor"){{
            requirements(Category.units, with(thallium, 270, livesteel, 80, prinute, 120, silicon, 140));
            plans = Seq.with(
                    new UnitLeagcyPlan(ewer, 60f * 37, with(thallium, 145, prinute, 75, silicon, 90)),
                    new UnitLeagcyPlan(xylem, 60f * 54, with(thallium, 110, prinute, 60, livesteel, 70, silicon, 75)),
                    new UnitLeagcyPlan(fundament, 60f * 60, with(thallium, 190, ceramic, 60, prinute, 90, silicon, 110))
            );
            size = 5;
            consumeLiquid(methanum, 5f/ 60);
            consumePower(4.2f);
        }};
        aquaConstructor = new LegacyFactoryPad("aqua-constructor"){{
            requirements(Category.units, with(thallium, 165, sporeWood, 90, prinute, 55, soptin, 70));
            plans = Seq.with(
                    new UnitLeagcyPlan(quant, 60f * 21, with(thallium, 28, prinute, 20, soptin, 15)),
                    new UnitLeagcyPlan(sin, 60f * 24f, with(soptin, 20, prinute, 15))
            );
            size = 3;
            floating = true;
            drawTop = false;
            consumePower(2.3f);
            researchCost = with(thallium, 600, sporeWood, 300, prinute, 250, soptin, 350);
        }};
        monsterNest = new EnemyNest("monster-nest"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 1000, scrap, 650));
            size = 5;
        }};
    }
}
