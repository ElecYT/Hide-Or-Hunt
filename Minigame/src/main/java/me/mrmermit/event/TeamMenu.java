package me.mrmermit.event;

import me.mrmermit.event.utils.ItemStacks;
import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeamMenu implements Listener {
    private GameManager gameManager;
    private static ItemStacks itemStacks;
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (StateManager.status == 1 || StateManager.status == 2) {
            if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Team selector")) {
                    ItemStacks.teamMenu(p);
                }
            }
        }

    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        HumanEntity hu = event.getWhoClicked();
        if(hu instanceof Player) {
            Player p = (Player)hu;
            if (StateManager.status == 1 || StateManager.status == 2) {
                if(event.getCurrentItem() !=null) {
                    if (event.getCurrentItem().getType() == Material.COMPASS) {
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Team selector")) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
            //je v gamemanagerovi
        }
    }

}
