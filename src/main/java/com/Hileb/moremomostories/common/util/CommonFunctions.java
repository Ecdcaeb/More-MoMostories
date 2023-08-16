package com.Hileb.moremomostories.common.util;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.Random;

public class CommonFunctions {

    public static double flunctate(double ori, double radius, Random random)
    {
        return ori + (random.nextFloat() * 2 - 1) * radius;
    }
    public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z)
    {
        if (player instanceof EntityPlayerMP){
            int oldDimension = player.getEntityWorld().provider.getDimension();
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
            MinecraftServer server = player.getEntityWorld().getMinecraftServer();
            WorldServer worldServer = server.getWorld(dimension);
            player.addExperienceLevel(0);

            if (worldServer == null || worldServer.getMinecraftServer() == null)
            {
                throw new IllegalArgumentException("Dimension: "+dimension+" doesn't exist!");
            }

            //todo
            //worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension, new CustomTeleporter(worldServer, x, y, z));
            player.setPositionAndUpdate(x, y, z);
        }
    }


    public static void SendMsgToPlayerStyled(EntityPlayerMP playerMP, String key, TextFormatting style, Object... args)
    {
        TextComponentTranslation textcomponenttranslation = new TextComponentTranslation(key, args);
        textcomponenttranslation.getStyle().setColor(style);
        playerMP.sendMessage(textcomponenttranslation);
    }


    public static void SendMsgToPlayer(EntityPlayerMP playerMP, String key, Object... args)
    {
        playerMP.sendMessage((new TextComponentTranslation(key, args)));
    }

    @SideOnly(Side.CLIENT)
    public static String GetStringLocalTranslated(String key) {
        //return "WIP";
        return I18n.format(key);

    }
    @SideOnly(Side.CLIENT)
    public static boolean isShiftPressed()
    {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
    }

    public static void addToEventBus(Object target)
    {
        MinecraftForge.EVENT_BUS.register(target);
    }
}
