package io.github.alerithe.thotpatrol.commands;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.winnpixie.spigoteer.commands.BaseCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandThotPatrol extends BaseCommand<ThotPatrolPlugin> {
    public CommandThotPatrol(ThotPatrolPlugin plugin) {
        super(plugin, "thotpatrol");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(String.format("%s \247rThis server is protected by Thot Patrol!", getPlugin().chatPrefix));

        return true;
    }
}
