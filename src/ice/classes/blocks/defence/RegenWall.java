package ice.classes.blocks.defence;

import mindustry.world.blocks.defense.Wall;

public class RegenWall extends Wall {
    public float regenSpeed = 0.25f;
    public RegenWall(String name) {
        super(name);
        update = true;
        suppressable = true;
    }
    public class RegenWallBuild extends WallBuild {
        public void update(){
            boolean canHeal = !checkSuppression();
            if (damaged() && canHeal){
                heal(regenSpeed);
            }
        }
    }
}
