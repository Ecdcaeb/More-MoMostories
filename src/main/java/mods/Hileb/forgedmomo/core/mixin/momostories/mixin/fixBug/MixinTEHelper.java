package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.fixBug;

import com.gq2529.momostories.blocks.tileEntity.TileEntityHandler;
import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityHandler.class)
public class MixinTEHelper {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void registerTileEntities(Block flag){
            //GameRegistry.registerTileEntity(flag.createTileEntity(null,null).getClass(), flag.getRegistryName());
    }
}
