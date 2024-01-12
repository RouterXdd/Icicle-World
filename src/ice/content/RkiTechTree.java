package ice.content;

import arc.struct.*;
import mindustry.content.*;
import mindustry.game.Objectives.*;

import static mindustry.content.TechTree.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static ice.content.IceItems.*;
import static ice.content.IceLiquids.*;
import static ice.content.IceBlocks.*;
import static ice.content.IceUnitTypes.*;
import static ice.content.IceSectors.*;

public class RkiTechTree {
    public static void load(){
        IcePlanets.rki.techTree = nodeRoot("rki", coreAngry, false, () -> {
            //items/liquids
            nodeProduce(thallium, () -> {
                nodeProduce(scrap, () -> {
                    nodeProduce(prinute, () -> {
                        nodeProduce(livesteel, () -> {

                        });
                        nodeProduce(soptin, () -> {
                            nodeProduce(polonium, () -> {
                                nodeProduce(poloniumCharge, () -> {

                                });
                            });
                            nodeProduce(methanum, () -> {
                                nodeProduce(water, () -> {

                                });

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
                    node(thalliumTunnel, Seq.with(new SectorComplete(primaryBase)),() -> {

                    });
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
                    node(laserCutter, Seq.with(new SectorComplete(primaryBase)),() -> {

                    });
                });
                node(advancedDrill, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(oreFinder, () -> {

                    });
                    node(methaneDigger, () -> {

                    });
                    node(engineDrill, () -> {
                        node(nuclearDrill, () -> {

                        });
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
                node(clockwise, () -> {
                    node(skimmer, () -> {
                        node(shatter, () -> {

                        });
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
            node(prinuteMerger, Seq.with(new OnSector(primaryBase)),() -> {
                node(siliconDissembler, () -> {
                    node(metalIncubator, () -> {

                    });
                    node(distiller, () -> {

                    });
                });
            });
            node(lamp, () -> {

            });
            //units
            node(simpleConstructor, Seq.with(new OnSector(primaryBase)), () -> {
                node(vessel, Seq.with(new OnSector(primaryBase)),() -> {

                });
                node(stem, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(xylem, Seq.with(new Research(forcedConstructor)),() -> {

                    });
                });
                node(basis, Seq.with(new SectorComplete(primaryBase)),() -> {

                });
                node(forcedConstructor, () -> {

                });
            });
            node(coreHate, () -> {

            });
            node(fallPoint, () -> {
                node(primaryBase, Seq.with(new OnSector(fallPoint)),() -> {

                });
            });
        });
    }
}
