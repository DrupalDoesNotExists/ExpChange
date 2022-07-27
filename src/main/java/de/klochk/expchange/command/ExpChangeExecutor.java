package de.klochk.expchange.command;

import de.klochk.expchange.config.ConfigImpl;
import de.klochk.expchange.util.Items;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static de.klochk.expchange.Singleton.CONFIG;
import static de.klochk.expchange.Singleton.EXPCHANGE;
import static de.klochk.expchange.util.Strings.colorize;

/**
 * /expchange command
 */
public class ExpChangeExecutor implements CommandExecutor {

    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {
        ConfigImpl config = CONFIG.get();
        boolean enabled = config.isEnableTransfer();

        JavaPlugin plugin = EXPCHANGE.get();

        if (args.length == 0) {

            if (sender.hasPermission("expchange.expchange") && enabled) {

                if (sender instanceof Player player) {

                    int exp = player.getLevel();
                    ItemStack item = Items.newItem(exp);

                    if (exp == 0) return true;
                    if (player.getInventory().firstEmpty() == -1) return true;

                    player.setLevel(0);
                    player.getInventory().addItem(item);
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,
                            3, 1);
                    return true;

                }

            }

        } else {

            String argument = args[0];
            if (sender.hasPermission("expchange.reload") &&
                    argument.equalsIgnoreCase("reload")) {

                plugin.reloadConfig();
                sender.sendMessage(colorize("&aSuccessfully reloaded configuration."));
                return true;

            } else if (sender.hasPermission("expchange.help")) {

                sender.sendMessage(colorize("&b/expchange        * Get EXP to bottle"));
                sender.sendMessage(colorize("&b/expchange reload * Reload plugin"));
                sender.sendMessage(colorize("&b/expchange help   * Show help message"));
                return true;

            }

        }

        String version = plugin.getDescription().getVersion();
        sender.sendMessage("&aExpChange version " + version + " by klochk");
        sender.sendMessage("&6SpigotMC: (WIP)");

        return true;
    }

}
