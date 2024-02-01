package ice.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.Items;
import mindustry.type.*;

import static mindustry.content.Items.*;

public class IceItems {
    public static Item
        thallium, prinute, ceramicalDust, sporeWood, soptin, ceramic, livesteel, polonium, poloniumCharge, denseAlloy;
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
        ceramic = new Item("ceramic", Color.valueOf("cbcbcb")){{
            hardness = 3;
            cost = 1.5f;
        }};
        livesteel = new Item("livesteel", Color.valueOf("8a5294")){{
            hardness = 3;
            cost = 2.1f;
            flammability = 0.55f;
        }};
        polonium = new Item("polonium", Color.valueOf("86b641")){{
            hardness = 4;
            cost = 3f;
            healthScaling = 2;
            radioactivity = 1.8f;
        }};
        poloniumCharge = new Item("polonium-charge", Color.valueOf("62b141")){{
            hardness = 2;
            cost = 3.5f;
            healthScaling = 1.8f;
            radioactivity = 2.4f;
            charge = 0.75f;
            buildable = false;
        }};
        denseAlloy = new Item("dense-alloy", Color.valueOf("6c4054")){{
            hardness = 2;
            cost = 3.8f;
            healthScaling = 2.6f;
        }};
        RkiItems.addAll(
                scrap, silicon, thallium, sporeWood, prinute, soptin, ceramicalDust, ceramic, livesteel, polonium, poloniumCharge, denseAlloy
        );
    }
}
