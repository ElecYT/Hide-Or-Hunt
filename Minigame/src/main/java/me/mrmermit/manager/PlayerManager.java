package me.mrmermit.manager;

import me.mrmermit.Minigame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private GameManager gameManager;
    private Minigame plugin;
    private PlayerListManager playerListManager;

    public PlayerManager(GameManager gameManager, Minigame plugin){
        this.plugin = plugin;
        this.gameManager = gameManager;
    }

    public void giveKits(){
        boolean team1 = true;
        boolean team2 = true;
        boolean team3 = true;
        boolean team4 = true;

        ArrayList<? extends Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
        for(Player p : playerList){
            p.getInventory().clear();
            if(PlayerListManager.team1Player.contains(p) && team1){
                p.sendMessage(ChatColor.BLUE + "You are in blue team with: ");
                for(Player pl : PlayerListManager.team1Player){
                    p.sendMessage(ChatColor.BLUE + "| " + pl.getDisplayName());
                }
                team1 = false;
                ItemStack item = new ItemStack(Material.BEACON, 1);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(ChatColor.BLUE + "Blue respawn beacon");
                ArrayList<String> main_lore = new ArrayList<>();
                main_lore.add(ChatColor.GRAY + "Place this beacon in prep faze");
                itemMeta.setLore(main_lore);
                item.setItemMeta(itemMeta);
                p.getInventory().setItem(8, item);
                p.updateInventory();
            }
            if(PlayerListManager.team2Player.contains(p) && team2){
                p.sendMessage(ChatColor.YELLOW+ "You are in yellow team with: ");
                for(Player pl : PlayerListManager.team2Player){
                    p.sendMessage(ChatColor.YELLOW + "| " + pl.getDisplayName());
                }
                team2 = false;
                ItemStack item = new ItemStack(Material.BEACON, 1);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(ChatColor.YELLOW + "Yellow respawn beacon");
                ArrayList<String> lore1 = new ArrayList<>();
                lore1.add(ChatColor.GRAY + "Place this beacon in prep faze");
                itemMeta.setLore(lore1);
                item.setItemMeta(itemMeta);
                p.getInventory().setItem(8, item);
                p.updateInventory();
            }
            if(PlayerListManager.team3Player.contains(p) && team3){
                p.sendMessage(ChatColor.GREEN + "You are in green team with: ");
                for(Player pl : PlayerListManager.team3Player){
                    p.sendMessage(ChatColor.GREEN + "| " + pl.getDisplayName());
                }
                team3 = false;
                ItemStack item = new ItemStack(Material.BEACON, 1);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(ChatColor.GREEN + "Green respawn beacon");
                ArrayList<String> lore1 = new ArrayList<>();
                lore1.add(ChatColor.GRAY + "Place this beacon in prep faze");
                itemMeta.setLore(lore1);
                item.setItemMeta(itemMeta);
                p.getInventory().setItem(8, item);
                p.updateInventory();
            }
            if(PlayerListManager.team4Player.contains(p) && team4){
                p.sendMessage(ChatColor.RED + "You are in red team with: ");
                for(Player pl : PlayerListManager.team4Player){
                    p.sendMessage(ChatColor.RED + "| " + pl.getDisplayName());
                }
                team4 = false;
                ItemStack item = new ItemStack(Material.BEACON, 1);
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.setDisplayName(ChatColor.RED + "Red respawn beacon");
                ArrayList<String> lore1 = new ArrayList<>();
                lore1.add(ChatColor.GRAY + "Place this beacon in prep faze");
                itemMeta.setLore(lore1);
                item.setItemMeta(itemMeta);
                p.getInventory().setItem(8, item);
                p.updateInventory();
            }
            giveKit(p);
        }
    }
    private static void giveKit(Player p){
        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        p.getInventory().setItem(0,sword);
//        p.getInventory().setItem(27,sword);
        ItemStack axe = new ItemStack(Material.STONE_AXE, 1);
        p.getInventory().setItem(1,axe);
//        p.getInventory().setItem(28,axe);
        ItemStack pick = new ItemStack(Material.STONE_PICKAXE, 1);

        p.getInventory().setItem(2,pick);
//        p.getInventory().setItem(29,pick);
        ItemStack shovel = new ItemStack(Material.STONE_SHOVEL, 1);
        p.getInventory().setItem(3,shovel);
//        p.getInventory().setItem(30,shovel);
        ItemStack stake = new ItemStack(Material.COOKED_BEEF, 64);
        p.getInventory().setItem(4,stake);
//        ItemStack block = new ItemStack(Material.REDSTONE_BLOCK, 64);
//        p.getInventory().setItem(19,block);
//        ItemStack piston = new ItemStack(Material.PISTON, 64);
//        p.getInventory().setItem(20,piston);
//        ItemStack sticky = new ItemStack(Material.STICKY_PISTON, 64);
//        p.getInventory().setItem(21,sticky);
//        ItemStack torch = new ItemStack(Material.REDSTONE_TORCH, 64);
//        p.getInventory().setItem(22,torch);
//        ItemStack dust = new ItemStack(Material.REDSTONE, 64);
//        p.getInventory().setItem(23,dust);
//        ItemStack rep = new ItemStack(Material.REPEATER, 64);
//        p.getInventory().setItem(18,rep);
        ItemStack iron = new ItemStack(Material.IRON_INGOT, 64);
        p.getInventory().setItem(27,iron);
        ItemStack wood = new ItemStack(Material.OAK_PLANKS, 64);
        p.getInventory().setItem(5,wood);
        ItemStack sunflower = new ItemStack(Material.SUNFLOWER, 1);
        ItemMeta comMeta = sunflower.getItemMeta();
        comMeta.setDisplayName(ChatColor.GOLD+ "Surface teleport");
        sunflower.setItemMeta(comMeta);
        p.getInventory().setItem(7,sunflower);

        p.updateInventory();
    }
    public static void giveRespawnKit(Player p){
        ItemStack sword = new ItemStack(Material.IRON_INGOT, 64);
        if(p.getInventory().getItem(0)==null){
            p.getInventory().setItem(0,sword);
        }else {
            p.getInventory().setItem(2,sword);
        }
        ItemStack axe = new ItemStack(Material.OAK_PLANKS, 64);
        if(p.getInventory().getItem(1)==null){
            p.getInventory().setItem(1,axe);
        }else {
            p.getInventory().setItem(3,axe);
        }
        p.updateInventory();
    }
    public static void updateList() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Team team = scoreboard.getTeam(onlinePlayer.getName()); // Assume player name is the same as the team name
            if (team == null) {
                System.out.println("team je nula");
                team = scoreboard.registerNewTeam(onlinePlayer.getName());
                team.addEntry(onlinePlayer.getName());
            }
            // Apply prefixes based on your team setup
            if (PlayerListManager.team1Player.contains(onlinePlayer)) {
                team.setPrefix(ChatColor.BLUE + "|BLUE|" + ChatColor.WHITE + " ");
            } else if (PlayerListManager.team2Player.contains(onlinePlayer)) {
                team.setPrefix(ChatColor.YELLOW + "|YELLOW|" + ChatColor.WHITE + " ");
            }else if (PlayerListManager.team3Player.contains(onlinePlayer)) {
                team.setPrefix(ChatColor.GREEN + "|GREEN| " + ChatColor.WHITE + " ");
            }else if (PlayerListManager.team4Player.contains(onlinePlayer)) {
                team.setPrefix(ChatColor.RED+ "|RED|" + ChatColor.WHITE + " ");
            }else {
                if(StateManager.status == 4){
                    team.setPrefix(ChatColor.DARK_GRAY + "|SPECTATOR|" + ChatColor.GRAY + " ");
                }else {
                    team.setPrefix(ChatColor.GRAY + "CHOOSING:" + ChatColor.WHITE + " ");
                }
            }
        }
    }
    public void setupScoreboard(Integer counter) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Team team = scoreboard.getTeam(player.getName());
            Objective objective = null;
            if (team != null) {
                if (team.getScoreboard().getObjective(player.getName()) != null) {
                    objective = team.getScoreboard().getObjective(player.getName());
                } else {
                    objective = scoreboard.registerNewObjective(player.getName(), "dummy", ChatColor.BOLD + "" + ChatColor.AQUA + "Nautical " + ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Network");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                }
            }

            // Clear existing scores
            for (String entry : scoreboard.getEntries()) {
                team.getScoreboard().resetScores(entry);
            }
            if((StateManager.status == 4 && (StateManager.game == 1 || StateManager.game == 2))|| StateManager.status == 3) {
                objective.getScore(ChatColor.RESET + "" + ChatColor.STRIKETHROUGH + "" + ChatColor.GRAY + "----------------").setScore(12);
                objective.getScore(ChatColor.AQUA + "» " + ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Teams Left").setScore(11);
                if(plugin.getConfig().getLocation("TeamBSpawn") != null && PlayerListManager.team2Player.size()>0){
                    objective.getScore(ChatColor.YELLOW +"" +ChatColor.BOLD +  "  Yellow: " + ChatColor.DARK_GREEN + "✔").setScore(10);
                }else {
                    objective.getScore(ChatColor.YELLOW +"" +ChatColor.BOLD +  "  Yellow: " + ChatColor.DARK_RED + "✖").setScore(10);
                }

                if(plugin.getConfig().getLocation("TeamASpawn") != null&& PlayerListManager.team1Player.size()>0){
                    objective.getScore(ChatColor.BLUE +"" +ChatColor.BOLD +  "  Blue: " + ChatColor.DARK_GREEN + "✔").setScore(9);
                }else {
                    objective.getScore(ChatColor.BLUE +"" +ChatColor.BOLD +  "  Blue: " + ChatColor.DARK_RED + "✖").setScore(9);
                }

                if(plugin.getConfig().getLocation("TeamCSpawn") != null && PlayerListManager.team3Player.size()>0){
                    objective.getScore(ChatColor.GREEN +"" +ChatColor.BOLD +  "  Green: " + ChatColor.DARK_GREEN + "✔").setScore(8);
                }else {
                    objective.getScore(ChatColor.GREEN +"" +ChatColor.BOLD +  "  Green: " + ChatColor.DARK_RED + "✖").setScore(7);
                }

                if(plugin.getConfig().getLocation("TeamDSpawn") != null && PlayerListManager.team4Player.size()>0){
                    objective.getScore(ChatColor.RED +"" +ChatColor.BOLD +  "  Red: " + ChatColor.DARK_GREEN + "✔").setScore(6);
                }else {
                    objective.getScore(ChatColor.RED +"" +ChatColor.BOLD +  "  Red: " + ChatColor.DARK_RED + "✖").setScore(5);
                }

                objective.getScore(ChatColor.RESET + " ").setScore(4); // Empty line

                if(StateManager.game == 2){
                    objective.getScore(ChatColor.RESET + "" + ChatColor.AQUA + "» " + ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "PvP faze on!!!").setScore(3);
                    objective.getScore(ChatColor.RESET + " " +  ChatColor.DARK_RED + "  --:--").setScore(2);
                } else if (StateManager.status == 3) {
                    objective.getScore(ChatColor.RESET + "" + ChatColor.AQUA + "» " + ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Grace period on").setScore(3);
                    objective.getScore(ChatColor.RESET + "  '" +  ChatColor.AQUA + "--:--" + ChatColor.WHITE+ "'").setScore(2);
                } else {
                    int minutes = counter/60;
                    int seconds = counter % 60;
                    String sec = null;
                    String min = null;

                    if(minutes <10){
                        min = "0" + minutes;
                    }else {
                        min = Integer.toString(minutes);
                    }

                    if(seconds <10){
                        sec = "0" + seconds;
                    }else {
                        sec = Integer.toString(seconds);
                    }

                    objective.getScore(ChatColor.RESET + "" + ChatColor.AQUA + "» " + ChatColor.BOLD + "" + ChatColor.DARK_AQUA + "Grace period on").setScore(3);

                    objective.getScore(ChatColor.RESET + "  '" + ChatColor.DARK_AQUA + min + ":" + sec + ChatColor.WHITE + "'").setScore(2);
                }
                objective.getScore(ChatColor.STRIKETHROUGH + "" + ChatColor.GRAY + "----------------").setScore(1);
            }
            player.setScoreboard(team.getScoreboard());

        }
    }
}
