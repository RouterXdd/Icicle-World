package ice.content;

import ice.classes.type.LogFile;

public class IceLogs {
    public static LogFile
            //crux
            crux1,
            //malis
            malis1,
            //venixen
            venixen1
            //sharded
            //genesis
    ;
    public static void load(){
        crux1 = new LogFile("crux-getout", false);
        malis1 = new LogFile("malis-fortress-attack", false);
        venixen1 = new LogFile("ignition-log", false);
    }
}
