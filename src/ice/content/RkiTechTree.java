package ice.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.game.Objectives.*;

import static mindustry.content.TechTree.*;
import static mindustry.content.Items.*;
import static ice.content.IceItems.*;
import static ice.content.IceBlocks.*;
import static ice.content.IceUnitTypes.*;
import static ice.content.IceSectors.*;

public class RkiTechTree {
    public static void load(){
        IcePlanets.rki.techTree = nodeRoot("rki", coreAngry, false, () -> {
            //items
            nodeProduce(thallium, () -> {
                nodeProduce(scrap, () -> {
                    nodeProduce(prinute, () -> {
                        nodeProduce(livesteel, () -> {

                        });
                        nodeProduce(soptin, () -> {
                            nodeProduce(polonium, () -> {

                            });
                        });
                    });
                });
                nodeProduce(ceramicalDust, () -> {
                    nodeProduce(silicon, () -> {

                    });
                    nodeProduce(ceramic, () -> {

                    });
                });
                nodeProduce(sporeWood, () -> {

                });
            });
            //distribution
            node(thalliumConveyor, () -> {
                node(thalliumJunction, () -> {

                });
                node(splitter, () -> {

                });
                node(soptinTube, () -> {
                    node(soptinRouter, () -> {

                    });

                });
            });
            //production
            node(protoDrill, () -> {
                node(mechanicalCutter, () -> {
                    node(laserCutter, () -> {

                    });
                });
                node(advancedDrill, () -> {
                    node(oreFinder, () -> {

                    });
                    node(methaneDigger, () -> {

                    });

                });
                node(burstPump, () -> {

                });
            });
            //support
            node(bleak, () -> {
                node(shine, () -> {

                });
                node(flameDome, () -> {

                });
            });
            //walls
            node(woodWall, () -> {
                node(ceramicWall, () -> {

                });
            });
            //turrets
            node(bail, () -> {
                //item
                node(skimmer, () -> {
                    node(shatter, () -> {

                    });
                    //power
                    node(demonCore, () -> {

                    });
                });
                //liquid
                node(perfection, () -> {

                });
            });
            //power
            node(scrapSolar, () -> {
                node(oldNode, () -> {

                });
                node(siliconSolar, () -> {

                });
                node(decomposer, () -> {

                });
            });
            //crafting
            node(prinuteMerger, () -> {
                node(siliconDissembler, () -> {

                });
            });
            node(lamp, () -> {

            });
            //units
            node(simpleConstructor, () -> {
                node(vessel, () -> {

                });
                node(stem, () -> {

                });
                node(basis, () -> {

                });
            });
            node(fallPoint, () -> {

            });
        });
    }
}
