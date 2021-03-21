package com.codepunisher.cosmetics.commands;

import com.codepunisher.cosmetics.gui.MainGui;
import com.mcaim.core.models.Permission;
import com.mcaim.core.util.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CosmCommand extends Command {
    public CosmCommand() {
        super(false, Permission.NONE, "cosmetics", "cosmetic", "cosm");
        printError();
    }

    /**
     * This method opens the cosmetic gui
     */
    @Override
    protected void onExecute(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;
        new MainGui(player);
    }
}
