package com.codepunisher.cosmetics.gui;

import com.codepunisher.cosmetics.cosmetics.BackParticle;
import com.codepunisher.cosmetics.user.BackParticleUsage;
import com.codepunisher.cosmetics.user.CosmUser;
import com.mcaim.core.gui.Gui;
import com.mcaim.core.models.ItemBuild;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class BackParticlesGui extends Gui {
    private final Player player;
    private final CosmUser cosmUser;

    public BackParticlesGui(Player player, CosmUser cosmUser) {
        super(player, "BackParticles", 36);
        this.player = player;
        this.cosmUser = cosmUser;
        fillGui();
        open();
    }

    private void fillGui() {
        int index = 0;

        // Creating trail buttons
        for (BackParticle particle : BackParticle.values()) {
            String pName = particle.getName();
            Material type = particle.getGuiItem();

            ItemStack applied = ItemBuild.of(type).flag(ItemFlag.HIDE_ATTRIBUTES).name(pName).lore("&7Status: &6&lAPPLIED").glow().getItem();
            ItemStack hasPerm = ItemBuild.of(type).flag(ItemFlag.HIDE_ATTRIBUTES).name(pName).lore("&7Click to equip").getItem();
            ItemStack noPerm = ItemBuild.of(Material.RED_STAINED_GLASS_PANE).name(pName).lore("&7Status: &c&lLOCKED").getItem();

            // Showing no permission item stack
            if (!player.hasPermission(particle.getPermission())) {
                setItem(index, noPerm);
                continue;
            }

            if (isParticleApplied(particle)) {
                setItem(index, applied);
            } else {
                // Applying trail if they don't have it on
                setItem(index, hasPerm, () -> {
                    cosmUser.getBackParticleUsage().setParticle(particle);

                    // Starting timer if it's not already started
                    if (!cosmUser.getBackParticleUsage().isActive())
                        cosmUser.getBackParticleUsage().backParticleTimer();

                    new BackParticlesGui(player, cosmUser);
                });
            }

            index++;
        }

        registerExtraButtons();
    }

    private void registerExtraButtons() {
        ItemStack remove = ItemBuild.of(Material.BARRIER).name("&c&lRemove").lore("&7Click to remove back particle").getItem();
        ItemStack back = ItemBuild.of(Material.OAK_FENCE_GATE).name("&f&lBack").lore("&7Click to go back").getItem();

        // Removing the back particle
        setItem(31, remove, () -> {
            cosmUser.getBackParticleUsage().setActive(false);
            player.sendMessage(ChatColor.RED + "Back particle deactivated!");
            new BackParticlesGui(player, cosmUser);
        });

        // Going back to the main menu
        setItem(35, back, () -> { new MainGui(player); });
    }

    private boolean isParticleApplied(BackParticle particle) {
        BackParticleUsage back = cosmUser.getBackParticleUsage();
        return back.isActive() && back.getParticle() == particle;
    }
}
