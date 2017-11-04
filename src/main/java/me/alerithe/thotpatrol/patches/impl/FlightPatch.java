package me.alerithe.thotpatrol.patches.impl;

import me.alerithe.thotpatrol.patches.Patch;
import me.alerithe.thotpatrol.utils.Helper;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;
import java.util.List;

// TODO: Buggy check
public class FlightPatch extends Patch {
    public FlightPatch() {
        super("SURVIVAL_FLIGHT");
    }

    private List<Material> nonSolidMaterials = Arrays.asList(Material.AIR, Material.YELLOW_FLOWER, Material.RED_ROSE, Material.LONG_GRASS);

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        boolean cancel = false;
        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            double dist = event.getFrom().getY() - event.getTo().getY();
            if (dist == 0) {
                UserData userData = UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player));
                int airBlocks = 0;
                for (int x = -1; x < 2; x++) {
                    for (int z = -1; z < 2; z++) {
                        if (nonSolidMaterials.contains(player.getWorld().getBlockAt(player.getLocation().add((double) x, -1D, (double) z)).getType())) {
                            airBlocks++;
                        }
                    }
                }
                if (airBlocks == 9) {
                    userData.flightVL++;
                    if (userData.flightVL >= 10) {
                        this.getTags().add("not_falling");
                        cancel = true;
                    }
                } else {
                    userData.flightVL = 0;
                }
            }
        }
        if (cancel) {
            event.setTo(event.getFrom());
            event.setCancelled(true);
        }
        if (!this.getTags().isEmpty()) {
            this.alertOnline(player);
            this.getTags().clear();
        }
        if (cancel) {
            Helper.kickPlayerGeneric(player);
        }
    }
}
