package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NormalMoveCheck extends Check {
    public NormalMoveCheck(ThotPatrolPlugin plugin) {
        super("NORMAL_MOVE", plugin);
    }

    @Override
    public boolean onPlayerMove(Player player, Location from, Location to) {
        if (isHeadObscured(player)) {
            return false;
        }

        if (player.isSprinting() && !canSprint(player)) {
            return false;
        }

        return true;
    }

    private boolean canSprint(Player player) {
        return (player.getFoodLevel() > 6 || player.getAllowFlight())
                && !player.isSneaking()
                && !player.isBlocking();
    }

    private boolean isHeadObscured(Player player) {
        float pitch = player.getLocation().getPitch();
        return pitch > 90f || pitch < -90f;
    }
}
