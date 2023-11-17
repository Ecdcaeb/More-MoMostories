package mods.Hileb.moremomostories.common.world.potion;

import mods.Hileb.moremomostories.MoreMoMoSrories;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public abstract class PotionBase extends Potion{
    protected final int iconIndex;

    public PotionBase(String name, boolean isBadEffect, int color, int icon)
    {
        super(isBadEffect, color);
        this.setRegistryName(name);
        this.setPotionName("effect." + MoreMoMoSrories.MODID+"." + name);

        iconIndex = icon;

        ModPotions.INSTANCES.add(this);
    }
    public void onUpdate(EntityLivingBase entityLivingBaseIn, int amplifier){

    }
    @Override
    public final void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier){
        onUpdate(entityLivingBaseIn,amplifier);
    }

    public boolean hasEffect(EntityLivingBase entity) {
        return hasEffect(entity, this);
    }

    public boolean hasEffect(EntityLivingBase entity, Potion potion) {
        return entity.getActivePotionEffect(potion) != null;
    }

    //continuousPotion Time trigger
    public boolean isReady(int duration, int amplifier)
    {
        return true;
    }

    //potion effect
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, EntityLivingBase entityLivingBaseIn, int amplifier, double health)
    {
//        if(this == ModPotions.NEW_POTION)
//        {
//              *addEffect
//        }
//
//        else if(this == ModPotions.PURIFY)
//        {
//              *addEffect
//        }
    }


    //tipped arrnow effect

    public abstract ResourceLocation getTexture();
    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc)
    {
        super.renderInventoryEffect(x, y, effect, mc);
        mc.renderEngine.bindTexture(getTexture());
        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x+6, y+7, 0, 0, 18, 18, 18, 18);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc, float alpha)
    {
        mc.renderEngine.bindTexture(getTexture());
        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x+3, y+3, 0, 0, 18, 18, 18, 18);
    }

}
