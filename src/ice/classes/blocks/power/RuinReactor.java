package ice.classes.blocks.power;

import arc.math.Mathf;
import ice.content.IceBlocks;
import ice.content.IceTeams;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.game.Team;
import mindustry.world.Tile;
import mindustry.world.blocks.power.NuclearReactor;

import static mindustry.Vars.world;

public class RuinReactor extends NuclearReactor {
    public int minPos = -6;
    public int maxPos = 6;
    public int amount = 37;

    public RuinReactor(String name) {
        super(name);
    }
    public class RuinReactorBuild extends NuclearReactorBuild {
        @Override
        public void createExplosion(){
            super.createExplosion();
                for(int i = 0; i < amount; i++) {
                    int posX = Mathf.random(minPos, maxPos);
                    int posY = Mathf.random(minPos, maxPos);
                    Tile tile = world.tileWorld(x + posX * 8, y + posY * 8);

                    if (tile != null && tile.block() == Blocks.air) tile.setBlock(IceBlocks.ruinBlock, Team.neoplastic);
                }
        }
    }
}
