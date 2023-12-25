package ice.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.Items;
import mindustry.type.*;

public class IceItems {
    public static Item
        thallium, prinute, ceramicalDust, sporeWood, soptin, livesteel;
    public static final Seq<Item> RkiItems = new Seq<>();
    public static void load(){
        Items.scrap.buildable = true;
        thallium = new Item("thallium", Color.valueOf("c53658")){{
            hardness = 2;
            cost = 1.1f;
        }};
        sporeWood = new Item("spore-wood", Color.valueOf("822d8b")){{
            hardness = 2;
            cost = 1.3f;
            flammability = 1.5f;
        }};
        prinute = new Item("prinute", Color.valueOf("989aa4")){{
            cost = 2.1f;
        }};
        soptin = new Item("soptin", Color.valueOf("151a59")){{
            hardness = 3;
            cost = 1.8f;
        }};
        ceramicalDust = new Item("ceramical-dust", Color.valueOf("827d5a")){{
            hardness = 2;
            cost = 0.6f;
            buildable = false;
        }};
        livesteel = new Item("livesteel", Color.valueOf("8a5294")){{
            hardness = 3;
            cost = 2.1f;
            flammability = 0.55f;
        }};
    }
}
