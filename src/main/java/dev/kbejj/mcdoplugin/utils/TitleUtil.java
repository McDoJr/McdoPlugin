package dev.kbejj.mcdoplugin.utils;

import org.bukkit.entity.Player;

public class TitleUtil {

    public static void send(Player player, String title, int stay) {
        send(player, title, "", stay);
    }

    public static void send(Player player, String title, String subtitle, int stay) {
        send(player, title, subtitle, 1, stay, 1);
    }

    public static void send(Player player, String title, String subtitle, int in, int stay, int out) {
        player.sendTitle(
                StringUtil.translate(title),
                StringUtil.translate(subtitle),
                in,
                stay,
                out
        );
    }
}
