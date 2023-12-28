package dev.kbejj.mcdoplugin.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

    public static FileConfiguration getConfig(JavaPlugin plugin, String filename) {
        File file = new File(plugin.getDataFolder(), filename);
        if(!file.exists()) {
            plugin.saveResource(filename, true);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        InputStream inputStream = plugin.getResource(filename);
        if(inputStream != null) {
            FileConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
            config.setDefaults(defaultConfig);
        }
        return config;
    }
}
