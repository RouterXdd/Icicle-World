package ice.classes.blocks.distribution;

import arc.func.Boolf;
import arc.math.Mathf;
import arc.math.geom.Geometry;
import arc.math.geom.Point2;
import arc.struct.Seq;
import arc.util.Time;
import ice.classes.type.meta.IcePlacement;
import ice.content.IceBlocks;
import mindustry.content.Blocks;
import mindustry.entities.units.BuildPlan;
import mindustry.input.Placement;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;

public class RegenConveyor extends Conveyor {
    public float regenAmount = 0.2f;
    public RegenConveyor(String name) {
        super(name);
        itemCapacity = 4;
        junctionReplacement = IceBlocks.thalliumJunction;
        bridgeReplacement = IceBlocks.thalliumTunnel;
    }
    @Override
    public void init(){
        super.init();

        if(junctionReplacement == null) junctionReplacement = IceBlocks.thalliumJunction;
        if(bridgeReplacement == null || !(bridgeReplacement instanceof DuctBridge)) bridgeReplacement = IceBlocks.thalliumTunnel;
    }
    @Override
    public void handlePlacementLine(Seq<BuildPlan> plans){
        if(bridgeReplacement == null) return;

        IcePlacement.calculateDirectBridges(plans, (DuctBridge)bridgeReplacement);
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
