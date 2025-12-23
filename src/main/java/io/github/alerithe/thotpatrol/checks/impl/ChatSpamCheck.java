package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import org.bukkit.entity.Player;

public class ChatSpamCheck extends Check {
    public ChatSpamCheck(ThotPatrolPlugin plugin) {
        super("CHAT_SPAM", plugin);
    }

    @Override
    public boolean onPlayerChat(Player player, String message) {
        // TODO: Implement (maybe?...)
        return true;
    }
}
