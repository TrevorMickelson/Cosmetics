package cosmetics;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public enum Pets
{
    PIG("&dPig", Material.PIG_SPAWN_EGG, EntityType.PIG, 1.8D),
    WOLF("&bWolf", Material.WOLF_SPAWN_EGG, EntityType.WOLF, 1.5D),
    BEE("&eBee", Material.BEE_SPAWN_EGG, EntityType.BEE, 2.0D),
    KITTY("&6Kitty", Material.CAT_SPAWN_EGG, EntityType.CAT, 1.5D),
    CHICKEN("&cChicken", Material.CHICKEN_SPAWN_EGG, EntityType.CHICKEN, 1.65D),
    CREEPER("&aCreeper", Material.CREEPER_SPAWN_EGG, EntityType.CREEPER, 1.65D),
    FOX("&6Fox", Material.FOX_SPAWN_EGG, EntityType.FOX, 1.5D),
    HOGLIN("&cHoglin", Material.HOGLIN_SPAWN_EGG, EntityType.HOGLIN, 1.5D),
    COW("&eCow", Material.COW_SPAWN_EGG, EntityType.COW, 2.0D),
    DONKEY("&7Donkey", Material.DONKEY_SPAWN_EGG, EntityType.DONKEY, 2.0D),
    SHEEP("&fSheep", Material.SHEEP_SPAWN_EGG, EntityType.SHEEP, 1.75D),
    RABBIT("&6Rabbit", Material.RABBIT_SPAWN_EGG, EntityType.RABBIT,3.0D),
    POLAR_BEAR("&fPolar Bear", Material.POLAR_BEAR_SPAWN_EGG, EntityType.POLAR_BEAR, 1.65D),
    PARROT("&aParrot", Material.PARROT_SPAWN_EGG, EntityType.PARROT, 2.0D),
    PANDA("&fPanda", Material.PANDA_SPAWN_EGG, EntityType.PANDA, 3.0D),
    LLAMA("&6Llama", Material.LLAMA_SPAWN_EGG, EntityType.LLAMA, 2.0D),
    HORSE("&eHorse", Material.HORSE_SPAWN_EGG, EntityType.HORSE, 2.0D),
    TURTLE("&2Turtle", Material.TURTLE_SPAWN_EGG, EntityType.TURTLE, 4.5D),
    SPIDER("&cSpider", Material.SPIDER_SPAWN_EGG, EntityType.SPIDER, 1.5D),
    IRON_GOLEM("&fIron Golem", Material.CARVED_PUMPKIN, EntityType.IRON_GOLEM, 1.65D);

    private String name;
    private Material guiItem;
    private EntityType entityType;
    double speed;

    Pets(String name, Material guiItem, EntityType entityType, double speed) {
        this.speed = speed;
        this.name = name;
        this.guiItem = guiItem;
        this.entityType = entityType;
    }

    public String getName() { return this.name; }
    public Material getGuiItem() { return this.guiItem; }
    public EntityType getEntityType() { return this.entityType; }
    public double getSpeed() { return this.speed; }
    public String getPermission() { return "cosmetics.pet." + name().toLowerCase(); }
}
