package io.github.alerithe.thotpatrol.utilities;

import org.bukkit.entity.Player;

public class PermissionHelper {
    private static final String PLUGIN_BASE = "thotpatrol";

    public static final String EXEMPT_BASE = PLUGIN_BASE + ".exempt";
    public static final String EXEMPT_WILDCARD = EXEMPT_BASE + ".all";

    public static final String COMMAND_BASE = PLUGIN_BASE + ".command";
    public static final String COMMAND_PLUGIN = COMMAND_BASE + ".thotpatrol";

    public static final String ALERTS_RECEIVE = PLUGIN_BASE + ".alerts.receive";

    private PermissionHelper() {
    }

    public static boolean isExempt(Player player, String check) {
        if (player.hasPermission(EXEMPT_WILDCARD)) return true;

        return player.hasPermission(EXEMPT_BASE + "." + check);
    }
}
