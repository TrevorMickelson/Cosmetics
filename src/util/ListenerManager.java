package util;


import events.ArmorRemoveEvent;
import events.PlayerStartMovingEvent;
import events.PlayerStopMovingEvent;
import listeners.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ListenerManager implements Listener
{
    private final JoinLeave joinLeave = new JoinLeave();
    private final MenuListener menuListener = new MenuListener();
    private final PlayerMove playerMove = new PlayerMove();
    private final PetListener petListener = new PetListener();
    private final SuitListener suitListener = new SuitListener();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.joinLeave.handleJoinEvent(event);
    }

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
    public void test(EntityTargetLivingEntityEvent event) { event.setCancelled(true); }
}
