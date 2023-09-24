package com.Hileb.moremomostories.common.util;

import com.Hileb.moremomostories.MoreMoMoSrories;
import com.Hileb.moremomostories.common.util.sound.ModSoundEvent;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ModSoundHandler {
    //To add a sound, remember assets.moremomostories.sounds.json
    public static final List<ModSoundEvent> SOUNDS = new ArrayList<>();
    public static SoundEvent SOUND_HURT_114514 = new ModSoundEvent("entity.114514.hurt");

//    public static SoundEvent SOUND_1 = new ModSoundEvent("entity.moroon.ambient");
//    public static SoundEvent SOUND_2 = new ModSoundEvent("entity.moroon.hurt");

    public static void soundRegister()
    {
        ForgeRegistries.SOUND_EVENTS.registerAll(ModSoundHandler.SOUNDS.toArray(new SoundEvent[0]));
        MoreMoMoSrories.LOGGER.info("Registered {} sounds.", SOUNDS.size());
    }

}
