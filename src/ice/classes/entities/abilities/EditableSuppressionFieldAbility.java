package ice.classes.entities.abilities;

import arc.util.Time;
import arc.util.Tmp;
import ice.classes.type.meta.IceDamage;
import ice.content.IceFx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.abilities.SuppressionFieldAbility;
import mindustry.gen.Unit;

public class EditableSuppressionFieldAbility extends SuppressionFieldAbility {
    public Effect effect = IceFx.regenSuppressSootSeek;
    public void update(Unit unit) {
        if (this.active) {
            if ((this.timer += Time.delta) >= this.reload) {
                Tmp.v1.set(this.x, this.y).rotate(unit.rotation - 90.0F).add(unit);
                IceDamage.applyEditableSuppression(unit.team, Tmp.v1.x, Tmp.v1.y, this.range, this.reload, this.reload, this.applyParticleChance, effect, unit);
                this.timer = 0.0F;
            }

        }
    }
}
