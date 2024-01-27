package ice.classes.entities.types;

import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import ice.content.IceItems;
import ice.graphics.IcePal;
import mindustry.game.Team;
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
