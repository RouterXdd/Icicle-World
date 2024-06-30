package ice.content;

import ice.classes.type.LogFile;

public class IceLogs {
    public static LogFile
            //crux
            crux1,
            //malis
            malis1
            //venixen
            //sharded
            //genesis
    ;
    public static void load(){
        crux1 = new LogFile("crux-getout");
        malis1 = new LogFile("malis-fortress-attack");
    }
}
