package ice.content;

import arc.util.Time;
import ice.classes.maps.weather.ScrapStorm;
import mindustry.gen.Sounds;
import mindustry.graphics.Pal;
import mindustry.type.Weather;
import mindustry.world.meta.Attribute;

//Useless weathers, because we are playing in underground
public class IceWeather {
    public static Weather
            metallicTornado;

    public static void load(){
        metallicTornado = new ScrapStorm("metallic-tornado"){{
            attrs.set(Attribute.light, -0.1f);
            opacityMultiplier = 0.35f;
            sound = Sounds.wind;
            soundVol = 1f;
            duration = 7.5f * Time.toMinutes;
        }};
    }
}
