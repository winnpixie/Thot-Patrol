package me.alerithe.thotpatrol.patches.impl;

import me.alerithe.thotpatrol.patches.Patch;
import me.alerithe.thotpatrol.utils.Helper;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class NormalMovePatch extends Patch {

    public NormalMovePatch() {
        super("NORMAL_MOVE");
    }

    /* TODO: Bad checks
    @EventHandler
    private void onItemUse(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player)).isConsumingItem = true;
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onFinishUse(PlayerItemConsumeEvent event) {
        if (event.getItem().getType().isEdible() || (event.getItem().getType() == Material.POTION && !Potion.fromItemStack(event.getItem()).isSplash()) || event.getItem().getType() == Material.MILK_BUCKET) {
            Player player = event.getPlayer();
            UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player)).isConsumingItem = false;
        }
    }*/

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        boolean cancel = false;
        UserData userData = UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player));
        if (player.isSprinting()) {
            // The player can only be sprinting until they have 7 food bars left, 6 and below stops it
            if (player.getFoodLevel() <= 6 && !((player.isFlying() && player.getAllowFlight()) || player.getGameMode() == GameMode.CREATIVE)) {
                this.getTags().add("sprint_no_food");
                cancel = true;
            }
            if (player.isBlocking()) {
                this.getTags().add("sprint_while_block");
                cancel = true;
            }
            if (player.isSneaking()) {
                this.getTags().add("sprint_while_sneak");
                cancel = true;
            }
            /* TODO: Bad check
            if (userData.isConsumingItem) {
                this.getTags().add("sprint_while_consume");
                cancel = true;
            }*/
        }
        // Head can not exceed [pitch] rotations of 90 degrees and can not be lower than -90 degrees
        if (userData.isHeadObscured()) {
            this.getTags().add("obscured_head");
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
