package dev.kbejj.mcdoplugin.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String translate(String paramString) {
        if(VersionUtil.getVersion() >= 1.16) {
            Matcher matcher = pattern.matcher(paramString);
            while (matcher.find()) {
                String color = paramString.substring(matcher.start(), matcher.end());
                paramString = paramString.replace(color, ChatColor.of(color) + "");
                matcher = pattern.matcher(paramString);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', paramString);
    }

    public static void message(CommandSender sender, String...messages) {
        Arrays.asList(messages).forEach(message -> {
            sender.sendMessage(translate(message));
        });
    }

    public static void logger(String...logs) {
        Arrays.asList(logs).forEach(log -> {
            Bukkit.getConsoleSender().sendMessage(translate(log));
        });
    }

    public static void onPluginLoadLogger(Runnable runnable) {
        logger("――――――――――――――――――――――――――――――――――――――――――", "");
        TimeUtil.run(runnable);
        logger("", "――――――――――――――――――――――――――――――――――――――――――");
    }

    public static String formatItemStack(ItemStack itemStack) {
        return formatItemStack(itemStack.getType());
    }
    public static String formatItemStack(Material material) {
        return formatItemStack(material.name());
    }

    public static String formatItemStack(String string) {
        String[] datas = string.split("_");
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < datas.length; i++) {
            String data = datas[i].toUpperCase().charAt(0) + datas[i].substring(1).toLowerCase();
            builder.append(i == 0 ? data : " " + data);
        }
        return builder.toString();
    }

}
