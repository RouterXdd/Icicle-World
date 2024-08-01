package ice.classes.type;

import arc.Core;
import ice.IcicleVars;
import mindustry.Vars;

public class ChapterKey extends LogFile{
    public ChapterKey(String name, boolean hidden) {
        super(name, hidden);
        hideDetails = false;
    }
    public void onUnlock(){
        IcicleVars.chapter2 = true;
        Vars.ui.showOkText("@mod.reloadrequired", Core.bundle.format("mod.requiresrestart"), () -> Core.app.exit());
    }
}
