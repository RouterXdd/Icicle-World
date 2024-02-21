package ice.classes.blocks.production;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import arc.util.Time;
import ice.classes.blocks.environment.RoofOre;
import mindustry.graphics.Layer;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.blocks.production.Drill;

public class HighDrill extends Drill {
    public float engineSize, engineScale;
    public HighDrill(String name) {
        super(name);
        buildType = HighDrillBuild::new;
    }
    @Override
    public boolean canMine(Tile tile) {
        if(tile == null || tile.block().isStatic()) return false;
        Item drops = getRoofDrop(tile.overlay());
        return drops != null && drops.hardness <= tier && drops != blockedItem;
    }

    @Override
    protected void countOre(Tile tile) {
        returnItem = null;
        returnCount = 0;

        oreCount.clear();
        itemArray.clear();

        for(Tile other : tile.getLinkedTilesAs(this, tempTiles)){
            if(canMine(other) && (other.overlay() instanceof RoofOre)) {
                oreCount.increment(getRoofDrop(other.overlay()), 0, 1);
            }
        }

        for(Item item : oreCount.keys()){
            itemArray.add(item);
        }

        itemArray.sort((item1, item2) -> {
            int type = Boolean.compare(!item1.lowPriority, !item2.lowPriority);
            if(type != 0) return type;
            int amounts = Integer.compare(oreCount.get(item1, 0), oreCount.get(item2, 0));
            if(amounts != 0) return amounts;
            return Integer.compare(item1.id, item2.id);
        });

        if(itemArray.size == 0){
            return;
        }

        returnItem = itemArray.peek();
        returnCount = oreCount.get(itemArray.peek(), 0);
    }
    protected Item getRoofDrop(Block b) {
        return b instanceof RoofOre u ? u.item : null;
    }
    public class HighDrillBuild extends DrillBuild {
        public void draw(){
            Draw.rect(region, x, y);
            Draw.z(Layer.blockCracks);
            drawDefaultCracks();

            Draw.z(Layer.blockAfterCracks);
                Draw.color(team.color);
                Draw.alpha(warmup);
                Fill.circle(
                        x,
                        y,
                        (engineSize + Mathf.absin(Time.time, 2f, engineSize / 4f)) * engineScale
                );
                Draw.color(Color.white);
                Draw.alpha(warmup);
                Fill.circle(
                        x,
                        y,
                        (engineSize + Mathf.absin(Time.time, 2f, engineSize / 4f)) / 2f  * engineScale
                );
        }
    }
}
