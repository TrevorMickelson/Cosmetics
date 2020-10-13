package com.codepunisher.cosmetics.util;


import com.codepunisher.mcaimcore.events.ArmorRemoveEvent;
import com.codepunisher.mcaimcore.events.PlayerStartMovingEvent;
import com.codepunisher.mcaimcore.events.PlayerStopMovingEvent;
import com.codepunisher.cosmetics.listeners.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener manager class
 *
 * The purpose of this class is to implement
 * the listener interface only once, and register
 * all events through this one class
 *
 * This is also very useful for having multiple
 * event types within 1 plugin, and only registering
 * them via one actual event
 */
public class ListenerManager implements Listener
{
    private final JoinLeave joinLeave = new JoinLeave();
    private final MenuListener menuListener = new MenuListener();
    private final SuitListener suitListener = new SuitListener();
    private final PlayerMove playerMove = new PlayerMove();
    private final PetListener petListener = new PetListener();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) { this.joinLeave.handleJoinEvent(event); }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) { this.joinLeave.handleQuitEvent(event);}

    @EventHandler
    public void onClick(InventoryClickEvent event) { this.menuListener.handleEvent(event); }

    @EventHandler
    public void onArmorRemove(ArmorRemoveEvent event) { this.suitListener.handleArmorRemove(event); }

    @EventHandler
    public void onItemDamage(PlayerItemDamageEvent event) { this.suitListener.handleItemDamage(event); }

    @EventHandler
    public void onStartMove(PlayerStartMovingEvent event) { this.playerMove.handleStartEvent(event); }

    @EventHandler
    public void onStopMove(PlayerStopMovingEvent event) { this.playerMove.handleStopEvent(event); }

    @EventHandler
    public void onPetHit(EntityTargetLivingEntityEvent event) { this.petListener.handleTargetEvent(event); }
}
