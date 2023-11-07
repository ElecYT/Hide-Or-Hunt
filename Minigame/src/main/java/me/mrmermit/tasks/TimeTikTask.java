package me.mrmermit.tasks;

import me.mrmermit.Minigame;
import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeTikTask extends BukkitRunnable {
    private GameManager gameManager;

    private int timeStart = 0;
    public TimeTikTask(GameManager gameManager, int time) {
        this.gameManager = gameManager;
        this.timeStart = time+1;
    }
    @Override
    public void run() {
        if(!(StateManager.status == 2)){
            cancel();
        }else {
            timeStart--;
            if (Bukkit.getOnlinePlayers().size() < 1) {//plugin.getConfig().getInt("PlayersForGame")
                cancel();
            } else if (timeStart != 0) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "The game is starting in " + ChatColor.BLUE + timeStart);
            } else if (timeStart <= 0) {
                Bukkit.broadcastMessage(ChatColor.DARK_BLUE + "The game has begun!");
                cancel();
                gameManager.setGameState(3);

            }

        }
    }
}
