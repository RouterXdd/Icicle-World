package ice.classes.blocks.environment;

import arc.math.Mathf;
import arc.util.Time;
import ice.classes.blocks.production.OreUpper;
import ice.content.IceBlocks;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.*;

import static mindustry.Vars.*;

public class WorldDuster extends EditorBlock {
    public int range = 2;
    public int amount = 1;
    public float reload = 430f;
    public Effect fallEffect = Fx.hitFuse;
    public Effect placeEffect = Fx.smeltsmoke;
    public WorldDuster(String name) {
        super(name);
        health = 100000000;
        update = true;
        solid = true;
        buildType = WorldDusterBuild::new;
        replaceable = false;
        targetable = false;
        destructible = false;
        drawTeamOverlay = false;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashSquare(Vars.player.team().color, x*8, y*8, range * 16 + 8);
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("crushing", (WorldDusterBuild e) -> new Bar("bar.loadprogress", Pal.ammo, e::fraction));
    }
    public class WorldDusterBuild extends Building {
        public float reloadTime;
        public float fraction(){
            return reloadTime;
        }
        @Override
        public void updateTile() {
            if (reloadTime > 1 && canConsume()) {
                place();
                consume();
                reloadTime = 0;
            }
            reloadTime += edelta() / reload;
        }
        public void place() {
            for(int i = 0; i < amount; i++) {
                int posX = Mathf.random(-range, range);
                int posY = Mathf.random(-range, range);
                Tile tile = world.tileWorld(x + posX * 8, y + posY * 8);
                    if (tile != null && !tile.floor().isLiquid && tile.floor().placeableOn) {
                        placeEffect.at(x + posX * 8, y + posY * 8);
                        tile.setOverlay(IceBlocks.oreDust);
                    }
                    if (tile != null && tile.floor().isLiquid) {
                        fallEffect.at(x + posX * 8, y + posY * 8);
                    }
                }
            }
        }
    }
