package dev.kbejj.mcdoplugin.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class ActionbarUtil {

    public static void send(Player player, String message) {
        player.spigot()
                .sendMessage(
                        ChatMessageType.ACTION_BAR,
                        TextComponent.fromLegacyText(StringUtil.translate(message))
                );
    }
}
