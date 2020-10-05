package listeners;

import main.CosmMain;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveEvent
{
    // Instance of main class
    private final CosmMain plugin = CosmMain.getInstance();

    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        plugin.getCosmManager().getCosmUser(player.getUniqueId()).setMoving(true);
    }

    public void handleEvent(PlayerMoveEvent event) { onMove(event); }
}
