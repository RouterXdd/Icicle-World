package ice.content;

import arc.graphics.*;
import mindustry.type.*;

import static mindustry.content.Liquids.*;

public class IceLiquids {
    public static Liquid methanum, blood;
    public static void load(){
        methanum = new Liquid("methanum", Color.valueOf("84a2bc")){{
            viscosity = 0.9f;
            flammability = 2f;
            heatCapacity = 0.6f;
            barColor = Color.valueOf("abcddb");
            boilPoint = 0.8f;
            gasColor = Color.grays(0.4f);
            canStayOn.add(water);
            canStayOn.add(cryofluid);
        }};
        blood = new Liquid("blood", Color.valueOf("5b080e")){{
            viscosity = 0.65f;
            flammability = 0f;
            heatCapacity = 0f;
            boilPoint = 2f;
            hidden = true;
            gasColor = Color.valueOf("5b080e").a(0.6f);
        }};
    }
}
