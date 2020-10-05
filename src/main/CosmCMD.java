package main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class literally just opens the main menu
 */
public class CosmCMD implements CommandExecutor
{
    // Instance of main class
    private final CosmMain plugin = CosmMain.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            player.openInventory(plugin.getGuiHelpers().mainMenu());
        }
        return false;
    }
}
