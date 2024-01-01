package ice.classes.blocks.environment;

import arc.Core;
import arc.graphics.g2d.*;
import arc.util.Nullable;
import ice.content.IceBlocks;
import mindustry.Vars;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.*;
//HPL moment
public class UndergroundOre extends OverlayFloor {
    public boolean needDrawBase = false;
    public @Nullable Block outputOre = IceBlocks.orePolonium;

    public UndergroundOre(String name) {
        super(name);

        useColor = false;
        playerUnmineable = true;
        variants = 0;
    }
    @Override
    public void drawBase(Tile tile) {
        if (needDrawBase || Vars.state.isEditor()) {
            float l = Draw.z();
            Draw.z(Layer.light);

            super.drawBase(tile);

            Draw.z(l);
        }
    }

    @Override
    public String getDisplayName(Tile tile) {
        return null;
    }
}
