package de.klochk.expchange.util;

import de.klochk.expchange.config.ConfigImpl;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import static de.klochk.expchange.Singleton.CONFIG;
import static de.klochk.expchange.Singleton.EXPCHANGE;

/**
 * ItemStack utility
 */
public class Items {

    private static final NamespacedKey expKey =
            NamespacedKey.fromString("stored_exp", EXPCHANGE.get());

    /**
     * Get saved experience from item
     * @param item Item
     * @return Exp or null
     */
    public static @Nullable Integer getStoredExp(PersistentDataHolder holder) {
        PersistentDataContainer container = holder.getPersistentDataContainer();
        assert expKey != null;
        if (!container.has(expKey, PersistentDataType.INTEGER)) return null;

        return container.get(expKey, PersistentDataType.INTEGER);
    }

    /**
     * Set stored experience to item
     * @param item Item
     * @param exp  Experience
     */
    public static void setStoredExp(PersistentDataHolder holder, int exp) {
        PersistentDataContainer container = holder.getPersistentDataContainer();
        assert expKey != null;

        container.set(expKey, PersistentDataType.INTEGER, exp);
    }

    /**
     * Build new item with EXP specified
     * @param exp EXP amount
     * @return Item
     */
    public static ItemStack newItem(int exp) {

        ConfigImpl config = CONFIG.get();

        ItemStack stack = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta meta = stack.getItemMeta();

        assert meta != null;
        meta.setDisplayName(config.getItemStackDisplayName());
        meta.setLore(config.getItemStackLore()
                .stream().map(line -> line.replace("{exp}", String.valueOf(exp)))
                .toList());

        setStoredExp(meta, exp);
        stack.setItemMeta(meta);

        return stack;

    }

}
