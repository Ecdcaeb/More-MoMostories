package com.Hileb.moremomostories.item;

import com.Hileb.moremomostories.init.ModCreativeTab;
import com.Hileb.moremomostories.item.Book.ItemBookBuddhaGodPalm;
import com.Hileb.moremomostories.item.food.ItemFoodBase;
import com.Hileb.moremomostories.item.food.ItemFoodDuckKao;
import com.Hileb.moremomostories.item.food.ItemFoodSyzg;
import com.Hileb.moremomostories.item.myItems.*;
import com.Hileb.moremomostories.item.paper.ItemPaper1;
import com.Hileb.moremomostories.item.paper.ItemScene1;
import com.Hileb.moremomostories.item.weapon.ItemZ;
import com.Hileb.moremomostories.util.YTXSYSounds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
    public static final List<Item> BLOCK_ITEMS = new ArrayList<Item>();

	//Basic





	public static final Item ITEM_COPYER =new ItemCopier("item_copyer");//复制卡
	public static final Item ITEM_CARD_ZFP =new ItemCardAddZFP("item_card_zfp", ModCreativeTab.IDL_MISC);//煮饭婆
	public static final Item ITEM_CARD_GET_FROM_NULL=new ItemCardGetFromNull();//无中生有
	public static final Item ITEM_CARD_NULL=new ItemBase("item_card_null");//空卡
    public static final Item ITEM_RAIN=new ItemBase("rain");//雨，只render
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
    public static final Item ITEM_11_A=new ItemRemainBase("item_11_a");//核心制造技术
    public static final Item ITEM_12_B=new Item12Base("item_12_b");//纤维绳
    public static final Item ITEM_FIRE=new Item12Base("item_fire");
    public static final Item ITEM_XE=new ItemXe("item_xe");//氙石
    public static final Item ITEM_ARROM_XE=new ItemQGX("item_qgx");
    public static final Item ITEM_DUCK_COOKED=new ItemFoodBase("duck_cooked",6,6,true);
    public static final Item ITEM_DUCK_KAO=new ItemFoodDuckKao("duck_kao");
    public static final Item ITEM_DAO=new ItemDao("item_dao");
    public static final Item ITEM_BOOK=new ItemBookMod("item_book");
    public static final Item ITEM_BOOK_BUDDHA_GOD_PALM=new ItemBookBuddhaGodPalm("item_book_buddha_god_palm");
    public static final Item ITEM_Z=new ItemZ("item_z");
    public static final Item ITEM_BOOK_DUST=new ItemBase("item_book_dust");

    public static final Item ITEM_YTXSY_SOUND=new ItemYTXSY("item_ytxsy_sound");





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
    private static final int cardCount=10;
    private static final int itemCount=7;
    private static final int sceneCount=2;
    private static final int mainCount=6;
    private static final int foodCount=3;
    public static void subItems(CreativeTabs tab, NonNullList<ItemStack> items){
        if (tab==ModCreativeTab.IDL_MISC){
            //cards
            int i=0;
            items.add(i++,new ItemStack(ITEM_CARD_ZFP));
            items.add(i++,new ItemStack(ITEM_CARD_GET_FROM_NULL));
            items.add(i++,new ItemStack(ITEM_CARD_MI_CARD));
            items.add(i++,new ItemStack(ITEM_CARD_FIVE));
            items.add(i++,new ItemStack(ITEM_TURN_INTO));
            items.add(i++,new ItemStack(ITEM_NO_CAN_HIT_IT));
            items.add(i++,new ItemStack(ITEM_CARD_NULL));
            items.add(i++,new ItemStack(ITEM_CARD_MI_CARD));
            items.add(i++,new ItemStack(ITEM_COPYER));
            items.add(i++,new ItemStack(ITEM_CARD_NULL));
            //items
            items.add((i++),new ItemStack(ITEM_CARD_MI_ITEM));
            items.add((i++),new ItemStack(ITEM_DAO));
            items.add((i++),new ItemStack(ITEM_12_B));
            items.add((i++),new ItemStack(ITEM_ADD_ENTITYZQ));
            items.add((i++),new ItemStack(ITEM_AXE_DIA));
            items.add((i++),new ItemStack(ITEM_DO_FOREVER));
            items.add((i++),new ItemStack(ITEM_ARROM_XE));
            items.add((i++),new ItemStack(ITEM_BOOK_BUDDHA_GOD_PALM));
            items.add((i++),new ItemStack(ITEM_BOOK_DUST));
            items.add((i++),new ItemStack(ITEM_Z));
//            ItemStack stack=new ItemStack(ITEM_YTXSY_SOUND);
//////            ((ItemYTXSY)ITEM_YTXSY_SOUND).getItemYTXSY(stack,11);
//////            items.add((i++),stack);
            for(int var1 = 0; var1< YTXSYSounds.getLimit(); var1++){
                         ItemStack stack=new ItemStack(ITEM_YTXSY_SOUND);
                         ((ItemYTXSY)ITEM_YTXSY_SOUND).getItemYTXSY(stack,var1);
                         items.add((i++),stack);
            }

            //scenes
            items.add((i++),new ItemStack(ITEM_PAPER_IDONOTWANTTODIE));
            items.add((i++),new ItemStack(ITEM_SCENE_1));
            //foods
            items.add((i++),new ItemStack(ITEM_FOOD_SYZG));
            items.add((i++),new ItemStack(ITEM_DUCK_COOKED));
            items.add((i++),new ItemStack(ITEM_DUCK_KAO));
            //main
            items.add((i++),new ItemStack(ITEM_11_A));
            items.add((i++),new ItemStack(ITEM_MAIN_NULL));
            items.add((i++),new ItemStack(ITEM_MAIN_XK));
            items.add((i++),new ItemStack(ITEM_MAIN_TR));
            items.add((i++),new ItemStack(ITEM_MAIN_SY));
            items.add((i++),new ItemStack(ITEM_MAIN_WJ));

            //block
            //items.add(0+ cardCount+itemCount+sceneCount+foodCount+mainCount,new ItemStack(ModItems.)));
            for(int ioi=0;ioi<BLOCK_ITEMS.size();ioi++){
                items.add((i++),new ItemStack(ModItems.BLOCK_ITEMS.get(ioi)));
            }
            //xe
            items.add((i++),ItemXe.get(ItemXe.XeType.EMPTY));

            items.add((i++),ItemXe.get(ItemXe.XeType.RED_0));
            items.add((i++),ItemXe.get(ItemXe.XeType.BLUE_0));
            items.add((i++),ItemXe.get(ItemXe.XeType.BLACK_0));

            items.add((i++),ItemXe.get(ItemXe.XeType.RED_1));
            items.add((i++),ItemXe.get(ItemXe.XeType.BLUE_1));
            items.add((i++),ItemXe.get(ItemXe.XeType.BLACK_1));
        }
    }
}
