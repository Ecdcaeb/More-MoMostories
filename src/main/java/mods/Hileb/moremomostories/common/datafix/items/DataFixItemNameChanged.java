package mods.Hileb.moremomostories.common.datafix.items;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/9/29 10:45
 **/
public class DataFixItemNameChanged implements IFixableData {
    public static HashMap<ItemDataCompondium,ItemDataCompondium> mapFromTo=new HashMap<>();
    static {
        mapFromTo.put(new ItemDataCompondium("moremomostories:item_12_b",ItemDataCompondium.META_ALL),
        new ItemDataCompondium("moremomostories:fiber_wire",ItemDataCompondium.META_ALL));
        mapFromTo.put(new ItemDataCompondium("moremomostories:item_devils_sword",ItemDataCompondium.META_ALL),
                new ItemDataCompondium("moremomostories:fiber_wire",ItemDataCompondium.META_ALL));
    }
    @Override
    public int getFixVersion() {
        return 10;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound compound) {
        for(Map.Entry<ItemDataCompondium,ItemDataCompondium> entry:mapFromTo.entrySet()){
            if (entry.getKey().apply(compound)){
                MoreMoMoSrories.LOGGER.info("replaced!"+ compound);
                return entry.getValue().reset(compound);
            }
        }
        return compound;
    }
    public static class ItemDataCompondium{
        public static final int META_ALL=32767;
        public int meta;
        public String name;
        public ItemDataCompondium(String nameIn,int metaIn){
            meta=metaIn;
            name=nameIn;
        }
        public boolean apply(NBTTagCompound compound){
            if (compound.hasKey("id", 8))
            {
                String nameIn = compound.getString("id");
                if (name.equals(nameIn)){
                    return meta==META_ALL || compound.getInteger("Damage")==meta;
                }
            }
            return false;
        }
        public NBTTagCompound reset(NBTTagCompound compound){
            compound.setString("id",name);
            compound.setInteger("Damage",meta==META_ALL?compound.getInteger("Damage"):meta);
            return compound;
        }
    }
}
