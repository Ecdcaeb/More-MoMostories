package com.Hileb.moremomostories.blocks;

import com.Hileb.moremomostories.init.ModConfig;
import com.Hileb.moremomostories.util.Teleport;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
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
        if (!worldIn.isRemote) {
            Teleport.teleportToDim(playerIn, ModConfig.dimension.WORLD_GEN_CONF, 1,
                    1,1);
            return true;
        }

        return false;
    }
//    public Teleport getTP(WorldServer s,int x,int y,int z){
//        return new Teleport(s,x,y,z);
//    }
}
