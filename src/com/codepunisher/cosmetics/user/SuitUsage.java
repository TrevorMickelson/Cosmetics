package com.codepunisher.cosmetics.user;

import com.codepunisher.cosmetics.cosmetics.Suit;
import com.mcaim.core.util.OfflinePlayerWrapper;
import net.minecraft.server.v1_16_R3.EntityHuman;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.UUID;

public class SuitUsage {
    private final UUID uuid;
    private Suit suit;
    private boolean wearingSuit;

    public SuitUsage(UUID uuid) {
        this.uuid = uuid;
    }

    public Suit getSuit() { return suit; }
    public void setSuit(Suit suit) { this.suit = suit; }

    public boolean isWearingSuit() { return wearingSuit; }
    public void setWearingSuit(boolean wearingSuit) { this.wearingSuit = wearingSuit; }

    public void equipSuit() {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null)
            return;

        PlayerInventory inv = player.getInventory();

        if (isNaked(inv)) {
            setWearingSuit(true);
            player.getInventory().setArmorContents(getSuit().getArmour());
        } else {
            player.sendMessage(ChatColor.RED + "You must strip naked to wear a suit!");
        }
    }

    /**
     * Works with online and offline players
     */
    public void removeSuit() {
        if (!wearingSuit)
            return;

        Player player = Bukkit.getPlayer(uuid);
        wearingSuit = false;

        // Online player
        if (player != null) {
            PlayerInventory inv = player.getInventory();
            inv.setArmorContents(new ItemStack[inv.getArmorContents().length]);
        } else {
            // Offline player
            OfflinePlayerWrapper wrapper = new OfflinePlayerWrapper(uuid);
            EntityHuman human = wrapper.getEntityHuman();
            human.inventory.armor.clear();
            wrapper.saveData();
        }
    }

    /**
     * If the player isn't wearing anything
     */
    boolean isNaked(PlayerInventory inv) {
        return wearingSuit || inv.getHelmet() == null && inv.getChestplate() == null &&
               inv.getLeggings() == null && inv.getBoots() == null;
    }
}
