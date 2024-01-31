package ice.content;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import mindustry.gen.Icon;
import mindustry.type.SectorPreset;

public class IceSectors {
    public static SectorPreset
    fallPoint, primaryBase, methanePurficate, purpleFortress
            ;
    public static void load(){
        fallPoint = new SectorPreset("fall-point", IcePlanets.rki, 33){{
            captureWave = 15;
            startWaveTimeMultiplier = 2.5f;
            alwaysUnlocked = true;
        }@Override
        public void loadIcon(){
            if(Icon.terrain != null){
                uiIcon = fullIcon = Core.atlas.find("icicle-world-terrain-story");
            }
        }};
        primaryBase = new SectorPreset("primary-base", IcePlanets.rki, 58){{
            difficulty = 2;
        }};
        methanePurficate = new SectorPreset("methane-purficate", IcePlanets.rki, 83){{
            difficulty = 4;
        }};
        purpleFortress = new SectorPreset("purple-fortress", IcePlanets.rki, 223){{
            captureWave = 30;
            difficulty = 6;
        }
            @Override
            public void loadIcon(){
                if(Icon.terrain != null){
                    uiIcon = fullIcon = Core.atlas.find("icicle-world-terrain-story");
                }
            }};
    }
}
