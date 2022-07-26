package de.klochk.expchange;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Singleton implementation
 */
@AllArgsConstructor
public enum Singleton {

    EXPCHANGE(JavaPlugin.getPlugin(ExpChange.class)),
    CONFIG(null),
    ;

    @Setter
    private Object object;
    public <T> T get() { return (T) object; }

}
