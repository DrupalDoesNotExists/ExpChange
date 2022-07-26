package de.klochk.expchange.config;

import de.klochk.expchange.util.Strings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static de.klochk.expchange.util.Strings.colorize;

@RequiredArgsConstructor @Getter
public class ConfigImpl {

    /*
    Configuration start
     */
    private final boolean enableTransfer;

    private final @NotNull String itemStackDisplayName;
    private final @NotNull List<String> itemStackLore;
    /*
    Configuration end
     */

    /**
     * Build configuration instance from section
     * @param section Section
     * @return Configuration instance
     */
    public static ConfigImpl build(ConfigurationSection section) {

        boolean enableTransfer = section.getBoolean("enable-transfer", true);

        ConfigurationSection itemStack = section.getConfigurationSection("itemstack");
        assert itemStack != null;

        String displayName = colorize(itemStack.getString("display-name"));
        List<String> lore  = itemStack.getStringList("lore")
                .stream().map(Strings::colorize).toList();

        return new ConfigImpl(enableTransfer, displayName, lore);

    }

}
