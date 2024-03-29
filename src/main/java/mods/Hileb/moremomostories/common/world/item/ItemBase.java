package mods.Hileb.moremomostories.common.world.item;

import mods.Hileb.forgedmomo.interfaces.IModelHolder;
import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.init.ModCreativeTab;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTDef;
import mods.Hileb.forgedmomo.utils.nbt.IDLNBTUtil;
import mods.Hileb.moremomostories.common.world.item.interfaces.IEntityItemX;
import mods.Hileb.forgedmomo.utils.CommonFunctions;
import mods.Hileb.forgedmomo.utils.StringUtil;
import mods.Hileb.moremomostories.common.util.TooltipHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBase extends Item implements IModelHolder.IItem {
	private boolean overrideRarity = false;
	private EnumRarity enumRarity = EnumRarity.COMMON;
	protected boolean showGuaSocketDesc = false;
	protected boolean shiftToShowDesc = false;
	protected boolean use_flavor = false;
	protected boolean useable = false;
	private boolean isRangedWeapon = false;
	protected boolean logNBT = false;
	protected boolean glitters = false;

	public boolean hasItemDescShiftDown=false;

	public String itemDescStringShiftUp=null;
	public String itemDescStringShiftDown=null;

	protected static final UUID OFF_HAND_MODIFIER = UUID.fromString("9271eeea-5f74-4e12-97b6-7cf3c60ef7a0");
	protected static final UUID MAIN_HAND_MODIFIER = UUID.fromString("7d766720-0695-46c6-b320-44529f3da63f");

	protected static final UUID POWER_UP_MODIFIER = UUID.fromString("dc8a0a25-24c4-43a9-bfc3-e31e431f4ebf");
	protected static final UUID POWER_UP_MODIFIER_PERCENT = UUID.fromString("9236a0fe-8f9b-4ede-80a3-05386216d06f");

	public ItemBase(String name)
	{
		setUnlocalizedName(MoreMoMoSrories.MODID+"."+name);
		setRegistryName(name);
		setCreativeTab(ModCreativeTab.IDL_MISC);
		ModItems.ITEMS.add(this);

		InitItem();
	}

	public ItemBase(String name, CreativeTabs tabs){
		ItemBaseuseTab(name,tabs);
	}
	public ItemBase(String name,CreativeTabs tabs,int  maxStackSize){
		this.maxStackSize=maxStackSize;
		ItemBaseuseTab(name,tabs);


	}
	public ItemBase(String name,int  maxStackSize){
		this.maxStackSize=maxStackSize;
		ItemBaseuseTab(name,ModCreativeTab.IDL_MISC);

	}
	public void ItemBaseuseTab(String name, CreativeTabs tabs){
		if (tabs == null){
			setUnlocalizedName(name);
			setRegistryName(name);
			ModItems.ITEMS.add(this);

			InitItem();
		}
		else{
			setUnlocalizedName(name);
			setRegistryName(name);
			setCreativeTab(tabs);
			ModItems.ITEMS.add(this);

			InitItem();
		}
	}
	public Item setDesc(String shiftDown,String shiftUp){
		hasItemDescShiftDown=true;
		itemDescStringShiftDown=shiftDown;
		itemDescStringShiftUp=shiftUp;
		return this;
	}


	protected ItemBase setGlitter()
	{
		glitters = true;
		return this;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return stack.isItemEnchanted() || glitters;
	}


	public ItemBase setRangedWeapon()
	{
		isRangedWeapon = true;
		return this;
	}

	public ItemBase setRarity(EnumRarity enumRarity)
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

	public void InitItem()
	{
	}

	public boolean isRangedWeaponItem()
	{
		return isRangedWeapon;
	}

	public String GetStringForThisByKey(String key)
	{
		return CommonFunctions.GetStringLocalTranslated(getUnlocalizedName() + key);
	}

	public String GetBasicDesc()
	{
		return CommonFunctions.GetStringLocalTranslated(getUnlocalizedName() + IDLNBTDef.DESC_COMMON);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
		//Particle;
		super.onUsingTick(stack, living, count);
		//MoreMoMoSrories.LogWarning(String.format("base onUsingTick %s",count));

		if (living.world.isRemote)
		{
			clientUseTick(stack, living, getMaxItemUseDuration(stack) - count);
		}
		else
		{
			serverUseTick(stack, living, getMaxItemUseDuration(stack) - count);
		}
	}


	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		if (useable)
		{
			player.setActiveHand(hand);
			ItemStack stack = player.getHeldItem(hand);
			boolean result = onUseSimple(player, stack);
			if (result)
			{
				return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
			}
			else {
				return ActionResult.newResult(EnumActionResult.FAIL, stack);
			}
		}
		else {
			return super.onItemRightClick(world, player, hand);
		}
	}

	public boolean onUseSimple(EntityPlayer player, ItemStack stack)
	{
		return true;
	}

	public void clientUseTick(ItemStack stack, EntityLivingBase living, int count)
	{

	}

	public void serverUseTick(ItemStack stack, EntityLivingBase living, int count)
	{

	}




	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		List<String> fakeList=new ArrayList<>();

		super.addInformation(stack,world,fakeList,flag);
		if(hasItemDescShiftDown){
			TooltipHelper.onTooltip(fakeList,itemDescStringShiftUp,itemDescStringShiftDown);
			return;
		}
		else{
			tooltip.add(getMainDesc(stack,world,tooltip,flag));

			if (logNBT) {
				fakeList.add(IDLNBTUtil.getNBT(stack).toString());
			}
		}
		for(String s:fakeList){
			tooltip.addAll(StringUtil.spiltStringLn(s));
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

	public void onMouseFire(EntityPlayer player)
	{

	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		//super.getSubItems(tab, items);
	}
	@Nullable
	@Override
	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
		if (this instanceof IEntityItemX){
			return ((IEntityItemX)this).createEntityItem(world,location,itemstack);
		}
		return super.createEntity(world, location, itemstack);
	}
}
