package com.Hileb.moremomostories.common.util.sound;

import com.Hileb.moremomostories.common.util.ModSoundHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class ModSoundEvent extends SoundEvent
{

    public ModSoundEvent(String path) {
        super(new ResourceLocation("moremomostories", path));
        ModSoundHandler.SOUNDS.add(this);
        this.setRegistryName(path);
    }
}
