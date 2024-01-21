package ice.classes.blocks.distribution;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.world.blocks.distribution.*;

public class RegenConveyor extends Conveyor {
    private static final float itemSpace = 0.5f;
    public float regenAmount = 0.2f;
    public RegenConveyor(String name) {
        super(name);
        itemCapacity = 4;
    }
    public class RegenConveyorBuild extends ConveyorBuild {
        @Override
        public void updateTile(){
            super.updateTile();
            if (damaged()){
                heal(regenAmount * Time.delta);
            }
        }
    }
}
