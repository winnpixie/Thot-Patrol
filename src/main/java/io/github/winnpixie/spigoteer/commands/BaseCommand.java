package io.github.winnpixie.spigoteer.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand<P extends JavaPlugin> implements TabExecutor {
    private final P plugin;
    private final String name;

    public BaseCommand(P plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public P getPlugin() {
        return plugin;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }
}
