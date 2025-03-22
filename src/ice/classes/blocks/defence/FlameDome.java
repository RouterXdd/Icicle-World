package ice.classes.blocks.defence;

import mindustry.core.World;
import mindustry.entities.Fires;
import mindustry.gen.Fire;
import mindustry.world.Tile;
import mindustry.world.blocks.defense.turrets.PowerTurret;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;

public class FlameDome extends PowerTurret {
    public FlameDome(String name) {
        super(name);
    }
    public class FlameDomeBuild extends PowerTurretBuild {
        @Override
        protected void findTarget() {
            int tx = World.toTile(x), ty = World.toTile(y);
            Fire result = null;
            float mindst = 0f;
            int tr = (int) (range / tilesize);
            for (int x = -tr; x <= tr; x++) {
                for (int y = -tr; y <= tr; y++) {
                    Tile other = world.tile(x + tx, y + ty);
                    var fire = Fires.get(x + tx, y + ty);
                    float dst = fire == null ? 0 : dst2(fire);
                    //do not extinguish fires on other team blocks
                    if (other != null && fire != null && Fires.has(other.x, other.y) && dst <= range * range && (result == null || dst < mindst) && (other.build == null || other.team() == team)) {
                        result = fire;
                        mindst = dst;
                    }
                }
            }

            if (result != null) {
                target = result;
            }
        }
    }
}
