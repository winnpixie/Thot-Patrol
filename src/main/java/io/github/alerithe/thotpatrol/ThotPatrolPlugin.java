package io.github.alerithe.thotpatrol;

import io.github.alerithe.spoiler.utilities.TextHelper;
import io.github.alerithe.thotpatrol.channels.ChannelManager;
import io.github.alerithe.thotpatrol.checks.CheckManager;
import io.github.alerithe.thotpatrol.commands.CommandThotPatrol;
import io.github.alerithe.thotpatrol.listeners.PlayerConnectionListener;
import io.github.alerithe.thotpatrol.listeners.PlayerEventListener;
import io.github.alerithe.thotpatrol.users.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A questionable anti-cheat [add-on]
 */
public class ThotPatrolPlugin extends JavaPlugin {
    private final String chatPrefix = TextHelper.formatAmp("&4T&cP&8:&r");

    private final UserManager userManager = new UserManager();
    private final ChannelManager channelManager = new ChannelManager(this);
    private final CheckManager checkManager = new CheckManager(this);

    @Override
    public void onEnable() {
        channelManager.load();
        checkManager.load();

        new PlayerConnectionListener(this).register();
        new PlayerEventListener(this).register();

        new CommandThotPatrol(this).register();
    }

    public String getChatPrefix() {
        return chatPrefix;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public ChannelManager getChannelManager() {
        return channelManager;
    }

    public CheckManager getCheckManager() {
        return checkManager;
    }
}
