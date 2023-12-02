package mods.Hileb.forgedmomo.api.client.resource;

import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.io.File;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/18 13:45
 **/
public class ResourceGenerateEvent extends Event {
    public final File root;
    public ResourceGenerateEvent(){
        root= Launch.minecraftHome;
    }
    @Override
    public boolean isCancelable() {
        return false;
    }
}
