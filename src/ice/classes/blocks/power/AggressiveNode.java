package ice.classes.blocks.power;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.Lightning;
import mindustry.entities.Units;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.power.PowerNode;

import static mindustry.Vars.tilesize;

public class AggressiveNode extends PowerNode {
    public float reload = 14;
    public int range = 36;
    public int lengthLight = range / 3;
    public float damageLight = 3.5f;

    public AggressiveNode(String name) {
        super(name);
        update = true;
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.placing);
    }
    public class AggressiveNodeBuild extends PowerNodeBuild {
        public float reloadTime = reload;
        @Override
        public void updateTile(){
            super.updateTile();
            Units.nearbyEnemies(team, x, y, range, u -> {
                reloadTime -= Time.delta;
            });
            if (reloadTime <= 0 && canConsume()){
                Lightning.create(this.team,team.color,damageLight ,this.x, this.y, Mathf.random(0, 360), lengthLight);
                reloadTime = reload;
            }
        }
        @Override
        public void drawSelect(){
            Drawf.dashCircle(x, y, range, Pal.placing);
        }
    }
}
