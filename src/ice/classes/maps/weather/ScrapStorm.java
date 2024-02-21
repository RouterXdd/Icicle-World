package ice.classes.maps.weather;

import arc.Core;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.entities.Damage;
import mindustry.game.Team;
import mindustry.gen.WeatherState;
import mindustry.type.Weather;
import mindustry.world.Tile;

import static mindustry.Vars.*;

public class ScrapStorm extends Weather {
    public float yspeed = 3.2f, xspeed = 1.1f, density = 1100f, sizeMin = 8f, sizeMax = 32f, splashTimeScale = 22f;
    public TextureRegion[] breaks = new TextureRegion[5];
    public TextureRegion scrap;
    public ScrapStorm(String name) {
        super(name);
    }
    @Override
    public void load(){
        super.load();

        for(int i = 0; i < breaks.length; i++){
            breaks[i] = Core.atlas.find("icicle-world-breaks-" + i);
        }
        scrap = Core.atlas.find("icicle-world-metallic" + "-" + Mathf.random(2));
    }
    @Override
    public void update(WeatherState state){
        super.update(state);
    }
    @Override
    public void drawOver(WeatherState state){
        drawScrap(sizeMin, sizeMax, xspeed, yspeed, density, state.intensity, scrap);
    }

    @Override
    public void drawUnder(WeatherState state){
        drawBreaks(breaks, sizeMax, density, state.intensity, state.opacity, splashTimeScale);
    }
    public static void drawScrap(float sizeMin, float sizeMax, float xspeed, float yspeed, float density, float intensity, TextureRegion region){
        rand.setSeed(0);
        float padding = sizeMax* 0.9f;

        Tmp.r1.setCentered(Core.camera.position.x, Core.camera.position.y, Core.graphics.getWidth() / renderer.minScale(), Core.graphics.getHeight() / renderer.minScale());
        Tmp.r1.grow(padding);
        Core.camera.bounds(Tmp.r2);
        int total = (int)(Tmp.r1.area() / density * intensity);
        float alpha = Draw.getColor().a;

        for(int i = 0; i < total; i++){
            float scl = rand.random(0.5f, 1f);
            float scl2 = rand.random(0.5f, 1f);
            float size = rand.random(sizeMin, sizeMax);
            float x = (rand.random(0f, world.unitWidth()) + Time.time * xspeed * scl2);
            float y = (rand.random(0f, world.unitHeight()) - Time.time * yspeed * scl);
            float tint = rand.random(1f) * alpha + 0.2f;

            x -= Tmp.r1.x;
            y -= Tmp.r1.y;
            x = Mathf.mod(x, Tmp.r1.width);
            y = Mathf.mod(y, Tmp.r1.height);
            x += Tmp.r1.x;
            y += Tmp.r1.y;

            if(Tmp.r3.setCentered(x, y, size).overlaps(Tmp.r2)){
                Draw.alpha(tint);
                Draw.rect(region, x, y, size/2f, size/2f, Angles.angle(xspeed * scl2, - yspeed * scl));
            }
        }
    }
    public static void drawBreaks(TextureRegion[] breaks, float padding, float density, float intensity, float opacity, float timeScale){
        Tmp.r1.setCentered(Core.camera.position.x, Core.camera.position.y, Core.graphics.getWidth() / renderer.minScale(), Core.graphics.getHeight() / renderer.minScale());
        Tmp.r1.grow(padding);
        Core.camera.bounds(Tmp.r2);
        int total = (int)(Tmp.r1.area() / density * intensity) / 2;
        rand.setSeed(0);

        float t = Time.time / timeScale;

        for(int i = 0; i < total; i++){
            float offset = rand.random(0f, 1f);
            float time = t + offset;

            int pos = (int)((time));
            float life = time % 1f;
            float x = (rand.random(0f, world.unitWidth()) + pos*953);
            float y = (rand.random(0f, world.unitHeight()) - pos*453);

            x -= Tmp.r1.x;
            y -= Tmp.r1.y;
            x = Mathf.mod(x, Tmp.r1.width);
            y = Mathf.mod(y, Tmp.r1.height);
            x += Tmp.r1.x;
            y += Tmp.r1.y;

            if(Tmp.r3.setCentered(x, y, life * 4f).overlaps(Tmp.r2)){
                Tile tile = world.tileWorld(x, y);
                if(tile != null){
                    Draw.alpha(Mathf.slope(life) * opacity);
                    Draw.rect(breaks[(int)(life * (breaks.length - 1))], x, y);
                    Damage.damage(Team.derelict, x, y, 5, 6);
                }
            }
        }
    }
}
