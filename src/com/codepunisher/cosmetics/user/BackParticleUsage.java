package com.codepunisher.cosmetics.user;

import com.codepunisher.cosmetics.CosmMain;
import com.codepunisher.cosmetics.cosmetics.BackParticle;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public class BackParticleUsage {
    private final UUID uuid;
    private BackParticle particle;
    private boolean active;
    private boolean moving;

    public BackParticleUsage(UUID uuid) {
        this.uuid = uuid;
    }

    public BackParticle getParticle() { return particle; }
    public void setParticle(BackParticle particle) { this.particle = particle; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public boolean isMoving() { return moving; }
    public void setMoving(boolean moving) { this.moving = moving; }

    /**
     * This method spawns the back particle
     * on the player async when the player
     * isn't moving
     */
    public void backParticleTimer() {
        Player player = Bukkit.getPlayer(uuid);

        if (player == null)
            return;

        setActive(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline() || player.isDead() || !active) {
                    cancel();
                }

                // Only spawning particle if the player isn't moving
                if (!isMoving()) {
                    double addX = particle.getAddX();
                    double addY = particle.getAddY();
                    double addZ = particle.getAddZ();
                    int amount = particle.getAmount();
                    float size = particle.getSize();
                    double radiusX = particle.getRadiusX();
                    double radiusY = particle.getRadiusY();
                    double radiusZ = particle.getRadiusZ();
                    double particleSpace = particle.getParticleSpace();
                    String[][] bluePrint = particle.getBlueprint();
                    String[] bluePrintChars = particle.getBlueprintChars();
                    Particle[] particles = particle.getParticles();
                    Color[] colors = particle.getColor();

                    Location loc = player.getLocation();
                    double tempX = loc.getX() - particleSpace * bluePrint.length - particleSpace + addX;
                    double x = tempX;
                    double y = loc.clone().getY() + addY;
                    double z = loc.getZ() + addZ;
                    double angle = -((loc.getYaw() + 180.0F) / 59.0F);
                    angle += ((loc.getYaw() < -180.0F) ? 3.25D : 2.985D);

                    for (String[] aShape : bluePrint) {
                        for (String anAShape : Arrays.toString(aShape).split(", ")) {
                            // Check if character from blueprint matches char in blueprint char array
                            if(Arrays.asList(bluePrintChars).contains(anAShape)) {
                                // Set target location for particles
                                Location target = loc.clone();
                                target.setX(x);
                                target.setY(y);
                                target.setZ(z);

                                // Get back vector
                                Vector v = target.toVector().subtract(loc.toVector());
                                Vector v2 = getBackVector(loc);
                                v = rotateAroundAxisY(v, angle);
                                v2.setY(0).multiply(-0.5D);
                                loc.add(v);
                                loc.add(v2);

                                // Spawn particles
                                for (int i = 0; i < particles.length; i++) {
                                    if(particles[i].equals(Particle.REDSTONE)) {
                                        if(anAShape.equalsIgnoreCase(bluePrintChars[i])) {
                                            Particle.DustOptions dustOptions = new Particle.DustOptions(colors[i], size);
                                            (Objects.requireNonNull(loc.getWorld())).spawnParticle(particles[i], loc.getX(), loc.getY(), loc.getZ(), amount, radiusX, radiusY, radiusZ, dustOptions);
                                        }
                                    } else {
                                        if(anAShape.equalsIgnoreCase("X") || anAShape.equalsIgnoreCase("i"))
                                            (Objects.requireNonNull(loc.getWorld())).spawnParticle(particles[i], loc.getX(), loc.getY(), loc.getZ(), amount, radiusX, radiusY, radiusZ);
                                    }
                                }

                                loc.subtract(v2); loc.subtract(v);
                            }

                            x += particleSpace;

                        }

                        y -= particleSpace;
                        x = tempX;
                    }
                }
            }

        }.runTaskTimerAsynchronously(CosmMain.getInstance(), 0L, 2L);
    }

    private static Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    private static Vector getBackVector(Location loc) {
        float newZ = (float) (loc.getZ() + Math.sin(Math.toRadians((loc.getYaw() + 90.0F))));
        float newX = (float) (loc.getX() + Math.cos(Math.toRadians((loc.getYaw() + 90.0F))));
        return new Vector(newX - loc.getX(), 0.0D, newZ - loc.getZ());
    }
}
