package de.klochk.expchange.event;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static de.klochk.expchange.util.Items.getStoredExp;

/**
 * Bottle consume event
 */
public class ConsumeListener implements Listener {

    @EventHandler
    public void onConsume(PlayerInteractEvent event) {

        if (event.getAction() == Action.RIGHT_CLICK_AIR ||
            event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            ItemStack stack = event.getItem();
            if (stack == null) return;

            ItemMeta meta = stack.getItemMeta();
            if (meta == null) return;

            Integer expStored = getStoredExp(meta);
            if (expStored == null) return;

            Player player = event.getPlayer();
            player.setLevel(player.getLevel() + expStored);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,
                    3, 1);
            player.getInventory().setItem(event.getHand(), null);
            event.setCancelled(true);

        }

    }

}
