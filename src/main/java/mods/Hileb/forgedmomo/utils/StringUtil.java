package mods.Hileb.forgedmomo.utils;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.Arrays;
import java.util.List;

public class StringUtil {
    public static ITextComponent getLocale(String key) {
        return new TextComponentTranslation(key);
    }
    public static List<String> spiltStringLn(String value){
        return Arrays.asList(value.split("\n"));
    }
}
