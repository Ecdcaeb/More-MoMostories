package mods.Hileb.moremomostories.common.world.item.myItems.armor;

import mods.Hileb.moremomostories.common.world.item.ItemArmorBase;
import mods.Hileb.moremomostories.common.world.item.armorMaterials.ClientModels;
import mods.Hileb.moremomostories.common.world.item.armorMaterials.ModArmorMaterials;
import mods.Hileb.moremomostories.common.world.item.armorMaterials.QGXModel5;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import ic2.api.item.ElectricItem;
import ic2.core.IC2;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

@Optional.Interface(modid = IC2.MODID, iface = "ic2.api.item.ISpecialElectricItem")
public class ItemQGX extends ItemArmorBase {


    public ItemQGX(String name){
        super(name, ModArmorMaterials.QGXMaterial,1, EntityEquipmentSlot.FEET);
    }

    @SideOnly(Side.CLIENT)
    @Nullable
    @Override
    public net.minecraft.client.model.ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,net.minecraft.client.model.ModelBiped _default) {
        if(itemStack!=null){
            if (!itemStack.isEmpty()){
                if (itemStack.getItem()==this){
                    QGXModel5 model= ClientModels.modelQGX;
                    model.bone_all.showModel=armorSlot.equals(EntityEquipmentSlot.FEET);
                    //model.bipedBody=_default.bipedBody;
                    model.isChild=_default.isChild;
                    model.isSneak=_default.isSneak;
                    model.isRiding = _default.isRiding;
                    model.rightArmPose = _default.rightArmPose;
                    model.leftArmPose = _default.leftArmPose;
                    //

                    return model;
                }
            }
        }
        return null;
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "moremomostories:textures/entity/goldenguide.png";
    }
    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack)
    {
        if (ignoreVanillaSystem)
        {
            return HashMultimap.<String, AttributeModifier>create();
        }else {
            return super.getAttributeModifiers(equipmentSlot, stack);
        }
    }
    public AxisAlignedBB getFace(EntityLivingBase entityLivingBase){
        Vec3d vec3d=entityLivingBase.getLookVec();
        double x=vec3d.x>=0?0.5:-0.5;
        double z=vec3d.z>=0?0.5:-0.5;
        AxisAlignedBB aabb=new AxisAlignedBB(new BlockPos(entityLivingBase.posX,entityLivingBase.posY-1,entityLivingBase.posZ),new BlockPos(entityLivingBase.posX+x,entityLivingBase.posY+1f , entityLivingBase.posZ+z));
        return aabb;
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        if (!worldIn.isRemote){
            EntityPlayer player=(EntityPlayer)entityIn;
            List<EntityLivingBase> e=worldIn.getEntitiesWithinAABB(EntityLivingBase.class,getFace(player));
            for(EntityLivingBase c:e){
                if (c!=entityIn){
                    c.attackEntityFrom(DamageSource.causeMobDamage(c),0.1f);
                    if (c instanceof EntityPlayer){
                        c.sendMessage(new TextComponentTranslation("say.player.hileb.qgx.say",entityIn.getDisplayName()));
                    }
                }
            }
            if (Loader.isModLoaded(IC2.MODID)){
                ic2charge(stack,worldIn,entityIn,itemSlot,isSelected);
            }
        }
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

    }
    @Optional.Method(modid = IC2.MODID)
    private void ic2charge(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {//试图充电
        if (!entity.world.isRemote && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {//遍历背包
                ItemStack toCharge = player.inventory.getStackInSlot(i);
                if (!toCharge.isEmpty()) {
                    ElectricItem.manager.charge(toCharge,
                            ElectricItem.manager.getMaxCharge(toCharge) -
                                    ElectricItem.manager.getCharge(toCharge),
                            (int) Math.abs(player.motionX)+(int)Math.abs(player.motionY)+(int)Math.abs(player.motionZ), true, false);//进行充电
                }
            }
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
        Multimap<String, AttributeModifier> map= super.getItemAttributeModifiers(equipmentSlot);
        if (equipmentSlot==this.armorType){
            map.put(SharedMonsterAttributes.MAX_HEALTH.getName(), //属性，它的UUID
                    //属性修饰符：
                    new AttributeModifier(ARMOR_MODIFIERS_OVERRIDE[equipmentSlot.getIndex()],
                            //来自ItemArmor的
                            // private static final UUID[] ARMOR_MODIFIERS
                            //ItemArmor弄了个一模一样的，叫
                            //protected static final UUID[] ARMOR_MODIFIERS_OVERRIDE
                            //它是指不同的部位。（4个）。通常与穿戴部位保持一致。

                            ((RangedAttribute)SharedMonsterAttributes.MAX_HEALTH).getDescription(),//"Armor toughness", //Display名字
                            // RangedAttribute是IAttribute的一种实现，它定义了displayName，
                            //MaxHealth是RangedAttribute，因此可以使用getDescription()获得名字。
                            //我们不使用魔法字符串，我们尊重getDescription()

                            5,//值。
                            0//方式：
                            //这是最为魔法的地方。一个int。

                            //   - operation == 0 时，原属性值 + value = 新属性值。
                            //   - operation == 1 时，原属性值 + 原属性值 * value = 新属性值。
                            //   - operation == 2 时，原属性值 * (1 + value) = 新属性值。


                            //https://teamcovertdragon.github.io/Harbinger/63/chapter-08/attribute/modifier.html
                    ));
        }
        return map;
    }
}
