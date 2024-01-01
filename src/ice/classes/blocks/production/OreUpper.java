package ice.classes.blocks.production;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import ice.classes.blocks.environment.UndergroundOre;
import ice.content.IceBlocks;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.Block;
import mindustry.world.Tile;
import mindustry.world.meta.BlockGroup;

import static mindustry.Vars.tilesize;
import static mindustry.Vars.world;
import static mindustry.content.Blocks.air;

public class OreUpper extends Block {

    public float range = 16;
    public float mineSpeed = 400;
    public Color workColor = Color.white;
    public Color endColor = Pal.remove;
    public Effect drillEffect = Fx.mineBig;
    TextureRegion indicatorRegion;
    public OreUpper(String name) {
        super(name);
        update = true;
        solid = true;
        hasPower = true;
    }
    @Override
    public void load(){
        super.load();
        indicatorRegion = Core.atlas.find(this.name + "-indicator");
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("drilling", (OreUpperBuild e) -> new Bar("bar.loadprogress", Pal.ammo, e::fraction));
    }
    public class OreUpperBuild extends Building {
        float progress;
        boolean drilled = false;
        public float fraction(){
            return progress;
        }
        public void draw(){
            super.draw();
            if (drilled){
                Draw.color(endColor);
            }else{
                Draw.color(workColor);
            }
            Draw.rect(indicatorRegion, x, y);
        }
        @Override
        public void updateTile(){
            progress += edelta() / mineSpeed;
            if (efficiency > 0 && !drilled){
                if (progress > 1){
                    excavate(range);
                    progress = 0;
                    drilled = true;
                    drillEffect.at(x, y);
                }
            }
        }
        public void excavate(float range) {
            tile.circle((int) (range / tilesize), (ore) -> {
                if (ore != null && ore.overlay() != null && ore.overlay() instanceof UndergroundOre u) {
                    ore.setOverlay(u.outputOre);
                }
            });
        }
    }
}
