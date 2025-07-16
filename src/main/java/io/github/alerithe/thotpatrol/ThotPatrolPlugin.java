package io.github.alerithe.thotpatrol;

import io.github.alerithe.thotpatrol.channels.ChannelManager;
import io.github.alerithe.thotpatrol.checks.CheckManager;
import io.github.alerithe.thotpatrol.commands.CommandThotPatrol;
import io.github.alerithe.thotpatrol.users.UserManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A questionable anti-cheat [add-on]
 */
public class ThotPatrolPlugin extends JavaPlugin implements Listener {
    public final String chatPrefix = "\247cT\2474P\247r:";

    public final UserManager userManager = new UserManager();
    public final ChannelManager channelManager = new ChannelManager(this);
    public final CheckManager checkManager = new CheckManager(this);

    @Override
    public void onEnable() {
        channelManager.load();
        checkManager.load();

        this.getServer().getPluginManager().registerEvents(this, this);

        this.getCommand("thotpatrol").setExecutor(new CommandThotPatrol(this));
    }


    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        userManager.add(event.getPlayer());
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        userManager.remove(event.getPlayer());
    }
}
