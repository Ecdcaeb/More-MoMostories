package com.Hileb.moremomostories.common.capabilities.nametag;

import com.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
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
        if (!event.getObject().hasCapability(CAPABILITY, null)) {
            int a=new Random().nextInt(14);
            event.addCapability(new ResourceLocation(MoreMoMoSrories.MODID, "cap_name_tag"), new CapabilityNameTagProvider(new NameTagContainer("tag." + a + ".name")));
        }
    }
    /*

tag.1.name=锐利
tag.2.name=高端
tag.3.name=强力
tag.4.name=碎裂
tag.5.name=破损
tag.6.name=粗劣
tag.7.name=致伤
tag.8.name=强劲
tag.9.name=粗鲁
tag.10.name=软弱
tag.11.name=无情
tag.12.name=神级
tag.13.name=恶魔
tag.14.name=狂热
    */
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
