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
                            nodeProduce(denseAlloy, () -> {});
                        });
                        nodeProduce(soptin, () -> {
                            nodeProduce(polonium, () -> {
                                nodeProduce(poloniumCharge, () -> {});
                            });
                            nodeProduce(methanum, () -> {
                                nodeProduce(water, () -> {
                                    nodeProduce(enmitium, () -> {});
                                });
                                nodeProduce(waste, () -> {});
                            });
                        });
                    });
                });
                nodeProduce(ceramicalDust, () -> {
                    nodeProduce(silicon, () -> {});
                    nodeProduce(ceramic, () -> {
                        nodeProduce(chalkStone, () -> {});
                        nodeProduce(cerymec, () -> {});
                    });
                });
                nodeProduce(sporeWood, () -> {});
            });
            //distribution
            node(thalliumConveyor, () -> {
                node(thalliumSorter, () -> {});
                node(thalliumJunction, () -> {
                    node(thalliumTunnel, Seq.with(new SectorComplete(primaryBase)),() -> {});
                });
                node(splitter);
                node(soptinTube, () -> {
                    node(soptinRouter, () -> {
                        node(soptinStash, () -> {
                            node(soptinCrate);
                        });
                    });
                    node(soptinTunnel);
                });
            });
            //production
            node(protoDrill, () -> {
                node(mechanicalCutter, () -> {
                    node(laserCutter, Seq.with(new SectorComplete(primaryBase)),() -> {});
                });
                node(advancedDrill, Seq.with(new SectorComplete(primaryBase)),() -> {
                    node(oreFinder, Seq.with(new OnSector(resurgent)),() -> {});
                    node(methaneDigger, Seq.with(new OnSector(resurgent)),() -> {});
                    node(engineDrill, Seq.with(new OnSector(trinityColumn)),() -> {
                        node(nuclearDrill);
                    });
                });
                node(electricCrusher, Seq.with(new SectorComplete(paths)), () -> {});
                node(burstPump, () -> {
                    node(pulsePump, Seq.with(new OnSector(trinityColumn)), () -> {

                    });
                });
            });
            //support
            node(bleak, () -> {
                node(shine, Seq.with(new SectorComplete(trinityColumn)),() -> {
                    node(repairPylon, Seq.with(new SectorComplete(flameOcean)), () -> {});;
                });
                node(flameDome,Seq.with(new OnSector(methanePurification)),() -> {
                    node(forceDome);
                });
            });
            //walls
            node(woodWall, () -> {
                node(ceramicWall, () -> {
                    node(aliveWall, () -> {
                        node(aliveWallLarge);
                    });
                    node(conductiveWall, () -> {
                        node(conductiveWallLarge);
                    });
                    node(bitWall, () -> {
                        node(bitWallLarge);
                    });
                    node(ceramicWallLarge);
                });
                node(woodWallLarge);
            });
            //turrets
            node(bail, () -> {
                //item turrets
                node(clockwise, () -> {
                    node(skimmer, () -> {
                        node(shatter, () -> {
                            node(burnout, () -> {
                                node(calamity, () -> {

                                });
                            });
                        });
                    });
                    //power turrets
                    node(cremator, Seq.with(new SectorComplete(purpleFortress)), () -> {
                        node(demonCore, Seq.with(new OnSector(flameOcean)),() -> {
                            node(discharge, () -> {

                            });
                        });
                    });

                });
                //liquid turrets
                node(perfection, Seq.with(new SectorComplete(methanePurification)),() -> {
                    node(crypt, () -> {

                    });
                });
            });
            //power
            node(scrapSolar, () -> {
                node(oldNode, () -> {
                    node(armoredNode, Seq.with(new OnSector(trinityColumn)),() -> {

                    });
                    node(recallNode, Seq.with(new OnSector(trinityColumn)),() -> {

                    });
                });
                node(siliconSolar, () -> {
                    node(poloniumPanel);
                });
                node(decomposer, () -> {
                    node(methaneBurner, Seq.with(new OnSector(trinityColumn)),() -> {
                        node(nuclearReactor, Seq.with(new SectorComplete(brokenComplex)), () -> {

                        });
                    });
                    node(liquidTurbine, () -> {

                    });
                });
            });
            //crafting
            node(prinuteMerger, Seq.with(new OnSector(primaryBase)),() -> {
                node(siliconDissembler, Seq.with(new OnSector(purpleFortress)),() -> {
                    node(quarry, Seq.with(new OnSector(resurgent)),() -> {});
                    node(metalIncubator, () -> {
                        node(poloniumCrucible, Seq.with(new SectorComplete(brokenComplex)), () ->{});
                        node(denseStructurer, Seq.with(new SectorComplete(aggressiveTactic)), () ->{});
                        node(enmitiumCrucible, Seq.with(new SectorComplete(flameOcean)), () ->{});
                    });
                    node(distiller);
                    node(prinuteFabricator, () -> {
                        node(ceramicSmelter, Seq.with(new SectorComplete(resurgent)),() -> {
                            node(ceramicalAssembler, Seq.with(new OnSector(paths)),() -> {});
                        });
                    });
                });
            });
            //lamps
            node(lamp, () -> {
                node(largeLamp, () -> {

                });
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
                    node(omegaConstructor, Seq.with(new Research(enmitiumCrucible)),() -> {

                    });
                });
                node(lustrousConstructor, () -> {
                    node(blaze, () -> {

                    });
                });
                node(aquaConstructor, Seq.with(new OnSector(methanePurification)), () -> {
                    node(quant, Seq.with(new OnSector(methanePurification)),() -> {
                        node(chronon, Seq.with(new Research(atlanticConstructor)),() -> {

                        });
                    });
                    node(sin, Seq.with(new OnSector(methanePurification)),() -> {
                        node(zen, Seq.with(new Research(atlanticConstructor)),() -> {

                        });
                    });
                    node(atlanticConstructor, () -> {

                    });
                });
            });
            //executioners
            node(yellow, Seq.with(new SectorComplete(fallPoint)), () -> {
                node(purple, Seq.with(new SectorComplete(purpleFortress)), () -> {
                    node(blue, Seq.with(new SectorComplete(paths)), () -> {
                        node(red, Seq.with(new SectorComplete(aggressiveTactic)), () -> {

                        });
                    });
                });
            });
            //cores
            node(coreHate, Seq.with(new SectorComplete(trinityColumn)),() -> {
                node(coreFury, Seq.with(new SectorComplete(brokenComplex)), () -> {
                    node(censure);
                });
                node(charity);
            });
            node(malice);
            //sectors
            node(fallPoint, () -> {
                node(crux1, Seq.with(new SectorComplete(fallPoint)),() -> {});
                node(primaryBase, Seq.with(new SectorComplete(fallPoint)),() -> {
                    node(ignintion, () -> {
                        node(venixen1, Seq.with(new SectorComplete(ignintion)),() -> {});
                    });
                    node(methanePurification, Seq.with(new SectorComplete(primaryBase)),() -> {
                        node(purpleFortress, Seq.with(new SectorComplete(methanePurification), new Research(perfection)),() -> {
                            node(malis1, Seq.with(new OnSector(purpleFortress)),() -> {});
                            node(trinityColumn, Seq.with(new SectorComplete(purpleFortress)),() -> {
                                node(resurgent, Seq.with(new SectorComplete(trinityColumn)),() -> {
                                    node(paths, Seq.with(new SectorComplete(resurgent), new Research(coreHate)),() -> {
                                        node(brokenComplex, Seq.with(new SectorComplete(paths), new Research(shatter), new Research(engineDrill)),() -> {
                                            node(flameOcean, Seq.with(new SectorComplete(brokenComplex), new Research(atlanticConstructor)),() -> {
                                                node(aggressiveTactic, Seq.with(new SectorComplete(flameOcean)),() -> {

                                                });
                                            });
                                        });
                                    });
                                });
                            });
                        });
                        node(genesis1, Seq.with(new SectorComplete(methanePurification)),() -> {});
                    });
                });
            });
        });
    }
}
