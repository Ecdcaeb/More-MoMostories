package mods.Hileb.moremomostories.common.world.enchantments;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import mods.Hileb.moremomostories.common.world.enchantments.enchantments.EnchantmentBlood;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = MoreMoMoSrories.MODID)
public class ModEnchantmentInit {

    public static final EntityEquipmentSlot[] armorSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET};
    public static final EntityEquipmentSlot[] allSlots = EntityEquipmentSlot.values();
    public static final EntityEquipmentSlot[] handSlots = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND, EntityEquipmentSlot.OFFHAND};
    public static final EntityEquipmentSlot[] mainHand = new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND};

    public static final RegistryNamespaced<ResourceLocation, Enchantment> REGISTRY = net.minecraftforge.registries.GameData.getWrapper(Enchantment.class);
    public static final List<Enchantment> ENCHANTMENT_LIST = new ArrayList<Enchantment>();//自动注册




    //ENCH
    public static final Enchantment BLOOD=new EnchantmentBlood("blood");
    public static final Enchantment YYU=new EnchantmentBlood("uu"){
        @Override
        public int getEnchantmentColor(ItemStack stack) {
            return 0x00FF00;
        }
    };
}
