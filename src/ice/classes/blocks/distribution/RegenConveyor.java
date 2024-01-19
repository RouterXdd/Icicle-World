package ice.classes.blocks.distribution;

import arc.math.Mathf;
import arc.util.*;
import mindustry.world.blocks.distribution.*;

public class RegenConveyor extends Conveyor {
    private static final float itemSpace = 0.5f;
    public float regenAmount = 0.2f;
    public RegenConveyor(String name) {
        super(name);
        itemCapacity = 4;
    }
    public class RegenConveyorBuild extends ConveyorBuild {
        @Override
        public void updateTile(){
            minitem = 1f;
            mid = 0;
            if (damaged()){
                heal(regenAmount*Time.delta);
            }

            //skip updates if possible
            if(len == 0){
                clogHeat = 0f;
                sleep();
                return;
            }

            float nextMax = aligned ? 1f - Math.max(itemSpace - nextc.minitem, 0) : 1f;
            float moved = speed * edelta();

            for(int i = len - 1; i >= 0; i--){
                float nextpos = (i == len - 1 ? 100f : ys[i + 1]) - itemSpace;
                float maxmove = Mathf.clamp(nextpos - ys[i], 0, moved);

                ys[i] += maxmove;

                if(ys[i] > nextMax) ys[i] = nextMax;
                if(ys[i] > 0.5 && i > 0) mid = i - 1;
                xs[i] = Mathf.approach(xs[i], 0, moved*2);

                if(ys[i] >= 1f && pass(ids[i])){
                    //align X position if passing forwards
                    if(aligned){
                        nextc.xs[nextc.lastInserted] = xs[i];
                    }
                    //remove last item
                    items.remove(ids[i], len - i);
                    len = Math.min(i, len);
                }else if(ys[i] < minitem){
                    minitem = ys[i];
                }
            }

            if(minitem < itemSpace + (blendbits == 1 ? 0.3f : 0f)){
                clogHeat = Mathf.approachDelta(clogHeat, 1f, 1f / 60f);
            }else{
                clogHeat = 0f;
            }


            noSleep();
        }
    }
}
