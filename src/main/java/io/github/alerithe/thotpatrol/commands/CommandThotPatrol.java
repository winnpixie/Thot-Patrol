package io.github.alerithe.thotpatrol.commands;

import io.github.alerithe.spoiler.commands.BaseCommand;
import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandThotPatrol extends BaseCommand<ThotPatrolPlugin> {
    public CommandThotPatrol(ThotPatrolPlugin plugin) {
        super("thotpatrol", plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(String.format("%s This server is protected by Thot Patrol!", getPlugin().getChatPrefix()));

        return true;
    }
}
