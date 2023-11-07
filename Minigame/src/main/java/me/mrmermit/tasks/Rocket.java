package me.mrmermit.tasks;

import me.mrmermit.Minigame;
import me.mrmermit.manager.BeaconManager;
import me.mrmermit.manager.GameManager;
import me.mrmermit.manager.StateManager;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Rocket extends BukkitRunnable {
    private GameManager gameManager;
    private Minigame plugin;
    private int time = 0;
    private BeaconManager beaconManager;
    private Glowing_stop glowingStop;


    public Rocket(GameManager gameManager, int time, Minigame plugin) {
        this.plugin = plugin;
        this.gameManager = gameManager;
        this.time = time;
    }
    private int counter= 0;

    @Override
    public void run() {
        if(!(StateManager.status == 4 &&StateManager.game == 2)){
            cancel();
        }else{
            counter++;
            if(counter%time ==0){
                if(BeaconManager.sTeam1 != null){;
                    Location l = BeaconManager.sTeam1;
                    l.setY(l.getWorld().getHighestBlockYAt(l)+15);
                    Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    fwm.setPower(2);
                    fwm.addEffect(FireworkEffect.builder().withColor(Color.BLUE).flicker(true).build());

                    fw.setFireworkMeta(fwm);
                    fw.detonate();

                    Location l2 = BeaconManager.sTeam1;
                    l2.setY(l2.getWorld().getHighestBlockYAt(l2)+30);
                    Firework fw2 = (Firework) l2.getWorld().spawnEntity(l2, EntityType.FIREWORK);
                    FireworkMeta fwm2 = fw2.getFireworkMeta();

                    fwm2.setPower(2);
                    fwm2.addEffect(FireworkEffect.builder().withColor(Color.BLUE).flicker(true).build());

                    fw2.setFireworkMeta(fwm2);
                    fw2.detonate();

                    Location l3 = BeaconManager.sTeam1;
                    l2.setY(l3.getWorld().getHighestBlockYAt(l3)+50);
                    Firework fw3 = (Firework) l3.getWorld().spawnEntity(l3, EntityType.FIREWORK);
                    FireworkMeta fwm3 = fw3.getFireworkMeta();

                    fwm3.setPower(2);
                    fwm3.addEffect(FireworkEffect.builder().withColor(Color.BLUE).flicker(true).build());

                    fw3.setFireworkMeta(fwm3);
                    fw3.detonate();

                }
                if(BeaconManager.sTeam2 != null){
                    Location l = BeaconManager.sTeam2;
                    l.setY(l.getWorld().getHighestBlockYAt(l)+15);
                    Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    fwm.setPower(2);
                    fwm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());

                    fw.setFireworkMeta(fwm);
                    fw.detonate();

                    Location l2 = BeaconManager.sTeam2;
                    l2.setY(l2.getWorld().getHighestBlockYAt(l2)+30);
                    Firework fw2 = (Firework) l2.getWorld().spawnEntity(l2, EntityType.FIREWORK);
                    FireworkMeta fwm2 = fw2.getFireworkMeta();

                    fwm2.setPower(2);
                    fwm2.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());

                    fw2.setFireworkMeta(fwm2);
                    fw2.detonate();

                    Location l3 = BeaconManager.sTeam2;
                    l2.setY(l3.getWorld().getHighestBlockYAt(l3)+50);
                    Firework fw3 = (Firework) l3.getWorld().spawnEntity(l3, EntityType.FIREWORK);
                    FireworkMeta fwm3 = fw3.getFireworkMeta();

                    fwm3.setPower(2);
                    fwm3.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).flicker(true).build());

                    fw3.setFireworkMeta(fwm3);
                    fw3.detonate();
                }
                if(BeaconManager.sTeam3 != null){
                    Location l = BeaconManager.sTeam3;
                    l.setY(l.getWorld().getHighestBlockYAt(l)+15);
                    Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    fwm.setPower(2);
                    fwm.addEffect(FireworkEffect.builder().withColor(Color.GREEN).flicker(true).build());

                    fw.setFireworkMeta(fwm);
                    fw.detonate();

                    Location l2 = BeaconManager.sTeam3;
                    l2.setY(l2.getWorld().getHighestBlockYAt(l2)+30);
                    Firework fw2 = (Firework) l2.getWorld().spawnEntity(l2, EntityType.FIREWORK);
                    FireworkMeta fwm2 = fw2.getFireworkMeta();

                    fwm2.setPower(2);
                    fwm2.addEffect(FireworkEffect.builder().withColor(Color.GREEN).flicker(true).build());

                    fw2.setFireworkMeta(fwm2);
                    fw2.detonate();

                    Location l3 = BeaconManager.sTeam3;
                    l2.setY(l3.getWorld().getHighestBlockYAt(l3)+50);
                    Firework fw3 = (Firework) l3.getWorld().spawnEntity(l3, EntityType.FIREWORK);
                    FireworkMeta fwm3 = fw3.getFireworkMeta();

                    fwm3.setPower(2);
                    fwm3.addEffect(FireworkEffect.builder().withColor(Color.GREEN).flicker(true).build());

                    fw3.setFireworkMeta(fwm3);
                    fw3.detonate();
                }
                if(BeaconManager.sTeam4 != null){
                    Location l = BeaconManager.sTeam4;
                    l.setY(l.getWorld().getHighestBlockYAt(l)+15);
                    Firework fw = (Firework) l.getWorld().spawnEntity(l, EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    fwm.setPower(2);
                    fwm.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

                    fw.setFireworkMeta(fwm);
                    fw.detonate();

                    Location l2 = BeaconManager.sTeam4;
                    l2.setY(l2.getWorld().getHighestBlockYAt(l2)+30);
                    Firework fw2 = (Firework) l2.getWorld().spawnEntity(l2, EntityType.FIREWORK);
                    FireworkMeta fwm2 = fw2.getFireworkMeta();

                    fwm2.setPower(2);
                    fwm2.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

                    fw2.setFireworkMeta(fwm2);
                    fw2.detonate();

                    Location l3 = BeaconManager.sTeam4;
                    l2.setY(l3.getWorld().getHighestBlockYAt(l3)+50);
                    Firework fw3 = (Firework) l3.getWorld().spawnEntity(l3, EntityType.FIREWORK);
                    FireworkMeta fwm3 = fw3.getFireworkMeta();

                    fwm3.setPower(2);
                    fwm3.addEffect(FireworkEffect.builder().withColor(Color.RED).flicker(true).build());

                    fw3.setFireworkMeta(fwm3);
                    fw3.detonate();
                }

            }
            if(counter%60 ==0) {
                for(Player p : Bukkit.getOnlinePlayers()){
                    if(p.getGameMode() == GameMode.SURVIVAL){
                        PotionEffect glowingEffect = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, 0, false, false);
                        p.addPotionEffect(glowingEffect);
                        p.setGlowing(true);
                        this.glowingStop = new Glowing_stop(this);
                        this.glowingStop.runTaskTimer(plugin,0,20);
                    }
                }
            }
        }
    }
}
