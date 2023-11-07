package me.mrmermit.manager;

import me.mrmermit.Minigame;
import me.mrmermit.event.utils.ItemStacks;
import me.mrmermit.tasks.*;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffectType;

import java.sql.Time;
import java.util.ArrayList;

public class GameManager implements Listener {
     //1 = lobby 2 = starting 3 = active 4 = game
    private final Minigame plugin;
    private JoinWait joinWait;
    private BeaconManager beaconManager;
    private final PlayerManager playerManager;
    private final EndGameManager endGameManager;
    private TimeTikTask timeTikTask;
    private JoinWait stateWriter;
    private GameTask gameTask;
    private ItemStacks itemStacks;
    private WorldResetManager worldResetManager;
    private ItemWaiterManager itemWaiterManager;
    private final PlayerListManager playerListManager;
    private Rocket rocket;
    private static TimeTikTaskStart timeTikTaskStart;
    public GameManager(Minigame plugin) {

        this.plugin = plugin;
        this.worldResetManager = new WorldResetManager(this, plugin);
        this.playerListManager = new PlayerListManager(this);
        this.playerManager = new PlayerManager(this, plugin);
        this.endGameManager = new EndGameManager(this, plugin);
        this.beaconManager = new BeaconManager(plugin, this);
        this.itemWaiterManager = new ItemWaiterManager(this,plugin);
        StateManager.status = 1;
        StateManager.game = -1;
    }
    public void scoreboard(Integer integer){
        playerManager.setupScoreboard(integer);
    }
    public void reset(){
        System.out.println("Reseting");
        StateManager.status = 1;
        StateManager.game = -1;
        beaconManager.loc_reset();
        playerListManager.resetLists();
        worldResetManager.reset_loc();
        StateManager.status = 1;
        StateManager.game = -1;
        playerManager.updateList();
        playerManager.setupScoreboard(0);
    }

    @EventHandler
    private void itemDrop(PlayerDropItemEvent e){
        if(!(StateManager.game == 1 || StateManager.game == 2)){
            e.setCancelled(true);
        }
        if(StateManager.status == 4 && e.getItemDrop().getItemStack().getType().equals(Material.SUNFLOWER) && e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+ "Surface teleport")){
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void inMenuClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (StateManager.status == 1 || StateManager.status == 2) {
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.BLUE_WOOL) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Blue team")) {
                        if (PlayerListManager.team1Player.size()<plugin.getConfig().getInt("TeamA")){
                            playerListManager.setTeam1Player(p);
                            e.setCancelled(true);
                            p.closeInventory();
                            p.sendMessage(ChatColor.BLUE + "You joined the blue team!");
                            playerManager.updateList();
                        } else {
                            p.sendMessage(ChatColor.RED + "Sorry, but you can´t join this team! There are too many player");
                            e.setCancelled(true);
                            p.closeInventory();
                        }
                    }
                }
            }
        }
        if (StateManager.status == 1 || StateManager.status == 2) {
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.YELLOW_WOOL) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Yellow team")) {
                        if (PlayerListManager.team2Player.size() < plugin.getConfig().getInt("TeamB")) {
                            playerListManager.setTeam2Player(p);
                            e.setCancelled(true);
                            p.closeInventory();
                            p.sendMessage(ChatColor.YELLOW + "You joined the yellow team!");
                            playerManager.updateList();
                        } else {
                            p.sendMessage(ChatColor.RED + "Sorry, but you can´t join this team! There are too many player");
                            e.setCancelled(true);
                            p.closeInventory();
                        }
                    }
                }
            }
        }
        if (StateManager.status == 1 || StateManager.status == 2) {
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.GREEN_WOOL) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Green team")) {
                        if (PlayerListManager.team3Player.size() < plugin.getConfig().getInt("TeamC")) {
                            playerListManager.setTeam3Player(p);
                            e.setCancelled(true);
                            p.closeInventory();
                            p.sendMessage(ChatColor.DARK_GREEN + "You joined the green team!");
                            playerManager.updateList();
                        } else {
                            p.sendMessage(ChatColor.RED + "Sorry, but you can´t join this team! There are too many player");
                            e.setCancelled(true);
                            p.closeInventory();
                        }
                    }
                }
            }
        }
        if (StateManager.status == 1 || StateManager.status == 2) {
            if(e.getCurrentItem() != null) {
                if (e.getCurrentItem().getType() == Material.RED_WOOL) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Red team")) {
                        if (PlayerListManager.team4Player.size() < plugin.getConfig().getInt("TeamD")) {
                            playerListManager.setTeam4Player(p);
                            e.setCancelled(true);
                            p.closeInventory();
                            p.sendMessage(ChatColor.RED + "You joined the red team!");
                            playerManager.updateList();
                        } else {
                            p.sendMessage(ChatColor.RED + "Sorry, but you can´t join this team! There are too many player");
                            e.setCancelled(true);
                            p.closeInventory();
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    private void playerSunflowerUse(PlayerInteractEvent e){
        if (StateManager.status == 4) {
            Player p = e.getPlayer();
            if(e.getItem() != null) {
                if (e.getItem().getType() == Material.SUNFLOWER) {
                    if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD+ "Surface teleport")) {
                        e.setCancelled(true);
                        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                            if(itemWaiterManager.tpSurface(p)) {
                                int y = p.getWorld().getHighestBlockYAt(p.getLocation());
                                if (p.getWorld().getBlockAt(new Location(p.getWorld(), p.getLocation().getX(), y, p.getLocation().getZ())).isPassable()) {
                                    p.teleport(new Location(p.getWorld(), p.getLocation().getX(), y, p.getLocation().getZ()));
                                } else {
                                    p.teleport(new Location(p.getWorld(), p.getLocation().getX(), y + 1, p.getLocation().getZ()));
                                }
                            }else {
                                p.sendMessage(ChatColor.YELLOW + "You can´t use the teleporter again yet!");
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        int minPlayer = plugin.getConfig().getInt("PlayersForGame");

        if (StateManager.status == 3 || StateManager.status == 4) {
            p.sendMessage(ChatColor.GRAY + "The game already begun. For now you can stay as spectator");
            if(plugin.getConfig().getLocation("SpawnSpec") != null) {
                EndGameManager.ghost(p);
                p.setBedSpawnLocation(plugin.getConfig().getLocation("SpawnSpec"), true);
                p.teleport(plugin.getConfig().getLocation("SpawnSpec"));

            }else{p.sendMessage("Error: SpecSpawn haven´t been setup. Please contact the AT!");}
        } else if (StateManager.status == 1) {
            playerManager.setupScoreboard(0);
            p.setGameMode(GameMode.SURVIVAL);
            if (plugin.getConfig().getLocation("Spawn") != null) {
                p.setBedSpawnLocation(plugin.getConfig().getLocation("Spawn"), true);
                Bukkit.getWorld("world").setSpawnLocation(plugin.getConfig().getLocation("Spawn"));
                p.teleport(plugin.getConfig().getLocation("Spawn"));
            }else{p.sendMessage("Error: Spawn haven´t been setup. Please contact the AT!");}
            int players = Bukkit.getOnlinePlayers().size();
            e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() + ChatColor.AQUA + " has joined. Only " + (minPlayer - players) + " players left to start the game.");
        } else if (StateManager.status == 2) {
            playerManager.setupScoreboard(0);
            p.setGameMode(GameMode.SURVIVAL);
            if (plugin.getConfig().getLocation("Spawn") != null) {
                p.setBedSpawnLocation(plugin.getConfig().getLocation("Spawn"), true);
                Bukkit.getWorld("world").setSpawnLocation(plugin.getConfig().getLocation("Spawn"));
                p.teleport(plugin.getConfig().getLocation("Spawn"));
            }else{p.sendMessage("Error: Spawn haven´t been setup. Please contact the AT!");}
            e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getDisplayName() + ChatColor.AQUA + " has joined. Game will start soon.");
        }
        int players = Bukkit.getOnlinePlayers().size();
        p.getInventory().clear();
        p.getInventory().setItem(4, ItemStacks.teamSetItem());
        p.setInvulnerable(false);
        p.setInvisible(false);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.removePotionEffect(PotionEffectType.GLOWING);
        p.setGlowing(false);
        p.removePotionEffect(PotionEffectType.GLOWING);
        playerManager.updateList();
        if (players == plugin.getConfig().getInt("PlayersForGame")) {
            timeTask();
        }
    }
    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL ||
                event.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("Entering this dimension is disabled.");
        }
    }
    @EventHandler
    private void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(plugin.getConfig().getLocation("spawn")!=null){
            p.teleport(plugin.getConfig().getLocation("spawn"));
        }
        if(PlayerListManager.team1Player.contains(p)){
            PlayerListManager.team1Player.remove(p);
        }
        if(PlayerListManager.team2Player.contains(p)){
            PlayerListManager.team2Player.remove(p);
        }
        if(PlayerListManager.team3Player.contains(p)){
            PlayerListManager.team3Player.remove(p);
        }
        if(PlayerListManager.team4Player.contains(p)){
            PlayerListManager.team4Player.remove(p);
        }
        int players = Bukkit.getOnlinePlayers().size();
        if(StateManager.status == 2){
            StateManager.status = 1;
        }else {
            if (PlayerListManager.team1Player.size() == 0 && PlayerListManager.team2Player.size() == 0 && PlayerListManager.team3Player.size() == 0) {
                EndGameManager.the_end(4);
            } else if (PlayerListManager.team4Player.size() == 0 && PlayerListManager.team2Player.size() == 0 && PlayerListManager.team3Player.size() == 0) {
                EndGameManager.the_end(1);
            } else if (PlayerListManager.team1Player.size() == 0 && PlayerListManager.team4Player.size() == 0 && PlayerListManager.team3Player.size() == 0) {
                EndGameManager.the_end(2);
            } else if (PlayerListManager.team1Player.size() == 0 && PlayerListManager.team2Player.size() == 0 && PlayerListManager.team4Player.size() == 0) {
                EndGameManager.the_end(3);
            }
        }
    }


    public void timeTask(){
        this.joinWait = new JoinWait(this, plugin.getConfig().getInt("PlayersForGame"));
        this.joinWait.runTaskLater(plugin,100);
    }
    public void setGameState(Integer intiger){
        int i = intiger;
        if (StateManager.status == 3 && i == 2) return;
        if (StateManager.status == 2 && i == 1) return;
        if (StateManager.status == 4 && i == 3) return;
        if(StateManager.status == i) return;

        switch (i){
            case 3:
                dorozřazení();
                StateManager.status = 3;
                playerManager.setupScoreboard(0);
                this.timeTikTaskStart = new TimeTikTaskStart(this);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    for (int k = 0; k < 100; k++) {
                        player.sendMessage("");
                    }
                }
                playerManager.updateList();

                this.timeTikTaskStart.runTaskTimer(plugin,0,20);
                break;

            case 2:
                this.timeTikTask = new TimeTikTask(this, plugin.getConfig().getInt("StartFazeTime"));
                StateManager.status = 2;
                this.timeTikTask.runTaskTimer(plugin,0,20);
                break;
            case 4:
                playerManager.giveKits();
                StateManager.status = 4;
                setGameStateInGame(1);



        }

    }
    public void setGameStateInGame(Integer intiger){
        switch (intiger){
            case 1:
                StateManager.game = 1;
                int cas = plugin.getConfig().getInt("FirstGameFazeTime");
                this.gameTask = new GameTask(cas,this, plugin);
                this.gameTask.runTaskTimer(plugin,20,20);
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.setInvisible(true);
                }
            case 2:
                StateManager.game = 2;
                scoreboard(0);

            case 3:
                StateManager.game = 3;
        }

    }

    public void dorozřazení(){
        ArrayList<? extends Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        int nejmensi = 0;
        if(PlayerListManager.team1Player.size() <= PlayerListManager.team2Player.size() && PlayerListManager.team3Player.size() <= PlayerListManager.team4Player.size() && PlayerListManager.team1Player.size() <= PlayerListManager.team3Player.size()){
            nejmensi = 1;
        } else if (PlayerListManager.team1Player.size() <= PlayerListManager.team2Player.size() && PlayerListManager.team3Player.size() <= PlayerListManager.team4Player.size() && PlayerListManager.team1Player.size() >= PlayerListManager.team3Player.size()) {
            nejmensi = 3;
        } else if (PlayerListManager.team1Player.size() >= PlayerListManager.team2Player.size() && PlayerListManager.team3Player.size() <= PlayerListManager.team4Player.size() && PlayerListManager.team2Player.size() <= PlayerListManager.team3Player.size()) {
            nejmensi = 2;
        }else {
            nejmensi = 4;
        }
        for (Player player : playerList) {

            player.closeInventory();
            player.getInventory().clear();

            if(!(PlayerListManager.team2Player.contains(player) || PlayerListManager.team1Player.contains(player)||PlayerListManager.team3Player.contains(player)||PlayerListManager.team4Player.contains(player))){
                if(nejmensi == 1){

                    PlayerListManager.team1Player.add(player);
                    if(!(plugin.getConfig().getLocation("TeamASpawn") == null)){
                        Location l = plugin.getConfig().getLocation("TeamASpawn");
                        l.setY(l.getWorld().getHighestBlockYAt(l));
                        if(!l.getBlock().isPassable()){
                            l.setY(l.getY()+1);
                        }
                        player.teleport(l);
                    }
                }else if (nejmensi == 2){
                    PlayerListManager.team2Player.add(player);
                    if(!(plugin.getConfig().getLocation("TeamBSpawn") == null)){
                        Location l = plugin.getConfig().getLocation("TeamBSpawn");
                        l.setY(l.getWorld().getHighestBlockYAt(l));
                        if(!l.getBlock().isPassable()){
                            l.setY(l.getY()+1);
                        }
                        player.teleport(l);
                    }
                }
                else if (nejmensi == 3){
                    PlayerListManager.team3Player.add(player);
                    if(!(plugin.getConfig().getLocation("TeamCSpawn") == null)){
                        Location l = plugin.getConfig().getLocation("TeamCSpawn");
                        l.setY(l.getWorld().getHighestBlockYAt(l));
                        if(!l.getBlock().isPassable()){
                            l.setY(l.getY()+1);
                        }
                        player.teleport(l);
                    }
                }
                else{
                    PlayerListManager.team4Player.add(player);
                    if(!(plugin.getConfig().getLocation("TeamDSpawn") == null)){
                        Location l = plugin.getConfig().getLocation("TeamDSpawn");
                        l.setY(l.getWorld().getHighestBlockYAt(l));
                        if(!l.getBlock().isPassable()){
                            l.setY(l.getY()+1);
                        }
                        player.teleport(l);
                    }
                }
            } else if (PlayerListManager.team2Player.contains(player)) {
                if(!(plugin.getConfig().getLocation("TeamBSpawn") == null)){
                    Location l = plugin.getConfig().getLocation("TeamBSpawn");
                    l.setY(l.getWorld().getHighestBlockYAt(l));
                    if(!l.getBlock().isPassable()){
                        l.setY(l.getY()+1);
                    }
                    player.teleport(l);
                }

            } else if (PlayerListManager.team1Player.contains(player)) {
                if(!(plugin.getConfig().getLocation("TeamASpawn") == null)){
                    Location l = plugin.getConfig().getLocation("TeamASpawn");
                    l.setY(l.getWorld().getHighestBlockYAt(l));
                    if(!l.getBlock().isPassable()){
                        l.setY(l.getY()+1);
                    }
                    player.teleport(l);
                }
            }else if (PlayerListManager.team3Player.contains(player)) {
                if(!(plugin.getConfig().getLocation("TeamCSpawn") == null)){
                    Location l = plugin.getConfig().getLocation("TeamCSpawn");
                    l.setY(l.getWorld().getHighestBlockYAt(l));
                    if(!l.getBlock().isPassable()){
                        l.setY(l.getY()+1);
                    }
                    player.teleport(l);
                }
            }else {
                if(!(plugin.getConfig().getLocation("TeamDSpawn") == null)){
                    Location l = plugin.getConfig().getLocation("TeamDSpawn");
                    l.setY(l.getWorld().getHighestBlockYAt(l));
                    if(!l.getBlock().isPassable()){
                        l.setY(l.getY()+1);
                    }
                    player.teleport(l);
                }
            }
        }
    }
}
