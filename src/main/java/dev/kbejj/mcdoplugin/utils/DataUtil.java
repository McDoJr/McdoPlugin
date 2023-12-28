package dev.kbejj.mcdoplugin.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class DataUtil {

    private final JavaPlugin plugin;

    public DataUtil(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public String getString(ItemStack itemStack, String key) {
        if(!itemStack.hasItemMeta()) return null;

        return itemStack.getItemMeta()
                .getPersistentDataContainer()
                .get(createKey(key), PersistentDataType.STRING);
    }

    public int getInt(ItemStack itemStack, String key) {
        if(!itemStack.hasItemMeta()) return 0;

        return itemStack.getItemMeta()
                .getPersistentDataContainer()
                .get(createKey(key), PersistentDataType.INTEGER);
    }

    public double getDouble(ItemStack itemStack, String key) {
        if(!itemStack.hasItemMeta()) return 0;

        return itemStack.getItemMeta()
                .getPersistentDataContainer()
                .get(createKey(key), PersistentDataType.DOUBLE);
    }

    public boolean getBoolean(ItemStack itemStack, String key) {
        if(!itemStack.hasItemMeta()) return false;

        return itemStack.getItemMeta()
                .getPersistentDataContainer()
                .get(createKey(key), PersistentDataType.BOOLEAN);
    }

    public NamespacedKey createKey(String name) {
        return new NamespacedKey(plugin, name);
    }
}
