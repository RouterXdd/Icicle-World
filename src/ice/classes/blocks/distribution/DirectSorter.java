package ice.classes.blocks.distribution;

import arc.Core;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.ui.Styles;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class DirectSorter extends Block {
    public float speed = 5f;

    public TextureRegion topRegion, selectRegion, dirRegion;
    public DirectSorter(String name) {
        super(name);

        group = BlockGroup.transportation;
        update = true;
        solid = false;
        hasItems = true;
        unloadable = false;
        itemCapacity = 1;
        noUpdateDisabled = true;
        configurable = true;
        saveConfig = true;
        rotate = true;
        clearOnDoubleTap = true;
        underBullets = true;
        priority = TargetPriority.transport;
        envEnabled = Env.space | Env.terrestrial | Env.underwater;

        config(Item.class, (DirectSorterBuild tile, Item item) -> tile.sortItem = item);
        configClear((DirectSorterBuild tile) -> tile.sortItem = null);
    }
    @Override
    public void load(){
        super.load();
        topRegion = Core.atlas.find(this.name + "-top");
        selectRegion = Core.atlas.find(this.name + "-select");
        dirRegion = Core.atlas.find(this.name + "-dir");
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.itemsMoved, 60f / speed, StatUnit.itemsSecond);
    }
    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{region, topRegion, dirRegion};
    }
    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list){
        Draw.rect(region, plan.drawx(), plan.drawy());
        Draw.rect(topRegion, plan.drawx(), plan.drawy(), plan.rotation * 90);
    }

    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    public class DirectSorterBuild extends Building {
        public @Nullable Item sortItem;
        boolean opposite = false;
        public float progress;
        public @Nullable Item current;

        @Override
        public void draw(){
            Draw.rect(region, x, y);
            if(sortItem != null){
                Draw.color(sortItem.color);
                Draw.rect(selectRegion, x, y);
                Draw.color();
            }
            Draw.rect(topRegion, x, y, rotdeg());
            if (!opposite){
                Draw.rect(dirRegion, x, y, rotdeg());
            } else {
                Draw.rect(dirRegion, x, y, rotdeg() + 180);
            }
        }

        @Override
        public void updateTile(){
            progress += edelta() / speed * 2f;

            if(current != null){
                if(progress >= (1f - 1f/speed)){
                    var target = target();
                    if(target != null){
                        target.handleItem(this, current);
                        items.remove(current, 1);
                        current = null;
                        progress %= (1f - 1f/speed);
                    }
                }
            }else{
                progress = 0;
            }

            if(current == null && items.total() > 0){
                current = items.first();
            }
        }

        @Override
        public void buildConfiguration(Table table){
            ItemSelection.buildTable(DirectSorter.this, table, content.items(), () -> sortItem, this::configure);
            table.button(Icon.flipX, Styles.clearTogglei, () -> {
                opposite = !opposite;
                deselect();
            }).size(30).left();
        }
        public void drawItemSelection(UnlockableContent selection){
            if(selection != null && Core.settings.getBool("displayselection", true)){
                TextureRegion region = selection.fullIcon;
                Draw.rect(region, x, y + block.size * tilesize / 2f + 4, 8f * region.ratio(), 8f);
            }
        }

        @Nullable
        public Building target(){
            if(current == null) return null;

            Building l = left(), r = right(), f = front();
            if((sortItem != null && (current == sortItem)) && f != null && f.team == team && f.acceptItem(this, current)){
                return f;
            }
            if (!opposite) {
                if (!(sortItem != null && (current == sortItem)) && l != null && l.team == team && l.acceptItem(this, current)) {
                    return l;
                }
            } else {
                if (!(sortItem != null && (current == sortItem)) && r != null && r.team == team && r.acceptItem(this, current)) {
                    return r;
                }
            }
            return null;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return current == null && items.total() == 0 &&
                    (Edges.getFacingEdge(source.tile(), tile).relativeTo(tile) == rotation);
        }

        @Override
        public int removeStack(Item item, int amount){
            int removed = super.removeStack(item, amount);
            if(item == current) current = null;
            return removed;
        }

        @Override
        public void handleStack(Item item, int amount, Teamc source){
            super.handleStack(item, amount, source);
            current = item;
        }

        @Override
        public void handleItem(Building source, Item item){
            current = item;
            progress = -1f;
            items.add(item, 1);
            noSleep();
        }

        @Override
        public Item config(){
            return sortItem;
        }

        @Override
        public byte version(){
            return 1;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(opposite);
            write.s(sortItem == null ? -1 : sortItem.id);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            opposite = read.bool();
            if(revision >= 1){
                sortItem = content.item(read.s());
            }
        }
    }
}
