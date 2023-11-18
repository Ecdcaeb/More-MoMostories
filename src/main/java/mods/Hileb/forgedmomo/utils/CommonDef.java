package mods.Hileb.forgedmomo.utils;

import java.text.SimpleDateFormat;

public class CommonDef {
    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");

    /**
     * Flag 1 will cause a block update. Flag 2 will send the change to clients. Flag 4 will prevent the block from
     * being re-rendered, if this is a client world. Flag 8 will force any re-renders to run on the main thread instead
     * of the worker pool, if this is a client world and flag 4 is clear. Flag 16 will prevent observers from seeing
     * this change. Flags can be OR-ed
     */
    public static class BlockFlags
    {
        public static int BLOCK_UPDATE = 1;
        public static int TO_CLIENT = 2;
        public static int CLIENT_DONT_RENDER = 4;
        public static int FORCE_RENDER = 8;
        public static int IGNORE_OB = 16;
    }


}
