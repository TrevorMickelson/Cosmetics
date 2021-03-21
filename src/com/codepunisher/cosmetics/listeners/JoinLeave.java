package com.codepunisher.cosmetics.listeners;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.user.CosmUser;
import com.codepunisher.cosmetics.util.CosmDataBase;
import com.codepunisher.cosmetics.util.CosmManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class JoinLeave implements Listener {
    private final CosmManager manager = CosmMain.getInstance().getCosmManager();

    /**
     * Loads data from data base
     * and stores user
     */
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPreJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();
        CosmUser cosmUser = new CosmUser(uuid);
        CosmDataBase.storeCosmeticInfo(cosmUser);
        manager.addCosmUser(cosmUser);
    }

    /**
     * Toggles cosmetics on
     */
    @EventHandler (priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        CosmUser cosmUser = manager.getCosmUser(uuid);

        if (cosmUser.getTrailUsage().isActive())
            cosmUser.getTrailUsage().trailParticleTimer();

        if (cosmUser.getSuitUsage().isWearingSuit())
            cosmUser.getSuitUsage().equipSuit();

        if (cosmUser.getBackParticleUsage().isActive())
            cosmUser.getBackParticleUsage().backParticleTimer();
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onLeave(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        CosmUser cosmUser = manager.getCosmUser(uuid);

        // Storing information to data base
        CosmDataBase.updateCosmUser(uuid, cosmUser);
    }
}
