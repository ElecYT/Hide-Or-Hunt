package me.mrmermit.event;

import me.mrmermit.Minigame;
import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener  {
    private GameManager gameManager;
    private final Minigame plugin;
    private StateManager stateManager;




    public BlockBreakListener(Minigame plugin){
        this.plugin = plugin;

    }
    @EventHandler
    private void onBlockBreak(BlockBreakEvent e){
        if(!((StateManager.game == 2 || StateManager.game == 1) && StateManager.status == 4)) {
            e.setCancelled(true);
        }else {
            if(e.getBlock().getType() == Material.DIAMOND_ORE || e.getBlock().getType() == Material.DEEPSLATE_DIAMOND_ORE){
                e.setDropItems(false);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.DIAMOND, 5));
            }
        }
    }
}
