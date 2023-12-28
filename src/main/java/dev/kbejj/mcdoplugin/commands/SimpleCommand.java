package dev.kbejj.mcdoplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class SimpleCommand implements CommandExecutor {

    protected final JavaPlugin plugin;

    public SimpleCommand(JavaPlugin plugin, String command) {
        this.plugin = plugin;
        plugin.getCommand(command).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return declare(sender, args);
    }

    public abstract boolean declare(CommandSender sender, String[] args);
}
