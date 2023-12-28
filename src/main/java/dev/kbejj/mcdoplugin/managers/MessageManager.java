package dev.kbejj.mcdoplugin.managers;

import dev.kbejj.mcdoplugin.McdoPlugin;
import dev.kbejj.mcdoplugin.utils.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MessageManager extends Manager {

    private final Map<String, Map<String, String>> messages;

    public MessageManager(JavaPlugin plugin) {
        this.messages = new HashMap<>();
        this.load(McdoPlugin.getPlugin(), plugin);
    }

    public MessageManager() {
        this.messages = new HashMap<>();
        this.load(McdoPlugin.getPlugin());
    }

    public String get(String path) {
        String[] data = path.split("\\.");
        return messages.get(data[0]).get(data[1]);
    }

    @Override
    public void load(JavaPlugin...plugins) {
        for(JavaPlugin plugin : plugins) {
            FileConfiguration config = FileUtil.getConfig(plugin, "messages.yml");
            for(String parentKey : config.getKeys(false)) {
                Map<String, String> childMessages = new HashMap<>();
                for(String key : config.getConfigurationSection(parentKey).getKeys(false)) {
                    childMessages.put(key, config.getString(parentKey + "." + key));
                }
                messages.put(parentKey, childMessages);
            }
        }
    }

    @Override
    public void unload() {

    }
}
