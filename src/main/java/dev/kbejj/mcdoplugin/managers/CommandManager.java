package dev.kbejj.mcdoplugin.managers;

import dev.kbejj.mcdoplugin.commands.Subcommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandManager {

    private final List<Subcommand> subcommands;

    public CommandManager(Subcommand...subcommands) {
        this.subcommands = new ArrayList<>(Arrays.asList(subcommands));
    }

    public List<String> getAccessibleSubcommandsAsString(CommandSender sender) {
        List<String> list = new ArrayList<>();
        for(Subcommand subcommand : getAccessibleSubcommands(sender)) {
            list.add(subcommand.info().command());
            list.addAll(Arrays.asList(subcommand.info().aliases()));
        }
        return list;
    }

    public List<Subcommand> getAccessibleSubcommands(CommandSender sender) {
        return this.subcommands.stream()
                .filter(subcommand -> sender.hasPermission(subcommand.info().permission()))
                .collect(Collectors.toList());
    }

    public Subcommand getSubcommand(String command) {
        return this.subcommands.stream()
                .filter(subcommand -> isSubcommand(command, subcommand))
                .findFirst().orElse(null);
    }

    private boolean isSubcommand(String command, Subcommand subcommand) {
        return subcommand.info().command().equalsIgnoreCase(command)
                || Arrays.asList(subcommand.info().aliases()).contains(command.toLowerCase());
    }
}
