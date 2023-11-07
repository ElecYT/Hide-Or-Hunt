package me.mrmermit.manager;

import me.mrmermit.Minigame;
import org.bukkit.*;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeaconManager implements Listener {
    private StateManager stateManager;
    private Minigame plugin;
    private GameManager gameManager;

    private int beaconState1 = 0;
    private int beaconState2 = 0;
    private int beaconState3 = 0;
    private int beaconState4 = 0;

    public static Location sTeam1 = null;
    public static Location sTeam2 = null;
    public static Location sTeam3 = null;
    public static Location sTeam4 = null;

    private static Location bTeam1 = null;
    private static Location bTeam2 = null;
    private static Location bTeam3 = null;
    private static Location bTeam4 = null;

    public BeaconManager(Minigame plugin, GameManager gameManager){
        this.plugin = plugin;
        this.gameManager = gameManager;
    }

    public void loc_reset(){
        if(bTeam1!=null){
            bTeam1.getBlock().setType(Material.AIR);
        }
        if(bTeam2!=null){
            bTeam2.getBlock().setType(Material.AIR);
        }
        if(bTeam3!=null){
            bTeam3.getBlock().setType(Material.AIR);
        }
        if(bTeam4!=null){
            bTeam4.getBlock().setType(Material.AIR);
        }
        bTeam1 = null;
        bTeam2 = null;
        bTeam3 = null;
        bTeam4 = null;
        sTeam1 = null;
        sTeam2 = null;
        sTeam3 = null;
        sTeam4 = null;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.BEACON) {
            event.setCancelled(true);
            event.getPlayer().openWorkbench(null, true);
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "Only way to open crafting menu is to right click on beacon!");
        }
    }

    @EventHandler
    private void onBeaconPlace(BlockPlaceEvent e) {
        if (StateManager.status == 4 && StateManager.game == 1) {
            if(e.getBlockPlaced().getType() == Material.BEACON){
                Player p = e.getPlayer();
                if(PlayerListManager.team1Player.contains(p)){
                    Bukkit.broadcastMessage(ChatColor.BLUE + "Blue team placed a beacon!!");
                    for (Player player : PlayerListManager.team1Player) {
                        player.sendMessage(ChatColor.GOLD + "Spawn point set");
                    }
                    bTeam1 = e.getBlockPlaced().getLocation();
                    plugin.getConfig().set("TeamASpawn", p.getLocation());
                    plugin.saveConfig();
                    sTeam1 = p.getLocation();
                }
                if(PlayerListManager.team2Player.contains(p)){
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Yellow team placed a beacon!!");
                    for (Player player : PlayerListManager.team2Player) {
                        player.sendMessage(ChatColor.GOLD + "Spawn point set");
                    }
                    bTeam2 = e.getBlockPlaced().getLocation();
                    plugin.getConfig().set("TeamBSpawn", p.getLocation());
                    plugin.saveConfig();
                    sTeam2 = p.getLocation();
                }
                if(PlayerListManager.team3Player.contains(p)){
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Green team placed a beacon!!");
                    for (Player player : PlayerListManager.team3Player) {
                        player.sendMessage(ChatColor.GOLD + "Spawn point set");
                    }
                    bTeam3 = e.getBlockPlaced().getLocation();
                    plugin.getConfig().set("TeamCSpawn", p.getLocation());
                    plugin.saveConfig();
                    sTeam3 = p.getLocation();
                }
                if(PlayerListManager.team4Player.contains(p)){
                    Bukkit.broadcastMessage(ChatColor.RED + "Red team placed a beacon!!");
                    for (Player player : PlayerListManager.team4Player) {
                        player.sendMessage(ChatColor.GOLD + "Spawn point set");
                    }
                    bTeam4 = e.getBlockPlaced().getLocation();
                    plugin.getConfig().set("TeamDSpawn", p.getLocation());
                    plugin.saveConfig();
                    sTeam4 = p.getLocation();
                }
            }
        }
    }
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        event.blockList().removeIf(block -> block.getType() == Material.BEACON);
    }

    @EventHandler
    public void onBlockPistonExtend(BlockPistonExtendEvent event) {
        if (event.getBlocks().stream().anyMatch(block -> block.getType() == Material.BEACON)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPistonRetract(BlockPistonRetractEvent event) {
        if (event.getBlocks().stream().anyMatch(block -> block.getType() == Material.BEACON)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void onBeaconBreak(BlockBreakEvent e){
        if (e.getBlock().getType() == Material.BEACON) {
            if (StateManager.status == 4 && StateManager.game ==2) {
                if(bTeam1!=null) {
                    if (e.getBlock().getLocation().getX() == bTeam1.getX() && e.getBlock().getLocation().getY() == bTeam1.getY() && e.getBlock().getLocation().getZ() == bTeam1.getZ()) {
                        if (PlayerListManager.team1Player.contains(e.getPlayer())) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "You can´t destroy your own beacon!");
                        } else {
                            bTeam1 = null;
                            sTeam1 = null;
                            plugin.getConfig().set("TeamASpawn", null);
                            plugin.saveConfig();
                            Bukkit.broadcastMessage(ChatColor.BLUE + "The blue beacon was destroyed");
                            World world = e.getBlock().getLocation().getWorld();
                            world.getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
                            e.setCancelled(true);
                            Location l = e.getBlock().getLocation();
                            Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();

                            fwm.setPower(1);
                            fwm.addEffect(FireworkEffect.builder().withColor(Color.BLUE).flicker(true).build());

                            fw.setFireworkMeta(fwm);
                            fw.detonate();
                            gameManager.scoreboard(0);
                        }
                    }
                }
                if(bTeam2!=null) {

                    if (e.getBlock().getLocation().getX() == bTeam2.getX() && e.getBlock().getLocation().getY() == bTeam2.getY() && e.getBlock().getLocation().getZ() == bTeam2.getZ()) {
                        if (PlayerListManager.team2Player.contains(e.getPlayer())) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "You can´t destroy your own beacon!");
                        } else {
                            bTeam2 = null;
                            sTeam2 = null;
                            plugin.getConfig().set("TeamBSpawn", null);
                            plugin.saveConfig();
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "The Yellow beacon was destroyed");
                            World world = e.getBlock().getLocation().getWorld();
                            world.getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
                            e.setCancelled(true);
                            Location l = e.getBlock().getLocation();
                            Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();

                            fwm.setPower(1);
                            fwm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());

                            fw.setFireworkMeta(fwm);
                            fw.detonate();
                            gameManager.scoreboard(0);
                        }
                    }
                }
                if(bTeam3!=null) {
                    if (e.getBlock().getLocation().getX() == bTeam3.getX() && e.getBlock().getLocation().getY() == bTeam3.getY() && e.getBlock().getLocation().getZ() == bTeam3.getZ()) {
                        if (PlayerListManager.team3Player.contains(e.getPlayer())) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "You can´t destroy your own beacon!");
                        } else {
                            bTeam3 = null;
                            sTeam3 = null;
                            plugin.getConfig().set("TeamCSpawn", null);
                            plugin.saveConfig();
                            Bukkit.broadcastMessage(ChatColor.GREEN + "The green beacon was destroyed");
                            World world = e.getBlock().getLocation().getWorld();
                            world.getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
                            e.setCancelled(true);
                            Location l = e.getBlock().getLocation();
                            Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();

                            fwm.setPower(1);
                            fwm.addEffect(FireworkEffect.builder().withColor(Color.GREEN).flicker(true).build());

                            fw.setFireworkMeta(fwm);
                            fw.detonate();
                            gameManager.scoreboard(0);
                        }
                    }
                }
                if(bTeam4!=null) {
                    if (e.getBlock().getLocation().getX() == bTeam4.getX() && e.getBlock().getLocation().getY() == bTeam4.getY() && e.getBlock().getLocation().getZ() == bTeam4.getZ()) {
                        if (PlayerListManager.team4Player.contains(e.getPlayer())) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.DARK_RED + "You can´t destroy your own beacon!");


                        } else {
                            bTeam4 = null;
                            sTeam4 = null;
                            plugin.getConfig().set("TeamDSpawn", null);

                            plugin.saveConfig();
                            Bukkit.broadcastMessage(ChatColor.RED + "The red beacon was destroyed");
                            gameManager.scoreboard(0);
                            World world = e.getBlock().getLocation().getWorld();
                            world.getBlockAt(e.getBlock().getLocation()).setType(Material.AIR);
                            e.setCancelled(true);
                            Location l = e.getBlock().getLocation();
                            Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                            FireworkMeta fwm = fw.getFireworkMeta();

                            fwm.setPower(1);
                            fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

                            fw.setFireworkMeta(fwm);
                            fw.detonate();
                        }
                    }
                }
            }else if (StateManager.status == 4 && StateManager.game == 1) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "You can´t destroy beacons in this faze!");
            }
        }

    }
    public static void place_beacon(){
        for (Player player : Bukkit.getOnlinePlayers()){
            if(player.getInventory().contains(Material.BEACON)){
                Inventory inventory = player.getInventory();
                for (int i = 0; i < inventory.getSize(); i++) {
                    ItemStack item = inventory.getItem(i);
                    if (item != null && item.getType() == Material.BEACON) {
                        inventory.setItem(i, null);
                        Location location = player.getLocation().getBlock().getLocation();
                        World world = location.getWorld();
                        world.getBlockAt(location).setType(Material.BEACON);
                        if(PlayerListManager.team1Player.contains(player)){
                            Bukkit.broadcastMessage(ChatColor.BLUE + "Blue team´s spawn was set!");
                            bTeam1 = new Location(world, location.getX(), location.getY(),location.getZ());
                            sTeam1 = new Location(world, location.getX(), location.getY()+1,location.getZ());
                        }
                        if(PlayerListManager.team2Player.contains(player)){
                            Bukkit.broadcastMessage(ChatColor.YELLOW + "Yellow team´s spawn was set!");
                            bTeam2 = new Location(world, location.getX(), location.getY(),location.getZ());
                            sTeam2 = new Location(world, location.getX(), location.getY()+1,location.getZ());
                        }
                        if(PlayerListManager.team3Player.contains(player)){
                            Bukkit.broadcastMessage(ChatColor.GREEN + "Green team´s spawn was set!");
                            bTeam3 = new Location(world, location.getX(), location.getY(),location.getZ());
                            sTeam3 = new Location(world, location.getX(), location.getY()+1,location.getZ());
                        }
                        if(PlayerListManager.team4Player.contains(player)){
                            Bukkit.broadcastMessage(ChatColor.RED + "Red team´s spawn was set!");
                            bTeam4 = new Location(world, location.getX(), location.getY(),location.getZ());
                            sTeam4 = new Location(world, location.getX(), location.getY()+1,location.getZ());
                        }
                    }
                }
            }
        }
    }
}
