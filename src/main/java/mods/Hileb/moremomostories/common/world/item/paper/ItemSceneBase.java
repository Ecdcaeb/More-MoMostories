package mods.Hileb.moremomostories.common.world.item.paper;

import mods.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.world.item.ItemInformationAdder;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
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
