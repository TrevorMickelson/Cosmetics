package util;

import cosmetics.*;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import models.CosmUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Objects;

/**
 * This class contains inventories
 * for the beautiful cosmetic menus
 */
public class GUIHelpers
{
    /**
     * Main cosmetics menu
     * @return inventory
     */
    public Inventory mainMenu() {
        Inventory inventory = Bukkit.createInventory(null, 36, "Cosmetics: Main");
        inventory.setItem(10, customItem(Material.DRAGON_HEAD, "&d&lBack Particles", "&7Click to view", false));
        inventory.setItem(12, customItem(Material.BLAZE_POWDER, "&6&lTrail Particles", "&7Click to view", false));
        inventory.setItem(14, customItem(Material.COW_SPAWN_EGG, "&a&lPets", "&7Click to view", false));
        inventory.setItem(16, customItem(Material.LEATHER_CHESTPLATE, "&b&lSuits", "&7Click to view", false));
        inventory.setItem(31, customItem(Material.BARRIER, "&c&lRemove", "&7Click to remove all cosmetics", false));
        return inventory;
    }

    /**
     * Trail particle menu
     * @return inventory
     */
    public Inventory trailInventory(CosmUser cosmUser) {
        int counter = 0;
        Inventory inventory = Bukkit.createInventory(null, 36, "Cosmetics: Trail Particles");

        for (TrailParticles trailParticle : TrailParticles.values())
        {
            boolean applied = cosmUser.isActiveTrailParticle() && cosmUser.getTrailParticle() == trailParticle;
            ItemStack current = customItem(Material.ORANGE_STAINED_GLASS_PANE, trailParticle.getName(), "&7Status: &6&lAPPLIED", true);
            ItemStack permission = customItem(trailParticle.getGuiItem(), trailParticle.getName(), "&7Click to equip", false);
            ItemStack noPermission = customItem(Material.RED_STAINED_GLASS_PANE, trailParticle.getName(), "&7Statis: &c&lLOCKED", false);

            if (Objects.requireNonNull(Bukkit.getPlayer(cosmUser.getUuid())).hasPermission(trailParticle.getPermission())) {
                if (applied) {
                    inventory.setItem(counter, current);
                } else {
                    inventory.setItem(counter, permission);
                }
            } else {
                inventory.setItem(counter, noPermission);
            }

            counter++;
        }

        inventory.setItem(31, customItem(Material.BARRIER, "&c&lRemove", "&7Click to remove trail particle", false));
        inventory.setItem(35, customItem(Material.OAK_FENCE_GATE, "&f&lBack", "&7Click to go back", false));
        return inventory;
    }

    /**
     * Back particle menu
     * @return inventory
     */
    public Inventory backInventory(CosmUser cosmUser) {
        int counter = 0;
        Inventory inventory = Bukkit.createInventory(null, 36, "Cosmetics: Back Particles");

        for (BackParticles backParticle : BackParticles.values())
        {
            boolean applied = cosmUser.isActiveBackParticle() && cosmUser.getBackParticle() == backParticle;
            ItemStack current = customItem(Material.ORANGE_STAINED_GLASS_PANE, backParticle.getName(), "&7Status: &6&lAPPLIED", true);
            ItemStack permission = customItem(backParticle.getGuiItem(), backParticle.getName(), "&7Click to equip", false);
            ItemStack noPermission = customItem(Material.RED_STAINED_GLASS_PANE, backParticle.getName(), "&7Statis: &c&lLOCKED", false);

            if (Objects.requireNonNull(Bukkit.getPlayer(cosmUser.getUuid())).hasPermission(backParticle.getPermission())) {
                if (applied) {
                    inventory.setItem(counter, current);
                } else {
                    inventory.setItem(counter, permission);
                }
            } else {
                inventory.setItem(counter, noPermission);
            }

            counter++;
        }

        inventory.setItem(31, customItem(Material.BARRIER, "&c&lRemove", "&7Click to remove back particle", false));
        inventory.setItem(35, customItem(Material.OAK_FENCE_GATE, "&f&lBack", "&7Click to go back", false));
        return inventory;
    }

    /**
     * Pet menu
     * @return inventory
     */
    public Inventory petInventory(CosmUser cosmUser) {
        int counter = 0;
        Inventory inventory = Bukkit.createInventory(null, 36, "Cosmetics: Pets");

        for (Pets pet : Pets.values())
        {
            boolean applied = cosmUser.isActivePet() && cosmUser.getPet() == pet;
            ItemStack current = customItem(Material.ORANGE_STAINED_GLASS_PANE, pet.getName(), "&7Status: &6&lAPPLIED", true);
            ItemStack permission = customItem(pet.getGuiItem(), pet.getName(), "&7Click to equip", false);
            ItemStack noPermission = customItem(Material.RED_STAINED_GLASS_PANE, pet.getName(), "&7Statis: &c&lLOCKED", false);

            if (Objects.requireNonNull(Bukkit.getPlayer(cosmUser.getUuid())).hasPermission(pet.getPermission())) {
                if (applied) {
                    inventory.setItem(counter, current);
                } else {
                    inventory.setItem(counter, permission);
                }
            } else {
                inventory.setItem(counter, noPermission);
            }

            counter++;
        }

        inventory.setItem(31, customItem(Material.BARRIER, "&c&lRemove", "&7Click to remove pet", false));
        inventory.setItem(34, customItem(Material.BOOK, "&6&lPet Editor", "&7Click for pet settings", false));
        inventory.setItem(35, customItem(Material.OAK_FENCE_GATE, "&f&lBack", "&7Click to go back", false));
        return inventory;
    }


    /**
     * Pet editor menu
     * @return inventory
     */
    public Inventory petEditorGUI(CosmUser cosmUser) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Cosmetics: Pet Editor");
        inventory.setItem(11, customItem(Material.APPLE, "&c&lPet Baby", "&7Doesn't apply to all pets", cosmUser.isBaby()));
        inventory.setItem(13, customItem(Material.CREEPER_SPAWN_EGG, "&a&lCharged", "&7Only applies to creepers", cosmUser.isCharged()));
        inventory.setItem(15, customItem(Material.BEACON, "&b&lGlow", "&7Click to make pet glow", cosmUser.isGlow()));
        inventory.setItem(26, customItem(Material.OAK_FENCE_GATE, "&f&lBack", "&7Click to go back", false));
        return inventory;
    }

    /**
     * Suit menu
     * @return inventory
     */
    public Inventory suitInventory(CosmUser cosmUser) {
        int counter = 0;
        Inventory inventory = Bukkit.createInventory(null, 45, "Cosmetics: Suits");

        for (Suits suit : Suits.values())
        {
            boolean applied = cosmUser.isWearingSuit() && cosmUser.getSuit() == suit;
            ItemStack current = customItem(Material.ORANGE_STAINED_GLASS_PANE, suit.getName(), "&7Status: &6&lAPPLIED", true);
            ItemStack permission = baseSkullItem(suit.getHeadId(), suit.getName(), false);
            ItemStack noPermission = customItem(Material.RED_STAINED_GLASS_PANE, suit.getName(), "&7Statis: &c&lLOCKED", false);

            if (Objects.requireNonNull(Bukkit.getPlayer(cosmUser.getUuid())).hasPermission(suit.getPermission())) {
                if (applied) {
                    inventory.setItem(counter, current);
                } else {
                    inventory.setItem(counter, permission);
                }
            } else {
                inventory.setItem(counter, noPermission);
            }

            counter++;
        }

        inventory.setItem(40, customItem(Material.BARRIER, "&c&lRemove", "&7Click to remove pet", false));
        inventory.setItem(44, customItem(Material.OAK_FENCE_GATE, "&f&lBack", "&7Click to go back", false));
        return inventory;
    }

    /** Methods returns custom item stack */
    private ItemStack customItem(Material material, String name, String lore, boolean glow) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null)
        {
            if (name != null)
                meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

            if (lore != null)
                meta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', lore)));

            if (glow)
            {
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }

            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }

        item.setItemMeta(meta);
        return item;
    }

    /** Returns head item stack (specifically for suit menu) */
    private ItemStack baseSkullItem(String id, String name, boolean glow) {
        // Create new head api class object
        HeadDatabaseAPI api = new HeadDatabaseAPI();

        // Create item-stack to hold head from database
        ItemStack item = api.getItemHead(id);

        // Set head item meta
        ItemMeta meta = item.getItemMeta();

        // Check that meta is not null
        if (meta != null)
        {
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            meta.setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&7Click to equip")));

            if (glow) {
                meta.addEnchant(Enchantment.DURABILITY, 1, true);
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            }
        }

        // Set item meta
        item.setItemMeta(meta);

        // Create item-stack to hold head from database
        return item;
    }
}
