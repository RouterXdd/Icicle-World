package ice.classes.entities.types;

import arc.graphics.Color;
import ice.content.IceLiquids;
import mindustry.entities.abilities.LiquidExplodeAbility;
import mindustry.type.UnitType;

import static ice.content.IceStatuses.radiation;
import static ice.content.IceStatuses.rusting;
import static mindustry.content.StatusEffects.*;

public class MethaniteUnitType extends UnitType {
    public float bloodAmount;
    public MethaniteUnitType(String name) {
        super(name);
        bloodAmount = 40f;
        drawCell = false;
        createScorch = false;
        immunities.add(corroded);
        immunities.add(disarmed);
        immunities.add(radiation);
        immunities.add(rusting);
        immunities.add(burning);
        abilities.add(new LiquidExplodeAbility(){{
            liquid = IceLiquids.methanum;
            amount = bloodAmount;
        }});
        outlineColor = Color.valueOf("03000d");
    }
}
