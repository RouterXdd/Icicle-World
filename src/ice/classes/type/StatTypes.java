package ice.classes.type;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.struct.*;
import arc.util.*;
import mindustry.type.*;
import mindustry.ui.Styles;
import mindustry.world.meta.*;

public class StatTypes {
    public static StatValue weaponModules(Weapon weapon){
        return table -> {
            table.row();


                TextureRegion region = !weapon.name.isEmpty() ? Core.atlas.find(weapon.name + "-preview", weapon.region) : null;

                table.table(Styles.grayPanel, w -> {
                    w.left().top().defaults().padRight(3).left();
                    if(region != null && region.found() && weapon.showStatSprite) w.image(region).size(60).scaling(Scaling.bounded).left().top();
                    w.left().top().defaults().padRight(3).padBottom(5).left();
                    if(weapon.inaccuracy > 0){
                        w.row();
                        w.add("[lightgray]" + Stat.inaccuracy.localized() + ": [white]" + (int)weapon.inaccuracy + " " + StatUnit.degrees.localized());
                    }
                    if(!weapon.alwaysContinuous && weapon.reload > 0){
                        w.row();
                        w.add("[lightgray]" + Stat.reload.localized() + ": " + (weapon.mirror ? "2x " : "") + "[white]" + Strings.autoFixed(60f / weapon.reload * weapon.shoot.shots, 2) + " " + StatUnit.perSecond.localized());
                    }
                    w.row();
                }).growX().pad(5).margin(10);
                table.row();
        };
    }
}
