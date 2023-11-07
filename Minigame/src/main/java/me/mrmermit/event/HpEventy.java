package me.mrmermit.event;

import me.mrmermit.Minigame;
import me.mrmermit.manager.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class HpEventy implements Listener {



    private BeaconManager beaconManager;
    private Minigame plugin;


    public HpEventy(Minigame plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerInteractEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getDamager() instanceof Player) {
                Player p_hit = (Player) event.getEntity();
                Player p_att = (Player) event.getDamager();
                if (StateManager.status == 4 && StateManager.game == 2) {
                    if (PlayerListManager.team1Player.contains(p_hit) && PlayerListManager.team1Player.contains(p_att) || PlayerListManager.team2Player.contains(p_hit) && PlayerListManager.team2Player.contains(p_att) || PlayerListManager.team3Player.contains(p_hit) && PlayerListManager.team3Player.contains(p_att) || PlayerListManager.team4Player.contains(p_hit) && PlayerListManager.team4Player.contains(p_att)) {
                        event.setCancelled(true);
                    }else {
                        event.setCancelled(false);
                    }
                }else {
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onFall(EntityDamageEvent e){
        if(!((StateManager.game == 2 || StateManager.game == 1) && StateManager.status == 4)){
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void whenDie(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            double health = player.getHealth() - event.getFinalDamage();

            // Check if the player's health will reach zero or below
            if(StateManager.status == 4) {
                if (health <= 0) {
                    Location teleportLocation = null;
                    event.setCancelled(true);
                    if (StateManager.status == 4 && StateManager.game == 1) {
                        if (player.getInventory().contains(Material.BEACON)) {
                            Inventory inventory = player.getInventory();
                            for (int i = 0; i < inventory.getSize(); i++) {
                                ItemStack item = inventory.getItem(i);
                                if (item != null && item.getType() != Material.BEACON) {
                                    inventory.setItem(i, null); // Clear the item from the inventory
                                }
                            }
                        }
                        if (PlayerListManager.team1Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamASpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamASpawn");
                            }
                        }
                        if (PlayerListManager.team2Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamBSpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamBSpawn");
                            }
                        }
                        if (PlayerListManager.team3Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamCSpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamCSpawn");
                            }
                        }
                        if (PlayerListManager.team4Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamDSpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamDSpawn");
                            }
                        }
                    } else {
                        player.getInventory().clear();
                        if (PlayerListManager.team1Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamASpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamASpawn");
                            } else {
                                PlayerListManager.team1Player.remove(player);
                                teleportLocation = plugin.getConfig().getLocation("SpawnSpec");
                                EndGameManager.ghost(player);
                                player.sendTitle(ChatColor.BLUE + "Game Over", "");
                                player.sendMessage(ChatColor.GRAY + "You can´t respawn now");
                                PlayerManager.updateList();
                            }
                        }
                        if (PlayerListManager.team2Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamBSpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamBSpawn");
                            } else {
                                PlayerListManager.team2Player.remove(player);
                                teleportLocation = plugin.getConfig().getLocation("SpawnSpec");
                                EndGameManager.ghost(player);
                                player.sendTitle(ChatColor.BLUE + "Game Over", "");
                                player.sendMessage(ChatColor.GRAY + "You can´t respawn now");
                                PlayerManager.updateList();
                            }
                        }
                        if (PlayerListManager.team3Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamCSpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamCSpawn");
                            } else {
                                PlayerListManager.team3Player.remove(player);
                                teleportLocation = plugin.getConfig().getLocation("SpawnSpec");
                                EndGameManager.ghost(player);
                                player.sendTitle(ChatColor.BLUE + "Game Over", "");
                                player.sendMessage(ChatColor.GRAY + "You can´t respawn now");
                                PlayerManager.updateList();
                            }
                        }
                        if (PlayerListManager.team4Player.contains(player)) {
                            if (plugin.getConfig().getLocation("TeamDSpawn") != null) {
                                teleportLocation = plugin.getConfig().getLocation("TeamDSpawn");
                            } else {
                                PlayerListManager.team4Player.remove(player);
                                teleportLocation = plugin.getConfig().getLocation("SpawnSpec");
                                EndGameManager.ghost(player);
                                player.sendTitle(ChatColor.BLUE + "Game Over", "");
                                player.sendMessage(ChatColor.GRAY + "You can´t respawn now");
                                PlayerManager.updateList();
                            }
                        }
                    }
                    if (PlayerListManager.team1Player.size() == 0 && PlayerListManager.team2Player.size() == 0 && PlayerListManager.team3Player.size() == 0) {
                        EndGameManager.the_end(4);
                    } else if (PlayerListManager.team4Player.size() == 0 && PlayerListManager.team2Player.size() == 0 && PlayerListManager.team3Player.size() == 0) {
                        EndGameManager.the_end(1);
                    } else if (PlayerListManager.team1Player.size() == 0 && PlayerListManager.team4Player.size() == 0 && PlayerListManager.team3Player.size() == 0) {
                        EndGameManager.the_end(2);
                    } else if (PlayerListManager.team1Player.size() == 0 && PlayerListManager.team2Player.size() == 0 && PlayerListManager.team4Player.size() == 0) {
                        EndGameManager.the_end(3);
                    } else {
                        if (teleportLocation != null) {
                            if(PlayerListManager.team1Player.contains(player)){
                                Bukkit.broadcastMessage(ChatColor.BLUE + player.getDisplayName() + " died");
                            }
                            if(PlayerListManager.team2Player.contains(player)){
                                Bukkit.broadcastMessage(ChatColor.YELLOW + player.getDisplayName() + " died");}
                            if(PlayerListManager.team3Player.contains(player)){Bukkit.broadcastMessage(ChatColor.GREEN + player.getDisplayName() + " died");}
                            if(PlayerListManager.team4Player.contains(player)){Bukkit.broadcastMessage(ChatColor.RED + player.getDisplayName() + " died");}
                            Location teleport2 = new Location(teleportLocation.getWorld(), teleportLocation.getX(), teleportLocation.getY()+1, teleportLocation.getZ());
                            if(teleportLocation.getBlock().isPassable()
                               &&(!teleportLocation.getBlock().getType().equals(Material.LAVA)&&!teleportLocation.getBlock().getType().equals(Material.WATER))
                               && teleport2.getBlock().isPassable()
                               &&(!teleport2.getBlock().getType().equals(Material.LAVA)&&!teleport2.getBlock().getType().equals(Material.WATER))){
                                player.teleport(teleportLocation);
                            }else{
                                teleportLocation.setY(teleportLocation.getWorld().getHighestBlockYAt(teleportLocation));
                                if (!teleportLocation.getBlock().isPassable()) {
                                    teleportLocation.setY(teleportLocation.getY() + 1);
                                }
                                player.teleport(teleportLocation);
                            }
                            PlayerManager.giveRespawnKit(player);
                            player.setHealth(player.getMaxHealth());
                            player.setFoodLevel(20);
                        }
                    }
                }
            }
        }
    }
}
