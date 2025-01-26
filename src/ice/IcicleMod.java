package ice;

import arc.*;
import arc.graphics.Texture;
import arc.math.Mathf;
import ice.graphics.IceEmojis;
import ice.content.*;
import ice.graphics.IceShaders;
import mindustry.Vars;
import mindustry.game.EventType.*;
import mindustry.mod.*;

import static arc.Core.*;
import static mindustry.Vars.*;

public class IcicleMod extends Mod{

    public IcicleMod(){
        if (!Vars.android) {
            Events.run(FileTreeInitEvent.class, () -> Core.app.post(IceShaders::init));
        }
        Events.on(ClientLoadEvent.class, e -> {
            loadSettings();
        });
    }
    @Override
    public void init(){
        IceEmojis.load();
        Mods.LoadedMod mod = mods.locateMod("icicle-world");
        if (IcicleVars.chapter2){
            mod.meta.displayName = "Buried in Ice: Chapter 2";
            mod.meta.description = "They are waiting you...";
            mod.meta.subtitle = bundle.get("subtitle-ch2-" + Mathf.random(0, 2));
            mod.iconTexture = new Texture(mod.root.child("icon-glitch.png"));
        } else {
            mod.meta.subtitle = bundle.get("subtitle-" + Mathf.random(0, 2));
        }
    }

    @Override
    public void loadContent(){
        IceSFX.load();
        IceAttributes.load();
        IceWeather.load();
        IceLogs.load();
        IceTeams.load();
        IceStatuses.load();
        IceLiquids.load();
        IceItems.load();
        IceBullets.load();
        IceUnitTypes.load();
        IceBlocks.load();
        IceSchematics.load();
        IcePlanets.load();
        IceSectors.load();
        RkiTechTree.load();
    }
    void loadSettings(){
        ui.settings.addCategory(bundle.get("setting.icicle-world-title"), "icicle-world-settings-ui", t -> {
            t.checkPref("icicle-world-debug", false);
            t.checkPref("icicle-world-hard-mode", false);
        });
    }
}
