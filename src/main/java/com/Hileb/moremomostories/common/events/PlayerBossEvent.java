package com.Hileb.moremomostories.common.events;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerBossEvent extends PlayerEvent {
    public EntityLivingBase boss;
    public final boolean isTrue;
    public PlayerBossEvent(EntityPlayer playerIn, EntityLivingBase bossIn){
        super(playerIn);
        boss=bossIn;
        isTrue=false;
    }
    public PlayerBossEvent(EntityPlayer playerIn, EntityLivingBase bossIn,boolean isTrueIn){
        super(playerIn);
        boss=bossIn;
        isTrue=isTrueIn;
    }
    @Override
    public boolean isCancelable() {
        return true;
    }
}
