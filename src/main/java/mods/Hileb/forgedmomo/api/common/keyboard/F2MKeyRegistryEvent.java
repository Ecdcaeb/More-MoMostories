package mods.Hileb.forgedmomo.api.common.keyboard;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/24 19:23
 **/
public final class F2MKeyRegistryEvent extends Event {
    public final Side SIDE;
    public F2MKeyRegistryEvent(Side side){
        SIDE=side;
    }
    public Side getSide(){return SIDE;}
    public void register(ResourceLocation registerName, KeyBindingHolder keyBinding){
        F2MKeyBoardManager.KEY_MAP.put(registerName,keyBinding);
    }
    @Override
    public boolean isCancelable() {
        return false;
    }
}
