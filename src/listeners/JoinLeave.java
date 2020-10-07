package listeners;

import main.CosmMain;
import models.CosmUser;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeave
{
    // Instance of main class
    private final CosmMain plugin = CosmMain.getInstance();

    /**
     * The purpose of this event is to re-apply
     * cosmetics (if need to)
     *
     * Also making sure the user has a cosmetic
     * user object stored
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

            if (cosmUser.isWearingSuit())
                cosmUser.equipSuit();

            if (cosmUser.isActivePet())
                cosmUser.petTimer();
        }
        else
        {
            // Storing new cosmetic user object
            plugin.getCosmManager().addCosmUser(player.getUniqueId());
        }
    }

    /**
     * Removes cosmetics
     * @param event quit event
     */
    private void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        CosmUser cosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

        if (cosmUser.isWearingSuit())
            cosmUser.removeSuit();

        if (cosmUser.isActivePet())
            cosmUser.getEntity().remove();
    }

    // --- HANDLING EVENTS --- //
    public void handleJoinEvent(PlayerJoinEvent event) { onJoin(event); }
    public void handleQuitEvent(PlayerQuitEvent event) { onQuit(event); }
}
