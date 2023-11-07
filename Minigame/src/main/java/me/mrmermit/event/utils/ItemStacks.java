package me.mrmermit.event.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemStacks {
    public static ItemStack teamSetItem(){
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta comMeta = compass.getItemMeta();
        comMeta.setDisplayName(ChatColor.GOLD+ "Team selector");
        comMeta.addEnchant(Enchantment.MENDING,1,false);
        compass.setItemMeta(comMeta);
        return compass;
    }
    public static void teamMenu(HumanEntity p){
        Inventory inventory = Bukkit.createInventory(p,27, ChatColor.DARK_BLUE + "Choose your team: ");

        ItemStack item = new ItemStack(Material.YELLOW_WOOL, 1);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Yellow team");
        ArrayList<String> lore1 = new ArrayList<>();
        lore1.add(ChatColor.GRAY +"This will add you to the yellow team");
        itemMeta.setLore(lore1);
        item.setItemMeta(itemMeta);
        inventory.setItem(11, item);

        ItemStack item2 = new ItemStack(Material.BLUE_WOOL, 1);
        ItemMeta itemMeta2 = item2.getItemMeta();
        itemMeta2.setDisplayName(ChatColor.BLUE + "Blue team");
        ArrayList<String> lore2 = new ArrayList<>();
        lore2.add(ChatColor.GRAY +"This will add you to the blue team");
        itemMeta2.setLore(lore2);
        item2.setItemMeta(itemMeta2);
        inventory.setItem(12, item2);


        ItemStack item3 = new ItemStack(Material.GREEN_WOOL, 1);
        ItemMeta itemMeta3 = item3.getItemMeta();
        itemMeta3.setDisplayName(ChatColor.GREEN + "Green team");
        ArrayList<String> lore3 = new ArrayList<>();
        lore3.add(ChatColor.GRAY +"This will add you to the green team");
        itemMeta3.setLore(lore3);
        item3.setItemMeta(itemMeta3);
        inventory.setItem(14, item3);

        ItemStack item4 = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta itemMeta4 = item4.getItemMeta();
        itemMeta4.setDisplayName(ChatColor.RED + "Red team");
        ArrayList<String> lore4 = new ArrayList<>();
        lore4.add(ChatColor.GRAY +"This will add you to the red team");
        itemMeta4.setLore(lore4);
        item4.setItemMeta(itemMeta4);
        inventory.setItem(15, item4);
        p.openInventory(inventory);
    }
}
