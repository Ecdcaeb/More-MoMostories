package com.Hileb.moremomostories.common.init;

import com.Hileb.moremomostories.common.world.item.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModCreativeTab {
	public static final CreativeTabs IDL_MISC = new CreativeTabs(CreativeTabs.getNextID(), "itzmxMiscTab")
	{
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.ITEM_CARD_ZFP);
        }
    };
    public static final CreativeTabs GAME_SOUNDS = new CreativeTabs(CreativeTabs.getNextID(), "sounds")
    {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ModItems.ITEM_YTXSY_SOUND);
        }
    };
}
