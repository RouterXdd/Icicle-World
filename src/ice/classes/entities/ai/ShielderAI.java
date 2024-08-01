package ice.classes.entities.ai;

import arc.math.Mathf;
import arc.util.Time;
import ice.IcicleVars;
import mindustry.entities.units.AIController;
import mindustry.gen.BuildingTetherc;

import static ice.IcicleVars.hardMode;

public class ShielderAI extends AIController {
    public float driveRange = 75;
    @Override
    public void updateMovement() {
        if (!(unit instanceof BuildingTetherc tether) || tether.building() == null) return;

        var build = tether.building();
        if (!hardMode) {
            circle(build, driveRange);
        } else {
            circle(build, driveRange - Mathf.sinDeg(Time.time * 4f));

        }
        if (target != null && unit.hasWeapons()) {
            unit.lookAt(target);
            if (unit.isShooting()) {
                unit.aimLook(unit.aimX, unit.aimY);
            }
        }
    }
}
