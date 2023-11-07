package me.mrmermit.tasks;

import me.mrmermit.manager.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Glowing_stop extends BukkitRunnable {
    private Rocket rocket;

    private int timeStart = 10;
    public Glowing_stop(Rocket rocket) {
        this.rocket = rocket;
    }
    @Override
    public void run() {
        if(!(StateManager.status == 4)){
            cancel();
        }else {
            timeStart--;
            if (Bukkit.getOnlinePlayers().size() < 1) {//plugin.getConfig().getInt("PlayersForGame")
                cancel();
            } else if (timeStart <= 0) {
                for(Player p : Bukkit.getOnlinePlayers()){
                    p.removePotionEffect(PotionEffectType.GLOWING);
                    p.setGlowing(false);
                    p.removePotionEffect(PotionEffectType.GLOWING);
                }
            }

        }
    }
}
