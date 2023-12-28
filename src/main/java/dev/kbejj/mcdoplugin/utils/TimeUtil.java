package dev.kbejj.mcdoplugin.utils;

import dev.kbejj.mcdoplugin.McdoPlugin;
import org.bukkit.Bukkit;

public class TimeUtil {

    private static final McdoPlugin plugin = McdoPlugin.getPlugin();

    public static void run(Runnable runnable) {
        runnable.run();
    }

    public static void delay(Runnable runnable) {
        delay(runnable, 10);
    }

    public static void delay(Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }
}
