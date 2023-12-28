package dev.kbejj.mcdoplugin.file;

import dev.kbejj.mcdoplugin.utils.StringUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Config {

    private File file;
    private FileConfiguration config;
    private String filename;

    public Config(JavaPlugin plugin, String filename) {
        create(plugin, filename, "");
    }

    public Config(JavaPlugin plugin, String filename, String path) {
        create(plugin, filename, path);
    }

    private void create(JavaPlugin plugin, String filename, String path){
        this.filename = filename;
        String finalPath = plugin.getDataFolder() + path;
        this.file = new File(finalPath, filename);
        reloadConfig();
    }

    public void reloadConfig() {
        saveDefaultConfig();
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void saveConfig() {
        try {
            config.save(file);
        }catch (IOException e) {
            StringUtil.logger("&cFailed to save " + filename);
        }
    }

    public void saveDefaultConfig() {
        try {
            if(file.createNewFile()) {
                StringUtil.logger("&a" + filename + " has been created!");
            }
        }catch (IOException e) {
            StringUtil.logger("&cFailed to create " + filename);
        }
    }
}
