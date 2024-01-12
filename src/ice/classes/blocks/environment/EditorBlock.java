package ice.classes.blocks.environment;

import mindustry.world.Block;
import mindustry.world.Tile;

import static mindustry.Vars.state;

public class EditorBlock extends Block {
    public EditorBlock(String name) {
        super(name);
        health = 100000000;
        underBullets = false;
        update = true;
        solid = false;
        replaceable = false;
        targetable = false;
        destructible = false;
        drawTeamOverlay = false;
    }
    @Override
    public boolean canBreak(Tile tile){
        return state.rules.editor || state.rules.infiniteResources;
    }
}