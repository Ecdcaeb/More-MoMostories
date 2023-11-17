package mods.Hileb.moremomostories.client.sound;

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
