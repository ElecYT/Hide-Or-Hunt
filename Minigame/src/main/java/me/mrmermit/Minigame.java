package me.mrmermit;

import me.mrmermit.commands.*;
import me.mrmermit.event.*;
import me.mrmermit.manager.BeaconManager;
import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import me.mrmermit.manager.WorldResetManager;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

public final class Minigame extends JavaPlugin {
    private GameManager gameManager;
    private WorldResetManager worldResetManager;




    @Override
    public void onEnable() {
        super.onEnable();

        this.gameManager = new GameManager(this);
        this.worldResetManager = new WorldResetManager(gameManager, this);
        System.out.println("Server běží");

//        worldResetManager.createMapBackup();
//        worldResetManager.wordlReset();
        worldResetManager.deleteAndCreateWorld();
        WorldCreator worldCreator = new WorldCreator("game_world");
        World w = Bukkit.getWorld("game_world");
        if (w==null) {
            worldCreator.createWorld();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", true);
        }

        getServer().getWorlds().forEach(world -> {
            world.setTime(6000);
            world.setClearWeatherDuration(110000);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        });

        worldResetManager.reset_loc();
//        disableBeaconRecipe();
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this),this);
        getServer().getPluginManager().registerEvents(new TeamMenu(),this);
        getServer().getPluginManager().registerEvents(new PlLobbyMoveListener(),this);
        getServer().getPluginManager().registerEvents(new GameManager(this),this);
        getServer().getPluginManager().registerEvents(new DieEvent(this),this);
        getServer().getPluginManager().registerEvents(new HpEventy(this),this);
        getServer().getPluginManager().registerEvents(new BeaconManager(this, gameManager),this);

        getCommand("reloadconfig").setExecutor(new ConfigReloader(this));
        getCommand("tpworld").setExecutor(new TpCommand(this));
        getCommand("setspawn").setExecutor(new SpawnSetCommand(this));
        StateManager.status = 1;

    }
    @Override
    public void onDisable() {
        // Save config on plugin disable
        worldResetManager.deleteAndCreateWorld();
        saveConfig();
    }

}
