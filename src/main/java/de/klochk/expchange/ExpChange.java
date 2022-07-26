package de.klochk.expchange;

import de.klochk.expchange.command.ExpChangeExecutor;
import de.klochk.expchange.config.ConfigImpl;
import de.klochk.expchange.event.ConsumeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import static de.klochk.expchange.Singleton.CONFIG;

public final class ExpChange extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getLogger().info("ExpChange | How many changes?");
        ConfigImpl config = ConfigImpl.build(getConfig());
        CONFIG.setObject(config);

        if (config.isEnableTransfer())
            Bukkit.getPluginManager().registerEvents(new ConsumeListener(), this);

        getCommand("expchange").setExecutor(new ExpChangeExecutor());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
