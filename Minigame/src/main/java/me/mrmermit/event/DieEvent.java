package me.mrmermit.event;

import me.mrmermit.Minigame;
import me.mrmermit.manager.StateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class DieEvent implements Listener {

    private StateManager stateManager;
    private final Minigame plugin;

    public DieEvent(Minigame plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void playerDieEvent(PlayerMoveEvent e) {
        if (stateManager.status == 1 || stateManager.status == 2) {
            Player p = e.getPlayer();
            if(p.getLocation().getY() < -16){
                if(!(plugin.getConfig().getLocation("spawn") == null))
                    p.teleport(plugin.getConfig().getLocation("Spawn"));
            }
        }
    }
}
