package ice.classes.entities.ai;

import mindustry.entities.units.AIController;
import mindustry.gen.BuildingTetherc;

public class ShielderAI extends AIController {
    public float driveRange = 75;
    @Override
    public void updateMovement() {
        if (!(unit instanceof BuildingTetherc tether) || tether.building() == null) return;

        var build = tether.building();
        circle(build, driveRange);
        if (target != null && unit.hasWeapons()) {
            unit.lookAt(target);
            if (unit.isShooting()) {
                unit.aimLook(unit.aimX, unit.aimY);
            }
        }
    }
}
