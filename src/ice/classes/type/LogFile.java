package ice.classes.type;

import arc.Core;
import mindustry.ctype.*;

public class LogFile extends UnlockableContent {
    public boolean hidden;

    public LogFile(String name, boolean hidden) {
        super(name);
        this.hidden = hidden;
        this.localizedName = Core.bundle.get("logs." + this.name + ".name", this.name);
        this.description = Core.bundle.getOrNull("logs." + this.name + ".description");
        this.details = Core.bundle.getOrNull("logs." + this.name + ".details");
    }


    @Override
    public ContentType getContentType() {
        return ContentType.ammo_UNUSED;
    }
    @Override
    public boolean isHidden(){
        return hidden;
    }
}
