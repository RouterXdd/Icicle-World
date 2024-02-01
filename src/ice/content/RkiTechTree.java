package ice.content;

import arc.struct.*;
import mindustry.game.Objectives.*;

import static mindustry.content.TechTree.*;
import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static ice.content.IceItems.*;
import static ice.content.IceLiquids.*;
import static ice.content.IceBlocks.*;
import static ice.content.IceUnitTypes.*;
import static ice.content.IceSectors.*;
import static ice.content.IceLogs.*;

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
                                nodeProduce(waste, () -> {

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
                    node(soptinTunnel, () -> {

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
                    node(aliveWall, () -> {

                    });
                });
            });
            //turrets
            node(bail, () -> {
                //item
                node(clockwise, () -> {
                    node(skimmer, () -> {
                        node(shatter, () -> {
                            node(burnout, () -> {

                            });
                        });
                    });
                    //power
                    node(demonCore, () -> {
                        node(discharge, () -> {

                        });
                    });
                });
                //liquid
                node(perfection, Seq.with(new SectorComplete(methanePurficate)),() -> {
                    node(crypt, () -> {

                    });
                });
            });
            //power
            node(scrapSolar, () -> {
                node(oldNode, () -> {
                    node(armoredNode, () -> {

                    });
                    node(recallNode, () -> {

                    });
                });
                node(siliconSolar, () -> {

                });
                node(decomposer, () -> {
                    node(methaneBurner, () -> {
                        node(nuclearReactor, () -> {

                        });
                    });
                });
            });
            //crafting
            node(prinuteMerger, Seq.with(new OnSector(primaryBase)),() -> {
                node(siliconDissembler, Seq.with(new OnSector(purpleFortress)),() -> {
                    node(metalIncubator, () -> {

                    });
                    node(poloniumCrucible, () -> {

                    });
                    node(distiller, () -> {

                    });
                    node(prinuteFabricator, () -> {

                    });
                });
            });
            node(lamp, () -> {

            });
            //units
            node(simpleConstructor, Seq.with(new OnSector(primaryBase)), () -> {
                node(vessel, Seq.with(new OnSector(primaryBase)),() -> {
                    node(ewer, Seq.with(new Research(forcedConstructor)),() -> {

                    });
                });
                node(stem, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(xylem, Seq.with(new Research(forcedConstructor)),() -> {

                    });
                });
                node(basis, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(fundament, Seq.with(new Research(forcedConstructor)),() -> {

                    });
                });
                node(forcedConstructor, () -> {

                });
                node(aquaConstructor, Seq.with(new OnSector(methanePurficate)), () -> {
                    node(quant, Seq.with(new OnSector(methanePurficate)),() -> {

                    });
                    node(sin, Seq.with(new OnSector(methanePurficate)),() -> {

                    });
                });
            });
            node(coreHate, () -> {
                node(coreFury, () -> {
                    //node(censure);
                });
                node(charity);
            });
            node(malice);
            node(fallPoint, () -> {
                node(primaryBase, Seq.with(new SectorComplete(fallPoint)),() -> {
                    node(methanePurficate, Seq.with(new SectorComplete(primaryBase)),() -> {
                        node(purpleFortress, Seq.with(new SectorComplete(methanePurficate), new Research(perfection)),() -> {
                            node(malis1, Seq.with(new OnSector(purpleFortress)),() -> {

                            });
                        });
                    });
                });
            });
        });
    }
}
