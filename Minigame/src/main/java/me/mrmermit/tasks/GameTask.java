package me.mrmermit.tasks;

import me.mrmermit.Minigame;
import me.mrmermit.manager.BeaconManager;
import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTask extends BukkitRunnable {
    private GameManager gameManager;
    private Rocket rocket;
    private Minigame plugin;
    private static int timeStartMinute = 0;
    public GameTask(int time, GameManager gameManager, Minigame plugin) {
        this.gameManager = gameManager;
        this.timeStartMinute = time;
        this.plugin = plugin;
    }
    private BeaconManager beaconManager;
    private int counter = 0;
    private int timeStartSeconds = 0;
    @Override
    public void run() {
        counter++;
        if (counter == 1){
            Bukkit.getServer().getWorlds().forEach(world -> {
                world.setGameRule(GameRule.FALL_DAMAGE, false);
            });
            StateManager.game = 1;
            timeStartSeconds = timeStartMinute*60 + 10;
        }
        if(counter == 11){
            Bukkit.getServer().getWorlds().forEach(world -> {
                world.setGameRule(GameRule.FALL_DAMAGE, true);
            });
        }
        if(!(StateManager.status == 4)){
            cancel();
        }else {
            timeStartSeconds--;
            gameManager.scoreboard(timeStartSeconds);
            if (timeStartSeconds == timeStartMinute*60) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Now you have "+timeStartMinute+" minutes to prepare yourself and place the beacon");
            }  else if (timeStartSeconds == 3*60) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Only 3 minutes remains. After that the pvp faze will start!");
            }  else if (timeStartSeconds == 60) {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Only 1 minute remains. After that the pvp faze will start!");
            }else if (timeStartSeconds == 0) {
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Prep faze is over now! PvP is now on!");
                gameManager.setGameStateInGame(2);
                StateManager.game = 2;
                BeaconManager.place_beacon();
                cancel();
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.setInvisible(false);
                }
                int rocket = plugin.getConfig().getInt("RocketTimer");
                this.rocket = new Rocket(gameManager, rocket, plugin);
                this.rocket.runTaskTimer(plugin,0,20);
            }

        }
    }
}
