package mods.Hileb.moremomostories.common.world.item.paper;

import mods.Hileb.moremomostories.common.world.advancements.ModAdvancementsInit;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.world.item.ItemInformationAdder;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemPaperBase extends ItemBase {
    public final int Gui_id;
    public final String achieve;
    public final ItemInformationAdder item_WWWW=new ItemInformationAdder("tip.item.know.no.desc","tip.item.know.no.desc");
    public ItemPaperBase(String name,int gui_id,String achieve_){
        super(name);
        Gui_id=gui_id;
        achieve=achieve_;
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onItemUse_event(PlayerInteractEvent.RightClickItem event){
        World world=event.getWorld();
        if (!world.isRemote){
            if (event.getEntityPlayer()!=null && event.getItemStack().getItem() ==this){
                EntityPlayer player=event.getEntityPlayer();
                BlockPos pos = player.getPosition();
                player.openGui(MoreMoMoSrories.instance, Gui_id, world, pos.getX(), pos.getY(), pos.getZ());
                ModAdvancementsInit.giveAdvancement(player,achieve);
                IDLNBTUtil.SetBoolean(event.getItemStack(),"paper",true);
            }
        }
    }

    public ItemInformationAdder paperDesc(){
        return new ItemInformationAdder();//this is null ItemInformation!
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (IDLNBTUtil.StackHasKey(stack,"paper") && IDLNBTUtil.GetBoolean(stack,"paper")){
            paperDesc().func_addInformation_item_base(stack,world,tooltip,flag);
        }
        else item_WWWW.func_addInformation_item_base(stack,world,tooltip,flag);
    }
}
