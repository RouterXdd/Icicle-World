package ice.classes.entities.abilities;

import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.Strings;
import arc.util.Time;
import ice.classes.type.IceStats;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.abilities.Ability;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;
import mindustry.gen.Unit;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

import java.util.Iterator;

public class WraithAbility extends Ability {
    public float activatePercent;
    public Effect activeEffect = Fx.none;
    public float shockTime;
    float shockTimer;
    public float shockSpread;
    public BulletType shockBullet;
    public WraithAbility(float activatePercent, float shockTime, float shockSpread){
        this.activatePercent = activatePercent;
        this.shockTime = shockTime;
        this.shockSpread = shockSpread;
    }
    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + IceStats.usePercent.localized() + ": [white]" + Strings.autoFixed(activatePercent, 2) + StatUnit.percent.localized());
        t.row();
        t.add("[lightgray]" + Stat.reload.localized() + ": [white]" + shockTime / 60 + StatUnit.seconds.localized());
    }
    @Override
    public void update(Unit unit){
        shockTimer += Time.delta;
        if (unit.health < unit.maxHealth * activatePercent / 100f) {
            if (shockTimer >= shockTime) {
                activeEffect.at(unit.x, unit.y);
                if (shockBullet != null) {
                        shockBullet.create(unit, unit.x, unit.y, unit.rotation() + Mathf.random(-shockSpread, shockSpread));
                }
                shockTimer = 0;
            }
        }
    }
}
