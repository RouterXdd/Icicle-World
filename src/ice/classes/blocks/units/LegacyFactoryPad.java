package ice.classes.blocks.units;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.math.geom.Vec2;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.*;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.entities.Units;
import mindustry.gen.*;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.io.TypeIO;
import mindustry.logic.LAccess;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.Block;
import mindustry.world.blocks.ItemSelection;
import mindustry.world.consumers.ConsumeItemDynamic;
import mindustry.world.consumers.ConsumeLiquidsDynamic;
import mindustry.world.meta.Stat;

public class LegacyFactoryPad extends Block {
    public int[] capacities = {};
    public TextureRegion topRegion;
    public boolean drawTop = true;
    public Seq<UnitLeagcyPlan> plans = new Seq<>(4);

    public LegacyFactoryPad(String name) {
        super(name);
        update = true;
        hasPower = true;
        hasItems = true;
        solid = false;
        configurable = true;
        clearOnDoubleTap = true;
        commandable = true;
        ambientSound = Sounds.respawning;

        config(Integer.class, (LegacyFactoryPadBuild tile, Integer i) -> {
            if(!configurable) return;

            if(tile.currentPlan == i) return;
            tile.currentPlan = i < 0 || i >= plans.size ? -1 : i;
            tile.progress = 0;
        });

        config(UnitType.class, (LegacyFactoryPadBuild tile, UnitType val) -> {
            if(!configurable) return;

            int next = plans.indexOf(p -> p.unit == val);
            if(tile.currentPlan == next) return;
            tile.currentPlan = next;
            tile.progress = 0;
        });

        consume(new ConsumeItemDynamic((LegacyFactoryPadBuild e) -> e.currentPlan != -1 ? plans.get(Math.min(e.currentPlan, plans.size - 1)).requirements : ItemStack.empty));
    }
    @Override
    public TextureRegion[] icons(){
        if (drawTop) {
            return new TextureRegion[]{region, topRegion};
        } else {
            return new TextureRegion[]{region};
        }
    }
    @Override
    public void load(){
        region = Core.atlas.find(this.name);
        topRegion = Core.atlas.find(this.name + "-top");
    }
    @Override
    public void init(){
        capacities = new int[Vars.content.items().size];
        for(UnitLeagcyPlan plan : plans){
            for(ItemStack stack : plan.requirements){
                capacities[stack.item.id] = Math.max(capacities[stack.item.id], stack.amount * 2);
                itemCapacity = Math.max(itemCapacity, stack.amount * 2);
            }
        }

        consumeBuilder.each(c -> c.multiplier = b -> Vars.state.rules.unitCost(b.team));

        super.init();
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("progress", (LegacyFactoryPadBuild e) -> new Bar("bar.progress", Pal.ammo, e::fraction));

        addBar("units", (LegacyFactoryPadBuild e) ->
                new Bar(
                        () -> e.unit() == null ? "[lightgray]" + Iconc.cancel :
                                Core.bundle.format("bar.unitcap",
                                        Fonts.getUnicodeStr(e.unit().name),
                                        e.team.data().countType(e.unit()),
                                        e.unit() == null ? Units.getStringCap(e.team) : (e.unit().useUnitCap ? Units.getStringCap(e.team) : "∞")
                                ),
                        () -> Pal.power,
                        () -> e.unit() == null ? 0f : (e.unit().useUnitCap ? (float)e.team.data().countType(e.unit()) / Units.getCap(e.team) : 1f)
                ));
    }

    @Override
    public boolean outputsItems(){
        return false;
    }
    @Override
    public void setStats(){
        super.setStats();

        stats.remove(Stat.itemCapacity);

        stats.add(Stat.output, table -> {
            table.row();

            for(var plan : plans){
                table.table(Styles.grayPanel, t -> {

                    if(plan.unit.isBanned()){
                        t.image(Icon.cancel).color(Pal.remove).size(40);
                        return;
                    }

                    if(plan.unit.unlockedNow()){
                        t.image(plan.unit.uiIcon).size(40).pad(10f).left().scaling(Scaling.fit);
                        t.table(info -> {
                            info.add(plan.unit.localizedName).left();
                            info.row();
                            info.add(Strings.autoFixed(plan.time / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).left();

                        t.table(req -> {
                            req.right();
                            for(int i = 0; i < plan.requirements.length; i++){
                                if(i % 6 == 0){
                                    req.row();
                                }

                                ItemStack stack = plan.requirements[i];
                                req.add(new ItemDisplay(stack.item, stack.amount, false)).pad(5);
                            }
                        }).right().grow().pad(10f);
                    }else{
                        t.image(Icon.lock).color(Pal.darkerGray).size(40);
                    }
                }).growX().pad(5);
                table.row();
            }
        });
    }
    public static class UnitLeagcyPlan{
        public UnitType unit;
        public ItemStack[] requirements;
        public float time;

        public UnitLeagcyPlan(UnitType unit, float time, ItemStack[] requirements){
            this.unit = unit;
            this.time = time;
            this.requirements = requirements;
        }

        UnitLeagcyPlan(){}
    }
    public class LegacyFactoryPadBuild extends Building {
        public @Nullable Vec2 commandPos;
        public int currentPlan = -1;
        public float speedScl = 4;
        public float time, progress;

        public float fraction() {
            return currentPlan == -1 ? 0 : progress / plans.get(currentPlan).time;
        }

        @Override
        public Vec2 getCommandPosition() {
            return commandPos;
        }

        @Override
        public void onCommand(Vec2 target) {
            commandPos = target;
        }

        @Override
        public Object senseObject(LAccess sensor) {
            if (sensor == LAccess.config) return currentPlan == -1 ? null : plans.get(currentPlan).unit;
            return super.senseObject(sensor);
        }

        @Override
        public boolean shouldActiveSound() {
            return shouldConsume();
        }

        @Override
        public double sense(LAccess sensor) {
            if (sensor == LAccess.progress) return Mathf.clamp(fraction());
            if (sensor == LAccess.itemCapacity) return Mathf.round(itemCapacity * Vars.state.rules.unitCost(team));
            return super.sense(sensor);
        }

        @Override
        public void buildConfiguration(Table table) {
            Seq<UnitType> units = Seq.with(plans).map(u -> u.unit).retainAll(u -> u.unlockedNow() && !u.isBanned());

            if (units.any()) {
                ItemSelection.buildTable(LegacyFactoryPad.this, table, units, () -> currentPlan == -1 ? null : plans.get(currentPlan).unit, unit -> configure(plans.indexOf(u -> u.unit == unit)), selectionRows, selectionColumns);
            } else {
                table.table(Styles.black3, t -> t.add("@none").color(Color.lightGray));
            }
        }
        @Override
        public void display(Table table){
            super.display(table);

            TextureRegionDrawable reg = new TextureRegionDrawable();

            table.row();
            table.table(t -> {
                t.left();
                t.image().update(i -> {
                    i.setDrawable(currentPlan == -1 ? Icon.cancel : reg.set(plans.get(currentPlan).unit.uiIcon));
                    i.setScaling(Scaling.fit);
                    i.setColor(currentPlan == -1 ? Color.lightGray : Color.white);
                }).size(32).padBottom(-4).padRight(2);
                t.label(() -> currentPlan == -1 ? "@none" : plans.get(currentPlan).unit.localizedName).wrap().width(230f).color(Color.lightGray);
            }).left();
        }

        @Override
        public Object config(){
            return currentPlan;
        }

        @Override
        public void draw(){
            Draw.rect(region, x, y);

            if(currentPlan != -1){
                UnitLeagcyPlan plan = plans.get(currentPlan);
                Draw.draw(Layer.blockOver, () -> Drawf.construct(this, plan.unit, 0f, progress / plan.time, speedScl, time));
            }
            if (drawTop) {
                Draw.rect(topRegion, x, y);
            }
        }

        @Override
        public void updateTile(){
            if(!configurable){
                currentPlan = 0;
            }

            if(currentPlan < 0 || currentPlan >= plans.size){
                currentPlan = -1;
            }

            if(efficiency > 0 && currentPlan != -1){
                time += edelta() * speedScl * Vars.state.rules.unitBuildSpeed(team);
                progress += edelta() * Vars.state.rules.unitBuildSpeed(team);
                speedScl = Mathf.lerpDelta(speedScl, 1f, 0.05f);
            }else{
                speedScl = Mathf.lerpDelta(speedScl, 0f, 0.05f);
            }


            if(currentPlan != -1){
                UnitLeagcyPlan plan = plans.get(currentPlan);

                //make sure to reset plan when the unit got banned after placement
                if(plan.unit.isBanned()){
                    currentPlan = -1;
                    return;
                }

                if(progress >= plan.time && Units.canCreate(team, plan.unit)){
                    progress %= 1f;

                    Unit unit = plan.unit.create(team);
                    unit.set(x + Mathf.random(-0.001f,0.001f), y + Mathf.random(-0.001f,0.001f));
                    unit.rotation = 90f;
                    unit.add();
                    if(commandPos != null && unit.isCommandable()){
                        unit.command().commandPosition(commandPos);
                    }

                    consume();
                }

                progress = Mathf.clamp(progress, 0, plan.time);
            }else{
                progress = 0f;
            }
        }

        @Override
        public boolean shouldConsume(){
            if(currentPlan == -1) return false;
            return enabled = true;
        }

        @Override
        public int getMaximumAccepted(Item item){
            return Mathf.round(capacities[item.id] * Vars.state.rules.unitCost(team));
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return currentPlan != -1 && items.get(item) < getMaximumAccepted(item) &&
                    Structs.contains(plans.get(currentPlan).requirements, stack -> stack.item == item);
        }

        public @Nullable UnitType unit(){
            return currentPlan == - 1 ? null : plans.get(currentPlan).unit;
        }

        @Override
        public byte version(){
            return 2;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);
            write.s(currentPlan);
            TypeIO.writeVecNullable(write, commandPos);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();
            currentPlan = read.s();
            if(revision >= 2){
                commandPos = TypeIO.readVecNullable(read);
            }
        }
    }
}
