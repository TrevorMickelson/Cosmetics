package com.codepunisher.cosmetics.gui;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.user.CosmUser;
import com.mcaim.core.gui.Gui;
import com.mcaim.core.models.ItemBuild;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class MainGui extends Gui {
    private final Player player;
    private final CosmUser cosmUser;

    public MainGui(Player player) {
        super(player, "Cosmetics Main", 36);
        this.player = player;
        this.cosmUser = CosmMain.getInstance().getCosmManager().getCosmUser(player.getUniqueId());
        registerButtons();
        open();
    }

    private void registerButtons() {
        ItemStack backParticles = ItemBuild.of(Material.DRAGON_HEAD).name("&d&lBack Particles").lore("&7Click to view").getItem();
        ItemStack trailParticles = ItemBuild.of(Material.BLAZE_POWDER).name("&6&lTrail Particles").lore("&7Click to view").getItem();
        ItemStack suits = ItemBuild.of(Material.LEATHER_CHESTPLATE).flag(ItemFlag.HIDE_ATTRIBUTES).name("&b&lSuits").lore("&7Click to view").getItem();
        ItemStack removeAll = ItemBuild.of(Material.BARRIER).name("&c&lRemove").lore("&7Click to remove all cosmetics").getItem();

        // Back particles menu
        setItem(11, backParticles, () -> { new BackParticlesGui(player, cosmUser); });

        // Trail particles menu
        setItem(13, trailParticles, () -> { new TrailGui(player, cosmUser); });

        // Suits menu
        setItem(15, suits, () -> { new SuitGui(player, cosmUser); });

        // Removing all cosmetics
        setItem(31, removeAll, () -> {
            cosmUser.getBackParticleUsage().setActive(false);
            cosmUser.getTrailUsage().setActive(false);

            if (cosmUser.getSuitUsage().isWearingSuit()) {
                cosmUser.getSuitUsage().removeSuit();
                cosmUser.getSuitUsage().setWearingSuit(false);
            }

            player.sendMessage(ChatColor.RED + "All cosmetics have been disabled!");
        });
    }
}
