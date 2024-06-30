package ice.classes.type;

import arc.Core;
import arc.struct.ObjectSet;
import mindustry.gen.Icon;
import mindustry.type.*;

public class IcicleSectorPreset extends SectorPreset {
    public boolean story;
    public ObjectSet<UnitType> enemies = new ObjectSet<>();
    public ObjectSet<UnitType> guardians = new ObjectSet<>();

    public IcicleSectorPreset(String name, Planet planet, int sector) {
        super(name, planet, sector);
        story = false;
    }
    public void loadIcon() {
        if (Icon.terrain != null && !story) {
            this.uiIcon = this.fullIcon = Icon.terrain.getRegion();
        } else if (isLastSector){
            uiIcon = fullIcon = Core.atlas.find("icicle-world-terrain-last-sector");
        } else {
            uiIcon = fullIcon = Core.atlas.find("icicle-world-terrain-story");
        }

    }
    public void setStats(){
        if (captureWave > 0){
            stats.add(IceStats.waves, captureWave);
        }
        if(enemies.size > 0){
            var units = enemies.toSeq().sort();
            stats.add(IceStats.enemies, StatTypes.unitTypes(units));
        }
        if(guardians.size > 0){
            var bosses = guardians.toSeq().sort();
            stats.add(IceStats.guardians, StatTypes.unitTypes(bosses));
        }
    }
}
