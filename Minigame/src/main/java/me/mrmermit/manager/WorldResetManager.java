package me.mrmermit.manager;

import me.mrmermit.Minigame;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Random;

public class WorldResetManager {
    private GameManager gameManager;
    private Minigame plugin;
    public WorldResetManager(GameManager gameManager, Minigame plugin){
        this.gameManager = gameManager;
        this.plugin = plugin;
    }

    public void deleteAndCreateWorld() {
        String worldName = "game_world";
        World world = Bukkit.getWorld(worldName);


        if (world != null) {
            // Unload the world if it is loaded
            if (world.getPlayers().size() == 0) {
                Bukkit.unloadWorld(world, false);
            } else {
                for (Player p : world.getPlayers()){
                    if(plugin.getConfig().getLocation("spawn")!=null){
                        p.teleport(plugin.getConfig().getLocation("spawn"));
                    }
                }
                Bukkit.unloadWorld(world, false);
            }

            // Delete the world folder
            File worldFolder = world.getWorldFolder();
            if (deleteWorldFolder(worldFolder)) {
                plugin.getLogger().info("World folder deleted successfully.");
            } else {
                plugin.getLogger().warning("Failed to delete the world folder.");
            }
        }
    }
    private boolean isNotWaterBiome(Biome biome) {
        return !biome.equals(Biome.OCEAN) &&
                !biome.equals(Biome.DEEP_OCEAN) &&
                !biome.equals(Biome.COLD_OCEAN) &&
                !biome.equals(Biome.DEEP_COLD_OCEAN) &&
                !biome.equals(Biome.FROZEN_OCEAN) &&
                !biome.equals(Biome.DEEP_FROZEN_OCEAN) &&
                !biome.equals(Biome.DEEP_LUKEWARM_OCEAN) &&
                !biome.equals(Biome.ICE_SPIKES) &&
                !biome.equals(Biome.FROZEN_PEAKS) &&
                !biome.equals(Biome.BEACH) &&
                !biome.equals(Biome.FROZEN_RIVER) &&
                !biome.equals(Biome.SNOWY_BEACH) &&
                !biome.equals(Biome.SNOWY_PLAINS) &&
                !biome.equals(Biome.SNOWY_SLOPES) &&
                !biome.equals(Biome.SNOWY_TAIGA) &&
                !biome.equals(Biome.RIVER) &&
                !biome.equals(Biome.SWAMP) &&
                !biome.equals(Biome.FROZEN_PEAKS) &&
                !biome.equals(Biome.WARM_OCEAN) &&
                !biome.equals(Biome.LUKEWARM_OCEAN);
    }
    public void reset_loc(){
        World world = Bukkit.getWorld("game_world");
        plugin.getConfig().set("PlayersForGame" ,plugin.getConfig().getInt("PlayersForGame"));
        plugin.getConfig().set("TeamA" ,plugin.getConfig().getInt("TeamA"));
        plugin.getConfig().set("TeamB" ,plugin.getConfig().getInt("TeamB"));
        plugin.getConfig().set("TeamC" ,plugin.getConfig().getInt("TeamC"));
        plugin.getConfig().set("TeamD" ,plugin.getConfig().getInt("TeamD"));
        plugin.getConfig().set("StartFazeTime" ,plugin.getConfig().getInt("StartFazeTime"));
        plugin.getConfig().set("FirstGameFazeTime" ,plugin.getConfig().getInt("FirstGameFazeTime"));
        plugin.getConfig().set("BorderSize" ,plugin.getConfig().getInt("BorderSize"));
        plugin.getConfig().set("RocketTimer" ,plugin.getConfig().getInt("RocketTimer"));
        plugin.getConfig().set("Spawn", plugin.getConfig().getLocation("Spawn"));
        plugin.getConfig().set("Spawn", plugin.getConfig().getLocation("spawn"));
        Random random = new Random();
        KK:
        while(true) {

            int x_set = random.nextInt(50000001) - 25000000;
            int z_set = random.nextInt(50000001) - 25000000;

            Biome chunkBiome = world.getBlockAt(x_set , 0 , z_set).getBiome();
            Biome chunkBiome1 = world.getBlockAt(x_set- (plugin.getConfig().getInt("BorderSize")/2) , 0 , z_set-(plugin.getConfig().getInt("BorderSize")/2)).getBiome();
            Biome chunkBiome2 = world.getBlockAt(x_set+(plugin.getConfig().getInt("BorderSize")/2) , 0 , z_set-(plugin.getConfig().getInt("BorderSize")/2)).getBiome();
            Biome chunkBiome3 = world.getBlockAt(x_set-(plugin.getConfig().getInt("BorderSize")/2) , 0 , z_set+(plugin.getConfig().getInt("BorderSize")/2)).getBiome();
            Biome chunkBiome4 = world.getBlockAt(x_set+(plugin.getConfig().getInt("BorderSize")/2) , 0 , z_set+(plugin.getConfig().getInt("BorderSize")/2)).getBiome();
            if (isNotWaterBiome(chunkBiome) && isNotWaterBiome(chunkBiome1) && isNotWaterBiome(chunkBiome2) && isNotWaterBiome(chunkBiome3) && isNotWaterBiome(chunkBiome4)) {
                Location biomeLocation = new Location(world, x_set, 0, z_set);
                biomeLocation.setY(world.getHighestBlockYAt(biomeLocation) + 30);
                plugin.getConfig().set("BorderCenter", biomeLocation);
                plugin.getConfig().set("SpawnSpec", new Location(world, x_set, world.getHighestBlockYAt(biomeLocation) + 30, z_set));
                System.out.println("Border center: " + plugin.getConfig().getLocation("BorderCenter"));

                world.getWorldBorder().setCenter(biomeLocation);
                world.getWorldBorder().setSize(plugin.getConfig().getInt("BorderSize"));
                plugin.getConfig().set("BorderCenter", biomeLocation);
                System.out.println("Found biome " + chunkBiome.toString() + " at location: " + biomeLocation);
                int size = (int) plugin.getConfig().getInt("BorderSize")/2;
                System.out.println("Size: " + size);

                Location l1 = new Location(world, x_set, -64, z_set);
                l1.setX(biomeLocation.getX() - size + 10);
                l1.setZ(biomeLocation.getZ() - size + 10);
                plugin.getConfig().set("TeamASpawn", l1);
                System.out.println(l1);
                Location l2 = new Location(world, x_set, -64, z_set);
                l2.setX(biomeLocation.getX() + size - 10);
                l2.setZ(biomeLocation.getZ() - size + 10);
                plugin.getConfig().set("TeamBSpawn", l2);
                System.out.println(l2);
                Location l3 = new Location(world, x_set, -64, z_set);
                l3.setX(biomeLocation.getX() - size + 10);
                l3.setZ(biomeLocation.getZ() + size - 10);
                plugin.getConfig().set("TeamCSpawn", l3);
                System.out.println(l3);
                Location l4 = new Location(world, x_set, -64, z_set);
                l4.setX(biomeLocation.getX() + size - 10);
                l4.setZ(biomeLocation.getZ() + size - 10);
                plugin.getConfig().set("TeamDSpawn", l4);
                System.out.println(l4);
                break KK;
            }
        }
        plugin.saveConfig();
        StateManager.status = 1;
        StateManager.game = -1;
    }

    private boolean deleteWorldFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteWorldFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        return folder.delete();
    }
}
