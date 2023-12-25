package ice.classes.type;

import arc.Core;
import arc.struct.*;
import mindustry.world.meta.*;

import java.util.Locale;

public class IceStats implements Comparable<Stat>{
    public static final Seq<IceStats> allStats = new Seq<>();

    public static final Stat
    usePercent = new Stat("usePercent"),
    healPercent = new Stat("healPercent"),
    weaponModules = new Stat("weaponModules", StatCat.function);

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
