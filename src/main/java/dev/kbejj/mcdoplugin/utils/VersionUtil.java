package dev.kbejj.mcdoplugin.utils;

import org.bukkit.Bukkit;

public class VersionUtil {

    public static String getStringVersion(){
        return Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }

    public static String getFinalVersion(){
        return getStringVersion().replace("v", "").replaceAll("_R\\d", "").replace("_", ".");
    }

    public static double getVersion(){
        return Double.parseDouble(getFinalVersion());
    }
}
