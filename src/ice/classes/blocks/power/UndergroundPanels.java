package ice.classes.blocks.power;

import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.power.*;

public class UndergroundPanels extends ThermalGenerator {
    public Color lightColor = Pal.powerLight;
    public float radius = 40f;
    public UndergroundPanels(String name) {
        super(name);
        fogRadius = 1;
    }
    public class UndergroundPanelsBuild extends ThermalGeneratorBuild{
        @Override
        public void drawLight(){
            Drawf.light(x, y, (radius + Mathf.absin(10f, 5f)) * Math.min(productionEfficiency, 2f) * size, lightColor, 0.4f);
        }

    }
}
