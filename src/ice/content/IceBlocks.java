package ice.content;


import arc.struct.*;
import ice.classes.blocks.distribution.*;
import ice.classes.blocks.environment.*;
import ice.classes.blocks.power.*;
import ice.classes.blocks.production.*;
import ice.classes.blocks.units.LegacyFactoryPad;
import ice.graphics.IcePal;
import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.pattern.*;
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
    dustCrusher, undergroundTree,
            //calamite pikes
            calamiteFloor, calamiteWall, calamiteBoulder,
            //dust valley
            dustFloor, dustWall, dustSpit,
            //methane ocean
            methaneFloor, thermalFloor, solidMethane, thermalBoulder,
            //magnet cavern
            magnetRock, magnetChunk, magnetPositiveRock, magnetNegativeRock, positiveCrystal, negativeCrystal, magnetBoulder,
            //other
            stoneLargeCrater, dustLargeCrater,
     //ores
    oreDust, oreThallium, oreSoptin,
    //crafting
    prinuteMerger,
    //production
    protoDrill, advancedDrill,mechanicalCutter, laserCutter,
    //distribution
    thalliumConveyor, thalliumJunction, splitter,
    //liquid
    burstPump, soptinTube, soptinRouter,
    //defence
    woodWall, bleak, flameDome,
    //effect
    lamp,
    //power
    oldNode, scrapSolar, decomposer,
    //storage
    coreAngry,
    //turrets
    bail, skimmer, perfection,
    //units
    simpleConstructor
    ;
    public static void load(){
        undergroundTree = new TreeBlock("underground-tree"){{
            itemDrop = sporeWood;
        }};
        calamiteFloor = new Floor("calamite-floor", 2);
        calamiteWall = new StaticWall("calamite-wall"){{
            variants = 2;
        }};
        calamiteBoulder = new Prop("calamite-boulder"){{
            variants = 1;
            calamiteFloor.asFloor().decoration = this;
        }};
        dustFloor = new Floor("dust-stone");
        dustWall = new StaticWall("dust-wall");
        dustSpit = new OverlayFloor("dust-spit"){{
            variants = 2;
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
        thermalFloor = new Floor("thermal-floor");
        solidMethane = new StaticWall("solid-methane"){{
            variants = 2;
        }};
        thermalBoulder = new Prop("thermal-boulder"){{
            variants = 1;
            thermalFloor.asFloor().decoration = this;
        }};
        magnetRock = new Floor("magnet-rock");
        magnetChunk = new StaticWall("magnet-chunk"){{
            variants = 2;
        }};
        magnetPositiveRock = new Floor("magnet-positive-rock", 2);
        magnetNegativeRock = new Floor("magnet-negative-rock", 2);
        positiveCrystal = new TallBlock("positive-crystal"){{
            variants = 2;
            clipSize = 64f;
        }};
        negativeCrystal = new TallBlock("negative-crystal"){{
            variants = 2;
            clipSize = 64f;
        }};
        magnetBoulder = new Prop("magnet-boulder"){{
            variants = 1;
            magnetRock.asFloor().decoration = this;
        }};
        oreDust = new OreBlock(ceramicalDust);
        oreThallium = new OreBlock(thallium);
        oreSoptin = new OreBlock(soptin);
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
        dustCrusher = new WorldDuster("dust-crusher"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 5));
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
        protoDrill = new BreakDrill("proto-drill"){{
            requirements(Category.production, with(thallium, 20));
            health = 200;
            tier = 2;
            drillTime = 600;
            size = 2;
            squareSprite = false;
            damageLight = 8;
            reload = 135;

            consumeLiquid(water, 0.05f).boost();
        }};
        advancedDrill = new BreakDrill("advanced-drill"){{
            requirements(Category.production, with(thallium, 45, prinute, 15));
            health = 450;
            tier = 3;
            drillTime = 500;
            size = 2;
            squareSprite = false;
            damageLight = 6;
            lengthLight = 4;
            reload = 160;

            consumeLiquid(water, 0.05f).boost();
        }};
        mechanicalCutter = new NearMiner("mechanical-cutter"){{
            requirements(Category.production, with(thallium, 40));

            drillTime = 280f;
            scaledHealth = 10f;
            tier = 3;
            size = 1;
            range = 1;
            fogRadius = 3;
            researchCost = with(thallium, 10);
            squareSprite = false;
            optionalBoostIntensity = 1;
        }};
        laserCutter = new BeamDrill("laser-cutter"){{
            requirements(Category.production, with(thallium, 30, prinute, 10));
            consumePower(0.2f);

            drillTime = 220f;
            scaledHealth = 20f;
            tier = 3;
            size = 1;
            range = 1;
            fogRadius = 3;
            researchCost = with(thallium, 30);
            optionalBoostIntensity = 1;
        }};
        thalliumConveyor = new RegenConveyor("thallium-conveyor"){{
            requirements(Category.distribution, with(thallium, 2));
            health = 90;
            speed = 0.04f;
            displayedSpeed = 5.6f;
        }};
        thalliumJunction = new Junction("thallium-junction"){{
            requirements(Category.distribution, with(thallium, 14, sporeWood, 4));
        }};
        splitter = new Splitter("splitter"){{
            requirements(Category.distribution, with(thallium, 10));
        }};
        //TODO effects
        burstPump = new BurstPump("burst-pump"){{
            requirements(Category.liquid, with(thallium, 60, prinute, 25, soptin, 40));
            pumpAmount = 1f;
            consumePower(0.4f);
            consumeItem(ceramicalDust, 2);
            consumeTime = 60 * 2;
            liquidCapacity = 200f;
            hasPower = true;
            hasItems = true;
            size = 2;
            squareSprite = false;
        }};
        soptinTube = new Conduit("soptin-tube"){{
            requirements(Category.liquid, with(soptin, 2));
            health = 50;
        }};
        soptinRouter = new LiquidRouter("soptin-router"){{
            requirements(Category.liquid, with(soptin, 5, sporeWood, 2));
            liquidCapacity = 12f;
            solid = true;
        }};

        woodWall = new Wall("wood-wall"){{
            requirements(Category.defense, with(sporeWood, 6));
            health = 100 * 4;
            envDisabled |= Env.scorching;
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
            coolant = consumeCoolant(0.1f);
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
        coreAngry = new CoreBlock("core-angry"){{
            requirements(Category.effect, with(thallium, 860, sporeWood, 600));
            alwaysUnlocked = true;

            isFirstTier = true;
            unitType = IceUnitTypes.malice;
            health = 700;
            itemCapacity = 1600;
            squareSprite = false;
            size = 2;

            unitCapModifier = 5;
            drawTeamOverlay = false;
        }};
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
            scaledHealth = 300;
            shootSound = Sounds.lasershoot;
            coolant = consumeCoolant(0.3f);
            drawer = new DrawTurret("rik-");
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
            envEnabled |= Env.space;
            reload = 110f;
            recoil = 3f;
            range = 130;
            shootCone = 30f;
            scaledHealth = 220;
            rotateSpeed = 2.8f;

            minRange = 20f;
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
            scaledHealth = 320;
            shootSound = Sounds.pulseBlast;
            coolant = consumeCoolant(0.65f);
            drawer = new DrawTurret("rik-");
        }};
        simpleConstructor = new LegacyFactoryPad("simple-constructor"){{
            requirements(Category.units, with(thallium, 120, sporeWood, 75, prinute, 50));
            plans = Seq.with(
                    new UnitLeagcyPlan(vessel, 60f * 18, with(thallium, 25, prinute, 10)),
                    new UnitLeagcyPlan(stem, 60f * 18, with(thallium, 15, prinute, 10, sporeWood, 20)),
                    new UnitLeagcyPlan(basis, 60f * 21, with(thallium, 30, scrap, 15, prinute, 10))
            );
            size = 3;
            consumePower(1.8f);
        }};
    }
}
