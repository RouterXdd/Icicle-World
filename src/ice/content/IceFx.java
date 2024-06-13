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
import mindustry.entities.effect.ParticleEffect;
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
    methaneCloud = new Effect(80f, e -> {
        color(IcePal.methaneLightish);
        randLenVectors(e.id, e.fin(), 7, 9f, (x, y, fin, fout) -> {
            Fill.circle(e.x + x, e.y + y, 5f * fout);
        });
    }),
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
                Lines.poly(e.x, e.y, 6, 6 * e.fin(), Time.time / 0.5f);
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
    }),
            shootWoodFlame = new Effect(33f, 80f, e -> {
                color(IcePal.sporeLightish, IcePal.sporeLight, Color.gray, e.fin());

                randLenVectors(e.id, 20, e.finpow() * 85f, e.rotation, 15f, (x, y) -> {
                    Fill.circle(e.x + x, e.y + y, 0.65f + e.fout() * 1.6f);
                });
            }),
    thalliumBlastExplosion = new Effect(30, e -> {
        color(e.color, IcePal.poloniumDark, e.fin());

        e.scaled(8, i -> {
            stroke(3f * i.fout());
            Lines.circle(e.x, e.y, 4f + i.fin() * 22f);
        });

        color(Color.gray);

        randLenVectors(e.id, 5, 2f + 40f * e.finpow(), (x, y) -> {
            Fill.circle(e.x + x, e.y + y, e.fout() * 5f + 0.5f);
        });

        color(IcePal.thalliumLight);
        stroke(e.fout());

        randLenVectors(e.id + 1, 4, 1f + 40f * e.finpow(), (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 2f + e.fout() * 3f);
        });

        Drawf.light(e.x, e.y, 60f, IcePal.poloniumMid, 0.8f * e.fout());
    }),
            poloniumSteam = new Effect(140f, e -> {
                color(IcePal.poloniumLightish, IcePal.poloniumMid, e.fin());

                alpha(e.fslope() * 0.78f);

                float length = 2.4f + e.finpow() * 10f;
                rand.setSeed(e.id);
                for(int i = 0; i < rand.random(3, 5); i++){
                    v.trns(rand.random(360f), rand.random(length));
                    Fill.circle(e.x + v.x, e.y + v.y, rand.random(1.2f, 2.7f) + e.fslope() * 1.1f);
                    Drawf.light(e.x, e.y, 10f, IcePal.poloniumLight, 1f * e.fout());
                }
            }),
    nuclearReactorExplosion = new Effect(30.0F, 500.0F, (b) -> {
        float intensity = 7.5F;
        float baseLifetime = 25.0F + intensity * 11.0F;
        b.lifetime = 50.0F + intensity * 65.0F;
        Draw.color(IcePal.poloniumMid);
        Draw.alpha(0.7F);

        for(int i = 0; i < 4; ++i) {
            rand.setSeed((long)(b.id * 2 + i));
            float lenScl = rand.random(0.4F, 1.0F);
            b.scaled(b.lifetime * lenScl, (e) -> {
                Angles.randLenVectors((long)(e.id + 3 - 1), e.fin(Interp.pow10Out), (int)(2.9F * intensity), 22.0F * intensity, (x, y, in, out) -> {
                    float fout = e.fout(Interp.pow5Out) * rand.random(0.5F, 1.0F);
                    float rad = fout * (2.0F + intensity) * 2.35F;
                    Fill.circle(e.x + x, e.y + y, rad);
                    Drawf.light(e.x + x, e.y + y, rad * 2.5F, IcePal.poloniumLightish, 0.5F);
                });
            });
        }

        b.scaled(baseLifetime, (e) -> {
            Draw.color();
            e.scaled(5.0F + intensity * 2.0F, (i) -> {
                Lines.stroke((3.1F + intensity / 5.0F) * i.fout());
                Lines.circle(e.x, e.y, (3.0F + i.fin() * 14.0F) * intensity);
                Drawf.light(e.x, e.y, i.fin() * 14.0F * 2.0F * intensity, Color.white, 0.9F * e.fout());
            });
            Draw.color(IcePal.poloniumLight, IcePal.poloniumMid, e.fin());
            Lines.stroke(2.0F * e.fout());
            Draw.z(110.001F);
            Angles.randLenVectors((long)(e.id + 1), e.finpow() + 0.001F, (int)(8.0F * intensity), 28.0F * intensity, (x, y, in, out) -> {
                Lines.lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), 1.0F + out * 4.0F * (4.0F + intensity));
                Drawf.light(e.x + x, e.y + y, out * 4.0F * (3.0F + intensity) * 3.5F, Draw.getColor(), 0.8F);
            });
        });
    }),
    thalliumChainLightning = new Effect(20.0F, 300.0F, (e) -> {
        Object p$temp = e.data;
        if (p$temp instanceof Position) {
            Position p = (Position)p$temp;
            float tx = p.getX();
            float ty = p.getY();
            float dst = Mathf.dst(e.x, e.y, tx, ty);
            Tmp.v1.set(p).sub(e.x, e.y).nor();
            float normx = Tmp.v1.x;
            float normy = Tmp.v1.y;
            float range = 6.0F;
            int links = Mathf.ceil(dst / range);
            float spacing = dst / (float)links;
            Lines.stroke(2.5F * e.fout());
            Draw.color(Color.white, e.color, e.fin());
            Lines.beginLine();
            Lines.linePoint(e.x, e.y);
            rand.setSeed((long)e.id);

            for(int i = 0; i < links; ++i) {
                float nx;
                float ny;
                if (i == links - 1) {
                    nx = tx;
                    ny = ty;
                } else {
                    float len = (float)(i + 1) * spacing;
                    Tmp.v1.setToRandomDirection(rand).scl(range / 2.0F);
                    nx = e.x + normx * len + Tmp.v1.x;
                    ny = e.y + normy * len + Tmp.v1.y;
                }
                Draw.color(IcePal.thalliumLightish);
                Lines.linePoint(nx, ny);
            }

            Lines.endLine();
        }
    }).followParent(false).rotWithParent(false),
            regenSuppressSootSeek = new Effect(140f, e -> {
                e.lifetime = Mathf.randomSeed(e.id, 120f, 200f);

                if(!(e.data instanceof Position to)) return;

                Tmp.v2.set(to).sub(e.x, e.y).nor().rotate90(1).scl(Mathf.randomSeedRange(e.id, 1f) * 50f);

                Tmp.bz2.set(Tmp.v1.set(e.x, e.y), Tmp.v2.add(e.x, e.y), Tmp.v3.set(to));

                Tmp.bz2.valueAt(Tmp.v4, e.fout());

                color(IcePal.sootSuppressParticleColor);
                Fill.poly(Tmp.v4.x, Tmp.v4.y, 6, e.fslope() * 2f + 0.1f, Time.time * 1.4f);
            }).followParent(false).rotWithParent(false),
    sporeRegen = new ParticleEffect(){{
        particles = 8;
        line = true;
        colorFrom = IcePal.sporeLightish;
        colorTo = IcePal.sporeMid;
        lenTo = 0;
        lenFrom = 4.5f;
        cone = 360;
        lifetime = 25;
        interp = Interp.pow2Out;
        sizeFrom = 2.4f;
        lightColor = IcePal.sporeLightish;
        lightScl = 3.2f;
    }};
}
