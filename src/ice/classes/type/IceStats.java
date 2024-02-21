package ice.classes.type;

import arc.Core;
import arc.struct.*;
import mindustry.world.meta.*;

import java.util.Locale;

public class IceStats implements Comparable<Stat>{
    public static final Seq<IceStats> allStats = new Seq<>();

    public static final Stat
    underOres = new Stat("underores"),
    droneAmount = new Stat("droneamount",  StatCat.function),
    inflictChance = new Stat("inflictchance",  StatCat.function),
    inflictStatus = new Stat("inflictstatus",  StatCat.function),
    inflictRange = new Stat("inflictrange",  StatCat.function),
    onlyDamage = new Stat("damagetake"),
    usePercent = new Stat("usePercent"),
    healPercent = new Stat("healPercent"),
    minHealth = new Stat("minhealth");

    public final StatCat category;
    public final String name;
    public final int id;

    public IceStats(String name, StatCat category){
        this.category = category;
        this.name = name;
        id = allStats.size;
        allStats.add(this);
    }

    public IceStats(String name){
        this(name, StatCat.general);
    }

    public String localized(){
        return Core.bundle.get("stat." + name.toLowerCase(Locale.ROOT));
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Stat o){
        return id - o.id;
    }
}
