package io.github.alerithe.spoiler.listeners;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseEventListener<P extends JavaPlugin> implements Listener {
    private final P plugin;

    public BaseEventListener(P plugin) {
        this.plugin = plugin;
    }

    public P getPlugin() {
        return plugin;
    }

    public void register() {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
}
