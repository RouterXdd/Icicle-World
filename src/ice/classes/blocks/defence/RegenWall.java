package ice.classes.blocks.defence;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.world.blocks.defense.Wall;

public class RegenWall extends Wall {
    public float regenSpeed = 0.25f;
    public Effect regenEffect = Fx.none;
    public float regenChance = 0.1f;
    public RegenWall(String name) {
        super(name);
        update = true;
        suppressable = true;
    }
    public class RegenWallBuild extends WallBuild {
        public void update(){
            boolean canHeal = !checkSuppression();
            if (damaged() && canHeal){
                if (Mathf.chance(regenChance)) regenEffect.at(x + Mathf.range(size * Vars.tilesize /2f - 1f),y + Mathf.range(size * Vars.tilesize /2f - 1f));
                heal(regenSpeed * Time.delta);
            }
        }
    }
}
