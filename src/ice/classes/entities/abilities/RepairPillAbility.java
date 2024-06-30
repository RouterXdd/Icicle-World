package ice.classes.entities.abilities;

import arc.graphics.g2d.*;
import arc.scene.ui.layout.Table;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import ice.classes.type.IceStats;
import ice.content.IceFx;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.graphics.Drawf;
import mindustry.graphics.*;
import mindustry.world.meta.StatUnit;

public class RepairPillAbility extends Ability{
    public float usePercent = 30, healPercent = 50, x = 0, y = 0;
    public float pillSize = 4, pillSpeedScl = 6;
    public int pillSides = 6;
    protected boolean used = false;

    RepairPillAbility(){}

    public RepairPillAbility(float usePercent, float healPercent){
        this.usePercent = usePercent;
        this.healPercent = healPercent;
    }
    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + IceStats.usePercent.localized() + ": [white]" + Strings.autoFixed(usePercent, 2) + StatUnit.percent.localized());
        t.row();
        t.add("[lightgray]" + IceStats.healPercent.localized() + ": [white]" + Strings.autoFixed(healPercent, 2) + StatUnit.percent.localized());
    }
    @Override
    public void update(Unit unit){
        if (unit.health < unit.maxHealth * usePercent / 100f && !used){
            used = true;
            unit.heal(unit.maxHealth * healPercent / 100f);
            IceFx.pillUse.at(unit.x + Tmp.v1.x, unit.y + Tmp.v1.y);
        }
    }
    @Override
    public void draw(Unit unit){
        if (!used){
            Tmp.v1.trns(unit.rotation - 90f, x, y);
            Draw.z(Layer.effect);
            Draw.color(Pal.surge);
            Fill.poly(unit.x + Tmp.v1.x, unit.y + Tmp.v1.y, pillSides, pillSize, Time.time / pillSpeedScl);
            Draw.reset();
            IceFx.pillTrail.at(unit.x + Tmp.v1.x, unit.y + Tmp.v1.y);
        }
    }

    public void write(Writes write){
        write.bool(used);
    }

    public void read(Reads read, byte revision){
        used = read.bool();
    }
}
