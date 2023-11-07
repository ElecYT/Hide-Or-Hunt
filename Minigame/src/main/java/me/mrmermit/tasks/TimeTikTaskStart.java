package me.mrmermit.tasks;

import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeTikTaskStart extends BukkitRunnable {
    private GameManager gameManager;



    public TimeTikTaskStart(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public int timeStart= 9;

    @Override
    public void run() {
        if(!(StateManager.status == 3)){
            cancel();
        }else{
            timeStart--;
            if(timeStart < 5 && timeStart > 0){
                String message = ChatColor.GREEN + "Starting in: " + ChatColor.RED + (timeStart);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendTitle(message,"");
                }

            }else if(timeStart <= 0) {
                cancel();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.sendTitle(ChatColor.BLUE + "Go!","");
                }
                gameManager.setGameState(4);
            }
        }
    }
}
