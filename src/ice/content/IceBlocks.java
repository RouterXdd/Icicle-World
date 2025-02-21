package ice.content;


import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.math.Interp;
import arc.struct.*;
import ice.IcicleVars;
import ice.classes.blocks.defence.*;
import ice.classes.blocks.distribution.*;
import ice.classes.blocks.environment.*;
import ice.classes.blocks.power.*;
import ice.classes.blocks.production.*;
import ice.classes.blocks.units.*;
import ice.classes.entities.bullets.ScaledBasicBulletType;
import ice.classes.type.StatTypes;
import ice.graphics.*;
import mindustry.Vars;
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

import static ice.content.IceStatuses.*;
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
            //stone (new)
            deepStone, deepStoneWall, deepBoulder,
            //calamite pikes
            calamiteFloor, calamiteWall, calamiteBoulder,
            //dust valley
            dustFloor, dustMix, dustWall, dustSpit,
            //methane ocean
            methaneFloor, methaneFloorShallow, thermalFloor, solidMethane, thermalBoulder,
            //magnet cavern
            magnetRock, magnetChunk, magnetPositiveRock, magnetNegativeRock, positiveCrystal, negativeCrystal, magnetBoulder,
            //other
            stoneLargeCrater, dustLargeCrater, rkiMetal, rkiMetal2, rkiMetal3, rkiMetalWall, floor,
     //ores
    oreDust, oreThallium, oreSoptin, orePolonium, oreChalk, orePoloniumUnderground, oreThalliumUnderground, oreChalkUnderground,
    //crafting
    prinuteMerger, prinuteFabricator, siliconDissembler, ceramicalAssembler, ceramicSmelter, distiller, quarry, metalIncubator, enmitiumCrucible, poloniumCrucible, denseStructurer,
    //production
    protoDrill, advancedDrill, engineDrill, nuclearDrill, mechanicalCutter, laserCutter, oreFinder, methaneDigger, electricCrusher,
    //distribution
    thalliumConveyor, thalliumJunction, splitter, thalliumTunnel, thalliumSorter,
    //liquid
    burstPump, pulsePump, soptinTube, soptinRouter, soptinTunnel,
    //defence
    woodWall, woodWallLarge, ceramicWall, ceramicWallLarge, aliveWall, aliveWallLarge, conductiveWall, conductiveWallLarge, bitWall, bitWallLarge, bleak, shine, repairPylon, flameDome, forceDome,
    //effect
    lamp, largeLamp,
    //power
    oldNode, armoredNode, recallNode, scrapSolar, siliconSolar, poloniumPanel, decomposer, methaneBurner, nuclearReactor, liquidTurbine,
    //storage
    coreAngry, coreHate, coreFury,
    //turrets
    bail, clockwise, skimmer, cremator, perfection, shatter, crypt, demonCore, burnout, discharge, calamity,
    //units
    simpleConstructor, forcedConstructor, omegaConstructor, aquaConstructor, atlanticConstructor, lustrousConstructor, monsterNest
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
        deepStone = new Floor("deep-stone", 3);
        deepStoneWall = new StaticWall("deep-stone-wall"){{
            variants = 2;
            deepStone.asFloor().wall = this;
        }};
        deepBoulder = new Prop("deep-boulder"){{
            variants = 1;
            deepStone.asFloor().decoration = this;
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
        dustFloor = new Floor("dust-stone", 3)/*{{
            effect = new ParticleEffect(){{
                particles = 1;
                sizeFrom = 3.5f;
                sizeTo = 0;
                lifetime = 80;
            }};
        }}*/;
        dustMix = new Floor("dust-mix"){{
            speedMultiplier = 0.4f;
            variants = 3;
            drownTime = 200;
            status = rusting;
            statusDuration = 140f;
            liquidDrop = waste;
            liquidMultiplier = 0.1f;
            isLiquid = true;
            cacheLayer = CacheLayer.mud;
            attributes.set(meth, 0.1f);
        }};
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
            cacheLayer = !Vars.android ? IceShaders.methaneLayer : CacheLayer.water;
            attributes.set(meth, 0.25f);
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
            cacheLayer = !Vars.android ? IceShaders.methaneLayer : CacheLayer.water;
            attributes.set(meth, 0.1f);
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
        positiveCrystal = new ChargeCrystal("positive-crystal"){{
            variants = 2;
            clipSize = 64f;
            status = positiveCharge;
            magnetPositiveRock.asFloor().decoration = this;
        }};
        negativeCrystal = new ChargeCrystal("negative-crystal"){{
            variants = 2;
            clipSize = 64f;
            status = negativeCharge;
            magnetNegativeRock.asFloor().decoration = this;
        }};
        magnetBoulder = new Prop("magnet-boulder"){{
            variants = 1;
            magnetRock.asFloor().decoration = this;
        }};
        oreDust = new OreBlock(ceramicalDust);
        oreThallium = new OreBlock(thallium);
        oreSoptin = new OreBlock(soptin);
        orePolonium = new ShineOre(polonium){{
            effect = new ParticleEffect(){{
                spin = 1;
                lifetime = 65;
                sizeFrom = 1.5f;
                colorFrom = IcePal.poloniumLightish;
                region = "icicle-world-star";
                particles = 2;
            }};
        }};
        oreChalk = new OreBlock(chalkStone);
        orePoloniumUnderground = new UndergroundOre("polonium-under");
        oreThalliumUnderground = new UndergroundOre("thallium-under"){{
            outputOre = oreThallium;
        }};
        oreChalkUnderground = new UndergroundOre("chalk-under"){{
            outputOre = oreChalk;
        }};
        stoneLargeCrater = new LargeCrater("stone-large-crater", 1){{
            attributes.set(sun, 1);
            emitLight = true;
            lightRadius = 26f;
            blendGroup = parent = deepStone;
        }};
        dustLargeCrater = new LargeCrater("dust-large-crater", 1){{
            attributes.set(sun, 1);
            variants = 2;
            emitLight = true;
            lightRadius = 26f;
            blendGroup = parent = dustFloor;
        }};
        rkiMetal = new Floor("rki-metal", 0);
        rkiMetal2 = new Floor("rki-metal2", 0){{
            blendGroup = rkiMetal;
        }};
        rkiMetal3 = new Floor("rki-metal3", 0){{
            blendGroup = rkiMetal;
        }};
        rkiMetalWall = new StaticWall("rki-metal-wall"){{
            variants = 2;
            rkiMetal.asFloor().wall = this;
        }};
        floor = new Floor("-floor", 0);
        dustCrusher = new WorldDuster("dust-crusher"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 5));
        }};
        dustSpreader = new WorldDuster("dust-spreader"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 30));
            range = 3;
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

            consumeItems(with(thallium, 1, scrap, 1));
            consumePower(1.5f);
        }};
        prinuteFabricator = new GenericCrafter("prinute-fabricator"){{
            requirements(Category.crafting, with(thallium, 240, sporeWood, 175, silicon, 140, prinute, 80, soptin, 110));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(prinute, 5);
            craftTime = 95f;
            size = 3;
            hasPower = true;
            hasLiquids = false;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(IcePal.thalliumLightish){{
                flameRadius = 5f;
            }});
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.12f;

            consumeItems(with(thallium, 2, scrap, 2, ceramic, 1));
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
        ceramicalAssembler = new GenericCrafter("ceramical-assembler"){{
            requirements(Category.crafting, with(thallium, 195, sporeWood, 150, silicon, 170, ceramic, 95, prinute, 120));
            craftEffect = new MultiEffect( new ParticleEffect(){{
                particles = 6;
                colorFrom = Color.valueOf("e1d8a5");
                colorTo = Color.valueOf("00000000");
                sizeFrom = 3;
                region = "icicle-world-square";
                length = 35;
                cone = 360;
                lifetime = 60;
            }});
            outputItems = with(cerymec, 2);
            craftTime = 110f;
            size = 3;
            hasPower = true;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault());
            fogRadius = 3;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.16f;
            researchCostMultiplier = 1.5f;

            consumeItems(with(chalkStone, 2, ceramic, 2));
            consumePower(6.5f);
        }};
        ceramicSmelter = new GenericCrafter("ceramic-smelter"){{
            requirements(Category.crafting, with(thallium, 260, silicon, 150, ceramic, 135, prinute, 170));
            craftEffect = new MultiEffect(new ParticleEffect(){{
                particles = 5;
                cone = 0;
                sizeFrom = 4;
                baseRotation = 90;
                sizeTo = 0;
                baseLength = 6;
                length = 20;
                lifetime = 36;
                colorTo = Color.valueOf("00000000");
                colorFrom = IcePal.sootColor;
            }}, new ParticleEffect(){{
                line = true;
                particles = 8;
                colorFrom = IcePal.sootColor;
                colorTo = Color.black;
                sizeFrom = 2;
                sizeTo = 0;
                lenFrom = 5;
                lenTo = 0;
                length = 40;
                cone = 360;
                lifetime = 50;
            }});
            outputItems = with(ceramic, 2);
            craftTime = 65f;
            size = 3;
            hasPower = true;
            itemCapacity = 30;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawWarmupRegion(){{
                color = IcePal.methaneLightish;
            }});
            fogRadius = 3;
            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.16f;
            researchCostMultiplier = 2f;

            consumeItems(with(ceramicalDust, 3, sporeWood, 1));
            consumePower(8f);
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
        quarry = new Separator("quarry"){{
            requirements(Category.crafting, with(thallium, 85, silicon, 55, ceramic, 70));
            results = with(
                    thallium, 2,
                    soptin, 2,
                    ceramic, 2
            );
            hasPower = true;
            hasLiquids = false;
            craftTime = 40f;
            size = 2;

            consumePower(2.75f);
            consumeItem(chalkStone);

            drawer = new DrawMulti( new DrawDefault(),new DrawPistons(){{
                lenOffset = 0f;
                sinScl = -3;
                sinMag = -1.75f;
            }});
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
        enmitiumCrucible = new GenericCrafter("enmitium-crucible"){{
            requirements(Category.crafting, with(thallium, 320, ceramic, 110, silicon, 135, livesteel, 90));
            /*craftEffect = new MultiEffect( new ParticleEffect(){{
                particles = 6;
                colorFrom = Color.valueOf("e1d8a5");
                colorTo = Color.valueOf("00000000");
                sizeFrom = 3;
                region = "icicle-world-square";
                length = 35;
                cone = 360;
                lifetime = 60;
            }});*/
            outputLiquid = new LiquidStack(enmitium, 5f / 60f);
            craftTime = 70f;
            size = 3;
            hasPower = true;
            itemCapacity = 10;
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCircles(){{
                color = Color.valueOf("be5159").a(0.24f);
                strokeMax = 2f;
                radius = 8f;
                amount = 5;
            }}, new DrawDefault());
            fogRadius = 3;
            ambientSound = Sounds.extractLoop;
            ambientSoundVolume = 0.1f;
            researchCostMultiplier = 0.9f;

            consumeItems(with(thallium, 2, ceramic, 2));
            consumeLiquid(water, 16f / 60f);
            consumePower(8f);
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
            requirements(Category.crafting, with(thallium, 340, sporeWood, 230, silicon, 185, prinute, 160, ceramic, 146, polonium, 128));
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
            requirements(Category.production, with(thallium, 28, prinute, 15));
            health = 450;
            tier = 3;
            drillTime = 500;
            size = 2;
            squareSprite = false;
            damageLight = 5;
            lengthLight = 4;
            reload = 160;
            minHealth = 0.32f;
            efficiencyCut = 1.75f;

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
            drillTime = 135;
            rotateSpeed = 5f;
            size = 3;
            colorLight = IcePal.poloniumLight;
            damageLight = 10;
            lengthLight = 5;
            reload = 14;
            consumePower(6.5f);
            minHealth = 0;

            consumeLiquid(water, 0.125f).boost();
        }};
        mechanicalCutter = new NearMiner("mechanical-cutter"){{
            requirements(Category.production, with(thallium, 30));

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
            range = 14 * 8;
            consumePower(1.6f);
            fogRadius = 14;
            researchCostMultiplier = 0.7f;
        }};
        methaneDigger = new OreUpper("methane-digger"){{
            requirements(Category.production, with(thallium, 220, soptin, 160, prinute, 110, silicon, 120, ceramic, 90));
            scaledHealth = 20;
            size = 3;
            consumePower(8f);
            consumeLiquid(methanum, 0.2f);
            researchCostMultiplier = 0.7f;
        }};
        electricCrusher = new WorldDuster("electric-crusher"){{
            requirements(Category.production,  with(ceramic, 140, cerymec, 95, silicon, 120));
            health = 800;
            removable = true;
            range = 2;
            amount = 4;
            size = 3;
            reload = 500;
            consumePower(3.5f);
            consumeItem(cerymec, 2);
        }};
        thalliumConveyor = new RegenConveyor("thallium-conveyor"){{
            requirements(Category.distribution, with(thallium, 1));
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
            ((RegenConveyor)thalliumConveyor).junctionReplacement = this;
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
        thalliumSorter = new DirectSorter("thallium-sorter"){{
            requirements(Category.distribution, with(thallium, 20, prinute, 5));
            researchCost = with(thallium, 80, prinute, 20);
            squareSprite = false;
        }};
        //TODO effects
        burstPump = new BurstPump("burst-pump"){{
            requirements(Category.liquid, with(thallium, 60, prinute, 25, soptin, 40));
            pumpAmount = 1f;
            consumeItem(ceramicalDust, 3);
            consumeTime = 60 * 2;
            liquidCapacity = 200f;
            hasItems = true;
            size = 2;
            squareSprite = false;
        }};
        //TODO effects
        pulsePump = new BurstPump("pulse-pump"){{
            requirements(Category.liquid, with(silicon, 120, ceramic, 95, soptin, 60, scrap, 80));
            pumpAmount = 1f;
            reload = 265f;
            consumePower(4.5f);
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
                    }},
                    Fx.shockwave);
        }};
        soptinTube = new Conduit("soptin-tube"){{
            requirements(Category.liquid, with(soptin, 2));
            health = 50;
            bridgeReplacement = soptinTunnel;
        }
            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{botRegions[0], topRegions[0]};
            }
        };
        soptinRouter = new LiquidRouter("soptin-router"){{
            requirements(Category.liquid, with(soptin, 5, sporeWood, 2));
            liquidCapacity = 12f;
            solid = true;
            ((Conduit)soptinTube).junctionReplacement = this;
        }};
        soptinTunnel = new DirectionLiquidBridge("soptin-tunnel"){{
            requirements(Category.liquid, with(soptin, 12, prinute, 5));
            health = 130;
            range = 6;
            researchCostMultiplier = 0.3f;
            ((Conduit)soptinTube).rotBridgeReplacement = this;
        }};
        woodWall = new Wall("wood-wall"){{
            requirements(Category.defense, with(sporeWood, 6));
            health = 100 * 4;
            envDisabled |= Env.scorching;
        }};
        woodWallLarge = new IceWall("wood-wall-large"){{
            requirements(Category.defense, with(sporeWood, 24));
            health = 100 * 4 * 4;
            envDisabled |= Env.scorching;
            size = 2;
            pierceDecrease = 1;
        }};
        ceramicWall = new Wall("ceramic-wall"){{
            requirements(Category.defense, with(ceramic, 6));
            health = 140 * 4;
            envDisabled |= Env.scorching;
        }};
        ceramicWallLarge = new IceWall("ceramic-wall-large"){{
            requirements(Category.defense, with(ceramic, 24));
            health = 140 * 4 * 4;
            envDisabled |= Env.scorching;
            size = 2;
            pierceDecrease = 1;
        }};
        aliveWall = new IceWall("alive-wall"){{
            requirements(Category.defense, with(prinute, 2, livesteel, 6));
            health = 155 * 4;
            envDisabled |= Env.scorching;
            regenEffect = IceFx.sporeRegen;
            regenerate = suppressable = true;
        }};
        aliveWallLarge = new IceWall("alive-wall-large"){{
            requirements(Category.defense, with(prinute, 8, livesteel, 24));
            health = 155 * 4 * 4;
            envDisabled |= Env.scorching;
            regenEffect = IceFx.sporeRegen;
            regenSpeed = 0.5f;
            regenerate = suppressable = true;
            size = 2;
            pierceDecrease = 1;
        }};
        conductiveWall = new IceWall("conductive-wall"){{
            requirements(Category.defense, with(ceramic, 3, polonium, 7));
            health = 182 * 4;
            envDisabled |= Env.scorching;
            inflictChance = 0.1f;
            armor = 5;
        }};
        conductiveWallLarge = new IceWall("conductive-wall-large"){{
            requirements(Category.defense, with(ceramic, 12, polonium, 28));
            health = 182 * 4 * 4;
            envDisabled |= Env.scorching;
            inflictChance = 0.1f;
            armor = 5;
            size = 2;
            pierceDecrease = 1;
        }};
        bitWall = new IceWall("bit-wall"){{
            requirements(Category.defense, with(denseAlloy, 6));
            health = 140;
            onlyDamage = 1;
            envDisabled |= Env.scorching;
            pierceDecrease = 1;
            armor = 70;
        }};
        bitWallLarge = new IceWall("bit-wall-large"){{
            requirements(Category.defense, with(denseAlloy, 24));
            health = 140 * 4;
            onlyDamage = 1;
            envDisabled |= Env.scorching;
            size = 2;
            pierceDecrease = 2;
            armor = 70;
        }};
        bleak = new PowerTurret("bleak"){{
            requirements(Category.effect, with(thallium, 50, sporeWood, 35));
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
            shootSound = Sounds.lasershoot;
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
            shootSound = Sounds.lasershoot;
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
            researchCostMultiplier = 0.75f;
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
            fogRadiusMultiplier = 0.1f;
            coolant = consumeCoolant(0.1f);
        }};
        forceDome = new ForceConstructor("force-dome"){{
            requirements(Category.effect, with(thallium, 270, silicon, 140, polonium, 90, livesteel, 65));

            size = 3;
            buildTime = 60f * 8f;

            consumePower(7f);
            consumeLiquid(methanum, 8f / 60f);
            consumeItems(with(thallium, 10, polonium, 6));
            itemCapacity = 20;
            researchCost = with(thallium, 3000, silicon, 500, polonium, 300, livesteel, 120);
        }};
        lamp = new LightBlock("lamp"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(thallium, 20, prinute, 5));
            brightness = 0.4f;
            radius = 70f;
            consumePower(0.2f);
        }};
        largeLamp = new LightBlock("large-lamp"){{
            requirements(Category.effect, BuildVisibility.lightingOnly, with(thallium, 60, prinute, 25, silicon, 10, cerymec, 5));
            size = 2;
            brightness = 0.65f;
            radius = 200f;
            consumePower(1.35f);
        }};
        oldNode = new PowerNode("old-node"){{
            requirements(Category.power, with(thallium, 4, sporeWood, 6));
            consumesPower = outputsPower = true;
            health = 120;
            laserRange = 6;
            maxNodes = 2;
            fogRadius = 1;
            laserColor2 = IcePal.thalliumLight;
            consumePowerBuffered(200f);
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
            fogRadius = 2;
            laserColor2 = IcePal.thalliumLight;
            consumePower(1);
            consumePowerBuffered(2500f);
        }};
        recallNode = new PowerNode("recall-node"){{
            requirements(Category.power, with(silicon, 18, ceramic, 10));
            consumesPower = outputsPower = true;
            health = 220;
            armor = 3;
            size = 2;
            laserRange = 9.5f;
            maxNodes = 3;
            fogRadius = 2;
            laserColor2 = IcePal.thalliumLight;
            consumePowerBuffered(1000f);
        }};
        scrapSolar = new UndergroundPanels("scrap-solar"){{
            requirements(Category.power, with(thallium, 15, scrap, 15));
            powerProduction = 0.5f;
            size = 1;
            attribute = sun;
            displayEfficiency = false;
            floating = false;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.02f;
            researchCost = with(thallium, 10, scrap, 10);
        }};
        siliconSolar = new UndergroundPanels("silicon-solar"){{
            requirements(Category.power, with(thallium, 40, prinute, 10, silicon, 10));
            powerProduction = 0.8f;
            radius = 18f;
            size = 2;
            fogRadius = 2;
            attribute = sun;
            displayEfficiency = false;
            minEfficiency = 4f - 0.00001f;
            displayEfficiencyScale = 1f / 4f;
            floating = false;
            ambientSound = Sounds.electricHum;
            ambientSoundVolume = 0.02f;
        }};
        poloniumPanel = new UndergroundPanels("polonium-solar"){{
            requirements(Category.power, with(thallium, 75, ceramic, 25, silicon, 42, polonium, 30));
            powerProduction = 1f;
            radius = 24.5f;
            fogRadius = 3;
            size = 3;
            attribute = sun;
            displayEfficiency = false;
            minEfficiency = 9f - 0.00001f;
            displayEfficiencyScale = 1f / 9f;
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

            consumeLiquid(methanum, 8.5f / 60f);
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
            powerProduction = 30f;
            fuelItem = poloniumCharge;
            lightColor = IcePal.poloniumLightish;
            hotColor = IcePal.poloniumLight;
            explodeEffect = IceFx.nuclearReactorExplosion;
            consumeItem(poloniumCharge, 1);
            consumeLiquid(water, 4f / 60).update(false);
        }};
        liquidTurbine = new UndergroundPanels("liquid-turbine"){{
            requirements(Category.power, with(thallium, 95, prinute, 42, soptin, 60));
            powerProduction = 0.78f;
            radius = -5.5f;
            size = 2;
            attribute = meth;
            floating = true;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.012f;
            researchCostMultiplier = 0.08f;
            drawer = new DrawMulti(new DrawRegion("-vint", 3, true){{
                layer = 1;
            }}, new DrawDefault(), new DrawRegion("-top"));
        }};
        coreAngry = new CoreBlock("core-angry"){{
            requirements(Category.effect, with(thallium, 860, sporeWood, 600));
            alwaysUnlocked = true;

            isFirstTier = true;
            unitType = malice;
            health = 700;
            itemCapacity = 1600;
            squareSprite = false;
            incinerateNonBuildable = true;
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
            incinerateNonBuildable = true;
            size = 3;

            unitCapModifier = 15;
            drawTeamOverlay = false;
            researchCostMultiplier = 0.3f;
        }
            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
            }
        };
        coreFury = new CoreBlock("core-fury"){{
            requirements(Category.effect, with(thallium, 3250, sporeWood, 2600, prinute, 1900, silicon, 1300, polonium, 930));
            unitType = censure;
            health = 5600;
            armor = 5;
            itemCapacity = 7000;
            squareSprite = false;
            incinerateNonBuildable = true;
            size = 4;

            unitCapModifier = 30;
            drawTeamOverlay = false;
            researchCostMultiplier = 0.3f;
        }
            @Override
            public TextureRegion[] icons(){
                return new TextureRegion[]{region,teamRegions[Team.sharded.id]};
            }
        };
        bail = new ItemTurret("bail"){{
            requirements(Category.turret, with(thallium, 90, sporeWood, 35, scrap, 40));
            ammo(
                    thallium, pointBullet);

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
                    silicon, siliconSkimmerBullet,
                    ceramic, new BasicBulletType(4, 40){{
                        shootEffect = new MultiEffect(Fx.shootTitan, new WaveEffect(){{
                            colorTo = Pal.lightTrail;
                            sizeTo = 26f;
                            lifetime = 14f;
                            strokeFrom = 4f;
                        }});
                        collideTerrain = true;
                        smokeEffect = Fx.shootSmokeTitan;
                        hitColor = Pal.lightTrail;

                        trailEffect = Fx.missileTrail;
                        trailInterval = 3f;
                        trailParam = 6f;
                        pierceCap = 5;
                        lifetime = 32.5f;
                        width = 7f;
                        height = 14f;
                        backColor = Pal.lightTrail;
                        frontColor = Color.white;
                        shrinkX = shrinkY = 0f;
                        trailColor = Pal.lightTrail;
                        trailLength = 7;
                        trailWidth = 2.2f;
                        despawnEffect = hitEffect = new ExplosionEffect(){{
                            waveColor = Pal.lightTrail;
                            smokeColor = Color.white;
                            sparkColor = Pal.darkerGray;
                            waveStroke = 5f;
                            waveRad = 20f;
                        }};
                        despawnSound = Sounds.dullExplosion;

                        intervalBullet = new BasicBulletType(1f, 5){{
                            width = 4f;
                            hitSize = 5f;
                            height = 8f;
                            lifetime = 30f;
                            hitColor = trailColor = Pal.lightTrail;
                            frontColor = Color.white;
                            trailWidth = 1.5f;
                            trailLength = 3;
                            collideTerrain = true;
                            hitEffect = despawnEffect = new WaveEffect(){{
                                colorFrom = colorTo = Pal.lightTrail;
                                sizeTo = 4f;
                                strokeFrom = 4f;
                                lifetime = 10f;
                            }};
                        }};

                        bulletInterval = 3f;
                        intervalBullets = 4;
                        intervalRandomSpread = 180f;
                        intervalSpread = 360f;
                    }});

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
            scaledHealth = 260;
            rotateSpeed = 2.8f;

            minRange = 20f;
            coolant = consumeCoolant(0.3f);
        }};
        cremator = new PowerTurret("cremator"){{
            requirements(Category.turret, with(thallium, 110, scrap, 85, prinute, 70));

            shootType = new RailBulletType(){{
                length = 100f;
                damage = 5f;
                status = StatusEffects.melting;
                statusDuration = 5 * 60;
                pierceArmor = true;
                pierce = false;
                pointEffectSpace = 1;
                pointEffect = new MultiEffect(
                        new ParticleEffect(){{
                            particles = 1;
                            length = 0;
                            sizeFrom = 1.5f;
                            colorFrom = colorTo = IcePal.thalliumLight;
                        }});
                hitEffect = Fx.explosion;
                collideTerrain = true;
                shootEffect = Fx.none;
            }};
            consumePower(2.5f);

            inaccuracy = 2f;
            outlineColor = IcePal.rkiOutline;
            shootSound = IceSFX.shock;
            heatColor = IcePal.thalliumLight;
            size = 2;
            envEnabled |= Env.space;
            reload = 10f;
            recoil = 1.25f;
            range = 100;
            shootCone = 55f;
            scaledHealth = 220;
            rotateSpeed = 4f;
            drawer = new DrawTurret("rik-");
            coolant = consumeCoolant(0.2f);
        }};
        perfection = new LiquidTurret("perfection"){{
            requirements(Category.turret, with(thallium, 140, sporeWood, 80, prinute, 65, soptin, 40));
            ammo(
                    methanum, methaneSpike,
                    waste, new BasicBulletType(){{
                        damage = 50;
                        splashDamage = 25;
                        splashDamageRadius = 36;
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
                        frontColor = IcePal.wasteLightish;
                        backColor = trailColor = hitColor = IcePal.wasteMid;
                        trailChance = 1f;
                        ammoMultiplier = 3f;
                        reloadMultiplier = 0.55f;

                        lifetime = 170f / 9f;
                        rotationOffset = 90f;
                        trailRotation = true;
                        trailEffect = Fx.disperseTrail;

                        hitEffect = despawnEffect = IceFx.hitLargeSplashBulletColor;
                        status = rusting;
                        statusDuration = 80;
                    }},
                    enmitium, new BasicBulletType(){{
                        damage = 30;
                        speed = 10f;
                        width = height = 18;
                        shrinkY = 0.3f;
                        backSprite = "large-bomb-back";
                        sprite = "mine-bullet";
                        collidesTiles = true;
                        collideTerrain = true;
                        shootEffect = Fx.shootBig2;
                        smokeEffect = Fx.shootSmokeDisperse;
                        frontColor = IcePal.enmitiumLightish;
                        backColor = trailColor = hitColor = IcePal.enmitiumMid;
                        trailChance = 1f;
                        ammoMultiplier = 6.5f;
                        reloadMultiplier = 6f;
                        lifetime = 170f / 10f;
                        rotationOffset = 90f;
                        trailRotation = true;
                        trailEffect = Fx.disperseTrail;

                        hitEffect = despawnEffect = Fx.hitBulletColor;
                    }}
            );
            extinguish = false;
            ammoPerShot = 100;
            outlineColor = IcePal.rkiOutline;
            squareSprite = false;
            reload = 45f;
            range = 170f;
            shoot.shots = 2;
            inaccuracy = 8;
            velocityRnd = 0.1f;
            size = 3;
            scaledHealth = 230;
            shootSound = Sounds.pulseBlast;
            drawer = new DrawTurret("rik-");
        }};
        shatter = new ItemTurret("shatter"){{
            requirements(Category.turret, with(thallium, 190, sporeWood, 110, prinute, 95, ceramic, 70));

            ammo(
                    ceramic, ceramicChunk,
                    cerymec, new BasicBulletType(8, 95){{
                        smokeEffect = Fx.shootBigSmoke;
                        shootEffect = Fx.shootBigColor;
                        width = 12f;
                        height = 12f;
                        lifetime = 18.75f;
                        hitSize = 7f;
                        hitColor = backColor = trailColor = IcePal.cerymecLight;
                        frontColor = Color.white;
                        trailWidth = 3f;
                        trailLength = 14;
                        splashDamage = 5;
                        splashDamageRadius = 46;
                        despawnEffect = hitEffect = new MultiEffect(new WrapEffect(Fx.dynamicSpikes, IcePal.cerymecLightish, 24f), new WaveEffect(){{
                            colorFrom = colorTo = IcePal.cerymecLightish;
                            sizeFrom = sizeTo = 46f;
                            lifetime = 20f;
                            strokeFrom = 4f;
                        }});
                        status = IceStatuses.flash;
                        statusDuration = 150;
                        collideTerrain = true;
                    }}
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
                        status = rusting;
                        statusDuration = 130;
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
                            heatProgress = PartProgress.recoil;
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
            shootSound = IceSFX.blowUp;
            coolant = consumeCoolant(0.4f);
            minRange = 30f;
            limitRange(0f);
            drawer = new DrawTurret("rik-");
            researchCostMultiplier = 0.75f;
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
            shootSound = IceSFX.shock;
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
        calamity = new ItemTurret("calamity"){{
            requirements(Category.turret, with(thallium, 365, scrap, 390, prinute, 340, ceramic, 430, polonium, 240, denseAlloy, 155));
            squareSprite = false;
            outlineColor = IcePal.rkiOutline;
            ammo(
                    poloniumCharge, new ScaledBasicBulletType(5, 100, 2.5f){{
                shootEffect = new MultiEffect(Fx.shootTitan, new WaveEffect(){{
                    colorTo = IcePal.poloniumLight;
                    sizeTo = 28f;
                    lifetime = 16f;
                    strokeFrom = 4f;
                }});
                smokeEffect = Fx.shootSmokeTitan;
                hitColor = IcePal.poloniumLight;
                collideTerrain = true;
                sprite = "large-orb";
                trailEffect = Fx.missileTrail;
                trailInterval = 3f;
                trailParam = 4f;
                lifetime = 35f;
                width = height = 25f;
                backColor = IcePal.poloniumLight;
                frontColor = Color.white;
                shrinkX = shrinkY = 0f;
                trailColor = IcePal.poloniumLight;
                trailLength = 16;
                trailWidth = 3.6f;
                despawnEffect = hitEffect = new ExplosionEffect(){{
                    waveColor = IcePal.poloniumLight;
                    smokeColor = Color.gray;
                    sparkColor = IcePal.poloniumDark;
                    waveStroke = 5f;
                    waveRad = 55f;
                }};
                despawnSound = Sounds.dullExplosion;
                shootSound = Sounds.cannon;

                fragBullet = new BasicBulletType(2f, 20){{
                    width = 15f;
                    hitSize = 7f;
                    height = 15f;
                    drag = 0.02f;
                    pierce = true;
                    pierceCap = 3;
                    lifetime = 110f;
                    sprite = "large-orb";
                    hitColor = backColor = trailColor = IcePal.poloniumLight;
                    frontColor = Color.white;
                    trailWidth = 3f;
                    trailLength = 7;
                    hitEffect = despawnEffect = new WaveEffect(){{
                        colorFrom = colorTo = IcePal.poloniumLight;
                        sizeTo = 15f;
                        sides = 6;
                        spin = 0.4f;
                        strokeFrom = 4f;
                        lifetime = 60f;
                    }};
                    fragBullet = new ScaledBasicBulletType(2.5f, 10, 1){{
                        width = 5f;
                        hitSize = 5f;
                        height = 12f;
                        pierce = true;
                        lifetime = 25f;
                        sprite = "missile-large";
                        hitColor = backColor = trailColor = IcePal.poloniumLight;
                        frontColor = Color.white;
                        trailWidth = 2.1f;
                        trailLength = 5;
                        splashDamage = 35;
                        splashDamageRadius = 20;
                        hitEffect = despawnEffect = new WaveEffect(){{
                            colorFrom = colorTo = IcePal.poloniumLight;
                            sizeTo = 8f;
                            strokeFrom = 4f;
                            lifetime = 10f;
                        }};
                    }};
                    fragBullets = 4;
                }};
                fragRandomSpread = 0;
                fragSpread = 360 / 14f;
                fragBullets = 14;
                fragVelocityMin = 1f;
                fragVelocityMax = 1f;
                fragLifeMin = 1f;
                fragLifeMax = 1f;
            }});
            drawer = new DrawTurret("rik-"){{
                for(int j = 0; j < 3; j++){
                    int i = j;
                    parts.add(new RegionPart("-blade"){{
                        layerOffset = -0.02f;
                        heatLayerOffset = -0.021f;
                        x = 2f;
                        moveX = 6f + i * 1.9f;
                        moveY = 8f + -7f * i;
                        moveRot = 20f - i * 50f;
                        rotation = 145;
                        mirror = true;
                        progress = PartProgress.warmup.delay(i * 0.2f);
                        heatProgress = PartProgress.warmup;

                        heatColor = IcePal.poloniumLightish;
                    }});
                }
            }};
            reload = 260f;
            shootCone = 75f;
            rotateSpeed = 5.5f;
            range = 190f;
            heatColor = IcePal.poloniumLight;
            recoil = 3f;
            size = 4;
            scaledHealth = 190;
            shootSound = Sounds.spark;
            consumePower(2.2f);
            coolant = consumeCoolant(0.4f);
        }
            @Override
            public void setStats(){
                super.setStats();

                stats.remove(Stat.ammo);
                stats.add(Stat.ammo, StatTypes.ammo(ammoTypes));
            }
        };
        simpleConstructor = new LegacyFactoryPad("simple-constructor"){{
            requirements(Category.units, with(thallium, 120, sporeWood, 75, prinute, 50));
            plans = Seq.with(
                    new UnitPlan(vessel, 60f * 18, with(thallium, 25, prinute, 10)),
                    new UnitPlan(stem, 60f * 19.5f, with(thallium, 15, prinute, 10, sporeWood, 20)),
                    new UnitPlan(basis, 60f * 21, with(thallium, 45, scrap, 20, prinute, 15))
            );
            size = 3;
            consumePower(1.8f);
            researchCost = with(thallium, 200, sporeWood, 100, scrap, 50);
        }};
        forcedConstructor = new LegacyFactoryPad("force-constructor"){{
            requirements(Category.units, with(thallium, 270, livesteel, 80, prinute, 120, silicon, 140));
            plans = Seq.with(
                    new UnitPlan(ewer, 60f * 37, with(thallium, 145, prinute, 75, silicon, 90, ceramic, 35)),
                    new UnitPlan(xylem, 60f * 49, with(thallium, 110, prinute, 60, livesteel, 70, silicon, 75)),
                    new UnitPlan(fundament, 60f * 66, with(thallium, 190, ceramic, 60, prinute, 90, silicon, 110, livesteel, 80))
            );
            size = 5;
            consumeLiquid(methanum, 6f/ 60);
            consumePower(6.45f);
        }};
        omegaConstructor = new LegacyFactoryPad("omega-constructor"){{
            requirements(Category.units, with(thallium, 970, livesteel, 460, prinute, 755, silicon, 600, ceramic, 720, polonium, 820, denseAlloy, 285));
            plans = Seq.with(
                    new UnitPlan(decanter, 60f * 400, with(prinute, 180, silicon, 150, ceramic, 175, livesteel, 110, denseAlloy, 70)),
                    new UnitPlan(stalk, 60f * 400, with(prinute, 220, silicon, 120, polonium, 190, livesteel, 135, denseAlloy, 80)),
                    new UnitPlan(groundwork, 60f * 400, with(prinute, 240, silicon, 190, cerymec, 115, livesteel, 150, denseAlloy, 100))
            );
            size = 10;
            consumeLiquid(enmitium, 30f/ 60);
            consumePower(15f);
        }};
        aquaConstructor = new LegacyFactoryPad("aqua-constructor"){{
            requirements(Category.units, with(thallium, 165, sporeWood, 90, prinute, 55, soptin, 70));
            plans = Seq.with(
                    new UnitPlan(quant, 60f * 21, with(thallium, 28, prinute, 20, soptin, 15)),
                    new UnitPlan(sin, 60f * 24f, with(soptin, 20, prinute, 15))
            );
            size = 3;
            floating = true;
            drawTop = false;
            consumePower(2.3f);
            researchCost = with(thallium, 600, sporeWood, 300, prinute, 250, soptin, 350);
        }};
        atlanticConstructor = new LegacyFactoryPad("atlantic-constructor"){{
            requirements(Category.units, with(thallium, 230, prinute, 130, soptin, 170, silicon, 155, livesteel, 95));
            plans = Seq.with(
                    new UnitPlan(chronon, 60f * 66f, with(thallium, 170, prinute, 80, silicon, 115, soptin, 80)),
                    new UnitPlan(zen, 60f * 80f, with(thallium, 150, soptin, 90, silicon, 110))
            );
            size = 4;
            floating = true;
            drawTop = false;
            consumePower(6.45f);
            consumeLiquid(waste, 9f/ 60);
        }};
        lustrousConstructor = new LegacyFactoryPad("lustrous-constructor"){{
            requirements(Category.units, with(thallium, 120, prinute, 90, ceramic, 70));
            plans = Seq.with(
                    new UnitPlan(blaze, 60f * 24, with(thallium, 40, prinute, 30))
                    //new UnitLeagcyPlan(sunLight, 60f * 40, with(thallium, 75, silicon, 50, ceramic, 60, cerymec, 35))
            );
            size = 3;
            consumePower(2.85f);
            researchCost = with(thallium, 550, prinute, 200, ceramic, 140);
        }};
        monsterNest = new EnemyNest("monster-nest"){{
            requirements(Category.effect, BuildVisibility.sandboxOnly, with(thallium, 1000, scrap, 650));
            size = 5;
            destroyEffect = new MultiEffect(

            );
        }};
    }
}
