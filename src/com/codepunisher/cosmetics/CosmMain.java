package com.codepunisher.cosmetics;

import com.codepunisher.mcaimcore.configuration.ConfigAPI;
import com.codepunisher.mcaimcore.configuration.DataFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.codepunisher.cosmetics.util.CosmDataManager;
import com.codepunisher.cosmetics.util.CosmManager;
import com.codepunisher.cosmetics.util.GUIHelpers;
import com.codepunisher.cosmetics.util.ListenerManager;

import java.util.Objects;

/**
 * Plugin is intentionally designed to be
 * reloadable via reload plugins such as
 * PlugMan. I do this with all of my plugins.
 * This makes patching stuff on a live server
 * possible (IF ABSOLUTELY NECESSARY)
 */
public class CosmMain extends JavaPlugin
{
    private static CosmMain cosmMain;
    public static CosmMain getInstance() { return cosmMain; }

    // Cosmetic class instance
    private CosmManager cosmManager = new CosmManager();
    public CosmManager getCosmManager() { return this.cosmManager; }

    // GUI helper class instance
    private GUIHelpers guiHelpers = new GUIHelpers();
    public GUIHelpers getGuiHelpers() { return this.guiHelpers; }

    // Data manager
    private CosmDataManager cosmDataManager = new CosmDataManager();
    public CosmDataManager getCosmDataManager() { return this.cosmDataManager; }

    @Override
    public void onEnable()
    {
        cosmMain = this;

        // Registering command
        Objects.requireNonNull(getCommand("cosmetics")).setExecutor(new CosmCMD());

        // Registering listener
        getServer().getPluginManager().registerEvents(new ListenerManager(), this);

        // Setting up cosm user objects
        initializeCosmUsers();

        // Loading config
        ConfigAPI.loadFile(this, "playerdata.yml");
    }

    public void onDisable() {
        DataFile dataFile = ConfigAPI.getDataFile(this, "playerdata.yml");

        // Storing cosmetics and disabling them from users
        for (CosmUser cosmUser : getCosmManager().cosmUsers.values()) {
            if (cosmUser.isWearingSuit())
                cosmUser.removeSuit();

            if (cosmUser.isActivePet())
                cosmUser.getEntity().remove();

            if (cosmUser.isActiveTrailParticle() || cosmUser.isActiveBackParticle() ||
                    cosmUser.isActivePet() || cosmUser.isWearingSuit() || cosmUser.isBaby() ||
                    cosmUser.isCharged() || cosmUser.isGlow())
            {
                dataFile.getData().set(cosmUser.getUuid().toString(), getCosmDataManager().cosmeticDataToString(cosmUser));
            }
            else
            {
                // Removing user if they're stored but don't need to be
                if (dataFile.getData().isSet(cosmUser.getUuid().toString()))
                    dataFile.getData().set(cosmUser.getUuid().toString(), null);
            }
        }

        dataFile.saveData();
    }

    /** Setting up cosm user objects for online users */
    private void initializeCosmUsers() {
        DataFile dataFile = ConfigAPI.getDataFile(this, "playerdata.yml");
        for (Player player : Bukkit.getOnlinePlayers()) {
            CosmUser cosmUser = getCosmDataManager().getCosmUserFromFile(dataFile, player.getUniqueId());

            if (cosmUser != null) {
                getCosmManager().cosmUsers.put(player.getUniqueId(), cosmUser);

                if (cosmUser.isActiveTrailParticle())
                    cosmUser.trailParticleTimer();

                if (cosmUser.isActiveBackParticle())
                    cosmUser.backParticleTimer();

                if (cosmUser.isWearingSuit())
                    cosmUser.equipSuit();

                if (cosmUser.isActivePet())
                    cosmUser.petTimer();
            } else {
                getCosmManager().addCosmUser(player.getUniqueId());
            }
        }
    }
}