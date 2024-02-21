package ice.classes.type.meta;

import arc.func.Boolf;
import arc.math.Mathf;
import arc.math.geom.Point2;
import arc.struct.Seq;
import mindustry.entities.units.BuildPlan;
import mindustry.input.Placement;
import mindustry.world.blocks.distribution.DuctBridge;

import static mindustry.Vars.player;

public class IcePlacement extends Placement {
    private static final Seq<BuildPlan> plans1 = new Seq<>();
    public static void calculateDirectBridges(Seq<BuildPlan> plans, DuctBridge bridge){
        if(isSidePlace(plans)) return;

        //check for orthogonal placement + unlocked state
        if(!(plans.first().x == plans.peek().x || plans.first().y == plans.peek().y) || !bridge.unlockedNow()){
            return;
        }

        Boolf<BuildPlan> placeable = plan -> (plan.placeable(player.team())) ||
                (plan.tile() != null && plan.tile().block() == plan.block); //don't count the same block as inaccessible

        var result = plans1.clear();
        var team = player.team();

        outer:
        for(int i = 0; i < plans.size;){
            var cur = plans.get(i);
            result.add(cur);

            //gap found
            if(i < plans.size - 1 && placeable.get(cur) && !placeable.get(plans.get(i + 1))){

                //find the closest valid position within range
                for(int j = i + 1; j < plans.size; j++){
                    var other = plans.get(j);

                    //out of range now, set to current position and keep scanning forward for next occurrence
                    if(!bridge.positionsValid(cur.x, cur.y, other.x, other.y)){
                        //add 'missed' conveyors
                        for(int k = i + 1; k < j; k++){
                            result.add(plans.get(k));
                        }
                        i = j;
                        continue outer;
                    }else if(other.placeable(team)){
                        //found a link, assign bridges
                        cur.block = bridge;
                        other.block = bridge;

                        i = j;
                        continue outer;
                    }
                }

                //if it got here, that means nothing was found. this likely means there's a bunch of stuff at the end; add it and bail out
                for(int j = i + 1; j < plans.size; j++){
                    result.add(plans.get(j));
                }
                break;
            }else{
                i ++;
            }
        }

        plans.set(result);
    }
}
