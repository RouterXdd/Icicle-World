package ice.classes.entities.types;

import arc.Core;
import mindustry.entities.abilities.RegenAbility;
import mindustry.gen.ElevationMoveUnit;
import mindustry.graphics.Pal;
import mindustry.type.unit.ErekirUnitType;

public class PolygonUnitType extends ErekirUnitType {
    public boolean tank;
    public float regenTime;
    public PolygonUnitType(String name) {
        super(name);
        outlineColor = Pal.darkOutline;
        tank = true;
        constructor = ElevationMoveUnit::create;
        drawCell = false;
        regenTime = 140;
        abilities.add(new RegenAbility(){{
            percentAmount = 1f / (regenTime * 60f) * 100f;
        }});
    }
    @Override
    public void load() {
        super.load();
        if (tank) {
            region = Core.atlas.find("icicle-world-tank");
        }
        baseRegion = Core.atlas.find("icicle-world-none");
    }
}
