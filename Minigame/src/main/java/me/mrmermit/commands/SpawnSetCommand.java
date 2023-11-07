package me.mrmermit.commands;

import me.mrmermit.Minigame;
import me.mrmermit.manager.GameManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnSetCommand implements CommandExecutor {

    private Minigame plugin;

    public SpawnSetCommand(Minigame plugin) {
        this.plugin = plugin;
    }
    private GameManager gameManager;

    public SpawnSetCommand(GameManager gameManager){
        this.gameManager = gameManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("beacon.at")) {
                Location l = player.getLocation();
                plugin.getConfig().set("spawn", l);
                plugin.getConfig().set("Spawn", l);
                plugin.saveConfig();
                player.sendMessage("Minigame´s join spawn have been setup");
            }else {player.sendMessage("You don´t have the necessary permission");}
        }else{
            System.out.println("You can´t send this command from console");
        }
        return true;
    }
}
