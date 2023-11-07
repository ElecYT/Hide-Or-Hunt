package me.mrmermit.event;

import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlLobbyMoveListener implements Listener {

    private GameManager gameManager;
    private StateManager stateManager;

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent e) {
        if (stateManager.status == 3) {
            e.setCancelled(true);
            System.out.println("Someone is trying to move when he isnt supposed to");
        }
        if(!(StateManager.status==4 &&(StateManager.game==1 || StateManager.game==2))){
            e.getPlayer().setFoodLevel(20);
        }
    }
}