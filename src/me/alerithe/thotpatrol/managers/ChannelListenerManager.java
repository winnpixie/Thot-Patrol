package me.alerithe.thotpatrol.managers;

import me.alerithe.thotpatrol.channellisteners.ChannelListener;

import java.util.ArrayList;
import java.util.List;

public class ChannelListenerManager {

    private List<ChannelListener> channelListeners;

    public ChannelListenerManager() {
        this.channelListeners = new ArrayList<>(0);
    }

    public List<ChannelListener> getChannelListeners() {
        return channelListeners;
    }
}
