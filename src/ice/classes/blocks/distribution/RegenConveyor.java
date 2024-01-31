package ice.classes.blocks.distribution;

import arc.func.Boolf;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.struct.Seq;
import arc.util.Time;
import ice.content.IceBlocks;
import mindustry.content.Blocks;
import mindustry.entities.units.BuildPlan;
import mindustry.input.Placement;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;

public class RegenConveyor extends Conveyor {
    private static final float itemSpace = 0.5f;
    public float regenAmount = 0.2f;
    public RegenConveyor(String name) {
        super(name);
        itemCapacity = 4;
        junctionReplacement = IceBlocks.thalliumJunction;
        bridgeReplacement = IceBlocks.thalliumTunnel;
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
