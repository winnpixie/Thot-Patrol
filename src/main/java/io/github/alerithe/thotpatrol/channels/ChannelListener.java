package io.github.alerithe.thotpatrol.channels;

import org.bukkit.plugin.messaging.PluginMessageListener;

public abstract class ChannelListener implements PluginMessageListener {
    private final String channel;

    public ChannelListener(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }
}
