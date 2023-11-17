package mods.Hileb.moremomostories.common.util.ticker;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
public class AttackRenderTask {
    /**
     * 因为前端没LivingHurtEvent，你需要手动在new一个put进去
     * 下面是一个Living的示例：
     *
     *
     * file:EntityZFP.java
     *
     * @Override
     * public boolean attackEntityFrom(DamageSource source, float amount) {
     *     boolean b= super.attackEntityFrom(source, amount);
     *
     *     if (world.isRemote){
     *         LivingHurtEvent event=new LivingHurtEvent(this,source,amount);
     *         AttackRenderTask.put(event);
     *     }
     *     return b;
     * }
     *
     */
    public static List<AttackRenderTask> taskList=new ArrayList<>();
    private static void render(AttackRenderTask task,float x,float y, float z){
        RenderManager manager= Minecraft.getMinecraft().getRenderManager();
        double d0 =task.event.getEntityLiving().getDistanceSq(manager.renderViewEntity);
        int maxDistance=64;
        String str=String.format("%f",task.event.getAmount());

        if (d0 <= (double)(maxDistance * maxDistance))
        {
            boolean flag = task.event.getEntityLiving().isSneaking();
            float f = manager.playerViewY;
            float f1 = manager.playerViewX;
            boolean flag1 = manager.options.thirdPersonView == 2;
            float f2 = task.event.getEntityLiving().height + 0.5F - (flag ? 0.25F : 0.0F);
            int i = "deadmau5".equals(str) ? -10 : 0;
            EntityRenderer.drawNameplate(manager.getFontRenderer(), str, (float)x, (float)y + f2+0.5f, (float)z, i, f, f1, flag1, flag);
        }
    }
    public static void put(LivingHurtEvent event){
        if (event.getEntityLiving().world.isRemote){
            for(int i=0;i<taskList.size();i++){
                if (taskList.get(i).event.getEntityLiving()==event.getEntityLiving()){
                    taskList.set(i,new AttackRenderTask(event));
                    return;
                }
            }
            taskList.add(new AttackRenderTask(event));
        }
    }
    @SubscribeEvent
    public static void render(RenderLivingEvent.Post event){
        /**这里曾经是事故高发区
         * **/
        List<AttackRenderTask> remove=new ArrayList<>();
        if (taskList!=null && taskList.size()>0){
            for(AttackRenderTask task:taskList){
                if (task!=null && !task.isEmpty()){
                    if (task.event.getEntityLiving()==event.getEntity()){
                        if (task.ticks+TICK<event.getEntity().world.getTotalWorldTime()){
                            remove.add(task);
                            return;
                        }else if (task.event.getAmount()>0.0f){
                            render(task,(float)event.getX(),(float)event.getY(),(float)event.getZ());
                        }
                    }
                }
            }
            for(AttackRenderTask task:remove){
                taskList.remove(task);
            }
        }
    }
    public static final int TICK=25;
    public LivingHurtEvent event;
    public long ticks;
    public AttackRenderTask(LivingHurtEvent eventIn){
        event=eventIn;
        ticks=eventIn.getEntityLiving().world.getTotalWorldTime();
    }
    public boolean isEmpty(){
        return event==null || event.getEntityLiving()==null || event.getSource()==null;
    }
}
