package ice.content;

import ice.classes.type.LogFile;

public class IceLogs {
    public static LogFile
            //crux
            crux1,
            //malis
            malis1,
            //venixen
            venixen1, venixen2,
            //sharded
            //genesis
            genesis1, genesis2,
            //origin
            origin1
    ;
    public static void load(){
        crux1 = new LogFile("crux-getout", false, "crux");
        malis1 = new LogFile("malis-fortress-attack", false, "malis");
        venixen1 = new LogFile("ignition-log", false, "venixen");
        venixen2 = new LogFile("mine-log", false, "venixen");
        genesis1 = new LogFile("methane-pur-threat", false, "genesis");
        genesis2 = new LogFile("execution-prelude", false, "genesis");
        origin1 = new LogFile("memory", false, "origin");
    }
}
