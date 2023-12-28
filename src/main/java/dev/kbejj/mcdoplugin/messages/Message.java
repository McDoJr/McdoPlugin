package dev.kbejj.mcdoplugin.messages;

import dev.kbejj.mcdoplugin.McdoPlugin;
import dev.kbejj.mcdoplugin.managers.MessageManager;
import dev.kbejj.mcdoplugin.utils.SoundUtil;
import dev.kbejj.mcdoplugin.utils.StringUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message {

    private final Player player;
    private static final MessageManager messageManager = McdoPlugin.getPlugin().getMessageManager();

    public Message(Player player) {
        this.player = player;
    }

    public static void message(CommandSender sender, String path) {
        StringUtil.message(sender, messageManager.get(path));
    }

    public Message send(String path) {
        StringUtil.message(player, messageManager.get(path));
        return this;
    }

    public void pling() {
        SoundUtil.pling(player);
    }

    public void no() {
        SoundUtil.villagerNo(player);
    }

    public void yes() {
        SoundUtil.villagerYes(player);
    }

    public void exp() {
        SoundUtil.exp(player);
    }
}
