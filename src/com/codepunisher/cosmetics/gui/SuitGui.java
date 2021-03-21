package com.codepunisher.cosmetics.gui;

import com.codepunisher.cosmetics.cosmetics.Suit;
import com.codepunisher.cosmetics.user.CosmUser;
import com.codepunisher.cosmetics.user.SuitUsage;
import com.mcaim.core.gui.Gui;
import com.mcaim.core.models.ItemBuild;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SuitGui extends Gui {
    private final Player player;
    private final CosmUser cosmUser;

    public SuitGui(Player player, CosmUser cosmUser) {
        super(player, "Suits", 45);
        this.player = player;
        this.cosmUser = cosmUser;
        fillGui();
        open();
    }

    private void fillGui() {
        int index = 0;

        // Creating trail buttons
        for (Suit suit : Suit.values()) {
            String name = suit.getName();
            ItemStack customHead = suit.getCustomHead();

            ItemStack applied = ItemBuild.of(customHead).name(name).lore("&7Status: &6&lAPPLIED").glow().getItem();
            ItemStack hasPerm = ItemBuild.of(customHead).name(name).lore("&7Click to equip").getItem();
            ItemStack noPerm = ItemBuild.of(Material.RED_STAINED_GLASS_PANE).name(name).lore("&7Status: &c&lLOCKED").getItem();

            // Showing no permission item stack
            if (!player.hasPermission(suit.getPermission())) {
                setItem(index, noPerm);
                continue;
            }

            if (isSuitApplied(suit)) {
                setItem(index, applied);
            } else {
                // Applying trail if they don't have it on
                setItem(index, hasPerm, () -> {
                    cosmUser.getSuitUsage().setSuit(suit);
                    cosmUser.getSuitUsage().equipSuit();

                    // Re-opening gui
                    new SuitGui(player, cosmUser);
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
        setItem(40, remove, () -> {
            cosmUser.getSuitUsage().removeSuit();
            player.sendMessage(ChatColor.RED + "Suit removed!");
            new SuitGui(player, cosmUser);
        });

        // Going back to the main menu
        setItem(44, back, () -> { new MainGui(player); });
    }

    private boolean isSuitApplied(Suit suit) {
        SuitUsage suitUsage = cosmUser.getSuitUsage();
        return suitUsage.isWearingSuit() && suitUsage.getSuit() == suit;
    }
}
