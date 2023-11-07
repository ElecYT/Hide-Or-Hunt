package me.mrmermit.manager;

import me.mrmermit.event.TeamMenu;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerListManager {
    private GameManager gameManager;
    private TeamMenu teamMenu;

    public PlayerListManager(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public static Set<Player> team4Player = new HashSet<>();
    public static Set<Player> team3Player = new HashSet<>();
    public static Set<Player> team2Player = new HashSet<>();
    public static Set<Player> team1Player = new HashSet<>();

    private static void remove(Player player){
        if(team2Player.contains(player)){
            team2Player.remove(player);
        }
        if(team3Player.contains(player)){
            team3Player.remove(player);
        }
        if(team1Player.contains(player)){
            team1Player.remove(player);
        }
        if(team4Player.contains(player)){
            team4Player.remove(player);
        }
    }
    public void setTeam1Player(Player player) {
        remove(player);
        team1Player.add(player);
    }

    public void setTeam4Player(Player player) {
        remove(player);
        team4Player.add(player);
    }




    public void setTeam3Player(Player player) {
        remove(player);
        team3Player.add(player);

    }


    public void setTeam2Player(Player player) {
        remove(player);
        team2Player.add(player);
    }

    public void resetLists(){
        team2Player = new HashSet<>();
        team1Player = new HashSet<>();
        team3Player = new HashSet<>();
        team4Player = new HashSet<>();
    }
}
