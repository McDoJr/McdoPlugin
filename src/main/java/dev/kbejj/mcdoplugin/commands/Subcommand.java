package dev.kbejj.mcdoplugin.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class Subcommand {

    public void handleCommand(CommandSender sender, String[] args) {
        onCommand(sender, args);
    }

    public abstract void onCommand(CommandSender sender, String[] args);

    public abstract List<String> getTabComplete(String[] args);

    public CommandInfo info() {
        return getClass().getAnnotation(CommandInfo.class);
    }
    
}
