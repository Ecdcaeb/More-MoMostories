package mods.Hileb.moremomostories.common.keybinding;

import mods.Hileb.forgedmomo.api.common.keyboard.F2MKeyRegistryEvent;
import mods.Hileb.forgedmomo.api.common.keyboard.KeyBindingHolder;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityBike;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/25 13:46
 **/
@Mod.EventBusSubscriber(modid=MoreMoMoSrories.MODID)
public class ModKeyBinding{
    public static KeyBindingHolder forward;
    public static KeyBindingHolder left;
    public static KeyBindingHolder back;
    public static KeyBindingHolder right;
    public static KeyBindingHolder jump;
    @SubscribeEvent
    public static void onRegister(F2MKeyRegistryEvent event){
        forward=new KeyBindingHolder(event,new ResourceLocation("minecraft","forward"),"key.forward");
        left=new KeyBindingHolder(event,new ResourceLocation("minecraft","left"),"key.left").setPressedHandler(
                (playerMP -> {
                    if (playerMP.isRiding() && playerMP.getRidingEntity() instanceof EntityBike){
                        EntityBike bike=(EntityBike) playerMP.getRidingEntity();
                        bike.isLockedLook=(!bike.isLockedLook);
                    }
                })
        );
        back=new KeyBindingHolder(event,new ResourceLocation("minecraft","back"),"key.back");
        right=new KeyBindingHolder(event,new ResourceLocation("minecraft","right"),"key.right");
        jump=new KeyBindingHolder(event,new ResourceLocation("minecraft","jump"),"key.jump");
    }
}
