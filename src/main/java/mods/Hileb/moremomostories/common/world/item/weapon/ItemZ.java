package mods.Hileb.moremomostories.common.world.item.weapon;

import mods.Hileb.moremomostories.common.world.item.ItemBase;
import mods.Hileb.moremomostories.common.world.entity.entity.projectile.EntityIceMother;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemZ extends ItemBase {
    public ItemZ(String name){
        super(name);
        BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, new BehaviorProjectileDispense()
        {
            /**
             * Return the projectile entity spawned by this dispense behavior.
             */
            protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stackIn)
            {
                EntityIceMother m=new EntityIceMother(worldIn, position.getX(), position.getY(), position.getZ());
                return m;
            }
        });
    }
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!worldIn.isRemote)
        {
            EntityIceMother entitysnowball = new EntityIceMother(worldIn,itemstack, playerIn);
            entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(entitysnowball);
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
        }

        //playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
    }
}
