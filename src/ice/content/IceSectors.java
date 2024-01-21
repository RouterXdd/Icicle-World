package ice.content;

import mindustry.type.SectorPreset;

public class IceSectors {
    public static SectorPreset
    fallPoint, primaryBase, methanePurficate
            ;
    public static void load(){
        fallPoint = new SectorPreset("fall-point", IcePlanets.rki, 33){{
            captureWave = 15;
            startWaveTimeMultiplier = 2.5f;
            alwaysUnlocked = true;
        }};
        primaryBase = new SectorPreset("primary-base", IcePlanets.rki, 58){{
            difficulty = 2;
        }};
        methanePurficate = new SectorPreset("methane-purficate", IcePlanets.rki, 83){{
            difficulty = 4;
        }};
    }
}
