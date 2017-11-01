package me.alerithe.thotpatrol.channellisteners;

import org.bukkit.plugin.messaging.PluginMessageListener;

public abstract class ChannelListener implements PluginMessageListener {

    private String pluginChannel;

    public ChannelListener(String pluginChannel) {
        this.pluginChannel = pluginChannel;
    }

    public String getPluginChannel() {
        return pluginChannel;
    }
}
