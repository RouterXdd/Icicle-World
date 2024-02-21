package ice.content;

import arc.audio.Sound;
import mindustry.Vars;

public class IceSFX {
    public static Sound
            blowUp,
            shock,
            shockwave;
    public static void load() {
        blowUp = Vars.tree.loadSound("blow-up");
        shock = Vars.tree.loadSound("shock");
        shockwave = Vars.tree.loadSound("shockwave");
    }
}
