package ice.classes.blocks.production;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.Mathf;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import ice.classes.blocks.environment.UndergroundOre;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.graphics.*;
import mindustry.logic.Ranged;
import mindustry.ui.Styles;
import mindustry.world.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;
import static mindustry.content.Blocks.air;
//HPL and FOS moment
public class OreFinder extends Block {
    public int range = 20 * 8;
    public float radarSpeed = 1f;
    public float radarCone = 28f;
    public float circleAlpha = 1f;
    public float coneAlpha = 0.4f;
    public float moveCircleAlpha = 0.4f;


    public OreFinder(String name) {
        super(name);
        solid = true;
        update = true;
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.range, range / tilesize, StatUnit.blocks);
        stats.add(Stat.speed, radarSpeed, StatUnit.blocks);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);
        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Pal.accent);
    }

    public class OreFinderBuild extends Building implements Ranged {
        public float start;

        @Override
        public float range() {
            return range * potentialEfficiency;
        }


        public float rot() {
            return (timeRad() * radarSpeed) % 360f;
        }

        public float timeRad() {
            return Time.time - start;
        }

        @Override
        public void draw() {
            super.draw();
            if (canConsume()) {
                Draw.z(Layer.light);
                Draw.alpha(0.6f);
                Lines.stroke(1.4f, team.color);

                Draw.alpha(moveCircleAlpha - (timeRad() % 120f) / 120f);
                Lines.circle(x, y, (timeRad() % 120f) / 120f * range());

                Draw.alpha(coneAlpha);
                Fill.arc(x, y, range(), radarCone / 360f, rot());
                Fill.arc(x, y, range(), radarCone / 360f, rot() + 180);

                Draw.alpha(circleAlpha);
                Lines.circle(x, y, range());

                Draw.reset();
                Draw.z(Layer.blockUnder);
                locateOres(range());
            }
        }

        @Override
        public void drawSelect() {
            Drawf.dashCircle(x, y, range, team.color);
        }

        public void locateOres(float radius) {
            tile.circle((int) (radius / tilesize), (ore) -> {
                if (ore != null && ore.overlay() != null && ore.overlay() instanceof UndergroundOre u && ore.block() == air) {
                    var angle = Mathf.angle(ore.x - tile.x, ore.y - tile.y);
                    var c1 = rot();
                    var c2 = rot() + radarCone;
                    if (c2 >= 360f && angle < 180f) {
                        angle += 360;
                    }
                    var angle2 = Mathf.angle(ore.x - tile.x, ore.y - tile.y);
                    var c12 = rot() + 180;
                    var c22 = rot() + radarCone;
                    if (c22 >= 360f && angle < 180f) {
                        angle2 += 360;
                    }

                    if (angle2 > c22 || angle2 < c12 || angle > c22 || angle2 < c1) {
                        u.needDrawBase = true;
                        u.drawBase(ore);
                        u.needDrawBase = false;
                    };
                }
            });
        }
    }
}
