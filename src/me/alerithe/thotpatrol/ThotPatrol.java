package me.alerithe.thotpatrol;

import me.alerithe.thotpatrol.managers.ChannelListenerManager;
import me.alerithe.thotpatrol.managers.PatchManager;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ThotPatrol extends JavaPlugin implements Listener {

    public static ThotPatrol instance;
    public final String prefix = "\247cT\2474P\247r:";

    public ChannelListenerManager channelListenerManager;
    public PatchManager patchManager;

    @Override
    public void onEnable() {
        instance = this;
        channelListenerManager = new ChannelListenerManager();
        this.channelListenerManager.getChannelListeners().forEach(cl -> this.getServer().getMessenger().registerIncomingPluginChannel(this, cl.getPluginChannel(), cl));
        this.patchManager = new PatchManager();
        this.patchManager.getPatches().forEach(p -> this.getServer().getPluginManager().registerEvents(p, this));
        this.getServer().getPluginManager().registerEvents(this, this);
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
