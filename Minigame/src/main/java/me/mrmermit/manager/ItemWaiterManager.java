package me.mrmermit.manager;

import me.mrmermit.Minigame;
import me.mrmermit.event.utils.ItemStacks;
import me.mrmermit.tasks.ItemWait;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemWaiterManager {
    private GameManager gameManager;
    private Minigame plugin;
    private ItemWait itemWait;
    public ItemWaiterManager(GameManager gameManager, Minigame plugin){
        this.gameManager = gameManager;
        this.plugin = plugin;
    }
    public static List<Player> surfaceList = new ArrayList<>();

    public boolean tpSurface(Player p ){
        if(surfaceList.contains(p)){
            return false;
        }else {
            surfaceList.add(p);
            this.itemWait = new ItemWait(this,p);
            this.itemWait.runTaskTimer(plugin,0,20);
            return true;
        }
    }

}
