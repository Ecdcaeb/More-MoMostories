package com.Hileb.moremomostories.item;

import com.Hileb.moremomostories.init.ModCreativeTab;
import com.Hileb.moremomostories.item.food.ItemFoodBase;
import com.Hileb.moremomostories.item.food.ItemFoodSyzg;
import com.Hileb.moremomostories.item.paper.ItemPaper1;
import com.Hileb.moremomostories.item.paper.ItemScene1;
import com.Hileb.moremomostories.item.weapon.*;
import com.gq2529.momostories.item.ModItemStoryboards.FraudulentBottles;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	//Basic





	public static final Item ITEM_COPYER =new ItemCopier("item_copyer");//复制卡
	public static final Item ITEM_CARD_ZFP =new ItemCardAddZFP("item_card_zfp", ModCreativeTab.IDL_MISC);//煮饭婆


	public static final Item ITEM_CARD_GET_FROM_NULL=new ItemCardGetFromNull();//无中生有
	public static final Item ITEM_CARD_NULL=new ItemBase("item_card_null");//空卡


    public static final Item ITEM_AXE_DIA = new ItemAxeDiamond_();//欺诈钻石镐子


	//public static final Item SlashBlade_ITZMX_RAINBOW = new ItemSlashBladeItzmx(TOOL_MATERIAL_RAINBOW,7f,"item_bdj_rainbow");
    //核心：
    public static final Item ITEM_MAIN_XK=new ItemBase("item_main_xk",new ItemInformationAdder("com.hileb.itzm.main.desc"));
    public static final Item ITEM_MAIN_TR=new ItemBase("item_main_tr",new ItemInformationAdder("com.hileb.itzm.main.desc"));
    public static final Item ITEM_MAIN_SY=new ItemBase("item_main_sy",new ItemInformationAdder("com.hileb.itzm.main.desc"));
    public static final Item ITEM_MAIN_WJ=new ItemBase("item_main_wj",new ItemInformationAdder("com.hileb.itzm.main.desc"));
    public static final Item ITEM_MAIN_NULL=new ItemBase("item_main_null",new ItemInformationAdder("com.hileb.itzm.main.desc"));

    public static final Item ITEM_PAPER_HILEB_A=new ItemBase("item_paper_hileb_a",new ItemInformationAdder("tooltip.paper.hileb.a.desc","tooltip.paper.hileb.a.desc"));
    public static final Item ITEM_FOOD_SYZG=new ItemFoodSyzg();//岁月煲

    public static final Item ITEM_CARD_MI_CARD=new ItemBase("item_card_mi",new ItemInformationAdder("com.hileb.item.mi.desc"));//万写的宝镜
    public static final Item ITEM_CARD_MI_ITEM=new ItemBase("item_card_mi_item",new ItemInformationAdder("com.hileb.item.mi.desc"));//


    public static final Item ITEM_RABBIT=new ItemBase("achievement");//成就
    //public static final Item ITEM_LUCK_BOTTLE=new FraudulentBottles("luck_bottle");//幸运之瓶
    public static final Item ITEM_TURN_INTO=new ItemTurnInto("item_turn_into");//腐朽者

    public static final Item ITEM_ADD_ENTITYZQ=new ItemAddEntityZQ("item_add_entityzq_item");//战旗
    public static final Item ITEM_NO_CAN_HIT_IT=new ItemNoCanHit("no_can_hit_it");//无懈可击

    public static final Item ITEM_PUTRID=new ItemBase("item_putrid_item");//腐烂的物品

    public static final Item ITEM_CARD_FIVE=new ItemBase("item_card_five");//五雷天师令

    public static final Item ITEM_DO_FOREVER=new ItemBase("item_do_forever",new ItemInformationAdder("item.item_do_forever.desc1"));//永动机

    public static final Item ITEM_PAPER_IDONOTWANTTODIE=new ItemPaper1();//纸"我不想死"
    public static final Item ITEM_SCENE_1=new ItemScene1();//场景"我不想死"





	/*
	WOOD(0, 59, 2.0F, 0.0F, 15),
    STONE(1, 131, 4.0F, 1.0F, 5),
    IRON(2, 250, 6.0F, 2.0F, 14),
    DIAMOND(3, 1561, 8.0F, 3.0F, 10),
    GOLD(0, 32, 12.0F, 0.0F, 22);

    harvestLevel, maxUses, efficiency, damage, enchantability
	*/

	//Tool Material
//	public static final Item BLOOD_IRON_INGOT = new ItemBase("blood_iron_ingot");
//
//    public static final Item.ToolMaterial TOOL_MATERIAL_BLOOD =
//			EnumHelper.addToolMaterial("material_blood", 3, 512, 3.0F, 4F, 20).setRepairItem(new ItemStack( ModItems.BLOOD_IRON_INGOT));
//
//	public static final ItemKinshipSword KINSHIP_SWORD = new ItemKinshipSword("kinship_sword", TOOL_MATERIAL_BLOOD);

	//Armor
//    LEATHER("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F),
//    CHAIN("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F),
//    IRON("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F),
//    GOLD("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F),
//    DIAMOND("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2.0F);
	//Note that if you want to set a mod thing as repair material, define them before the material, otherwise it will be empty.

//    public static final ItemArmor.ArmorMaterial moroonArmorMaterial = EnumHelper.addArmorMaterial(
//            "moremomostories:armor_moroon", "moremomostories:armor_moroon", 80, new int[] {3, 6, 8, 3}, 2, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2)
//            .setRepairItem(new ItemStack(Items.QUARTZ));
//

	//Food
//	static PotionEffect eff = new PotionEffect(MobEffects.LEVITATION, TICK_PER_SECOND * 60, 0);
//	public static final ItemFoodBase FIGHT_BREAD = (ItemFoodBase) new ItemFoodBase("war_bread", 10, 10, false).
//			setPotionEffect(eff, 1.0f).
//			setAlwaysEdible();
//    public static final ItemFoodBase MEMORY_BREAD = new ItemFoodBase("memory_bread", 3, 0.6f, false).SetXP(10);





	//Armor
//	public static final ItemHelmSniper helmetSniper = (ItemHelmSniper) new ItemHelmSniper("helmet_sniper", moroonArmorMaterialSniper, 1, EntityEquipmentSlot.HEAD);
//
//	public static final ItemArmorBase[] MOR_GENERAL_ARMOR =
//			{        new ItemArmorBase("mor_armor_1", moroonArmorMaterial, 1, EntityEquipmentSlot.HEAD)
//					,new ItemArmorBase("mor_armor_2", moroonArmorMaterial, 1, EntityEquipmentSlot.CHEST)
//					,new ItemArmorBase("mor_armor_3", moroonArmorMaterial, 1, EntityEquipmentSlot.LEGS)
//					,new ItemArmorBase("mor_armor_4", moroonArmorMaterial, 1, EntityEquipmentSlot.FEET)
//			};

	//public static final ItemSkillDecodeItem skillDecodeItem = (ItemSkillDecodeItem) new ItemSkillDecodeItem("skill_decode_item").setRarity(EnumRarity.RARE);

	//Package Example
//	public static final ItemPackage RANDOM_SKILL = (ItemPackage) new ItemPackage("random_skill", new Item[]{
//			Items.BLAZE_ROD, Items.PAPER
//	}).setMaxStackSize(1);
}
