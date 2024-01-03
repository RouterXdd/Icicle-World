package ice.content;

import mindustry.type.SectorPreset;

public class IceSectors {
    public static SectorPreset
    fallPoint
            ;
    public static void load(){
        fallPoint = new SectorPreset("fall-point", IcePlanets.rki, 33){{
            captureWave = 15;
            startWaveTimeMultiplier = 2.5f;
            alwaysUnlocked = true;
        }};
    }
}
