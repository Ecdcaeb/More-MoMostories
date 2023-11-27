package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.forgedmomo.api.common.ModCommandFunction;
import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.Hileb.moremomostories.modplugins.ModLoadingPlugin;
import mods.Hileb.forgedmomo.api.mods.momostories.MoMoCards;
import mods.Hileb.moremomostories.common.util.TooltipHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ItemCardGetFromNull extends ItemBase {

    //private final ItemInformationAdder ItemDesc=new ItemInformationAdder("item.item_card_get_from_null.desc1","item.item_card_get_from_null.desc2");
    public ItemCardGetFromNull(){
        super("item_card_get_from_null");
        MoMoCards.registerCard(this,MoMoCards.CardType.HILEB);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
        if(!world.isRemote){
            if(player.getHeldItemMainhand().getItem()==this){
                if (ModLoadingPlugin.isLoaded_Momostories) {
                    Item card=MoMoCards.getCard(new Random().nextInt(MoMoCards.getCount()));
                    if (card!=null){
                        ItemStack stack=new ItemStack(card);
                        ModCommandFunction.give(player,stack.copy());
                    }
                    else give(player);
                }
                else{
                    give(player);
                }
                if (!player.isCreative()){
                    player.getHeldItemMainhand().shrink(1);
                }
            }
        }
        return super.onItemRightClick(world,player,hand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        TooltipHelper.onTooltip(tooltip,"item.item_card_get_from_null.desc2","item.item_card_get_from_null.desc1");
    }
    private void give(EntityPlayer player){
        ModCommandFunction.give(player,new ItemStack(ModItems.ITEM_CARD_NULL));
        ModCommandFunction.give(player,new ItemStack(ModItems.ITEM_MAIN_TR));
        ModCommandFunction.give(player,new ItemStack(ModItems.ITEM_MAIN_XK));
    }
}
