package listeners;

import main.CosmMain;
import models.CosmUser;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent
{
    // Instance of main class
    private final CosmMain plugin = CosmMain.getInstance();

    /**
     * The purpose of this event is to start
     * cosmetic timers back up if need be
     */
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

        // Toggling cosmetics if need to
        if (cosmUser != null)
        {
            if (cosmUser.isActiveTrailParticle())
                cosmUser.trailParticleTimer();

            if (cosmUser.isActiveBackParticle())
                cosmUser.backParticleTimer();
        }
        else
        {
            // Storing new cosmetic user object
            plugin.getCosmManager().addCosmUser(player.getUniqueId());
        }
    }

    /** Handles player join event for listener manager class */
    public void handleEvent(PlayerJoinEvent event) { onJoin(event); }
}
