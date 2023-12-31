package ice.classes.entities.types;

import arc.graphics.Color;
import ice.content.IceItems;
import ice.graphics.IcePal;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;

public class RkiUnitType extends UnitType {

    public RkiUnitType(String name){
        super(name);
        outlineColor = IcePal.rkiOutline;
        ammoType = new ItemAmmoType(IceItems.thallium);
        researchCostMultiplier = 6f;
        mechLegColor = Color.valueOf("704a66");
    }
}
