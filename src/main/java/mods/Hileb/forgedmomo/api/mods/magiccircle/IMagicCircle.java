package mods.Hileb.forgedmomo.api.mods.magiccircle;

import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/21 13:17
 **/
public interface IMagicCircle {
    boolean check(TileEntityLockableLoot loot);
    void doCircle(World world, BlockPos pos,TileEntityLockableLoot tileEntityLockableLoot);
}
