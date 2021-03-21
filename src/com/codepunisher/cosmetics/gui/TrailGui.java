package com.codepunisher.cosmetics.gui;

import com.codepunisher.cosmetics.cosmetics.TrailParticle;
import com.codepunisher.cosmetics.user.CosmUser;
import com.codepunisher.cosmetics.user.TrailUsage;
import com.mcaim.core.gui.Gui;
import com.mcaim.core.models.ItemBuild;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class TrailGui extends Gui {
    private final Player player;
    private final CosmUser cosmUser;

    public TrailGui(Player player, CosmUser cosmUser) {
        super(player, "Trails", 27);
        this.player = player;
        this.cosmUser = cosmUser;
        fillGui();
        open();
    }

    private void fillGui() {
        int index = 0;

        // Creating trail buttons
        for (TrailParticle particle : TrailParticle.values()) {
            String pName = particle.getName();
            Material type = particle.getGuiItem();

            ItemStack applied = ItemBuild.of(type).name(pName).flag(ItemFlag.HIDE_ATTRIBUTES).lore("&7Status: &6&lAPPLIED").glow().getItem();
            ItemStack hasPerm = ItemBuild.of(type).name(pName).flag(ItemFlag.HIDE_ATTRIBUTES).lore("&7Click to equip").getItem();
            ItemStack noPerm = ItemBuild.of(Material.RED_STAINED_GLASS_PANE).name(pName).lore("&7Status: &c&lLOCKED").getItem();

            // Showing no permission item stack
            if (!player.hasPermission(particle.getPermission())) {
                setItem(index, noPerm);
                continue;
            }

            if (isTrailApplied(particle)) {
                setItem(index, applied);
            } else {
                // Applying trail if they don't have it on
                setItem(index, hasPerm, () -> {
                    cosmUser.getTrailUsage().setParticle(particle);

                    // Starting timer if a trail isn't currently active
                    if (!cosmUser.getTrailUsage().isActive())
                        cosmUser.getTrailUsage().trailParticleTimer();

                    // Re-opening gui
                    new TrailGui(player, cosmUser);
                });
            }

            index++;
        }

        registerExtraButtons();
    }

    private void registerExtraButtons() {
        ItemStack remove = ItemBuild.of(Material.BARRIER).name("&c&lRemove").lore("&7Click to remove trail particle").getItem();
        ItemStack back = ItemBuild.of(Material.OAK_FENCE_GATE).name("&f&lBack").lore("&7Click to go back").getItem();

        // Removing the trail particle
        setItem(22, remove, () -> {
            cosmUser.getTrailUsage().setActive(false);
            player.sendMessage(ChatColor.RED + "Trail particle deactivated!");
            new TrailGui(player, cosmUser);
        });

        // Going back to the main menu
        setItem(26, back, () -> { new MainGui(player); });
    }

    private boolean isTrailApplied(TrailParticle particle) {
        TrailUsage trail = cosmUser.getTrailUsage();
        return trail.isActive() && trail.getParticle() == particle;
    }
}
