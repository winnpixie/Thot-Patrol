package io.github.alerithe.spoiler.utilities;

import net.md_5.bungee.api.ChatColor;

public class TextHelper {
    private TextHelper() {
    }

    public static String formatAmp(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
