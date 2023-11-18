package mods.Hileb.moremomostories.common.world.item.myItems;

import mods.Hileb.moremomostories.common.world.entity.entity.living.EntityZFP;
import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.Hileb.forgedmomo.utils.CommonFunctions;
import mods.Hileb.forgedmomo.api.momostories.MoMoCards;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemCardAddZFP extends ItemBase {

    public ItemCardAddZFP(String name, CreativeTabs tabs){
        super(name, tabs, 1);
        setDesc("desc.cardzfp.desc","desc.cardzfp2.desc");
        CommonFunctions.addToEventBus(this);
        MoMoCards.registerCard(this,MoMoCards.CardType.HILEB);
    }

    @SubscribeEvent
    public  void PlayerRightClickBlockEvent(PlayerInteractEvent.LeftClickBlock event){
        World world=event.getWorld();
        if(!world.isRemote){
            if(event.getEntityPlayer().getHeldItemMainhand().getItem() == this) {
                event.getWorld().addWeatherEffect(new EntityLightningBolt(event.getWorld(),event.getPos().getX(),event.getPos().getY(),event.getPos().getZ(),true));
                EntityZFP sakura=new EntityZFP(world);
                sakura.setPosition(event.getPos().getX(),event.getPos().getY()+1,event.getPos().getZ());
                event.getWorld().spawnEntity(sakura);
                event.getItemStack().setCount(0);
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        ModItems.subItems(tab,items);
    }
}
