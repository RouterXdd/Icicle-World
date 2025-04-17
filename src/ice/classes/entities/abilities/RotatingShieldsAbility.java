package ice.classes.entities.abilities;

import arc.func.Cons;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.scene.ui.layout.Table;
import arc.util.Nullable;
import arc.util.Strings;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.Vars;
import mindustry.content.Fx;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.ui.Bar;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;

public class RotatingShieldsAbility extends Ability {
    private static Unit paramUnit;
    private static RotatingShieldsAbility paramField;
    private static Vec2 paramPos = new Vec2();
    private static final Cons<Bullet> shieldConsumer = b -> {
        if(b.team != paramUnit.team && b.type.absorbable && paramField.data > 0 &&
                !b.within(paramPos, paramField.radius - paramField.width/2f) &&
                Tmp.v1.set(b).add(b.vel).within(paramPos, paramField.radius + paramField.width/2f) &&
                Angles.within(paramPos.angleTo(b), paramField.angleOffset + Time.time * 2, paramField.angle / 2f)){

            b.absorb();
            Fx.absorb.at(b);

            //break shield
            if(paramField.data <= b.damage()){
                paramField.data -= paramField.cooldown * paramField.regen;

                Fx.shieldBreak.at(paramPos.x, paramPos.y, 0, paramUnit.team.color, paramUnit);
            }

            paramField.data -= b.damage();
            paramField.alpha = 1f;
        }
    };

    /** Shield radius. */
    public float radius = 30f;
    /** Shield regen speed in damage/tick. */
    public float regen = 0.1f;
    /** Maximum shield. */
    public float max = 750f;
    /** Cooldown after the shield is broken, in ticks. */
    public float cooldown = 60f * 5;
    /** Angle of shield arc. */
    public float angle = 80f;
    /** Offset parameters for shield. */
    public float angleOffset = 0f, x = 0f, y = 0f;
    /** Width of shield line. */
    public float width = 6f;

    /** Whether to draw the arc line. */
    public boolean drawArc = true;
    /** If not null, will be drawn on top. */
    public @Nullable String region;
    /** If true, sprite position will be influenced by x/y. */
    public boolean offsetRegion = false;

    /** State. */
    protected float widthScale, alpha;

    @Override
    public void addStats(Table t){
        t.add("[lightgray]" + Stat.health.localized() + ": [white]" + Math.round(max));
        t.row();
        t.add("[lightgray]" + Stat.repairSpeed.localized() + ": [white]" + Strings.autoFixed(regen * 60f, 2) + StatUnit.perSecond.localized());
        t.row();
        t.add("[lightgray]" + Stat.cooldownTime.localized() + ": [white]" + Strings.autoFixed(cooldown / 60f, 2) + " " + StatUnit.seconds.localized());
        t.row();
    }

    @Override
    public void update(Unit unit){

        if(data < max){
            data += Time.delta * regen;
        }

        alpha = Math.max(alpha - Time.delta/10f, 0f);

            widthScale = Mathf.lerpDelta(widthScale, 1f, 0.06f);
            paramUnit = unit;
            paramField = this;
            paramPos.set(x, y).rotate(unit.rotation + Time.time * 2).add(unit);

            float reach = radius + width / 2f;
            Groups.bullet.intersect(paramPos.x - reach, paramPos.y - reach, reach * 2f, reach * 2f, shieldConsumer);
    }

    @Override
    public void init(UnitType type){
        data = max;
    }

    @Override
    public void draw(Unit unit){
        if(widthScale > 0.001f){
            Draw.z(Layer.shields);

            Draw.color(unit.team.color, Color.white, Mathf.clamp(alpha));
            var pos = paramPos.set(x, y).rotate(Time.time * 2).add(unit);

            if(!Vars.renderer.animateShields){
                Draw.alpha(0.4f);
            }

            if(region != null){
                Vec2 rp = offsetRegion ? pos : Tmp.v1.set(unit);
                Draw.yscl = widthScale;
                Draw.rect(region, rp.x, rp.y, Time.time * 2);
                Draw.yscl = 1f;
            }

            if(drawArc){
                Lines.stroke(width * widthScale * data / max);
                Lines.arc(pos.x, pos.y, radius, angle / 360f, angleOffset - angle / 2f + Time.time * 2);
            }
            //Draw.reset();
        }
    }

    @Override
    public void displayBars(Unit unit, Table bars){
        bars.add(new Bar("stat.shieldhealth", Pal.accent, () -> data / max)).row();
    }
}