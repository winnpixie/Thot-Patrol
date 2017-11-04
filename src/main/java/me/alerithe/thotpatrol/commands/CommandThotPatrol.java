package me.alerithe.thotpatrol.commands;

import me.alerithe.thotpatrol.ThotPatrol;
import me.alerithe.thotpatrol.managers.PermissionManager;
import me.alerithe.thotpatrol.utils.Helper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandThotPatrol implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission(PermissionManager.ANTITHOT_COMMAND)) {
            commandSender.sendMessage(String.format("%s \247rThis server is protected by Thot Patrol!", ThotPatrol.instance.chatPrefix));
        } else {
            Helper.sendNoPermsAlert(commandSender, PermissionManager.ANTITHOT_COMMAND);
        }
        return false;
    }
}
