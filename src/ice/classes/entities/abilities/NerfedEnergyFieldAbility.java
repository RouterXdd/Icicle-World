package ice.classes.entities.abilities;

import mindustry.entities.abilities.EnergyFieldAbility;
import arc.*;
import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class NerfedEnergyFieldAbility extends EnergyFieldAbility {
    private static final Seq<Healthc> all = new Seq<>();
    public NerfedEnergyFieldAbility(float damage, float reload, float range) {
        super(damage, reload, range);
    }

    public float buildingDamageMultiplier = 0.01f;
    @Override
    public void update(Unit unit){

        curStroke = Mathf.lerpDelta(curStroke, anyNearby ? 1 : 0, 0.09f);

        if((timer += Time.delta) >= reload && (!useAmmo || unit.ammo > 0 || !state.rules.unitAmmo)){
            Tmp.v1.trns(unit.rotation - 90, x, y).add(unit.x, unit.y);
            float rx = Tmp.v1.x, ry = Tmp.v1.y;
            anyNearby = false;

            all.clear();

            if(hitUnits){
                Units.nearby(null, rx, ry, range, other -> {
                    if(other != unit && other.checkTarget(targetAir, targetGround) && other.targetable(unit.team) && (other.team != unit.team || other.damaged())){
                        all.add(other);
                    }
                });
            }

            if(hitBuildings && targetGround){
                Units.nearbyBuildings(rx, ry, range, b -> {
                    if((b.team != Team.derelict || state.rules.coreCapture) && ((b.team != unit.team && b.block.targetable) || b.damaged()) && !b.block.privileged){
                        all.add(b);
                    }
                });
            }

            all.sort(h -> h.dst2(rx, ry));
            int len = Math.min(all.size, maxTargets);
            for(int i = 0; i < len; i++){
                Healthc other = all.get(i);

                //lightning gets absorbed by plastanium
                var absorber = Damage.findAbsorber(unit.team, rx, ry, other.getX(), other.getY());
                if(absorber != null){
                    other = absorber;
                }

                if(((Teamc)other).team() == unit.team){
                    if(other.damaged()){
                        anyNearby = true;
                        float healMult = (other instanceof Unit u && u.type == unit.type) ? sameTypeHealMult : 1f;
                        other.heal(healPercent / 100f * other.maxHealth() * healMult);
                        healEffect.at(other);
                        damageEffect.at(rx, ry, 0f, color, other);
                        hitEffect.at(rx, ry, unit.angleTo(other), color);

                        if(other instanceof Building b){
                            Fx.healBlockFull.at(b.x, b.y, 0f, color, b.block);
                        }
                    }
                }else{
                    anyNearby = true;
                    if(other instanceof Building b){
                        b.damage(unit.team, damage * state.rules.unitDamage(unit.team) * buildingDamageMultiplier);
                    }else{
                        other.damage(damage * state.rules.unitDamage(unit.team));
                    }
                    if(other instanceof Statusc s){
                        s.apply(status, statusDuration);
                    }
                    hitEffect.at(other.x(), other.y(), unit.angleTo(other), color);
                    damageEffect.at(rx, ry, 0f, color, other);
                    hitEffect.at(rx, ry, unit.angleTo(other), color);
                }
            }

            if(anyNearby){
                shootSound.at(unit);

                if(useAmmo && state.rules.unitAmmo){
                    unit.ammo --;
                }
            }

            timer = 0f;
        }
    }
}
