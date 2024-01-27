package ice;

import arc.*;
import arc.util.*;
import ice.classes.graphic.IceEmojis;
import ice.content.*;
import ice.graphics.IceShaders;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static arc.Core.*;
import static mindustry.Vars.ui;

public class IcicleMod extends Mod{

    public IcicleMod(){
        Events.run(FileTreeInitEvent.class, () -> Core.app.post(IceShaders::init));
        Events.on(ClientLoadEvent.class, e -> {
            loadSettings();
        });
    }
    @Override
    public void init(){
        IceEmojis.load();
    }

    @Override
    public void loadContent(){
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
        IceLoadouts.load();
        IcePlanets.load();
        IceSectors.load();
        RkiTechTree.load();
    }
    void loadSettings(){
        ui.settings.addCategory(bundle.get("setting.icicle-world-title"), "icicle-world-settings-ui", t -> {
            t.checkPref("icicle-world-debug", false);
        });
    }
}
