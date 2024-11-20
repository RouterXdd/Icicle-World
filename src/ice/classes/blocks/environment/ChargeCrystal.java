package ice.classes.blocks.environment;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import ice.IcicleVars;
import ice.graphics.IcePal;
import mindustry.entities.Units;
import mindustry.game.Team;
import mindustry.type.StatusEffect;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.TallBlock;
//idk how make this work

public class ChargeCrystal extends TallBlock implements EnvUpdate{
    public float range = 32;
    public StatusEffect status;
    public float statusDuration = 10 * 60;
    public ChargeCrystal(String name) {
        super(name);
    }
    public void updateTile(Tile tile){
        Units.nearby(null, tile.x, tile.y, range, other -> {
            other.apply(status, statusDuration);
        });
    }
}
