package ice.classes.blocks.environment;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import mindustry.Vars;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.OverlayFloor;

public class RoofOre extends OverlayFloor {
    public Item item;
    public RoofOre(String name) {
        super(name);
        useColor = false;
        playerUnmineable = true;
        variants = 2;
    }
    @Override
    public void drawBase(Tile tile) {
        float l = Draw.z();
        Draw.z(Layer.light);

        super.drawBase(tile);

        Draw.z(l);
    }
    @Override
    public void load() {
        super.load();

        if (itemDrop != null) {
            item = itemDrop;
            itemDrop = null;
        }
    }

    @Override
    public String getDisplayName(Tile tile) {
        return null;
    }
}
