package util;


import listeners.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class ListenerManager implements Listener
{
    private final JoinEvent joinEvent = new JoinEvent();
    private final MenuListener menuListener = new MenuListener();
    private final MoveEvent moveEvent = new MoveEvent();
    private final PetListener petListener = new PetListener();
    private final SuitListener suitListener = new SuitListener();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.joinEvent.handleEvent(event);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        this.menuListener.handleEvent(event);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) { this.moveEvent.handleEvent(event); }
}
