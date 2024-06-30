package ice.content;

import arc.math.Mathf;
import ice.IcicleVars;
import ice.graphics.IcePal;
import mindustry.entities.Lightning;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.type.StatusEffect;

public class IceStatuses {
    public static StatusEffect inSoot, positiveCharge, negativeCharge, rusting, radiation, flash, enmity;
    public static void load(){
        inSoot = new StatusEffect("in-soot"){{
            color = IcePal.sootColor;
            speedMultiplier = IcicleVars.hardMode ? 0.65f : 0.9f;
            healthMultiplier = IcicleVars.hardMode ? 0.65f : 0.9f;
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
        flash = new StatusEffect("flash"){{
            color = IcePal.cerymecLight;
            speedMultiplier = 0.7f;
            reloadMultiplier = 0.7f;
        }};
        enmity = new StatusEffect("enmity"){{
            color = IcePal.enmitiumLightish;
            speedMultiplier = IcicleVars.hardMode ? 1.5f : 2f;
            healthMultiplier = IcicleVars.hardMode ? 1.25f : 1.5f;
            reloadMultiplier = IcicleVars.hardMode ? 1.5f : 2f;
        }};
    }
}
