package mods.Hileb.moremomostories.common.world.item.interfaces;

import mods.Hileb.moremomostories.common.world.entity.entity.EntityItemX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public interface IEntityItemX {
    @Nullable
    default Entity createEntityItem(World world, Entity location, ItemStack itemstack) {
        EntityItemX x=new EntityItemX((EntityItem)location);
        x.setNoPickupDelay();
        return x;
    }
}
