package de.klochk.expchange.util;

import org.bukkit.ChatColor;

/**
 * String utility
 */
public class Strings {

    /**
     * Colorize source string
     * @param source Source
     * @return Colorful string
     */
    public static String colorize(String source) {
        return ChatColor.translateAlternateColorCodes('&', source);
    }

}
