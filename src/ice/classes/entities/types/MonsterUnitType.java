package ice.classes.entities.types;

import arc.graphics.Color;
import ice.content.IceItems;
import ice.content.IceLiquids;
import ice.graphics.IcePal;
import mindustry.entities.abilities.LiquidExplodeAbility;
import mindustry.type.Liquid;
import mindustry.type.UnitType;
import static mindustry.content.StatusEffects.*;
import static ice.content.IceStatuses.*;

public class MonsterUnitType extends UnitType {
    public float bloodAmount;
    public Liquid bloodType;
    public MonsterUnitType(String name){
        super(name);
        outlineColor = IcePal.monsterOutline;
        bloodAmount = 40f;
        bloodType = IceLiquids.blood;
        createScorch = false;
        immunities.add(corroded);
        immunities.add(disarmed);
        immunities.add(radiation);
        immunities.add(rusting);
        abilities.add(new LiquidExplodeAbility(){{
            liquid = bloodType;
            amount = bloodAmount;
        }});
        drawCell = false;
        mechLegColor = Color.valueOf("041600");
    }
}
