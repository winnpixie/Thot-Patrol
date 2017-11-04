package me.alerithe.thotpatrol.patches.impl;

import me.alerithe.thotpatrol.patches.Patch;
import me.alerithe.thotpatrol.utils.Helper;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoFallPatch extends Patch {

    public NoFallPatch() {
        super("NO_FALL");
    }

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // do i really care if they're beneath the void no i could honestly care less because they're falling to their death anyway
        if (event.getTo().getY() > 0) {
            UserData userData = UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player));
            if (event.getFrom().getY() > event.getTo().getY()) {
                double dist = event.getFrom().getY() - event.getTo().getY();
                if ((userData.fallDistance += dist) >= 4 && ((Entity) player).isOnGround()) {
                    userData.nofallVL++;
                }
                if (userData.nofallVL > 1) {
                    this.getTags().add(String.format("fall_dist_invalid(%s)", (int) userData.fallDistance));
                }
            } else if (event.getFrom().getY() == event.getTo().getY() && ((Entity) player).isOnGround()) {
                userData.fallDistance = 0;
                userData.nofallVL = 0;
            }
        }
        if (!this.getTags().isEmpty()) {
            this.alertOnline(player);
            this.getTags().clear();
            Helper.kickPlayerGeneric(player);
        }
    }
}
