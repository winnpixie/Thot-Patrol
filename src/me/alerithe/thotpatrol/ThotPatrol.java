package me.alerithe.thotpatrol;

import me.alerithe.thotpatrol.commands.CommandThotPatrol;
import me.alerithe.thotpatrol.managers.ChannelListenerManager;
import me.alerithe.thotpatrol.managers.PatchManager;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A really bad AntiCheat add-on/by itself
 * <br>
 * Some checks do a double check on cancelling just in case whoever is getting alerted will get alerted even if they're getting kicked
 */
public class ThotPatrol extends JavaPlugin implements Listener {

    public static ThotPatrol instance;
    public final String chatPrefix = "\247cT\2474P\247r:";
    public final String prefix = "\247cThot \2474Patrol:";

    public ChannelListenerManager channelListenerManager;
    public PatchManager patchManager;

    @Override
    public void onEnable() {
        instance = this;
        this.channelListenerManager = new ChannelListenerManager();
        this.channelListenerManager.getChannelListeners().forEach(cl -> {
            this.getServer().getMessenger().registerIncomingPluginChannel(this, cl.getPluginChannel(), cl);
            this.getLogger().info(String.format("Channel listener loaded for: %s", cl.getPluginChannel()));
        });
        this.patchManager = new PatchManager();
        this.patchManager.getPatches().forEach(p -> {
            this.getServer().getPluginManager().registerEvents(p, this);
            this.getLogger().info(String.format("Patch loaded: %s", p.getLabel()));
        });
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getCommand("thotpatrol").setExecutor(new CommandThotPatrol());
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        UserData.userDataMap.put(event.getPlayer().getUniqueId().toString(), new UserData(event.getPlayer()));
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event) {
        UserData.userDataMap.remove(event.getPlayer().getUniqueId().toString());
    }
}
