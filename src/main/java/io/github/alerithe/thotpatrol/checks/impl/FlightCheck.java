package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import io.github.alerithe.thotpatrol.users.User;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// FIXME: Buggy
public class FlightCheck extends Check {
    public FlightCheck(ThotPatrolPlugin plugin) {
        super("SURVIVAL_FLIGHT", plugin);
    }

    private final Set<Material> nonSolid = new HashSet<>(Arrays.asList(
            Material.AIR,
            Material.YELLOW_FLOWER,
            Material.RED_ROSE,
            Material.LONG_GRASS
    ));

    @Override
    public boolean onPlayerMove(Player player, Location from, Location to) {
        if (player.getGameMode() == GameMode.SPECTATOR) {
            return true;
        }

        if (player.isFlying()) {
            return true;
        }

        double yDelta = to.getY() - from.getY();
        if (yDelta != 0.0) {
            return true;
        }

        User user = getPlugin().getUserManager().get(player);

        int airBelow = 0;
        for (int x = -1; x < 2; x++) {
            for (int z = -1; z < 2; z++) {
                if (nonSolid.contains(player.getWorld().getBlockAt(player.getLocation().add(x, -0.0625, z)).getType())) {
                    airBelow++;
                }
            }
        }

        user.getViolations().setSurvivalFlight(airBelow < 9 ? 0
                : user.getViolations().getSurvivalFlight() + 1);

        return user.getViolations().getSurvivalFlight() > 9;
    }
}
