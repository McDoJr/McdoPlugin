package dev.kbejj.mcdoplugin;

import dev.kbejj.mcdoplugin.managers.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class McdoPlugin extends JavaPlugin {

    private static McdoPlugin plugin;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        plugin = this;
        this.messageManager = new MessageManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static McdoPlugin getPlugin() {
        return plugin;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
