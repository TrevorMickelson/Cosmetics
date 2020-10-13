package com.codepunisher.cosmetics.listeners;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.mcaimcore.events.PlayerStartMovingEvent;
import com.codepunisher.mcaimcore.events.PlayerStopMovingEvent;
import org.bukkit.entity.Player;

public class PlayerMove
{
    // Instance of main class
    private final CosmMain plugin = CosmMain.getInstance();

    private void onStartMove(PlayerStartMovingEvent event) {
        Player player = event.getPlayer();
        plugin.getCosmManager().getCosmUser(player.getUniqueId()).setMoving(true);
    }

    private void onStopMove(PlayerStopMovingEvent event) {
        Player player = event.getPlayer();
        plugin.getCosmManager().getCosmUser(player.getUniqueId()).setMoving(false);
    }

    public void handleStartEvent(PlayerStartMovingEvent event) { onStartMove(event); }
    public void handleStopEvent(PlayerStopMovingEvent event) { onStopMove(event); }
}
