package me.alerithe.thotpatrol.patches.impl;

import me.alerithe.thotpatrol.patches.Patch;
import me.alerithe.thotpatrol.utils.Helper;
import me.alerithe.thotpatrol.utils.MathHelper;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class NormalMovePatch extends Patch {

    public NormalMovePatch() {
        super("NORMAL_MOVE");
    }

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        boolean cancel = false;
        if (player.isSprinting()) {
            // The player can only be sprinting until they have 7 food bars left, 6 and below stops it
            if (player.getFoodLevel() <= 6 && !((player.isFlying() && player.getAllowFlight()) || player.getGameMode() == GameMode.CREATIVE)) {
                this.getTags().add("sprinting_no_food");
                cancel = true;
            }
            if (player.isBlocking()) {
                this.getTags().add("sprinting_while_blocking");
                cancel = true;
            }
            if (player.isSneaking()) {
                this.getTags().add("sprinting_while_sneaking");
                cancel = true;
            }
        }
        UserData userData = UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player));
        // Head can not exceed [pitch] rotations of 90 degrees and can not be lower than -90 degrees
        if (userData.isHeadObscured()) {
            this.getTags().add("obscured_head");
            event.getFrom().setPitch(MathHelper.clampFloat(player.getLocation().getPitch(), -90, 90));
            event.getTo().setPitch(event.getFrom().getPitch());
            player.getLocation().setPitch(event.getFrom().getPitch());
            cancel = true;
        }
        if (cancel) {
            event.setCancelled(true);
        }
        if (!this.getTags().isEmpty()) {
            this.alertOnline(player);
            if (this.getTags().contains("obscured_head")) {
                Helper.kickPlayerGeneric(player);
            }
            this.getTags().clear();
        }

    }
}
