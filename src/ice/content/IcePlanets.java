package ice.content;


import arc.graphics.Color;
import arc.struct.Seq;
import ice.IcicleVars;
import ice.classes.maps.generator.RkiSurfaceGenerator;
import ice.classes.maps.generator.RkiTrueGenerator;
import ice.graphics.IcePal;
import mindustry.content.Items;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.ItemStack;
import mindustry.type.Planet;
import mindustry.world.meta.Env;

import static ice.content.IceItems.*;

public class IcePlanets {
    public static Planet
    rki, rkiFake;
    public static void load() {
        rki = new Planet("rki", Planets.sun, 1f, 3) {{
            generator = new RkiTrueGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 2, 0.15f, 0.14f, 5, IcePal.methaneLight.a(0.75f), 2, 0.42f, 1f, 0.43f),
                    new HexSkyMesh(this, 3, 0.6f, 0.15f, 5, IcePal.methaneMid.a(0.75f), 2, 0.42f, 1.2f, 0.45f)
            );
            alwaysUnlocked = true;
            landCloudColor = Color.valueOf("ed654200");
            atmosphereColor = IcePal.methaneLightish;
            startSector = 33;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            tidalLock = true;
            orbitSpacing = 2f;
            totalRadius += 2.6f;
            lightSrcTo = 0.5f;
            lightDstFrom = 0.2f;
            clearSectorOnLose = true;
            allowLaunchLoadout = true;
            defaultCore = IceBlocks.coreAngry;
            iconColor = IcePal.methaneLight;
            itemWhitelist = Seq.with(RkiItems);
            enemyBuildSpeedMultiplier = 0.4f;

            allowLaunchToNumbered = false;

            updateLighting = false;

            ruleSetter = r -> {
                r.waveTeam = IceTeams.genesis;
                r.loadout = ItemStack.list(thallium, 200, sporeWood, 80);
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.fog = true;
                r.staticFog = true;
                r.lighting = true;
                r.ambientLight = Color.valueOf("000000d9");
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };

            unlockedOnLand.add(IceBlocks.coreAngry);
        }};
        rkiFake = new Planet("fake", rki, 1f, 2) {{
            generator = new RkiSurfaceGenerator();
            visible = accessible = alwaysUnlocked = IcicleVars.debug;
            landCloudColor = Color.valueOf("ed654200");
            atmosphereColor = Pal.slagOrange;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            tidalLock = true;
            orbitSpacing = 2f;
            totalRadius += 2.6f;
            lightSrcTo = 0.5f;
            lightDstFrom = 0.2f;
            clearSectorOnLose = true;
            ruleSetter = r -> {
                r.waveTeam = IceTeams.origin;
                r.loadout = ItemStack.list(Items.beryllium, 300, Items.graphite, 180, Items.silicon, 200);
            };

            meshLoader = () -> new HexMesh(this, 5);
        }};
    }

}
