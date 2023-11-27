package mods.Hileb.forgedmomo.api.common.keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/24 19:17
 **/
public class KeyBindingHolder {
    @SideOnly(Side.CLIENT)
    public KeyBinding keyBinding;
    public KeyHandler pressedHandler=KeyHandler.EMPTY;
    public final ResourceLocation name;
    public KeyBindingHolder(F2MKeyRegistryEvent event, ResourceLocation registerName,
                            String description, IKeyConflictContext keyConflictContext, KeyModifier keyModifier, int keyCode, String category){
        name=registerName;
        event.register(registerName,this);
        if(event.SIDE==Side.CLIENT){
            keyBinding=new KeyBinding(description,keyConflictContext,keyModifier,keyCode,category);
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }
    public KeyBindingHolder(F2MKeyRegistryEvent event, ResourceLocation registerName,
                            String description,IKeyConflictContext keyConflictContext, int keyCode, String category){
        name=registerName;
        event.register(registerName,this);
        if(event.SIDE==Side.CLIENT){
            keyBinding=new KeyBinding(description,keyConflictContext,keyCode,category);
            ClientRegistry.registerKeyBinding(keyBinding);
        }

    }
    public KeyBindingHolder(F2MKeyRegistryEvent event, ResourceLocation registerName,
                            String description, int keyCode, String category){
        name=registerName;
        event.register(registerName,this);
        if(event.SIDE==Side.CLIENT){
            keyBinding=new KeyBinding(description,keyCode,category);
            ClientRegistry.registerKeyBinding(keyBinding);
        }
    }
    public KeyBindingHolder(F2MKeyRegistryEvent event, ResourceLocation registerName,
                            String vanillaKeyDescription){
        name=registerName;
        event.register(registerName,this);
        if (event.SIDE==Side.CLIENT){
            keyBinding=KEYBIND_ARRAY.get(vanillaKeyDescription);
        } else{
            keyBinding=null;
        }
    }
    public KeyBindingHolder setPressedHandler(KeyHandler handler){
        pressedHandler=handler;
        return this;
    }
    @SideOnly(Side.CLIENT)
    public static Map<String, KeyBinding> KEYBIND_ARRAY;
    static {
        if (FMLCommonHandler.instance().getSide()==Side.CLIENT){
            KEYBIND_ARRAY=ReflectionHelper.getPrivateValue(KeyBinding.class,null,"field_74516_a","KEYBIND_ARRAY");
        }
    }
    @FunctionalInterface
    public interface KeyHandler{
        KeyHandler EMPTY= playerMP -> {};
        void handle(EntityPlayerMP playerMP);
    }
}
