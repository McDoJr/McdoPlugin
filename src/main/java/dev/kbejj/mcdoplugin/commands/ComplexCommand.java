package dev.kbejj.mcdoplugin.commands;

import dev.kbejj.mcdoplugin.managers.CommandManager;
import dev.kbejj.mcdoplugin.messages.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ComplexCommand implements CommandExecutor, TabCompleter {

    private final CommandManager commandManager;

    public ComplexCommand(JavaPlugin plugin, String command, Subcommand...subcommands) {
        this.commandManager = new CommandManager(subcommands);
        plugin.getCommand(command).setExecutor(this);
        plugin.getCommand(command).setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Run declare function's condition if there's no args
        if(args.length == 0) {
            return declare(sender, args);
        }

        Subcommand subcommand = this.commandManager.getSubcommand(args[0]);
        if(subcommand == null) {
            Message.message(sender, "subcommand.invalid");
            return false;
        }

        if(!(sender instanceof Player) && subcommand.info().player()) {
            Message.message(sender, "subcommand.player");
            return false;
        }

        if(!sender.hasPermission(subcommand.info().permission())) {
            Message.message(sender, "permission.failed");
            return false;
        }

        if(Arrays.stream(subcommand.info().args()).noneMatch(i -> i == args.length)) {
            Message.message(sender, "subcommand.args");
            return false;
        }

        subcommand.handleCommand(sender, args);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {
            return this.commandManager.getAccessibleSubcommandsAsString(sender)
                    .stream()
                    .filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }

        Subcommand subcommand = this.commandManager.getSubcommand(args[0]);
        return subcommand == null ? new ArrayList<>() : subcommand.getTabComplete(args);
    }

    public abstract boolean declare(CommandSender sender, String[] args);
}
