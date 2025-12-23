package io.github.alerithe.thotpatrol.listeners;

import io.github.alerithe.spoiler.listeners.BaseEventListener;
import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener extends BaseEventListener<ThotPatrolPlugin> {
    public PlayerConnectionListener(ThotPatrolPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        getPlugin().getUserManager().add(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        getPlugin().getUserManager().remove(event.getPlayer());
    }
}
