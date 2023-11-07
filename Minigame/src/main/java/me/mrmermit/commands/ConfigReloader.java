package me.mrmermit.commands;

import me.mrmermit.Minigame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConfigReloader implements CommandExecutor {
    private Minigame plugin;

    public ConfigReloader(Minigame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadconfig")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("beacon.at")) {
                    plugin.reloadConfig();
                    sender.sendMessage("Config reloaded successfully");
                    return true;
                }else {p.sendMessage("You don´t have the necessary permission");}
            }else {
                plugin.reloadConfig();
                System.out.println("Config reloaded successfully");
            }
        }
        return false;
    }
}
