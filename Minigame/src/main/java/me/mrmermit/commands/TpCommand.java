package me.mrmermit.commands;

import me.mrmermit.Minigame;
import me.mrmermit.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    private Minigame plugin;

    public TpCommand(Minigame plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("beacon.at")) {
                if (args.length < 1) {
                    player.sendMessage("Usage: /tpworld <world name>");
                    return true;
                } else {
                    String message = String.join(" ", args);
                    World world = Bukkit.getWorld(message);
                    Location l = player.getLocation();
                    if (world != null) {
                        l.setWorld(world);
                        player.teleport(l);
                    } else {
                        player.sendMessage("This world doesnt exist!");
                    }

                }
            }

        }else{
            System.out.println("You can´t send this command from console");
        }
        return true;
    }
}
