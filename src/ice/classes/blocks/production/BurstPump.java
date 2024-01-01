package ice.classes.blocks.production;

import arc.math.Mathf;
import arc.util.Time;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.Pump;

public class BurstPump extends Pump {
    public float reload = 300;
    public int multiplier = 7;
    public BurstPump(String name) {
        super(name);
    }
    @Override
    public void setBars(){
        super.setBars();
        addBar("escape", (BurstPumpBuild e) -> new Bar("bar.pump", Pal.ammo, e::fraction));
    }
    public class BurstPumpBuild extends PumpBuild {
        float reloadProgress;
        public float fraction(){
            return reloadProgress;
        }
        @Override
        public void updateTile(){
            reloadProgress += edelta() / reload;
            if(efficiency > 0 && liquidDrop != null){
                float maxPump = Math.min(liquidCapacity - liquids.get(liquidDrop), amount * pumpAmount * edelta());
                if (reloadProgress > 1) {
                    liquids.add(liquidDrop, maxPump * multiplier);
                    reloadProgress = 0;
                }

                //does nothing for most pumps, as those do not require items.
                if((consTimer += delta()) >= consumeTime){
                    consume();
                    consTimer %= 1f;
                }

                warmup = Mathf.approachDelta(warmup, maxPump > 0.001f ? 1f : 0f, warmupSpeed);
            }else{
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
            }

            totalProgress += warmup * Time.delta;

            if(liquidDrop != null){
                dumpLiquid(liquidDrop);
            }
        }
    }
}
