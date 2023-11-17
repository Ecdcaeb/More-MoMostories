package mods.Hileb.moremomostories.common.capabilities.nametag;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/8 22:32
 **/
@Mod.EventBusSubscriber
public class CapabilityNameTag {
    @CapabilityInject(CapNameTag.class)
    public static Capability<CapNameTag> CAPABILITY = null;
    public static void register(){
        CapabilityManager.INSTANCE.register(CapNameTag.class, new Capability.IStorage<CapNameTag>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<CapNameTag> capability, CapNameTag instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<CapNameTag> capability, CapNameTag instance, EnumFacing side, NBTBase nbt) {
                if (nbt instanceof NBTTagCompound){
                    instance.deserializeNBT((NBTTagCompound) nbt);
                }
            }
        },()->CapNameTag.EMPTY);
    }
    @SubscribeEvent
    public static void onRegister(AttachCapabilitiesEvent<ItemStack> event){
        if (event.getObject().getItem() instanceof ItemTool){
            if (!event.getObject().hasCapability(CAPABILITY, null)) {
                int a=new Random().nextInt(13)+1;
                event.addCapability(new ResourceLocation(MoreMoMoSrories.MODID, "cap_name_tag"), new CapabilityNameTagProvider(new NameTagContainer("tag." + a + ".name")));
            }
        }
    }
    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event){
        if (event.getItemStack().hasCapability(CAPABILITY,null)){
            CapNameTag tag=event.getItemStack().getCapability(CAPABILITY,null);
            String name=event.getToolTip().get(0);
            String trueName= "§e"+ I18n.format(tag.getTagTranslateKey())+"§f"+" "+name;
            event.getToolTip().set(0,trueName);
        }
    }
}
