package cn.codetector.minecraftplugins.gamemode4.betterFire;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EventListener implements Listener {
    JavaPlugin plugin;
    public EventListener(JavaPlugin plugin){
        super();
        this.plugin = plugin;
    }
    @EventHandler
    public void arrowHitBlock(ProjectileHitEvent e){
        if (e.getEntity() instanceof Arrow){
            final Arrow arrow = (Arrow)e.getEntity();
            if(arrow.getFireTicks()>0){
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
                    public void run() {
                        if(arrow.isOnGround() && arrow.getLocation().getBlock().getType() == Material.AIR){
                            arrow.getLocation().getBlock().setType(Material.FIRE);
                        }
                    }
                },2);
            }
        }
    }

    @EventHandler
    public void arrowHitCreeper(EntityDamageByEntityEvent event){
        if((event.getDamager() instanceof Arrow)&&(event.getEntity() instanceof Creeper)){
            Arrow arrow = (Arrow)event.getDamager();
            if(arrow.getFireTicks()>0){
                Creeper creeper = (Creeper)event.getEntity();
                creeper.setFireTicks(-1);
                creeper.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,50,1));
                creeper.getWorld().createExplosion(creeper.getLocation(),3,false);
                creeper.setHealth(0);
            }
        }
    }
}
