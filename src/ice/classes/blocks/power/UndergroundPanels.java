package ice.classes.blocks.power;

import arc.graphics.Color;
import arc.math.Mathf;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.world.blocks.power.*;

public class UndergroundPanels extends ThermalGenerator {
    public Color lightColor = Pal.powerLight;
    public UndergroundPanels(String name) {
        super(name);
    }
    public class UndergroundPanelsBuild extends ThermalGeneratorBuild{
        @Override
        public void drawLight(){
            Drawf.light(x, y, (40f + Mathf.absin(10f, 5f)) * Math.min(productionEfficiency, 2f) * size, lightColor, 0.4f);
        }

    }
}
