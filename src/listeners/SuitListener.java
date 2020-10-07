package listeners;

import events.ArmorRemoveEvent;
import main.CosmMain;
import models.Armor;
import models.ArmorTier;
import models.ArmorType;
import models.CosmUser;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class SuitListener
{
    // Instance of plugin
    private final CosmMain plugin = CosmMain.getInstance();

    /**
     * Stops players from removing suits
     * @param event armor remove event
     */
    private void onArmorRemove(ArmorRemoveEvent event) {
        Player player = event.getPlayer();
        CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

        if (cosmUser.isWearingSuit())
            event.setCancelled(true);
    }

    /**
     * Stop armor from taking damage
     * @param event item damage event
     */
    private void onArmorDamage(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

        if (cosmUser.isWearingSuit()) {
            if (item.getType().toString().toLowerCase().contains("helmet") ||
                item.getType().toString().toLowerCase().contains("chestplate") ||
                item.getType().toString().toLowerCase().contains("leggings") ||
                item.getType().toString().toLowerCase().contains("boots")) {
                event.setCancelled(true);
            }
        }
    }

    // --- HANDLING EVENTS --- //
    public void handleArmorRemove(ArmorRemoveEvent event) { onArmorRemove(event); }
    public void handleItemDamage(PlayerItemDamageEvent event) { onArmorDamage(event); }
}
