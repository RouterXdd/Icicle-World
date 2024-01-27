package ice.content;

import ice.graphics.IcePal;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

public class IceStatuses {
    public static StatusEffect inSoot, positiveCharge, negativeCharge, rusting, radiation;
    public static void load(){
        inSoot = new StatusEffect("in-soot"){{
            color = IcePal.sootColor;
            speedMultiplier = 0.9f;
            healthMultiplier = 0.9f;
        }};
        positiveCharge = new StatusEffect("positive-charge"){{
            color = IcePal.positiveStatusCol;
            reloadMultiplier = 0.5f;
            dragMultiplier = 0.5f;
            buildSpeedMultiplier = 1.5f;
            damageMultiplier = 1.5f;
        }};
        negativeCharge = new StatusEffect("negative-charge"){{
            color = IcePal.negativeStatusCol;
            reloadMultiplier = 1.5f;
            dragMultiplier = 1.5f;
            buildSpeedMultiplier = 0.5f;
            damageMultiplier = 0.5f;
        }};
        rusting = new StatusEffect("rusting"){{
            color = IcePal.wasteLight;
            speedMultiplier = reloadMultiplier = 0.7f;
            damage = 0.05f;
        }};
        radiation = new StatusEffect("radiation"){{
            color = IcePal.poloniumLight;
            speedMultiplier = 0.8f;
            damage = 0.25f;
        }};
    }
}
