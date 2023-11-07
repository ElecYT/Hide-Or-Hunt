package me.mrmermit.tasks;

import me.mrmermit.manager.ItemWaiterManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemWait extends BukkitRunnable {
    private ItemWaiterManager itemWaiterManager;

    private int time = 60;
    private Player p = null;
    public ItemWait(ItemWaiterManager itemWaiterManager, Player player) {
        this.itemWaiterManager = itemWaiterManager;
        this.p = player;
    }
    @Override
    public void run() {
        if(!(StateManager.status == 4)){
            cancel();
        }else {
            time--;
            if(time<=0){
                ItemWaiterManager.surfaceList.remove(p);
                cancel();
            }
        }
    }
}
