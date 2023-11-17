package mods.Hileb.moremomostories.common.world.recipe;

import com.gq2529.momostories.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class FakeDiamondBottleRecipe extends ShapelessOreRecipe {

    public FakeDiamondBottleRecipe() {
        super(new ResourceLocation("moremomostories", "recipe_diamond_bottle"), getStack(), new ItemStack(ModItems.FAKE_DIAMOND, 1, 0),new ItemStack(ModItems.FRAUDULENT_BOTTLES, 1, 0));

        //MinecraftForge.EVENT_BUS.register(this);
    }
    public static ItemStack getStack(){
        ItemStack stack=new ItemStack(ModItems.FRAUDULENT_BOTTLES, 1, 0);
        stack.setTranslatableName("item.luck_bottle.name");
        return stack;
    }
    @Override
    public boolean isDynamic() {
        return true;
    }

//    @SubscribeEvent
//    public void onCraft(PlayerEvent.ItemCraftedEvent event){
//        try {
//            if (!event.player.world.isRemote) {
//                InventoryCrafting inv = (InventoryCrafting) event.craftMatrix;
//                if (matches(inv,event.player.world)){
//                    ModAdvancementsInit.giveAdvancement(event.player, Advancementkeys.AD_GOODLUCK_MASTER);
//                }
//            }
//        }catch (Exception ignored){
//
//        }
//    }
}
