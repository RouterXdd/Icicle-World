package ice.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import ice.graphics.IcePal;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.units.UnitAssembler.*;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;

public class IceFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect
            lightningThalliumShoot = new Effect(10f, e -> {
        color(Color.white, IcePal.thalliumMid, e.fin());
        stroke(e.fout() * 1.2f + 0.5f);

        randLenVectors(e.id, 10, 25f * e.finpow(), e.rotation, 40f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fin() * 5f + 1f);
        });
    }),
            hitThalliumLaser = new Effect(8, e -> {
                color(Color.white, IcePal.thalliumMid, e.fin());
                stroke(0.5f + e.fout());
                Lines.circle(e.x, e.y, e.fin() * 5f);

                Drawf.light(e.x, e.y, 18f, IcePal.thalliumMid, e.fout() * 0.7f);
            }),
    pointEject = new Effect(30f, e -> {
        color(IcePal.thalliumMid, IcePal.thalliumLight, IcePal.thalliumLightish, e.fin());
        alpha(e.fout(0.3f));
        float rot = Math.abs(e.rotation) + 90f;
        int i = -Mathf.sign(e.rotation);

        float len = (2f + e.finpow() * 6f) * i;
        float lr = rot + e.fin() * 30f * i;
        Fill.rect(
                e.x + trnsx(lr, len) + Mathf.randomSeedRange(e.id + i + 7, 3f * e.fin()),
                e.y + trnsy(lr, len) + Mathf.randomSeedRange(e.id + i + 8, 3f * e.fin()),
                1.2f, 1.2f, rot + e.fin() * 50f * i
        );

    }).layer(Layer.bullet),
    pillTrail = new Effect( 30f, (e) ->{
        Draw.z(Layer.effect);
        Draw.color(Pal.surge);
        Lines.stroke(0.5F + e.fout());
        Lines.poly(e.x, e.y, 6, 3 * e.fin(), Time.time / 2);
        Draw.reset();
    }),
    pillUse = new Effect( 80f, (e) ->{
                Draw.z(Layer.effect);
                Draw.color(Pal.surge);
                Lines.stroke(1F + e.fin());
                Lines.poly(e.x, e.y, 6, 5 * e.fin(), Time.time / 0.5f);
                Draw.reset();
    }),
    hitSplashBulletColor = new Effect(14.0F, (e) -> {
        float size = 36;
        Draw.color(Color.white, e.color, e.fin());
        e.scaled(20.0F, (s) -> {
            Lines.stroke(0.5F + s.fout());
            Lines.circle(e.x, e.y,  size);
            Draw.color(e.color);
            Draw.alpha(0.6f);
            Fill.circle(e.x, e.y,size * e.fout());
        });
        Lines.stroke(0.6F + e.fout());
        Angles.randLenVectors((long)e.id, 8, e.fin() * 15.0F, (x, y) -> {
            float ang = Mathf.angle(x, y);
            Lines.lineAngle(e.x + x, e.y + y, ang, e.fout() * size + 1.5F);
        });
        Drawf.light(e.x, e.y, size, e.color, 0.6F * e.fout());
    });
}
