package listeners;

import cosmetics.Pets;
import cosmetics.Suits;
import main.CosmMain;
import models.CosmUser;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import cosmetics.BackParticles;
import cosmetics.TrailParticles;

import java.util.Objects;

/**
 * This is the gui listener class
 * This class is what applies the cosmetics
 * for the user when they are clicking in the menu
 */
public class MenuListener
{
    // Instance of main class
    private final CosmMain plugin = CosmMain.getInstance();

    /**
     * Menu click for cosmetic menu
     * @param event inventory click event
     */
    private void menuClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        ItemStack item = event.getCurrentItem();
        String title = event.getView().getTitle();

        if (item != null)
        {
            // If player is clicking in cosmetics menu
            if (title.toLowerCase().contains("cosmetics:"))
            {
                // Cancelling click
                event.setCancelled(true);

                // If player clicks on red button (no access)
                if (item.getType() == Material.RED_STAINED_GLASS_PANE)
                    return;

                // Cosmetic user object
                CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

                // Main menu (never changes)
                Inventory mainMenu = plugin.getGuiHelpers().mainMenu();

                // If player wants to go back (All menus do the same thing)
                if (item.getType() == Material.OAK_FENCE_GATE) {
                    player.openInventory(mainMenu);
                    return;
                }

                // Main menu
                if (title.equalsIgnoreCase("Cosmetics: Main")) {
                    switch (item.getType().toString().toLowerCase())
                    {
                        case "dragon_head":
                            player.openInventory(plugin.getGuiHelpers().backInventory(cosmUser));
                            break;

                        case "blaze_powder":
                            player.openInventory(plugin.getGuiHelpers().trailInventory(cosmUser));
                            break;

                        case "cow_spawn_egg":
                            player.openInventory(plugin.getGuiHelpers().petInventory(cosmUser));
                            break;

                        case "leather_chestplate":
                            player.openInventory(plugin.getGuiHelpers().suitInventory(cosmUser));
                            break;

                        case "barrier":
                            boolean hasActiveCosmetic = cosmUser.isActiveTrailParticle() || cosmUser.isActiveBackParticle() || cosmUser.isActivePet() || cosmUser.isWearingSuit();

                            if (hasActiveCosmetic) {
                                cosmUser.setActiveBackParticle(false);
                                cosmUser.setActiveTrailParticle(false);
                                cosmUser.setActivePet(false);

                                if (cosmUser.isWearingSuit())
                                    cosmUser.removeSuit();

                                player.sendMessage(ChatColor.RED + "All cosmetics have been disabled!");
                            } else {
                                player.sendMessage(ChatColor.RED + "You don't have any cosmetics enabled!");
                            }
                            break;
                    }

                    return;
                }

                // Trail particle menu
                if (title.equalsIgnoreCase("Cosmetics: Trail Particles")) {
                    boolean isActive = cosmUser.isActiveTrailParticle();

                    // If player tries to remove particle
                    if (item.getType() == Material.BARRIER) {
                        if (isActive)
                        {
                            cosmUser.setActiveTrailParticle(false);
                            player.sendMessage(ChatColor.RED + "Trail particle disabled!");
                            player.openInventory(plugin.getGuiHelpers().trailInventory(cosmUser));
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "You don't currently have a trail particle active!");
                        }

                        return;
                    }

                    // Trail particle object
                    TrailParticles trailParticle = Objects.requireNonNull(TrailParticles.valueOf(getEnumName(item)));

                    // If they try to apply something they already have
                    if (cosmUser.isActiveTrailParticle())
                    {
                        if (cosmUser.getTrailParticle() == trailParticle)
                            return;

                        // If the timer is already active (simply changing particle to spawn)
                        cosmUser.setTrailParticle(trailParticle);
                    }
                    else
                    {
                        // Starting timer (only here)
                        cosmUser.setTrailParticle(trailParticle);
                        cosmUser.setActiveTrailParticle(true);
                        cosmUser.trailParticleTimer();
                    }

                    // Updating menu (to make item glow) makes it more responsive
                    player.openInventory(plugin.getGuiHelpers().trailInventory(cosmUser));
                    return;
                }

                // Back particle menu
                if (title.equalsIgnoreCase("Cosmetics: Back Particles")) {
                    boolean isActive = cosmUser.isActiveBackParticle();

                    // If player tries to remove particle
                    if (item.getType() == Material.BARRIER) {
                        if (isActive)
                        {
                            cosmUser.setActiveBackParticle(false);
                            player.sendMessage(ChatColor.RED + "Back particle disabled!");
                            player.openInventory(plugin.getGuiHelpers().backInventory(cosmUser));
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "You don't currently have a back particle active!");
                        }

                        return;
                    }

                    // Back particle object
                    BackParticles backParticle = Objects.requireNonNull(BackParticles.valueOf(getEnumName(item)));

                    // If they try to apply something they already have
                    if (cosmUser.isActiveBackParticle())
                    {
                        if (cosmUser.getBackParticle() == backParticle)
                            return;

                        // If the timer is already active (simply changing particle to spawn)
                        cosmUser.setBackParticle(backParticle);
                    }
                    else
                    {
                        // Starting timer (only here)
                        cosmUser.setBackParticle(backParticle);
                        cosmUser.setActiveBackParticle(true);
                        cosmUser.backParticleTimer();
                    }

                    // Updating menu (to make item glow) makes it more responsive
                    player.openInventory(plugin.getGuiHelpers().backInventory(cosmUser));
                    return;
                }

                // Pet menu
                if (title.equalsIgnoreCase("Cosmetics: Pets")) {
                    boolean isActive = cosmUser.isActivePet();

                    // If player tries to remove pet
                    if (item.getType() == Material.BARRIER) {
                        if (isActive)
                        {
                            cosmUser.setActivePet(false);
                            player.sendMessage(ChatColor.RED + "Pet disabled!");
                            player.openInventory(plugin.getGuiHelpers().petInventory(cosmUser));
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "You don't currently have a pet active!");
                        }

                        return;
                    }

                    // Pet object
                    Pets pet = Objects.requireNonNull(Pets.valueOf(getEnumName(item)));

                    // If they try to apply something they already have
                    if (cosmUser.isActivePet())
                    {
                        if (cosmUser.getPet() == pet)
                            return;

                        // If the timer is already active (simply changing particle to spawn)
                        cosmUser.setPet(pet);
                    }
                    else
                    {
                        // Starting timer (only here)
                        cosmUser.setPet(pet);
                        cosmUser.setActivePet(true);
                        cosmUser.petTimer();
                    }

                    // Updating menu (to make item glow) makes it more responsive
                    player.openInventory(plugin.getGuiHelpers().petInventory(cosmUser));
                }

                // Suit menu
                if (title.equalsIgnoreCase("Cosmetics: Suits")) {
                    boolean isActive = cosmUser.isWearingSuit();

                    // If player tries to remove suit
                    if (item.getType() == Material.BARRIER) {
                        if (isActive)
                        {
                            cosmUser.removeSuit();
                            player.sendMessage(ChatColor.RED + "Suit removed!");
                            player.openInventory(plugin.getGuiHelpers().suitInventory(cosmUser));
                        }
                        else
                        {
                            player.sendMessage(ChatColor.RED + "You aren't currently wearing a suit!");
                        }

                        return;
                    }

                    // Pet object
                    Suits suit = Objects.requireNonNull(Suits.valueOf(getEnumName(item)));

                    // If they try to apply something they already have
                    if (cosmUser.isWearingSuit())
                    {
                        if (cosmUser.getSuit() == suit)
                            return;

                        // If the timer is already active (simply changing particle to spawn)
                        cosmUser.setSuit(suit);
                        cosmUser.equipSuit();
                    }
                    else
                    {
                        // Starting timer (only here)
                        cosmUser.setSuit(suit);
                        cosmUser.equipSuit();
                    }

                    // Updating menu (to make item glow) makes it more responsive
                    player.openInventory(plugin.getGuiHelpers().suitInventory(cosmUser));
                }
            }
        }
    }

    /**
     * Returns edited string
     * Used to get enum value from cosmetic enums
     */
    private String getEnumName(ItemStack item) {
        String name = Objects.requireNonNull(item.getItemMeta()).getDisplayName();
        name = name.substring(name.indexOf("§") + 2).toUpperCase();

        if (name.contains(" "))
            name = name.replaceAll(" ", "_");

        return name;
    }

    /** Handles click event for listener manager class */
    public void handleEvent(InventoryClickEvent event) { menuClick(event); }
}
