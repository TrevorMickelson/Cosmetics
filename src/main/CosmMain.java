package main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import util.CosmManager;
import util.GUIHelpers;
import util.ListenerManager;

import java.util.Objects;

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
    }

    public void onDisable() {}

    /** Setting up cosm user objects for online users */
    private void initializeCosmUsers() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            getCosmManager().addCosmUser(player.getUniqueId());
        }
    }
}
