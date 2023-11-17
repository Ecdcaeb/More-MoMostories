package mods.Hileb.moremomostories.common.world.blocks;

import mods.Hileb.moremomostories.common.init.ModConfig;
import mods.Hileb.moremomostories.common.util.Teleport;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTeleporter extends BlockBase {
    public BlockTeleporter(String name, Material material) {
        super(name, material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (playerIn instanceof EntityPlayerMP) {
            Teleport.teleportToDim(playerIn, ModConfig.dimension.WORLD_GEN_CONF, 1,
                    5,1);
            for(int i=0;i<=10;i++){
                BlockEndBlockShelf.spawnVan(((EntityPlayerMP)playerIn).getServer().getWorld(ModConfig.dimension.WORLD_GEN_CONF), playerIn.getPosition());
            }
            return true;
        }

        return false;
    }
}
