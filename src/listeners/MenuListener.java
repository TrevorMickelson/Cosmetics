package listeners;

import cosmetics.*;
import main.CosmMain;
import models.CosmUser;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
        boolean playerInvClick = Objects.equals(event.getClickedInventory(), player.getInventory());

        if (item != null)
        {
            // If player is clicking in cosmetics menu
            if (title.toLowerCase().contains("cosmetics:"))
            {
                // Cancelling click
                event.setCancelled(true);

                if (!playerInvClick)
                {
                    // Section for displaying sound to user
                    switch (item.getType()) {
                        case OAK_FENCE_GATE:
                            player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_DOOR_CLOSE, 0.5f, 0.75f);
                            break;

                        case DRAGON_HEAD:
                        case BLAZE_POWDER:
                        case COW_SPAWN_EGG:
                        case LEATHER_CHESTPLATE:
                        case BOOK:
                            player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 0.5f, 1.50f);
                            break;

                        case APPLE:
                        case CREEPER_SPAWN_EGG:
                        case BEACON:
                            if (title.equalsIgnoreCase("Cosmetics: Pet Editor"))
                                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 0.5f, 1.25f);
                            break;

                        case BARRIER:
                            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 0.5f, 1.25f);
                            break;

                        default:
                            if (item.getType() != Material.ORANGE_STAINED_GLASS_PANE)
                                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 0.5f, 1.25f);
                            break;
                    }
                }

                // If player clicks on red button (no access)
                if (item.getType() == Material.RED_STAINED_GLASS_PANE)
                    return;

                if (!playerInvClick)
                {
                    // Cosmetic user object
                    CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

                    // Main menu (never changes)
                    Inventory mainMenu = plugin.getGuiHelpers().mainMenu();

                    // If player wants to go back (All menus do the same thing)
                    if (item.getType() == Material.OAK_FENCE_GATE) {
                        if (!title.equalsIgnoreCase("Cosmetics: Pet Editor")) {
                            player.openInventory(mainMenu);
                        } else {
                            player.openInventory(plugin.getGuiHelpers().petInventory(cosmUser));
                        }
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

                                    cosmUser.setWearingSuit(false);
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

                        // Opening pet editor
                        if (item.getType() == Material.BOOK) {
                            player.openInventory(plugin.getGuiHelpers().petEditorGUI(cosmUser));
                            return;
                        }

                        // Pet object
                        Pets pet = Objects.requireNonNull(Pets.valueOf(getEnumName(item)));

                        // If they try to apply something they already have
                        if (cosmUser.isActivePet())
                        {
                            if (cosmUser.getPet() == pet)
                                return;

                            // If the timer is already active (simply changing pet to spawn)
                            cosmUser.getEntity().remove();
                            cosmUser.setPet(pet);
                            cosmUser.spawnPet(player);
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

                    // Pet editor menu
                    if (title.equalsIgnoreCase("Cosmetics: Pet Editor"))
                    {
                        boolean petAlive = cosmUser.isActivePet();
                        switch (item.getType())
                        {
                            case APPLE:
                                cosmUser.setBaby(!cosmUser.isBaby());

                                if (petAlive) {
                                    if (cosmUser.getEntity() instanceof Animals) {
                                        Animals animals = (Animals) cosmUser.getEntity();

                                        if (cosmUser.isBaby()) {
                                            animals.setBaby();
                                        } else {
                                            animals.setAdult();
                                        }
                                    }
                                }
                                break;

                            case CREEPER_SPAWN_EGG:
                                cosmUser.setCharged(!cosmUser.isCharged());

                                if (petAlive) {
                                    if (cosmUser.getEntity() instanceof Creeper) {
                                        Creeper creeper = (Creeper) cosmUser.getEntity();
                                        creeper.setPowered(cosmUser.isCharged());
                                    }
                                }
                                break;

                            case BEACON:
                                cosmUser.setGlow(!cosmUser.isGlow());

                                if (petAlive)
                                    cosmUser.getEntity().setGlowing(cosmUser.isGlow());
                                break;
                        }

                        player.openInventory(plugin.getGuiHelpers().petEditorGUI(cosmUser));
                    }

                    // Suit menu
                    if (title.equalsIgnoreCase("Cosmetics: Suits")) {
                        boolean isActive = cosmUser.isWearingSuit();

                        // If player tries to remove suit
                        if (item.getType() == Material.BARRIER) {
                            if (isActive)
                            {
                                cosmUser.removeSuit();
                                cosmUser.setWearingSuit(false);
                                player.sendMessage(ChatColor.RED + "Suit removed!");
                                player.openInventory(plugin.getGuiHelpers().suitInventory(cosmUser));
                            }
                            else
                            {
                                player.sendMessage(ChatColor.RED + "You aren't currently wearing a suit!");
                            }

                            return;
                        }

                        // Suit object
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
