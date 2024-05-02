package ice.classes.entities.abilities;

import arc.math.Mathf;
import ice.IcicleVars;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Unit;

public class DeathExplosionAbility extends Ability {
    public float exploDamage;
    public float exploRange;
    public int exploBullets;
    public BulletType exploBullet;
    public Effect exploEffect = Fx.none;
    public DeathExplosionAbility(float exploDamage, float exploRange){
        this.exploDamage = exploDamage;
        this.exploRange = exploRange;
    }
    @Override
    public void death(Unit unit) {
        exploEffect.at(unit.x, unit.y);
        if (!IcicleVars.hardMode) {
            Damage.damage(unit.team, unit.x, unit.y, exploRange, exploDamage);
        } else {
            Damage.damage(unit.team, unit.x, unit.y, exploRange, exploDamage, true);
        }
        if (exploBullet != null) {
            for(int i = 0; i < exploBullets; i++) {
                exploBullet.create(unit, unit.x, unit.y, unit.rotation() + Mathf.random(360));
            }
        }
    }
}
