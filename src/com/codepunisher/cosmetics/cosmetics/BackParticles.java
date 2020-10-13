package com.codepunisher.cosmetics.cosmetics;

import com.codepunisher.cosmetics.cosmetics.ParticleBluePrints;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;

public enum BackParticles
{
    //---(SYTHES)---
    SYTHE("&cSythe", Material.DIAMOND_HOE, ParticleBluePrints.Sythe, 1.3D, 2.2D, 0, 1.0F,  0.1, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE, Particle.REDSTONE }, null, Color.fromRGB(5, 5, 5), Color.fromRGB(209, 1, 1)),
    VOID_SYTHE("&5Void Sythe", Material.NETHERITE_HOE, ParticleBluePrints.Sythe, 1.3D, 2.2D, 0, 1.0F,  0.1, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE, Particle.REDSTONE }, null, Color.fromRGB(5, 5, 5), Color.fromRGB(58, 1, 138)),

    //---(WINGS)---
    DARK_MAIDEN_WINGS("&4Dark Maiden Wings", Material.OBSIDIAN, ParticleBluePrints.BatWings, 0, 2.2D, 0,  1.0F, 0.2, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE }, null, Color.fromRGB(97, 1, 1)),
    MOON_WINGS("&bMoon Wings", Material.ENDER_PEARL, ParticleBluePrints.BatWings, 0, 2.2D, 0,  1.0F, 0.2, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE }, null, Color.fromRGB(1, 188, 201)),
    END_MASTER_WINGS("&5End Master Wings", Material.ENDER_CHEST, ParticleBluePrints.AbyseWing, 0, 2.7D, 0,  1.0F, 0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(5, 5, 5), Color.fromRGB(125, 1, 125)),
    SPACE_WINGS("&3Space Wings", Material.DIAMOND, ParticleBluePrints.SpaceWings, 0, 1.8D, 0,  1.0F, 0.2, new Particle[] { Particle.CRIT_MAGIC, Particle.REDSTONE }, null, Color.fromRGB(1, 189, 60)),
    FLAME_WINGS("&cFlame Wings", Material.BLAZE_POWDER, ParticleBluePrints.FlameWings, 1.2D, 2.3D, 0,  1.0F, 0.2, new Particle[] { Particle.FLAME }, null, null),
    ICE_WINGS("&fIce Wings", Material.ICE, ParticleBluePrints.iceWings, 1.0D, 2.1D, 0, 1.0F,  0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(75, 250, 255), Color.fromRGB(255, 255, 255)),
    BLUE_FLAME_WINGS("&bBlue Flame Wings", Material.SOUL_LANTERN, ParticleBluePrints.FlameWings, 1.2D, 2.3D, 0,  1.0F, 0.2, new Particle[] { Particle.SOUL_FIRE_FLAME }, null, null),
    LIGHT_WINGS("&fLight Wings", Material.GOLD_INGOT, ParticleBluePrints.lightWings, 0.5D, 2.5D, 0,  1.0F, 0.1, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE, Particle.REDSTONE }, null, Color.fromRGB(192, 192, 192), Color.fromRGB(255, 255, 255)),
    DARKNESS_WINGS("&8Darkness Wings", Material.BLACK_DYE, ParticleBluePrints.AbyseWing, 0, 1.9D, 0,  1.0F, 0.1, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(5, 5, 5), Color.fromRGB(20, 20, 20)),
    PINK_BAT("&dPink Bat", Material.PINK_DYE, ParticleBluePrints.BatWings, 0, 1.6D, 0,  1.0F, 0.1, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE }, null, Color.fromRGB(255, 0, 255)),

    //---(WEAPONS)---
    GOLDEN_GREAT_SWORD("&6Golden Great Sword", Material.GOLDEN_SWORD, ParticleBluePrints.BasicGreatSword, 1.2D, 2.2D, 0,  1.0F, 0.2D, new Particle[] { Particle.REDSTONE, Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(51, 51, 51), Color.fromRGB(5, 5, 5), Color.fromRGB(232, 159, 1)),
    DARK_KATANA("&7Dark Katana", Material.IRON_SWORD, ParticleBluePrints.DarkKatana, 1.2, 2.2D, 0,  1.0F, 0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(110, 109, 109), Color.fromRGB(5, 5, 5), Color.fromRGB(255, 1, 1)),
    RED_GREAT_SWORD("&4Red Great Sword", Material.NETHERITE_SWORD, ParticleBluePrints.BasicGreatSword, 1.2D, 2.2D, 0,  1.0F, 0.2, new Particle[] { Particle.SMOKE_NORMAL, Particle.REDSTONE, Particle.REDSTONE }, null, Color.fromRGB(5, 5, 5), Color.fromRGB(54, 1, 1)),

    //---(CHRISTMAS)---
    RED_CANDY_CANE("&cRed Candy Cane", Material.RED_DYE, ParticleBluePrints.CandyCane, 0.4, 1.8D, 0,  1.0F, 0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(191, 1, 1), Color.fromRGB(255, 255, 255)),
    GREEN_CANDY_CANE("&aGreen Candy Cane", Material.GREEN_DYE, ParticleBluePrints.CandyCane, 0.4, 1.8D, 0,  1.0F, 0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(19, 194, 1), Color.fromRGB(255, 255, 255)),

    //---(RANDOM)---
    BABY_BOTTLE("&bBaby Bottle", Material.WHITE_DYE, ParticleBluePrints.BabyBottle, 1.2D, 2.2D, 0,  1.0F, 0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(1, 191, 172), Color.fromRGB(255, 255, 255)),
    RAIN_CLOUD("&7Rain Cloud", Material.WATER_BUCKET, ParticleBluePrints.RAIN_CLOUD, 0.2,3,0.6,5,1.0F,0.2,0,0.2,0.1, new Particle[] { Particle.WATER_DROP, Particle.REDSTONE }, null, Color.fromRGB(79, 79, 79)),
    HEARTS("&cHearts", Material.POPPY, ParticleBluePrints.Heart, 0.6, 2.3D, 0,  1.0F, 0.1, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(102, 1, 1), Color.fromRGB(204, 1, 1)),
    ASTRONAUT("&3Astronaut", Material.ELYTRA, ParticleBluePrints.astronaut, 0.8, 1.55D, 0,  1.0F, 0.1, new Particle[] { Particle.REDSTONE, Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(255, 255, 255), Color.fromRGB(64, 64, 64), Color.fromRGB(255, 94, 1)),
    OFFICER_BAT("&9Officer Bat", Material.STICK, ParticleBluePrints.OfficerBat, 0.75, 2.05D, 0,  1.0F, 0.2, new Particle[] { Particle.REDSTONE, Particle.REDSTONE }, Color.fromRGB(5, 5, 5), Color.fromRGB(5, 5, 5));

    private String name;
    private Material guiItem;
    private double addX, addY, addZ;                                 // Adds to x, y, z of particle location
    private int amount;                                              // Amount of particles to display
    private float size;                                              // Size of particles to display
    private double radiusX, radiusY, radiusZ;                        // Particle radius
    private double particleSpace;                                    // Space between particles
    private String[][] blueprint;                                    // Holds mapped blueprint (can be found in blueprints class)
    private String[] blueprintChars = { "X", "+", "*", "`" };        // Holds blueprint characters used to create particles
    private Particle[] particles;                                    // Holds particles to be used in creation
    private Color[] color;                                           // Holds colors to be used for redstone particles

    BackParticles(String name, Material guiItem, String[][] blueprint, double addX, double addY, double addZ, float size, double particleSpace, Particle[] particles, Color... colors) {
        this.name = name;
        this.guiItem = guiItem;
        this.addX = addX;
        this.addY = addY;
        this.addZ = addZ;
        this.size = size;
        this.particleSpace = particleSpace;
        this.blueprint = blueprint;
        this.particles = particles;
        this.color = colors;
    }
    BackParticles(String name, Material guiItem, String[][] blueprint, double addX, double addY, double addZ, int amount, float size, double radiusX, double radiusY, double radiusZ, double particleSpace, Particle[] particles, Color... colors) {
        this.name = name;
        this.guiItem = guiItem;
        this.addX = addX;
        this.addY = addY;
        this.addZ = addZ;
        this.amount = amount;
        this.size = size;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        this.particleSpace = particleSpace;
        this.blueprint = blueprint;
        this.particles = particles;
        this.color = colors;
    }

    public String getName() { return this.name; }
    public Material getGuiItem() { return this.guiItem; }
    public double getAddX() { return this.addX; }
    public double getAddY() { return this.addY; }
    public double getAddZ() { return this.addZ; }
    public int getAmount() { return this.amount; }
    public float getSize() { return this.size; }
    public double getRadiusX() { return this.radiusX; }
    public double getRadiusY() { return this.radiusY; }
    public double getRadiusZ() { return this.radiusZ; }
    public double getParticleSpace() { return this.particleSpace; }
    public String[][] getBlueprint() { return this.blueprint; }
    public String[] getBlueprintChars() { return this.blueprintChars;}
    public Particle[] getParticles() { return this.particles; }
    public Color[] getColor() { return this.color; }
    public String getPermission() { return "cosmetics.back." + name().toLowerCase(); }
}
