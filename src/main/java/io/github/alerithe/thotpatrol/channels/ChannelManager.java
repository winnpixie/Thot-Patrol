package io.github.alerithe.thotpatrol.channels;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;

import java.util.ArrayList;
import java.util.List;

public class ChannelManager {
    private final ThotPatrolPlugin plugin;

    private final List<ChannelListener> listeners = new ArrayList<>();

    public ChannelManager(ThotPatrolPlugin plugin) {
        this.plugin = plugin;
    }

    public List<ChannelListener> getListeners() {
        return listeners;
    }

    public void load() {
        for (ChannelListener listener : listeners) {
            plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, listener.getChannel(), listener);
            plugin.getLogger().info(() -> String.format("Registered channel listener '%s'", listener.getChannel()));
        }
    }
}
