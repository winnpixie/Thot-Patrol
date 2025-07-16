package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class NormalMoveCheck extends Check {
    public NormalMoveCheck(ThotPatrolPlugin plugin) {
        super(plugin, "NORMAL_MOVE");
    }

    @Override
    public boolean onPlayerMove(Player player, Location from, Location to) {
        return !isHeadObscured(player)
                && (!player.isSprinting() || canSprint(player));
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
