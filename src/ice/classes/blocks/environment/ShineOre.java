package ice.classes.blocks.environment;

import arc.math.Mathf;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.OreBlock;

public class ShineOre extends OreBlock {
    public Effect effect = Fx.none;
    public float effectChance = 0.0001f;
    public ShineOre(Item ore) {
        super(ore);
    }
    @Override
    public boolean updateRender(Tile tile) {
        return true;
    }

    @Override
    public void renderUpdate(UpdateRenderState state) {
        if(Mathf.chanceDelta(effectChance) && state.tile.block() == Blocks.air) {
            effect.at(state.tile.worldx(), state.tile.worldy());
        }
    }

}
