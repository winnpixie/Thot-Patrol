package me.alerithe.thotpatrol.commands;

import me.alerithe.thotpatrol.managers.PermissionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandAntiThot implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(PermissionManager.ANTITHOT_COMMAND)) {

        } else {

        }
        return true;
    }
}
