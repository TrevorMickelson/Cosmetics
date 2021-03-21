package com.codepunisher.cosmetics.listeners;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.util.CosmManager;
import com.mcaim.core.events.PlayerStartMovingEvent;
import com.mcaim.core.events.PlayerStopMovingEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class PlayerMove implements Listener {
    private final CosmManager manager = CosmMain.getInstance().getCosmManager();

    // Sets moving to true
    @EventHandler
    public void onStartMove(PlayerStartMovingEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        manager.getCosmUser(uuid).getBackParticleUsage().setMoving(true);
    }

    // Sets move to false
    @EventHandler
    private void onStopMove(PlayerStopMovingEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        manager.getCosmUser(uuid).getBackParticleUsage().setMoving(false);
    }
}
