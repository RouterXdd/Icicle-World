package ice.classes.blocks.environment;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.TallBlock;

public class StaticTallBlock extends TallBlock {
    public StaticTallBlock(String name) {
        super(name);
    }
    @Override
    public void drawBase(Tile tile){
        Draw.color();

        Draw.z(layer);
        Draw.rect(variants > 0 ? variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))] : region,
                tile.worldx(), tile.worldy(), 0);
    }
}
