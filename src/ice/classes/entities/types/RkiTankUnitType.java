package ice.classes.entities.types;

import mindustry.world.meta.Env;

public class RkiTankUnitType extends RkiUnitType{
    public RkiTankUnitType(String name) {
        super(name);
        squareShape = true;
        omniMovement = false;
        rotateMoveFirst = true;
        rotateSpeed = 1.3f;
        envDisabled = Env.none;
        speed = 0.8f;
    }
}
