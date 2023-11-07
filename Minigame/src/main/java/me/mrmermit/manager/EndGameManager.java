package me.mrmermit.manager;

import me.mrmermit.Minigame;
import me.mrmermit.event.utils.ItemStacks;
import me.mrmermit.tasks.JoinWait;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class EndGameManager {
    private static GameManager gameManager;
    private static Minigame plugin;

    public EndGameManager(GameManager gameManager, Minigame plugin){
        this.plugin = plugin;
        this.gameManager = gameManager;
    }


    public static void ghost(Player p){
        p.setGameMode(GameMode.SPECTATOR);
    }
    public static void p_reset(Player p){
        p.getInventory().clear();
        p.setInvulnerable(false);
        p.setInvisible(false);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.setGameMode(GameMode.SURVIVAL);
        p.removePotionEffect(PotionEffectType.GLOWING);
        p.setGlowing(false);
        p.removePotionEffect(PotionEffectType.GLOWING);
        p.getInventory().setItem(4, ItemStacks.teamSetItem());
        for (int k = 0; k < 100; k++) {
            p.sendMessage("");
        }
    }
    public static void the_end(int i){
        gameManager.setGameStateInGame(3);
        for (Player p: Bukkit.getOnlinePlayers()){

            p_reset(p);
            if(plugin.getConfig().getLocation("Spawn") != null){
                Location teleportLocation = plugin.getConfig().getLocation("Spawn");
                Location teleport2 = new Location(teleportLocation.getWorld(), teleportLocation.getX(), teleportLocation.getY()+1, teleportLocation.getZ());
                if(teleportLocation.getBlock().isPassable()
                        &&(!teleportLocation.getBlock().getType().equals(Material.LAVA)&&!teleportLocation.getBlock().getType().equals(Material.WATER))
                        && teleport2.getBlock().isPassable()
                        &&(!teleport2.getBlock().getType().equals(Material.LAVA)&&!teleport2.getBlock().getType().equals(Material.WATER))){
                    p.teleport(teleportLocation);
                }else {
                    teleportLocation.setY(teleportLocation.getWorld().getHighestBlockYAt(teleportLocation)+1);
                    p.teleport(teleportLocation);
                }
            }
            if(i == 1){
                p.sendTitle(ChatColor.BLUE + "Blue team won", "");
            } else if (i ==2) {
                p.sendTitle(ChatColor.YELLOW + "Yellow team won", "");
            }else if (i ==3) {
                p.sendTitle(ChatColor.DARK_GREEN + "Green team won", "");
            }else if (i ==4) {
                p.sendTitle(ChatColor.RED + "Red team won", "");
            }
        }
        gameManager.reset();
        int players = Bukkit.getOnlinePlayers().size();
        if(StateManager.status == 1 || StateManager.status == 2 || (StateManager.game == 3 && StateManager.status == 4)) {
            if (players >= plugin.getConfig().getInt("PlayersForGame")) {
                gameManager.timeTask();
            }
        }
    }
}
