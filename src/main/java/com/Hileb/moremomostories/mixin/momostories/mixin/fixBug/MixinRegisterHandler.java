package com.Hileb.moremomostories.mixin.momostories.mixin.fixBug;

import com.Hileb.moremomostories.IdlFramework;
import com.gq2529.momostories.blocks.tileEntity.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(RegistryHandler.class)
public class MixinRegisterHandler {
    @SubscribeEvent
    @Overwrite
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        IdlFramework.LogWarning("I fix Bug!");
        //do noting
    }
}
