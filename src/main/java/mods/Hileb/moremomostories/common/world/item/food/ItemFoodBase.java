package mods.Hileb.moremomostories.common.world.item.food;

import mods.Hileb.forgedmomo.interfaces.IModelHolder;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.init.ModCreativeTab;
import mods.Hileb.moremomostories.common.world.item.ModItems;
import mods.Hileb.moremomostories.common.util.CommonFunctions;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTDef;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemFoodBase extends ItemFood implements IModelHolder.IItem {

    private boolean overrideRarity = false;
    private EnumRarity enumRarity = EnumRarity.COMMON;
    protected boolean showGuaSocketDesc = false;
    protected boolean shiftToShowDesc = false;
    protected boolean use_flavor = false;
    protected boolean useable = false;
    protected boolean logNBT = false;

    //for creating variants
    protected int value_main = 1;
    public ItemFoodBase setValue(int amount)
    {
        this.value_main = amount;
        return this;
    }

    public ItemFoodBase setRarity(EnumRarity enumRarity)
    {
        overrideRarity = true;
        this.enumRarity = enumRarity;
        return this;
    }

    public EnumRarity getRarity(ItemStack stack)
    {
        if (overrideRarity)
        {
            return enumRarity;
        }else {
            return super.getRarity(stack);
        }
    }

    public int addXP = 0;

    Potion potion;
    int level;
    int duration;

    public ItemFoodBase SetXP(int addXP)
    {
        this.addXP = addXP;
        return this;
    }
    public ItemFoodBase(String name, int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        setUnlocalizedName(MoreMoMoSrories.MODID+"."+name);
        setRegistryName(name);
        setCreativeTab(ModCreativeTab.IDL_MISC);

        ModItems.ITEMS.add(this);

        InitItem();
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        //MoreMoMoSrories.Log("%s:on Food Eaten", getUnlocalizedName());
        super.onFoodEaten(stack, worldIn, player);
        if (addXP > 0)
        {
            player.addExperience(addXP);
        }
    }

    public void InitItem()
    {

    }


    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {

        tooltip.add(getMainDesc(stack,world,tooltip,flag));

        if (logNBT)
        {
            tooltip.add(IDLNBTUtil.getNBT(stack).toString());
        }
    }

    @SideOnly(Side.CLIENT)
    public String descGetKey(ItemStack stack, World world, boolean showFlavor)
    {
        return showFlavor ? (stack.getUnlocalizedName() + IDLNBTDef.FLAVOR_KEY)
                : (stack.getUnlocalizedName() + IDLNBTDef.DESC_COMMON);
    }

    @SideOnly(Side.CLIENT)
    public String getMainDesc(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag)
    {
        if (CommonFunctions.isShiftPressed() || !shiftToShowDesc)
        {
            String key = descGetKey(stack,world,false);
            if (I18n.hasKey(key))
            {
                return I18n.format(key);
            }
            else
            {
                return "";
            }
        }

        if (!CommonFunctions.isShiftPressed() && use_flavor)
        {
            String key = descGetKey(stack,world,true);
            if (I18n.hasKey(key))
            {
                return I18n.format(key);
            }
            else
            {
                return "";
            }
        }

        return "";
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        //
    }
}
