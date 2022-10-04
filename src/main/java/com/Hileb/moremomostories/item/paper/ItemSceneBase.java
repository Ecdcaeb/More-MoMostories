package com.Hileb.moremomostories.item.paper;

import com.Hileb.moremomostories.Advancements.ModAdvancementsInit;
import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.item.ItemInformationAdder;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemSceneBase extends ItemBase {
    public final String achieve;
    public final ItemInformationAdder item_WWWW=new ItemInformationAdder("tip.item.know.no.desc","tip.item.know.no.desc");
    public ItemSceneBase(String name,String achieve_){
        super(name);
        achieve=achieve_;
        MinecraftForge.EVENT_BUS.register(this);
    }
    public ItemInformationAdder sceneDesc(){
        return new ItemInformationAdder();//this is null ItemInformation!
    }
    @SubscribeEvent
    public void onItemClick(PlayerInteractEvent.LeftClickBlock event){
        //only once if it is Left
        World world=event.getWorld();
        if (!world.isRemote){
            if (event.getEntityPlayer()!=null){
                EntityPlayer player=event.getEntityPlayer();
                if (player.getHeldItemMainhand().getItem()==this){
                    IDLNBTUtil.SetBoolean(event.getItemStack(),"paper",true);
                    ModAdvancementsInit.giveAdvancement(player,achieve);
                    doScene(event);
                }
            }
        }

    }
    public void doScene(PlayerInteractEvent.LeftClickBlock event){
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (IDLNBTUtil.StackHasKey(stack,"paper") && IDLNBTUtil.GetBoolean(stack,"paper")){
            sceneDesc().func_addInformation_item_base(stack,world,tooltip,flag);
        }
        else item_WWWW.func_addInformation_item_base(stack,world,tooltip,flag);
    }
}
