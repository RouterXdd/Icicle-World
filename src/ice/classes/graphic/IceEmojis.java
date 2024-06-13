package ice.classes.graphic;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.Font.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import ice.content.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.game.Team;
import mindustry.mod.Mods.*;
import mindustry.ui.*;
import mindustry.world.blocks.*;
//Code yonked from Dusted Lands lol
public class IceEmojis {
    public static int id;

    public static void load() {
        //load the character id
        LoadedMod mod = Vars.mods.getMod("icicle-world");
        OrderedMap<String, String> map = new OrderedMap<>();
        PropertiesUtils.load(map, Core.files.internal("icons/icons.properties").reader(256));

        id = 0xF8FF;

        ObjectMap<String, String> content2id = new ObjectMap<>();
        map.each((key, val) -> content2id.put(val.split("\\|")[0], key));

        Seq<UnlockableContent> cont = Seq.withArrays(
                Vars.content.blocks(),
                Vars.content.items(),
                Vars.content.liquids(),
                Vars.content.units(),
                Vars.content.statusEffects()
        );
        cont.removeAll(u -> u instanceof ConstructBlock || u == Blocks.air || u.minfo.mod == mod);

        for (String key : map.keys()) {
            id = Math.min(Integer.parseInt(key), id);
        }

        for (UnlockableContent content : cont) {
            if (!content2id.containsKey(content.name)) {
                id--;
            }
        }

        //actually start loading the emojis
        Seq<Font> fonts = Seq.with(Fonts.def, Fonts.outline);

        Texture pure = IceItems.thallium.uiIcon.texture;
        Texture genned = IceBlocks.metalIncubator.uiIcon.texture;

        fonts.each(f -> f.getRegions().add(new TextureRegion(pure)));
        fonts.each(f -> f.getRegions().add(new TextureRegion(genned)));

        int purePage = Fonts.def.getRegions().indexOf(t -> t.texture == pure);
        int gennedPage = Fonts.def.getRegions().indexOf(t -> t.texture == genned);

        Seq.<UnlockableContent>withArrays(
                        Vars.content.blocks(),
                        Vars.content.items(),
                        Vars.content.liquids(),
                        Vars.content.units(),
                        Vars.content.statusEffects()
                ).removeAll(u -> u.minfo.mod != mod)
                .map(c -> new GenData(c.uiIcon.texture == pure, c.name, c.uiIcon))
                .add(new GenData(true, "genesis", Core.atlas.find("icicle-world-genesis")))
                .add(new GenData(true, "origin", Core.atlas.find("icicle-world-origin")))
                .add(new GenData(true, "green", Core.atlas.find("icicle-world-green-t")))
                .add(new GenData(true, "blue", Core.atlas.find("icicle-world-blue-t")))
                .each(data -> {
                    TextureRegion region = data.glyphRegion;
                    id--;

                    Reflect.<ObjectIntMap<String>>get(Fonts.class, "unicodeIcons").put(data.name, id);
                    Reflect.<ObjectMap<String, String>>get(Fonts.class, "stringIcons").put(data.name, ((char) id) + "");

                    int size = (int) (Fonts.def.getData().lineHeight / Fonts.def.getData().scaleY);

                    Vec2 out = Scaling.fit.apply(region.width, region.height, size, size);

                    Glyph glyph = new Glyph();
                    glyph.id = id;
                    glyph.srcX = 0;
                    glyph.srcY = 0;
                    glyph.width = (int) out.x;
                    glyph.height = (int) out.y;
                    glyph.u = region.u;
                    glyph.v = region.v2;
                    glyph.u2 = region.u2;
                    glyph.v2 = region.v;
                    glyph.xoffset = 0;
                    glyph.yoffset = -size;
                    glyph.xadvance = size;
                    glyph.kerning = null;
                    glyph.fixedWidth = true;
                    glyph.page = region.texture == pure ? purePage : gennedPage;
                    fonts.each(f -> f.getData().setGlyph(id, glyph));
                });

        IceTeams.genesis.emoji = Reflect.<ObjectMap<String, String>>get(Fonts.class, "stringIcons").get(IceTeams.genesis.name, "");
        IceTeams.origin.emoji = Reflect.<ObjectMap<String, String>>get(Fonts.class, "stringIcons").get(IceTeams.origin.name, "");
        Team.green.emoji = Reflect.<ObjectMap<String, String>>get(Fonts.class, "stringIcons").get(Team.green.name, "");
        Team.blue.emoji = Reflect.<ObjectMap<String, String>>get(Fonts.class, "stringIcons").get(Team.blue.name, "");
    }

    public static class GenData {
        public boolean pure;
        public String name;
        public TextureRegion glyphRegion;

        public GenData(boolean pure, String name, TextureRegion glyphRegion) {
            this.pure = pure;
            this.name = name;
            this.glyphRegion = glyphRegion;
        }
    }
}
