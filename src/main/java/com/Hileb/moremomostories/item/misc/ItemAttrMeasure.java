package com.Hileb.moremomostories.item.misc;

import com.Hileb.moremomostories.item.ItemBase;
import com.Hileb.moremomostories.util.EntityUtil;
import com.Hileb.moremomostories.util.NBTStrDef.IDLNBTUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import static com.Hileb.moremomostories.util.NBTStrDef.IDLNBTDef.*;

public class ItemAttrMeasure extends ItemBase {
    public ItemAttrMeasure(String name) {
        super(name);
        useable = true;

    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {

        IDLNBTUtil.SetDouble(stack, MARK_ATK, EntityUtil.getAttr(target, SharedMonsterAttributes.ATTACK_DAMAGE));
        IDLNBTUtil.SetDouble(stack, MARK_HP, EntityUtil.getAttr(target, SharedMonsterAttributes.MAX_HEALTH));
        IDLNBTUtil.SetDouble(stack, MARK_DEF, EntityUtil.getAttr(target, SharedMonsterAttributes.ARMOR));
        IDLNBTUtil.SetDouble(stack, MARK_ARMOR_T, EntityUtil.getAttr(target, SharedMonsterAttributes.ARMOR_TOUGHNESS));
        IDLNBTUtil.SetDouble(stack, MARK_RANGE, EntityUtil.getAttr(target, SharedMonsterAttributes.FOLLOW_RANGE));
        IDLNBTUtil.SetDouble(stack, MARK_KB_R, EntityUtil.getAttr(target, SharedMonsterAttributes.KNOCKBACK_RESISTANCE));
        IDLNBTUtil.SetDouble(stack, MARK_SPEED, EntityUtil.getAttr(target, SharedMonsterAttributes.MOVEMENT_SPEED));
        IDLNBTUtil.SetDouble(stack, MARK_ATK_SPEED, EntityUtil.getAttr(target, SharedMonsterAttributes.ATTACK_SPEED));

        return true;
    }


}
