package mods.Hileb.forgedmomo.core.mixin.momostories.mixin.fixBug;

import com.gq2529.momostories.MoMoFramework;
import com.gq2529.momostories.blocks.tileEntity.RegistryHandler;
import com.gq2529.momostories.blocks.tileEntity.TEBattleFlag;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RegistryHandler.class)
public class MixinRegisterHandler {
    /**
     * @author Hileb
     * @reason fixBug
     */
    @Overwrite
    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        GameRegistry.registerTileEntity(TEBattleFlag.class, new ResourceLocation(MoMoFramework.MODID,"battle_flag"));
    }
}
