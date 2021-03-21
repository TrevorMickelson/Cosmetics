package com.codepunisher.cosmetics;

import com.codepunisher.cosmetics.commands.CosmCommand;
import com.codepunisher.cosmetics.listeners.JoinLeave;
import com.codepunisher.cosmetics.listeners.PlayerMove;
import com.codepunisher.cosmetics.listeners.SuitListener;
import com.codepunisher.cosmetics.util.CosmDataBase;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.codepunisher.cosmetics.util.CosmManager;

public class CosmMain extends JavaPlugin {
    private static CosmMain cosmMain;
    public static CosmMain getInstance() { return cosmMain; }

    private CosmManager cosmManager;
    public CosmManager getCosmManager() { return this.cosmManager; }

    @Override
    public void onEnable() {
        cosmMain = this;
        cosmManager = new CosmManager();
        registerCommands();
        registerListeners();
        CosmDataBase.createTable();
    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        new CosmCommand().registerCommand();
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new JoinLeave(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new SuitListener(), this);
    }
}