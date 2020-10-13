package com.codepunisher.cosmetics.cosmetics;

import org.bukkit.*;

public enum TrailParticles
{
    // Random Trails
    SUSPENDED("&5Suspended", Material.ENDER_PEARL, Particle.TOWN_AURA, 20, 0.2, 0.1, 0.2),
    SMOKEY("&7Smokey", Material.SMOKER, Particle.SMOKE_NORMAL, 12, 0.1, 0, 0.1),
    BLAST_OFF("&fBlast off", Material.COBWEB, Particle.SPIT, 20,  0, 0, 0),
    GREEN_SPARKLE("&aGreen Sparkle", Material.EMERALD, Particle.VILLAGER_HAPPY, 2, 0.2,0.1,0.2),
    FLAME("&6Flame", Material.BLAZE_POWDER, Particle.FLAME, 2, 0.2, 0.1, 0.2),
    HEART("&cHeart", Material.POPPY, Particle.HEART, 1,  0, 0, 0),
    WHITE_SPARKLE("&fWhite Sparkle", Material.IRON_INGOT, Particle.FIREWORKS_SPARK, 3,  0.2, 0.1, 0.2),
    BLUE_FLAME("&bBlue Flame", Material.SOUL_TORCH, Particle.SOUL_FIRE_FLAME, 2,  0.2, 0.1, 0.2),
    DRAGON_BREATH("&5Dragon Breath", Material.ENDER_CHEST, Particle.DRAGON_BREATH, 4,  0.2, 0.1, 0.2),
    ENCHANTMENT("&cEnchantment", Material.ENCHANTING_TABLE, Particle.ENCHANTMENT_TABLE, 20,  0.2, 0.1, 0.2);

    private String name;
    private Material guiItem;
    private double radiusX;
    private double radiusY;
    private double radiusZ;
    private int amount;
    private Particle particle;

    TrailParticles(String name, Material guiItem, Particle particle, int amount, double radiusX, double radiusY, double radiusZ) {
        this.name = name;
        this.guiItem = guiItem;
        this.particle = particle;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.radiusZ = radiusZ;
        this.amount = amount;
    }

    public String getName() { return this.name; }
    public Material getGuiItem() { return this.guiItem; }
    public double getRadiusX() { return this.radiusX; }
    public double getRadiusY() { return this.radiusY; }
    public double getRadiusZ() { return this.radiusZ; }
    public int getAmount() { return this.amount; }
    public Particle getParticle() { return this.particle; }
    public String getPermission() { return "cosmetics.trail." + name().toLowerCase(); }
}
