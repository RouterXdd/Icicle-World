package ice.content;

import mindustry.Vars;
import mindustry.game.Schematic;
import mindustry.game.Schematics;

import java.io.IOException;

public class IceSchematics {
    public static Schematic
    angryBasic;
    public static void load() {
        angryBasic = loadSchem("basic-angry");
    }

    private static Schematic loadSchem(String name) {
        Schematic schematic;
        try {
            schematic = Schematics.read(Vars.tree.get("schematics/" + name + ".msch"));
        } catch (IOException e) {
            schematic = null;
            e.printStackTrace();
        }
        return schematic;
    }

    private static Schematic loadBase64(String b64) {
        return Schematics.readBase64(b64);
    }
}
