package me.alerithe.thotpatrol.utils;

import me.alerithe.thotpatrol.ThotPatrol;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Helper {

    public static void sendNoPermsAlert(CommandSender sender, String missingPerm) {
        sender.sendMessage(String.format("%s Permission missing: %s", ThotPatrol.instance.chatPrefix, missingPerm));
    }

    public static void kickPlayer(Player player, String reason) {
        player.kickPlayer(reason);
    }

    public static void kickPlayerGeneric(Player player) {
        kickPlayer(player, String.format("%s \247cThot \2477-> \247cEliminated", ThotPatrol.instance.prefix));
    }
}
