package ice.content;

import arc.audio.Sound;
import mindustry.Vars;

public class IceSFX {
    public static Sound
            blowUp, shock, shockwave, throwSFX, purplesDeath, radCharge, radArtillery, shootDecanter, shootStem, shootFundament, shootGroundwork, shootBolt, methBlast, shootGunExecutioner, shootSkimmer, shootCremator, shootDischarge;
    public static void load() {
        blowUp = Vars.tree.loadSound("blow-up");
        shock = Vars.tree.loadSound("shock");
        shockwave = Vars.tree.loadSound("shockwave");
        throwSFX = Vars.tree.loadSound("throw");
        purplesDeath = Vars.tree.loadSound("purples-death");
        radCharge = Vars.tree.loadSound("rad-charge");
        radArtillery = Vars.tree.loadSound("rad-artillery");
        shootDecanter = Vars.tree.loadSound("shoot-decanter");
        shootStem = Vars.tree.loadSound("shoot-stem");
        shootFundament = Vars.tree.loadSound("shoot-fundament");
        shootGroundwork = Vars.tree.loadSound("shoot-groundwork");
        shootBolt = Vars.tree.loadSound("shoot-bolt");
        methBlast = Vars.tree.loadSound("meth-blast");
        shootGunExecutioner = Vars.tree.loadSound("shoot-gun-executioner");
        shootSkimmer = Vars.tree.loadSound("shoot-skimmer");
        shootCremator = Vars.tree.loadSound("shoot-cremator");
        shootDischarge = Vars.tree.loadSound("shoot-discharge");
    }
}
