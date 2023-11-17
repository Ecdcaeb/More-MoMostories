package mods.Hileb.moremomostories.common.world.command;

public class OnGameUpdate {
//    public OnGameUpdate(){
//        MinecraftForge.EVENT_BUS.register(this);
//    }
//
//    @SubscribeEvent
//    public void onPlayerKeyDown(LivingEvent.LivingUpdateEvent event){
//        MoreMoMoSrories.Log("1");
//        World world=event.getEntity().world;
//        if(world.isRemote){
//            MoreMoMoSrories.Log("2");
//            if(event.getEntityLiving() instanceof EntityPlayer){
//                EntityPlayer player=(EntityPlayer)event.getEntityLiving();
//                UUID uuid=player.getUniqueID();
//                if(Keyboard.isKeyDown(Keyboard.KEY_0)){
//                    PlayerKeyDownEvent playerKeyDownEvent=new PlayerKeyDownEvent(uuid,Keyboard.KEY_0,player.world);
//                    MinecraftForge.EVENT_BUS.post(event);
//                    MoreMoMoSrories.Log("the world isRemote!");
//                    return;
//                }
//                PlayerKeyDownEvent playerKeyDownEvent=new PlayerKeyDownEvent(uuid,0,world);
//                MoreMoMoSrories.Log("the world isRemote2!");
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public void onPlayerKeyDown_key_0(PlayerKeyDownEvent event){
//        World world=event.getWorld();
//        if(!world.isRemote){
//            MoreMoMoSrories.Log("the world is successfully turn into noRemote!");
//        }
//    }
//    @SideOnly(Side.CLIENT)
//    private int anykeydown(){
//        for(int i=1;i<Keyboard.getKeyCount();i++){
//            if (Keyboard.isKeyDown(i))return i;
//        }
//    }
}
