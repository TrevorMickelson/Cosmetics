package com.codepunisher.cosmetics.cosmetics;

import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public enum Suit {
    MOON("&bMoon", new String[] {"Shrouded Boots","Shrouded Leggings","Shrouded Breastplate","Shrouded Helmet"},
        new Color[] { Color.fromRGB(255, 255, 255), Color.fromRGB(255, 255, 255), Color.fromRGB(255, 255, 255) }, ChatColor.AQUA , "30494",
        Collections.singletonList("Corrupt angel from the Suspended Depths"), true),

    DARK_MAIDEN("&cDark Maiden", new String[] {"Gladiator Boots","Gladiator Legs","Gladiator Chest","Gladiator Helmet"},
        new Color[] { Color.fromRGB(20,20,20), Color.fromRGB(150,0,0), Color.fromRGB(20,20,20) }, ChatColor.RED, "29678",
        Collections.singletonList("Forged in an eternal nightmare!"), false),

    END_MASTER("&5End Master", new String[] {"Ender Boots","Ender Leggings","Ender Chest","Ender Helm"},
        new Color[] { Color.fromRGB(0,0,0), Color.fromRGB(0,0,0), Color.fromRGB(0,0,0) }, ChatColor.DARK_PURPLE, "18809",
        Collections.singletonList("King Of The Enderman"), true),

    DARK_NINJA("&7Dark Ninja", new String[] {"Dark Shadow Boots","Dark Shadow Pants","Dark Shadow Tunic","Dark Cloak"},
        new Color[] { Color.fromRGB(0,0,0), Color.fromRGB(0,0,0), Color.fromRGB(0,0,0) }, ChatColor.GRAY, "14947",
        Collections.singletonList("I lurk in the shadows.."), false),

    FLAME_QUEEN("&6Flame Queen", new String[] {"Flame Boots","Flame Leggings","Flame Chest","Flame Helm"},
        new Color[] { Color.fromRGB(255,111,0), Color.fromRGB(255, 77, 0), Color.fromRGB(255,111,0) }, ChatColor.GOLD, "13077",
        Collections.singletonList("I am the queen of fire!"), true),

    ICE_QUEEN("&bIce Queen", new String[] {"Ice Boots","Ice Leggings","Ice Chest","Ice Helm"},
        new Color[] { Color.fromRGB(0,222,255), Color.fromRGB(0, 154, 255), Color.fromRGB(0,222,255) }, ChatColor.AQUA, "28213",
        Collections.singletonList("I am the queen of ice!"), true),

    LIGHT("&fLight", new String[] {"Light Boots","Light Leggings","Light Chest","Light Helm"},
        new Color[] { Color.fromRGB(255,255,255), Color.fromRGB(255,255,255), Color.fromRGB(255,255,255) }, ChatColor.WHITE,  "24485",
        Collections.singletonList("I am brighter than light itself"), true),

    DARKNESS("&6Darkness", new String[] {"Darkness Boots","Darkness Leggings","Darkness Chest","Darkness Helm"},
        new Color[] { Color.fromRGB(0,0,0), Color.fromRGB(0,0,0), Color.fromRGB(0,0,0) }, ChatColor.GOLD, "14262",
        Collections.singletonList("Darkness follows"), false),

    LOVE_ME("&cLove Me", new String[] {"Love Boots","Love Leggings","Love Chest","Love Helm"},
        new Color[] { Color.fromRGB(235, 255,46), Color.fromRGB(235, 255,46), Color.fromRGB(235, 255,46) }, ChatColor.RED, "25606",
        Collections.singletonList("Please.. Love.. Me.."), true),

    REAPER("&4Reaper", new String[] {"Destructive Boots","Grim Pants","Deathplate","Depressed Helm"},
        new Color[] { Color.fromRGB(38, 38, 38), Color.fromRGB(38, 38, 38), Color.fromRGB(38, 38, 38) }, ChatColor.DARK_RED, "22771",
        Collections.singletonList("I sense death..."), false),

    VOID_REAPER("&5Void Reaper", new String[] {"Void Boots","Void Pants","Void plate","Void Helm"},
        new Color[] { Color.fromRGB(0,0,0), Color.fromRGB(0,0,0), Color.fromRGB(0,0,0) }, ChatColor.DARK_PURPLE, "16884",
        Collections.singletonList("A breath of darkness"), false),

    GOLDEN_KNIGHT("&6Golden Knight", new String[] {"Shiny Golden Boots","Shiny Golden Pants","Shiny Golden Tunic", "Shiny Golden Helm"},
        new Color[] { Color.fromRGB(232, 159, 0), Color.fromRGB(232, 159, 0), Color.fromRGB(232, 159, 0) }, ChatColor.GOLD, "1523",
        Collections.singletonList("I fight for GOLD!"), false),

    BRIGHT("&fBright", new String[] {"Shiny Boots","Shiny Leggings","Shiny Chest","Shiny Helm"},
        new Color[] { Color.fromRGB(211, 195, 230), Color.fromRGB(211, 195, 230), Color.fromRGB(211, 195, 230) }, ChatColor.WHITE, "31795",
        Collections.singletonList("Everything Shines When I Am Around!"), true),

    MR_ELF("&aMr Elf", new String[] {"Tiny Boots","Tiny Pants","Tiny Coat","Tiny Hat"},
        new Color[] { Color.fromRGB(191, 0, 10), Color.fromRGB(89, 52, 0), Color.fromRGB(191, 0, 10) }, ChatColor.GREEN, "2470",
        Collections.singletonList("Would you like a present?"), false),

    MRS_ELF("&cMrs Elf", new String[] {"Winter Boots","Winter Pants","Winter Coat","Winter Hat"},
        new Color[] { Color.fromRGB(19, 194, 0), Color.fromRGB(89, 52, 0), Color.fromRGB(19, 194, 0) }, ChatColor.RED, "2472",
        Collections.singletonList("Winter Is Coming..."), false),

    CHICKY_CHICK("&eChicky Chick", new String[] {"Chicken Helmet","Chicken Pants","Chicken Coat","Chicken Hat"},
        new Color[] { Color.fromRGB(204, 204, 0), Color.fromRGB(204, 204, 0), Color.fromRGB(204, 204, 0) }, ChatColor.YELLOW, "4267",
        Collections.singletonList("I destroy all in my path"), false),

    BLOOD_KNIGHT("&4Blood Knight", new String[] {"Bloody Helmet","Bloody Pants","Bloody Coat","Bloody Hat"},
        new Color[] { Color.fromRGB(51, 0, 0), Color.fromRGB(0, 0, 0), Color.fromRGB(51, 0, 0) }, ChatColor.DARK_RED, "2133",
        Collections.singletonList("Please don't eat me..."), false),

    ASTRONAUT("&3Astronaut", new String[] {"Space Helmet","Space Pants","Space Chest","Space Boots"},
        new Color[] { Color.fromRGB(255, 255, 255), Color.fromRGB(255, 255, 255), Color.fromRGB(255, 255, 255) }, ChatColor.DARK_AQUA, "4634",
        Collections.singletonList("I love Space!"), true),

    REGINAL_PIG("&dReginal Pig", new String[] {"Piggy Helmet","Piggy Pants","Piggy Chest","Piggy Boots"},
        new Color[] { Color.fromRGB(255, 171, 250), Color.fromRGB(255, 171, 250), Color.fromRGB(255, 171, 250) }, ChatColor.LIGHT_PURPLE, "25778",
        Collections.singletonList("Piggy Poog"), false),

    ESTABAN("&9Estaban", new String[] {"Officer Helmet","Officer Pants","Officer Chest","Officer Boots"},
        new Color[] { Color.fromRGB(0, 6, 169), Color.fromRGB(0, 6, 169), Color.fromRGB(0, 6, 169) }, ChatColor.BLUE, "8115",
        Collections.singletonList("I catch bad guys homie"), false),

    BEAVER("&6Beaver", new String[] {"Beaver Fur","Beaver Fur","Beaver Fur","Beaver Fur"},
        new Color[] { Color.fromRGB(51, 25, 0), Color.fromRGB(51, 25, 0), Color.fromRGB(51, 25, 0) }, ChatColor.GOLD, "6740",
        Collections.singletonList("I can build anything!"), false),

    BABY("&bBaby", new String[] {"GooGaa","GooGoo","GaGa","Uhm.. GooGaa"},
        new Color[] { Color.fromRGB(117, 170, 255), Color.fromRGB(117, 170, 255), Color.fromRGB(117, 170, 255) }, ChatColor.AQUA, "14244",
        Collections.singletonList("Give Me My Bottle!"), false),

    ARC_ANGEL("&dArc Angel", new String[] {"Glory Boots","Glory Leggings","Glory Chest","Glory Helm"},
        new Color[] { Color.fromRGB(243, 171, 255), Color.fromRGB(243, 171, 255), Color.fromRGB(243, 171, 255) }, ChatColor.LIGHT_PURPLE, "12146",
        Collections.singletonList("Glory be upon you!"), false),

    DARK_KNIGHT("&4Dark Knight", new String[] {"Stainless Black Boots","Stainless Black Pants","Stainless Black Tunic", "Stainless Black Helm"},
        new Color[] { Color.fromRGB(0, 0, 0), Color.fromRGB(0, 0, 0), Color.fromRGB(0, 0, 0) }, ChatColor.DARK_RED, "32755",
        Collections.singletonList("The Darkness Will Prevail!"), false),

    SANTA("&cSanta", new String[] {"Santa Boots","Santa Pants","Santa Coat","Santa Hat"},
        new Color[] { Color.fromRGB(201, 40, 40), Color.fromRGB(201, 40, 40), Color.fromRGB(201, 40, 40) }, ChatColor.RED, "24200",
        Collections.singletonList("Ho..Ho..Ho..Merry Christmas!"), false),

    BABY_PENGUIN("&eBaby Penguin", new String[] {"Penguin Boots","Penguin Pants","Penguin Chest","Penguin Hat"},
        new Color[] { Color.fromRGB(255, 255, 255), Color.fromRGB(0, 0, 0), Color.fromRGB(255, 255, 255) }, ChatColor.YELLOW, "1697",
        Collections.singletonList("Slippy slippy slide!"), false),

    EMO_KID("&4Emo Kid", new String[] {"Emo Boots","Emo Pants","Emo Chest","Emo Hat"},
        new Color[] { Color.fromRGB(0, 0, 0), Color.fromRGB(200, 0, 0), Color.fromRGB(0, 0, 0) }, ChatColor.DARK_RED, "18640",
        Collections.singletonList("No one understands me..."), false),

    ALIEN("&2Alien", new String[] {"Bee boo bop","Beep beep","Boop boop","I won't probe you"},
        new Color[] { Color.fromRGB(96, 96, 96), Color.fromRGB(96, 96, 96), Color.fromRGB(96, 96, 96) }, ChatColor.DARK_GREEN, "31273",
        Collections.singletonList("I. Come. In. Piece."), false),

    EGIRL("&dEgirl", new String[] {"Egirl Boots","Egirl Pants","Egirl Chest","Egirl Hat"},
        new Color[] { Color.fromRGB(153, 51, 255), Color.fromRGB(153, 51, 255), Color.fromRGB(153, 51, 255) }, ChatColor.LIGHT_PURPLE, "19429",
        Collections.singletonList("Omg I love E-Dating!"), false),

    SOLDIER("&2Soldier", new String[] {"Boots","Trousers","Flak","Kevlar"},
        new Color[] { Color.fromRGB(128, 106, 69), Color.fromRGB(128, 106, 69), Color.fromRGB(128, 106, 69) }, ChatColor.DARK_GREEN, "13425",
        Collections.singletonList("...."), false),

    REINDEER("&6Reindeer", new String[] {"Raindeer Fur","Raindeer Fur","Raindeer Fur","Raindeer Fur"},
        new Color[] { Color.fromRGB(66, 28, 0), Color.fromRGB(66, 28, 0), Color.fromRGB(66, 28, 0) }, ChatColor.GOLD, "2910",
        Collections.singletonList("Rudolf sucks!"), false),

    SNOWMAN("&fSnowman", new String[] {"Snow","Snow","Snow","Snow"},
        new Color[] { Color.fromRGB(255, 255, 255), Color.fromRGB(255, 255, 255), Color.fromRGB(255, 255, 255) }, ChatColor.WHITE, "24080",
        Collections.singletonList("Is It Christmas Yet?"), false),

    BOB("&7Bob", new String[] {"Plain Boots","Plain Boring Pants","Plain Old Shirt","Bald"},
        new Color[] { Color.fromRGB(89, 89, 89), Color.fromRGB(89, 89, 89), Color.fromRGB(89, 89, 89) }, ChatColor.GRAY, "2597",
        Collections.singletonList("The only feeling I feel is sad..."), false),

    POOP("&6Poop", new String[] {"Poopy Boots","Poopy Pants","Poopy Chest","Poopy Face"},
        new Color[] { Color.fromRGB(102, 51, 0), Color.fromRGB(102, 51, 0), Color.fromRGB(102, 51, 0) }, ChatColor.GOLD, "1682",
        Collections.singletonList("Does anyone have Febreeze?"), false),

    ASTRODUG("&3Astrodug", new String[] {"Gravity Boots","Carbon Fiber Legs","Carbon Fiber Chest","Air Inducting Helmet"},
        new Color[] { Color.fromRGB(28, 26, 41), Color.fromRGB(28, 26, 41), Color.fromRGB(28, 26, 41) }, ChatColor.DARK_AQUA, "23420",
        Collections.singletonList("A Treasure From Interstellar Space!"), true);

    private final String name;
    private final String[] displayName;
    private final Color[] armorColor;
    private final ChatColor tierColor;
    private final String headId;
    private final List<String> lore;
    private final boolean isGlowing;

    Suit(String name, String[] displayName, Color[] armorColor, ChatColor tierColor, String headId, List<String> lore, boolean isGlowing) {
        this.name = name;
        this.displayName = displayName;
        this.armorColor = armorColor;
        this.tierColor = tierColor;
        this.headId = headId;
        this.lore = lore;
        this.isGlowing = isGlowing;
    }

    public String getName() { return this.name; }
    public ItemStack[] getArmour() {
        try {
            // Armor item stacks
            ItemStack[] itemStacks = new ItemStack[] {
                new ItemStack(Material.LEATHER_BOOTS),
                new ItemStack(Material.LEATHER_LEGGINGS),
                new ItemStack(Material.LEATHER_CHESTPLATE),
                customHead(displayName[3], tierColor, lore, headId)
            };

            // For each armor piece in base armor items
            for(int i = 0; i < itemStacks.length - 1; i++) {
                // Create leather item meta and set desired values
                LeatherArmorMeta meta = (LeatherArmorMeta) itemStacks[i].getItemMeta();

                // Check that meta is not null
                if(!Objects.isNull(meta)) {
                    // Set flags for armor
                    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);

                    // Check if suit is glowing or not
                    if(isGlowing)
                        meta.addEnchant(Enchantment.DURABILITY, 1, true);

                    // Set display name of armor
                    if(!Objects.isNull(displayName[i]))
                        meta.setDisplayName(tierColor + displayName[i]);

                    // Set lore for armor
                    if(!Objects.isNull(lore))
                        meta.setLore(lore);

                    // Set armor color
                    if(!Objects.isNull(armorColor))
                        meta.setColor(armorColor[i]);

                    // Set armor to unbreakable
                    meta.setUnbreakable(true);
                }

                itemStacks[i].setItemMeta(meta);
            }

            // Return items as Item Stack Array
            return itemStacks;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getHeadId() { return headId; }
    public String getPermission() { return "cosmetics.suit." + name().toLowerCase(); }

    // Get custom head from heads database
    private ItemStack customHead(String tag, ChatColor tierColor, List<String> lore, String id) {
        try {
            HeadDatabaseAPI api = new HeadDatabaseAPI();

            // Create item-stack to hold head from database
            ItemStack item = api.getItemHead(id);
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                meta.isUnbreakable();
                meta.setDisplayName(tierColor + tag);
                meta.setLore(lore);
            }

            item.setItemMeta(meta);
            return item;
        }
        catch (Exception e) {
            Bukkit.getServer().getConsoleSender().sendMessage("§cError getting custom head from database");
            return null;
        }
    }

    public ItemStack getCustomHead() { return customHead(displayName[3], tierColor, lore, headId); }
}
