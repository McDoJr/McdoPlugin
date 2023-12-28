package dev.kbejj.mcdoplugin.managers;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Manager {

    public abstract void load(JavaPlugin...plugins);

    public abstract void unload();
}
