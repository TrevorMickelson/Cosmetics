package com.codepunisher.cosmetics.cosmetics;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public enum Pets
{
    PIG("&dPig", Material.PIG_SPAWN_EGG, EntityType.PIG, 1.8D, ChatColor.LIGHT_PURPLE),
    WOLF("&bWolf", Material.WOLF_SPAWN_EGG, EntityType.WOLF, 1.5D, ChatColor.AQUA),
    BEE("&eBee", Material.BEE_SPAWN_EGG, EntityType.BEE, 2.0D, ChatColor.YELLOW),
    KITTY("&6Kitty", Material.CAT_SPAWN_EGG, EntityType.CAT, 1.5D, ChatColor.GOLD),
    CHICKEN("&cChicken", Material.CHICKEN_SPAWN_EGG, EntityType.CHICKEN, 1.65D, ChatColor.RED),
    CREEPER("&aCreeper", Material.CREEPER_SPAWN_EGG, EntityType.CREEPER, 1.65D, ChatColor.GREEN),
    FOX("&6Fox", Material.FOX_SPAWN_EGG, EntityType.FOX, 1.5D, ChatColor.GOLD),
    HOGLIN("&cHoglin", Material.HOGLIN_SPAWN_EGG, EntityType.HOGLIN, 1.5D, ChatColor.RED),
    COW("&eCow", Material.COW_SPAWN_EGG, EntityType.COW, 2.0D, ChatColor.YELLOW),
    DONKEY("&7Donkey", Material.DONKEY_SPAWN_EGG, EntityType.DONKEY, 2.0D, ChatColor.GRAY),
    SHEEP("&fSheep", Material.SHEEP_SPAWN_EGG, EntityType.SHEEP, 1.75D, ChatColor.DARK_GREEN),
    RABBIT("&6Rabbit", Material.RABBIT_SPAWN_EGG, EntityType.RABBIT,3.0D, ChatColor.GOLD),
    POLAR_BEAR("&fPolar Bear", Material.POLAR_BEAR_SPAWN_EGG, EntityType.POLAR_BEAR, 1.65D, ChatColor.AQUA),
    PARROT("&aParrot", Material.PARROT_SPAWN_EGG, EntityType.PARROT, 2.0D, ChatColor.GREEN),
    PANDA("&fPanda", Material.PANDA_SPAWN_EGG, EntityType.PANDA, 3.0D, ChatColor.GREEN),
    LLAMA("&6Llama", Material.LLAMA_SPAWN_EGG, EntityType.LLAMA, 2.0D, ChatColor.GOLD),
    HORSE("&eHorse", Material.HORSE_SPAWN_EGG, EntityType.HORSE, 2.0D, ChatColor.YELLOW),
    TURTLE("&2Turtle", Material.TURTLE_SPAWN_EGG, EntityType.TURTLE, 4.5D, ChatColor.DARK_GREEN),
    SPIDER("&cSpider", Material.SPIDER_SPAWN_EGG, EntityType.SPIDER, 1.5D, ChatColor.RED),
    IRON_GOLEM("&fIron Golem", Material.CARVED_PUMPKIN, EntityType.IRON_GOLEM, 1.65D, ChatColor.GOLD);

    private String name;
    private Material guiItem;
    private EntityType entityType;
    double speed;
    private ChatColor chatColor;

    Pets(String name, Material guiItem, EntityType entityType, double speed, ChatColor chatColor) {
        this.speed = speed;
        this.name = name;
        this.guiItem = guiItem;
        this.entityType = entityType;
        this.chatColor = chatColor;
    }

    public String getName() { return this.name; }
    public Material getGuiItem() { return this.guiItem; }
    public EntityType getEntityType() { return this.entityType; }
    public double getSpeed() { return this.speed; }
    public ChatColor getChatColor() { return this.chatColor; }
    public String getPermission() { return "cosmetics.pet." + name().toLowerCase(); }
}
