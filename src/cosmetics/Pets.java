package cosmetics;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public enum Pets
{
    PIGGY("&dPiggy", Material.PIG_SPAWN_EGG, EntityType.PIG);

    private String name;
    private Material guiItem;
    private EntityType entityType;

    Pets(String name, Material guiItem, EntityType entityType) {
        this.name = name;
        this.guiItem = guiItem;
        this.entityType = entityType;
    }

    public String getName() { return this.name; }
    public Material getGuiItem() { return this.guiItem; }
    public EntityType getEntityType() { return this.entityType; }
    public String getPermission() { return "cosmetics.pet." + name().toLowerCase(); }
}
