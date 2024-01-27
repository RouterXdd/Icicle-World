package ice.classes.entities.types;

import arc.graphics.Color;
import ice.content.IceItems;
import ice.content.IceLiquids;
import ice.graphics.IcePal;
import mindustry.entities.abilities.LiquidExplodeAbility;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;
import static mindustry.content.StatusEffects.*;
import static ice.content.IceStatuses.*;

public class MonsterUnitType extends UnitType {
    public float bloodAmount = 40f;
    public MonsterUnitType(String name){
        super(name);
        outlineColor = IcePal.monsterOutline;
        createScorch = false;
        immunities.add(corroded);
        immunities.add(disarmed);
        immunities.add(radiation);
        immunities.add(rusting);
        abilities.add(new LiquidExplodeAbility(){{
            liquid = IceLiquids.blood;
            amount = bloodAmount;
        }});
        drawCell = false;
        mechLegColor = Color.valueOf("041600");
    }
}
