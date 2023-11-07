package me.mrmermit.tasks;

import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinWait extends BukkitRunnable {
    private GameManager gameManager;
    private StateManager stateManager;
    int max_player = 0;

    public JoinWait(GameManager gameManager, Integer player) {
        this.gameManager = gameManager;
        this.max_player = player;
    }
    @Override
    public void run() {
        if(Bukkit.getOnlinePlayers().size() >= max_player){
            gameManager.setGameState(2);
            cancel();
        }
    }
}
