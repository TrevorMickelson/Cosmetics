package com.codepunisher.cosmetics.user;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.cosmetics.TrailParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class TrailUsage {
    private final UUID uuid;
    private TrailParticle particle;
    private boolean active;

    public TrailUsage(UUID uuid) {
        this.uuid = uuid;
    }

    public TrailParticle getParticle() { return particle; }
    public void setParticle(TrailParticle particle) { this.particle = particle; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    /**
     * Spawns the trail particle at the
     * players feet on an async timer
     */
    public void trailParticleTimer() {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null)
            return;
        
        setActive(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline() || player.isDead() || !active)
                    cancel();

                Location loc = player.getLocation();
                player.getWorld().spawnParticle(particle.getParticle(), loc, particle.getAmount(), particle.getRadiusX(), particle.getRadiusY(), particle.getRadiusZ(), 0);
            }

        }.runTaskTimerAsynchronously(CosmMain.getInstance(), 0L, 2L);
    }
}
