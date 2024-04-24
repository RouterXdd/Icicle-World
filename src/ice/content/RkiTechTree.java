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
                        nodeProduce(livesteel, () -> {});
                        nodeProduce(soptin, () -> {
                            nodeProduce(polonium, () -> {
                                nodeProduce(poloniumCharge, () -> {});
                            });
                            nodeProduce(methanum, () -> {
                                nodeProduce(water, () -> {});
                                nodeProduce(waste, () -> {});
                            });
                        });
                    });
                });
                nodeProduce(ceramicalDust, () -> {
                    nodeProduce(silicon, () -> {});
                    nodeProduce(ceramic, () -> {
                        nodeProduce(chalkStone, () -> {});
                    });
                });
                nodeProduce(sporeWood, () -> {});
            });
            //distribution
            node(thalliumConveyor, () -> {
                node(thalliumJunction, () -> {
                    node(thalliumTunnel, Seq.with(new SectorComplete(primaryBase)),() -> {});
                });
                node(splitter);
                node(soptinTube, () -> {
                    node(soptinRouter);
                    node(soptinTunnel);
                });
            });
            //production
            node(protoDrill, () -> {
                node(mechanicalCutter, () -> {
                    node(laserCutter, Seq.with(new SectorComplete(primaryBase)),() -> {});
                });
                node(advancedDrill, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(oreFinder);
                    node(methaneDigger);
                    node(engineDrill, Seq.with(new OnSector(trinityCollumn)),() -> {
                        node(nuclearDrill);
                    });
                });
                node(burstPump, () -> {
                    node(pulsePump, Seq.with(new OnSector(trinityCollumn)), () -> {

                    });
                });
            });
            //support
            node(bleak, () -> {
                node(shine, Seq.with(new SectorComplete(trinityCollumn)),() -> {
                    node(repairPylon);
                });
                node(flameDome,Seq.with(new OnSector(methanePurficate)),() -> {
                    node(forceDome);
                });
            });
            //walls
            node(woodWall, () -> {
                node(ceramicWall, () -> {
                    node(aliveWall);
                    node(conductiveWall);
                    node(bitWall);
                });
            });
            //turrets
            node(bail, () -> {
                //item
                node(clockwise, () -> {
                    node(skimmer, () -> {
                        node(shatter, () -> {
                            node(burnout, () -> {
                                node(calamity, () -> {

                                });
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
                    node(armoredNode, Seq.with(new OnSector(trinityCollumn)),() -> {

                    });
                    node(recallNode, Seq.with(new OnSector(trinityCollumn)),() -> {

                    });
                });
                node(siliconSolar, () -> {
                    node(poloniumPanel);
                });
                node(decomposer, () -> {
                    node(methaneBurner, Seq.with(new OnSector(trinityCollumn)),() -> {
                        node(nuclearReactor, () -> {

                        });
                    });
                    node(liquidTurbine, () -> {

                    });
                });
            });
            //crafting
            node(prinuteMerger, Seq.with(new OnSector(primaryBase)),() -> {
                node(siliconDissembler, Seq.with(new OnSector(purpleFortress)),() -> {
                    node(quarry);
                    node(metalIncubator, () -> {
                        node(poloniumCrucible);
                        node(denseStructurer);
                    });
                    node(distiller);
                    node(prinuteFabricator);
                });
            });
            node(lamp, () -> {

            });
            //units
            node(simpleConstructor, Seq.with(new OnSector(primaryBase)), () -> {
                node(vessel, Seq.with(new OnSector(primaryBase)),() -> {
                    node(ewer, Seq.with(new Research(forcedConstructor)),() -> {
                        node(decanter, Seq.with(new Research(omegaConstructor)),() -> {});
                    });
                });
                node(stem, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(xylem, Seq.with(new Research(forcedConstructor)),() -> {
                        node(stalk, Seq.with(new Research(omegaConstructor)),() -> {});
                    });
                });
                node(basis, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(fundament, Seq.with(new Research(forcedConstructor)),() -> {
                        node(groundwork, Seq.with(new Research(omegaConstructor)),() -> {});
                    });
                });
                node(forcedConstructor, () -> {
                    node(omegaConstructor, () -> {

                    });
                });
                node(lustrousConstructor, () -> {
                    node(blaze, () -> {

                    });
                });
                node(aquaConstructor, Seq.with(new OnSector(methanePurficate)), () -> {
                    node(quant, Seq.with(new OnSector(methanePurficate)),() -> {

                    });
                    node(sin, Seq.with(new OnSector(methanePurficate)),() -> {

                    });
                });
            });
            node(coreHate, Seq.with(new SectorComplete(trinityCollumn)),() -> {
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
                            node(malis1, Seq.with(new OnSector(purpleFortress)),() -> {});
                            node(trinityCollumn, Seq.with(new SectorComplete(purpleFortress)),() -> {

                            });
                        });
                    });
                });
            });
        });
    }
}
