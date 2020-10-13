package com.codepunisher.cosmetics.listeners;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.CosmUser;
import com.codepunisher.mcaimcore.configuration.ConfigAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
     *
     * Also gets cosmetic user object from file
     * and applies it (if need tO)
     *
     * I'm aware that this join event is sort of
     * a must convoluted fuck fest but honestly
     * I'm not sure I care anymore... this plugin
     * has taken SO MUCH FUCKING TIME
     *
     * Running async because it's a bit intensive
     */
    private void onJoin(PlayerJoinEvent event) {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                Player player = event.getPlayer();

                CosmUser cosmUser = plugin.getCosmDataManager().getCosmUserFromFile(ConfigAPI.getDataFile(plugin, "playerdata.yml"), player.getUniqueId());

                if (cosmUser != null && plugin.getCosmManager().getCosmUser(player.getUniqueId()) == null)
                {
                    plugin.getCosmManager().cosmUsers.put(player.getUniqueId(), cosmUser);

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
                    CosmUser onlineCosmUser = plugin.getCosmManager().getCosmUser(player.getUniqueId());

                    if (onlineCosmUser != null) {
                        if (onlineCosmUser.isActiveTrailParticle())
                            onlineCosmUser.trailParticleTimer();

                        if (onlineCosmUser.isActiveBackParticle())
                            onlineCosmUser.backParticleTimer();

                        if (onlineCosmUser.isWearingSuit())
                            onlineCosmUser.equipSuit();

                        if (onlineCosmUser.isActivePet())
                            onlineCosmUser.petTimer();
                    } else {
                        plugin.getCosmManager().addCosmUser(player.getUniqueId());
                    }
                }
            }

        }.runTaskAsynchronously(plugin);
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
