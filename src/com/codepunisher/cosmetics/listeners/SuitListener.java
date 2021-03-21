package com.codepunisher.cosmetics.listeners;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.user.CosmUser;
import com.mcaim.core.events.ArmorRemoveEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class SuitListener implements Listener {
    private final CosmMain plugin = CosmMain.getInstance();

    @EventHandler
    private void onArmorRemove(ArmorRemoveEvent event) {
        Player player = event.getPlayer();
        CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

        if (cosmUser.getSuitUsage().isWearingSuit())
            event.setCancelled(true);
    }

    @EventHandler
    public void onArmorDamage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

        if (cosmUser.getSuitUsage().isWearingSuit()) {
            if (isSuitArmor(item))
                event.setCancelled(true);
        }
    }

    private boolean isSuitArmor(ItemStack item) {
        Material type = item.getType();

        return type == Material.LEATHER_HELMET || type == Material.LEATHER_CHESTPLATE ||
               type == Material.LEATHER_LEGGINGS || type == Material.LEATHER_BOOTS;
    }
}
