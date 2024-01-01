package ice.classes.blocks.environment;

import arc.math.Mathf;
import arc.util.Time;
import ice.content.IceBlocks;
import mindustry.Vars;
import mindustry.content.*;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.*;

import static mindustry.Vars.*;

public class WorldDuster extends Block {
    public int displayRange = 16;
    public int minPos = -2;
    public int maxPos = 2;
    public int amount = 1;
    public float reload = 430f;
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
    public boolean canBreak(Tile tile){
        return state.rules.editor || state.rules.infiniteResources;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashSquare(Vars.player.team().color, x*8, y*8, displayRange * 2 + 8);
    }
    public class WorldDusterBuild extends Building {
        public float reloadTime = reload;

        @Override
        public void updateTile() {
            if (reloadTime <= 0) {
                place();
                reloadTime = reload;
            }
            reloadTime -= Time.delta;
        }
        public void place() {
            for(int i = 0; i < amount; i++) {
                int posX = Mathf.random(minPos, maxPos);
                int posY = Mathf.random(minPos, maxPos);
                Tile tile = world.tileWorld(x + posX * 8, y + posY * 8);

                if (tile.floor() != Blocks.air) Fx.smeltsmoke.at(x + posX * 8, y + posY * 8);
                tile.setOverlay(IceBlocks.oreDust);
            }
        }
    }
}
