package ice.content;

import ice.classes.type.IcicleSectorPreset;
import mindustry.type.SectorPreset;

import static ice.content.IceUnitTypes.*;

public class IceSectors {
    public static SectorPreset
            //main sectors
    fallPoint, primaryBase, methanePurification, purpleFortress, trinityColumn, resurgent, paths, brokenComplex, flameOcean, aggressiveTactic, execution, finality,
    //optional sectors
    ignintion, cathedral
            ;
    public static void load(){
        fallPoint = new IcicleSectorPreset("fall-point", IcePlanets.rki, 33){{
            captureWave = 15;
            startWaveTimeMultiplier = 2.5f;
            story = true;
            alwaysUnlocked = true;
            enemies.addAll(vessel, stem);
            guardians.addAll(basis);
        }};
        primaryBase = new IcicleSectorPreset("primary-base", IcePlanets.rki, 58){{
            difficulty = 2;
            enemies.addAll(stem);
        }};
        ignintion = new IcicleSectorPreset("ignition", IcePlanets.rki, 203){{
            difficulty = 4;
            captureWave = 20;
            enemies.addAll(vessel, stem, basis);
        }};
        methanePurification = new IcicleSectorPreset("methane-purification", IcePlanets.rki, 83){{
            difficulty = 4;
            enemies.addAll(quant, stem);
        }};
        purpleFortress = new IcicleSectorPreset("purple-fortress", IcePlanets.rki, 223){{
            captureWave = 30;
            difficulty = 6;
            story = true;
            enemies.addAll(vessel, stem, basis, ewer);
            guardians.addAll(unknown);
        }};
        trinityColumn = new IcicleSectorPreset("trinity-column", IcePlanets.rki, 238){{
            difficulty = 6;
            enemies.addAll(vessel, stem, ewer);
        }};
        resurgent = new IcicleSectorPreset("resurgent", IcePlanets.rki, 25){{
            captureWave = 35;
            difficulty = 6;
            enemies.addAll(vessel, stem, quant, basis, xylem, fundament);
        }};
        paths = new IcicleSectorPreset("paths", IcePlanets.rki, 108){{
            captureWave = 50;
            difficulty = 7;
            startWaveTimeMultiplier = 2.5f;
            story = true;
            enemies.addAll(vessel, stem, basis, quant, ewer, xylem, fundament, chronon);
            guardians.addAll(unknown);
        }};
        brokenComplex = new IcicleSectorPreset("broken-complex", IcePlanets.rki, 235){{
            captureWave = 35;
            difficulty = 6;
            enemies.addAll(vessel, stem, basis, quant, ewer, xylem, fundament, chronon);
        }};
        flameOcean = new IcicleSectorPreset("flame-ocean", IcePlanets.rki, 239){{
            difficulty = 7;
            enemies.addAll(vessel, quant, chronon, sin, zen);
        }};
        aggressiveTactic = new IcicleSectorPreset("aggressive-tactic", IcePlanets.rki, 186){{
            difficulty = 8;
            captureWave = 45;
            enemies.addAll(vessel, stem, basis, quant, ewer, xylem, fundament, chronon);
            guardians.addAll(unknown);
        }};
    }
}
