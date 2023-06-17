package com.Hileb.moremomostories.util.named.skillTag;

import com.Hileb.moremomostories.util.NBTStrDef.IDFBasicNBTUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ItemSkillList {
//    public static final String NBT_LIST="hileb_skill_list";
    public static final HashMap<String, ItemSkill> REGISTER=new HashMap<>();
    public static final ItemSkill SKILL_END_MEMORY=new ItemSkill("endMemory"){
        {
            tooltips.add(I18n.format("com.hileb.momo.lang.skill.endmemory.name"));
            tooltips.add(I18n.format("com.hileb.momo.lang.skill.endmemory.desc"));
        }
    };
//    public static List<ItemSkill> getSkillsFromStack(ItemStack stack){
//        try {
//            if (!stack.isEmpty() && stack.hasTagCompound() && stack.getTagCompound().hasKey(NBT_LIST)) {
//                NBTTagList list = stack.getTagCompound().getTagList(NBT_LIST, IDFBasicNBTUtil.NBTType.NBTTagString);
//                List<String> keys = new ArrayList<>();
//                for (int i = 0; i < list.tagCount(); i++) {
//                    keys.add( ((NBTTagString) list.get(i)).getString());
//                }
//                List<ItemSkill> skills=new ArrayList<>();
//                for(String s:keys){
//                    skills.add(REGISTER.get(s));
//                }
//                Iterator iterator=skills.iterator();
//                while(iterator.hasNext()){
//                    if (iterator.next()==null){
//                        iterator.remove();
//                    }
//                }
//
//                return skills;
//            }
//            return null;
//        }catch (Exception e){
//
//        }
//        return null;
//    }
//    public static void addSkillIntoStack(ItemStack stack,ItemSkill skill){
//        if (stack.isEmpty())return;
//        if (!stack.hasTagCompound())stack.setTagCompound(new NBTTagCompound());
//        NBTTagCompound nbtTagCompound=stack.getTagCompound();
//        if (!nbtTagCompound.hasKey(NBT_LIST))nbtTagCompound.setTag(NBT_LIST,new NBTTagList());
//        NBTTagList list=nbtTagCompound.getTagList(NBT_LIST,IDFBasicNBTUtil.NBTType.NBTTagString);
//        NBTTagString string=new NBTTagString(skill.registerNAME);
//        if (list.hasNoTags())list.set(0,string);
//        else list.appendTag(string);
//    }
}
