package models;

import cosmetics.*;
import general.HelpersAPI;
import main.CosmMain;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.PathEntity;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * This class stores users that have cosmetics
 * This is an easy an organized way to get
 * cosmetic data from the user who applies it
 */
public class CosmUser
{
    private CosmMain plugin = CosmMain.getInstance();                                   // Plugin instance
    private UUID uuid;                                                                  // Uuid of cosmetic user
    private boolean moving;                                                             // If player is moving

    // Trail particle instance variables
    private TrailParticles trailParticle;                                               // Trail particle of user
    private boolean activeTrailParticle = false;                                        // If trail is active or not

    // Back particle instance variables
    private BackParticles backParticle;                                                 // Back particle of user
    private boolean activeBackParticle;                                                 // If back particle is active or not

    // Pet instance variables
    private LivingEntity livingEntity;                                                  // The literal entity of pet
    private Pets pet;                                                                   // Pet for user
    private String name;                                                                // Pet name
    private boolean baby;                                                               // If pet is baby
    private boolean charged;                                                             // If pet is charged (creeper)
    private boolean isActivePet;                                                        // If pet is active

    // Suit instance variables
    private Suits suit;                                                                 // Player suit object
    private boolean wearingSuit;                                                        // If player is wearing suit or not

    /** Constructor for creating object */
    public CosmUser(UUID uuid) {
        this.uuid = uuid;
        this.moving = false;
        this.activeBackParticle = false;
        this.activeBackParticle = false;
        this.isActivePet = false;
        this.wearingSuit = false;
        this.name = ChatColor.DARK_AQUA + HelpersAPI.getUUIDName(uuid) + "s pet";
    }

    // UUID of cosmetic user
    public UUID getUuid() { return this.uuid; }

    // If player is moving or not
    private boolean isMoving() { return this.moving; }
    public void setMoving(boolean moving) { this.moving = moving; }

    /** ---------- TRAIL PARTICLES SECTION ---------- */
    public TrailParticles getTrailParticle() { return this.trailParticle; }
    public void setTrailParticle(TrailParticles trailParticle) { this.trailParticle = trailParticle; }

    public boolean isActiveTrailParticle() { return this.activeTrailParticle; }
    public void setActiveTrailParticle(boolean activeTrailParticle) { this.activeTrailParticle = activeTrailParticle; }

    public void trailParticleTimer() {
        Player player = Bukkit.getPlayer(getUuid());

        if (player != null)
        {
            new BukkitRunnable() {
                @Override
                public void run()
                {
                    if (!player.isOnline() || player.isDead() || !isActiveTrailParticle())
                        cancel();

                    Location loc = player.getLocation();
                    player.getWorld().spawnParticle(getTrailParticle().getParticle(), loc, getTrailParticle().getAmount(), getTrailParticle().getRadiusX(), getTrailParticle().getRadiusY(), getTrailParticle().getRadiusZ(), 0);
                }

            }.runTaskTimerAsynchronously(plugin, 0L, 2L);
        }
    }

    /** ---------- BACK PARTICLES SECTION ---------- */
    public BackParticles getBackParticle() { return this.backParticle; }
    public void setBackParticle(BackParticles backParticle) { this.backParticle = backParticle; }

    public boolean isActiveBackParticle() { return this.activeBackParticle; }
    public void setActiveBackParticle(boolean activeBackParticle) { this.activeBackParticle = activeBackParticle; }

    public void backParticleTimer() {
        Player player = Bukkit.getPlayer(getUuid());

        if (player != null)
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    if (!player.isOnline() || player.isDead() || !isActiveBackParticle()) {
                        cancel();
                    }

                    if (!isMoving())
                    {
                        double addX = getBackParticle().getAddX();
                        double addY = getBackParticle().getAddY();
                        double addZ = getBackParticle().getAddZ();
                        int amount = getBackParticle().getAmount();
                        float size = getBackParticle().getSize();
                        double radiusX = getBackParticle().getRadiusX();
                        double radiusY = getBackParticle().getRadiusY();
                        double radiusZ = getBackParticle().getRadiusZ();
                        double particleSpace = getBackParticle().getParticleSpace();
                        String[][] bluePrint = getBackParticle().getBlueprint();
                        String[] bluePrintChars = getBackParticle().getBlueprintChars();
                        Particle[] particles = getBackParticle().getParticles();
                        Color[] colors = getBackParticle().getColor();

                        Location loc = player.getLocation();
                        double tempX = loc.getX() - particleSpace * bluePrint.length - particleSpace + addX;
                        double x = tempX;
                        double y = loc.clone().getY() + addY;
                        double z = loc.getZ() + addZ;
                        double angle = -((loc.getYaw() + 180.0F) / 59.0F);
                        angle += ((loc.getYaw() < -180.0F) ? 3.25D : 2.985D);

                        for (String[] aShape : bluePrint)
                        {
                            for (String anAShape : Arrays.toString(aShape).split(", "))
                            {
                                // Check if character from blueprint matches char in blueprint char array
                                if(Arrays.asList(bluePrintChars).contains(anAShape))
                                {
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
                                    for (int i = 0; i < particles.length; i++)
                                    {
                                        if(particles[i].equals(Particle.REDSTONE))
                                        {
                                            if(anAShape.equalsIgnoreCase(bluePrintChars[i]))
                                            {
                                                Particle.DustOptions dustOptions = new Particle.DustOptions(colors[i], size);
                                                (Objects.requireNonNull(loc.getWorld())).spawnParticle(particles[i], loc.getX(), loc.getY(), loc.getZ(), amount, radiusX, radiusY, radiusZ, dustOptions);
                                            }
                                        }
                                        else
                                        {
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

            }.runTaskTimerAsynchronously(plugin, 0L, 2L);
        }
    }

    /** ---------- PETS SECTION ---------- */
    public LivingEntity getEntity() { return this.livingEntity; }
    public void setEntity(LivingEntity livingEntity) { this.livingEntity = livingEntity; }

    public Pets getPet() { return this.pet; }
    public void setPet(Pets pet) { this.pet = pet; }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name;}

    public boolean isBaby() { return this.baby; }
    public void setBaby(boolean baby) { this.baby = baby; }

    public boolean isCharged() { return this.charged; }
    public void setCharged(boolean charged) { this.charged = charged; }

    public boolean isActivePet() { return this.isActivePet; }
    public void setActivePet(boolean isActivePet) { this.isActivePet = isActivePet; }

    public void spawnPet(Player player) {
        // Creating/spawning entity before starting timer
        LivingEntity pet = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), getPet().getEntityType());
        setEntity(pet);
        LivingEntity livingEntity = getEntity();
        livingEntity.setInvulnerable(true);
        livingEntity.setCanPickupItems(false);
        livingEntity.setCustomNameVisible(true);
        livingEntity.setCustomName(ChatColor.DARK_AQUA + player.getName() + "s pet");
    }

    public void petTimer() {
        Player player = Bukkit.getPlayer(getUuid());

        if (player != null)
        {
            // Creating/spawning entity before starting timer
            LivingEntity pet = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), getPet().getEntityType());
            setEntity(pet);
            spawnPet(player);
            new BukkitRunnable() {
                @Override
                public void run()
                {
                    if (!player.isOnline() || player.isDead() || !isActivePet()
                        || getEntity() == null || getEntity().isDead() || !getEntity().isValid()) {
                        cancel();
                        getEntity().remove();
                        setActivePet(false);
                        return;
                    }

                    if (isMoving()) {
                        Location loc = player.getLocation().add(1.5, 0, -1.5);
                        Object petObject = ((CraftEntity) getEntity()).getHandle();
                        PathEntity path = ((EntityInsentient) petObject).getNavigation().a(loc.getX(), loc.getY(), loc.getZ(), 0);

                        if(path != null)
                            ((EntityInsentient)petObject).getNavigation().a(path, getPet().getSpeed());

                        int distance = (int) player.getLocation().distance(getEntity().getLocation());
                        if(distance > 10  && player.isOnGround())
                            getEntity().teleport(loc);
                    }

                    // Spawning player particles on pet (if they have any on)
                    if (isActiveTrailParticle()) {
                        Location loc = getEntity().getLocation();
                        getEntity().getWorld().spawnParticle(getTrailParticle().getParticle(), loc, getTrailParticle().getAmount(), getTrailParticle().getRadiusX(), getTrailParticle().getRadiusY(), getTrailParticle().getRadiusZ(), 0);
                    }
                }

            }.runTaskTimer(plugin, 0L, 1L);
        }
    }

    /** ---------- SUIT SECTION ---------- */
    public Suits getSuit() { return this.suit; }
    public void setSuit(Suits suit) { this.suit = suit; }
    public boolean isWearingSuit() { return this.wearingSuit; }
    public void setWearingSuit(boolean isWearingSuit) { this.wearingSuit = isWearingSuit; }
    public void equipSuit() {
        Player player = Bukkit.getPlayer(getUuid());

        if (player != null)
        {
            if((player.getInventory().getHelmet() == null &&
                player.getInventory().getChestplate() == null &&
                player.getInventory().getLeggings() == null   &&
                player.getInventory().getBoots() == null) || isWearingSuit())
            {
                setWearingSuit(true);
                player.getInventory().setArmorContents(getSuit().getArmour());
            } else {
                player.sendMessage(ChatColor.RED + "You must strip naked to wear a suit!");
            }
        }
    }
    public void removeSuit() {
        Player player = Bukkit.getPlayer(getUuid());

        if (player != null)
        {
            PlayerInventory inv = player.getInventory();
            inv.setArmorContents(new ItemStack[inv.getArmorContents().length]);
        }
    }

    /** Methods that do shit */
    private static Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }
    private static Vector getBackVector(Location loc) {
        float newZ = (float)(loc.getZ() + 1.0D * Math.sin(Math.toRadians((loc.getYaw() + 90.0F))));
        float newX = (float)(loc.getX() + 1.0D * Math.cos(Math.toRadians((loc.getYaw() + 90.0F))));
        return new Vector(newX - loc.getX(), 0.0D, newZ - loc.getZ());
    }
}
