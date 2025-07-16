package io.github.alerithe.thotpatrol.checks;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerEventListener implements Listener {
    private final ThotPatrolPlugin plugin;

    public PlayerEventListener(ThotPatrolPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent event) {
        for (Check check : plugin.checkManager.getChecks()) {
            if (!check.onPlayerMove(event.getPlayer(), event.getFrom(), event.getTo())) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    private void onPlayerConsumeStart(PlayerInteractEvent event) {
        if (!event.hasItem()) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        for (Check check : plugin.checkManager.getChecks()) {
            if (!check.onPlayerStartUseItem(event.getPlayer(), event.getItem())) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    private void onPlayerConsumeEnd(PlayerItemConsumeEvent event) {
        for (Check check : plugin.checkManager.getChecks()) {
            if (!check.onPlayerEndUseItem(event.getPlayer(), event.getItem())) {
                event.setCancelled(true);
                break;
            }
        }
    }

    @EventHandler
    private void onPlayerCombat(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;

        for (Check check : plugin.checkManager.getChecks()) {
            if (!check.onPlayerAttack((Player) event.getDamager(), event.getEntity())) {
                event.setCancelled(true);
                break;
            }
        }
    }
}
