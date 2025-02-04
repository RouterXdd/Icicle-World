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
            genesis1
    ;
    public static void load(){
        crux1 = new LogFile("crux-getout", false);
        malis1 = new LogFile("malis-fortress-attack", false);
        venixen1 = new LogFile("ignition-log", false);
        venixen2 = new LogFile("mine-log", false);
        genesis1 = new LogFile("methane-pur-threat", false);
    }
}
