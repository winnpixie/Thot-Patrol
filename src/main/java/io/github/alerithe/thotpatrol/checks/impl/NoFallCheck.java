package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import io.github.alerithe.thotpatrol.users.User;
import io.github.alerithe.spoiler.utilities.MathHelper;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

// FIXME: Buggy
public class NoFallCheck extends Check {
    public NoFallCheck(ThotPatrolPlugin plugin) {
        super("NO_FALL", plugin);
    }

    @Override
    public boolean onPlayerMove(Player player, Location from, Location to) {
        if (to.getY() < 0.0) { // Void-headed, don't care.
            return true;
        }

        User user = getPlugin().getUserManager().get(player);
        double yDelta = to.getY() - from.getY();
        boolean onGround = ((Entity) player).isOnGround();
        if (yDelta == 0.0) { // No longer moving vertically
            if (!onGround) {
                return true;
            }

            user.setFallDistance(0.0);
            user.getViolations().setNoFallDamage(0);
        } else if (yDelta < 0.0) { // Falling
            user.setFallDistance(user.getFallDistance() + MathHelper.abs(yDelta));

            if (user.getFallDistance() <= 3.0625) return true; // FIXME: Remove buffer zone beyond 3 blocks
            if (!onGround) {
                return true;
            }

            user.getViolations().setNoFallDamage(user.getViolations().getNoFallDamage() + 1);
            if (user.getViolations().getNoFallDamage() < 1) {
                return true;
            }

            // TODO: React when NoFall detected.
        } // yDelta > 0.0 == Rising, not relevant here.

        return true;
    }
}
