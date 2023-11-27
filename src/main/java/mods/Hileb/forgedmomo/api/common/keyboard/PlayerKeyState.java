package mods.Hileb.forgedmomo.api.common.keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.*;

/**
 * @Project More-MoMostories
 * @Author Hileb
 * @Date 2023/11/25 12:51
 **/
public class PlayerKeyState {
    public final UUID playerUUID;
    public final KeyBindingHolder holder;
    public long lastUpdateNanoTime;
    public boolean isKeyDown;
    public PlayerKeyState(UUID playerUUIDIn,KeyBindingHolder holderIn){
        playerUUID=playerUUIDIn;
        holder=holderIn;
    }
    public boolean isKeyDown(){
        return isKeyDown;
    }

    public static class Manager{
        public final HashMap<KeyBindingHolder,PlayerKeyState> KEY_STATES=new HashMap<>();
        public final UUID playerUUID;
        public Manager(UUID playerUUIDIn){
            playerUUID=playerUUIDIn;
        }
        public boolean isKeyDown(KeyBindingHolder holder){
            if (KEY_STATES.containsKey(holder))return KEY_STATES.get(holder).isKeyDown;
            else return false;
        }
        public void upDateKeySet(){
            HashMap<KeyBindingHolder,PlayerKeyState> keyStates=new HashMap<>();
            for(PlayerKeyState state:KEY_STATES.values()){
                if (F2MKeyBoardManager.KEY_MAP.containsValue(state.holder)){
                    keyStates.put(state.holder,state);
                }
            }
            for(KeyBindingHolder holder:F2MKeyBoardManager.KEY_MAP.values()){
                if (!keyStates.containsKey(holder)){
                    keyStates.put(holder,new PlayerKeyState(playerUUID,holder));
                }
            }
            KEY_STATES.clear();
            KEY_STATES.putAll(keyStates);
        }
        public void markKey(KeyBindingHolder holder,boolean state){
            PlayerKeyState state_=KEY_STATES.get(holder);
            if (state_.isKeyDown!=state){
                state_.isKeyDown=state;
                state_.lastUpdateNanoTime=System.nanoTime();
            }
        }
        public static class GlobalManager{
            public static final GlobalManager INSTANCE=new GlobalManager();
            public final HashMap<UUID,Manager> UUID_2_MANAGER=new HashMap<>();
            public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
                UUID_2_MANAGER.put(event.player.getUniqueID(), new Manager(event.player.getUniqueID()));
            }
            public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event){
                UUID_2_MANAGER.remove(event.player.getUniqueID());
            }
            public void handleKeyUpdate(UUID player,KeyBindingHolder holder,boolean isKeyDown){
                UUID_2_MANAGER.get(player).markKey(holder,isKeyDown);
            }
            public boolean isKeyDown(UUID player,KeyBindingHolder holder){
                return UUID_2_MANAGER.get(player).isKeyDown(holder);
            }
            public long getKeyTime(UUID player,KeyBindingHolder holder){
                return System.nanoTime()-UUID_2_MANAGER.get(player).KEY_STATES.get(holder).lastUpdateNanoTime;
            }
            public boolean isKeyDown(EntityPlayer player, KeyBindingHolder holder){
                return UUID_2_MANAGER.get(player.getUniqueID()).isKeyDown(holder);
            }
        }
    }
}
