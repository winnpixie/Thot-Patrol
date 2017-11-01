package me.alerithe.thotpatrol.utils;

import me.alerithe.thotpatrol.ThotPatrol;
import org.bukkit.command.CommandSender;

public class Helper {

    public static void sendNoPermsAlert(CommandSender sender, String missingPerm) {
        sender.sendMessage(String.format("%s Permission missing: %s", ThotPatrol.instance.prefix, missingPerm));
    }
}
