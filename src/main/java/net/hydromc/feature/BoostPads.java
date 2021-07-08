package net.hydromc.feature;

import net.hydromc.HHub;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.List;

public class BoostPads implements Listener {
    private final HHub plugin;
    public BoostPads(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWalkOver(PlayerMoveEvent event) {

        Player p = event.getPlayer();
        List<String> ignoredWorld = plugin.getConfig().getStringList("boostPadWorlds.allowed");
        String world = p.getWorld().getName();

        if(!ignoredWorld.contains(world)) {
            Vector v = p.getLocation().getDirection();

            int count = 1;

            if (p.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.EMERALD_BLOCK) {
                Vector direction = p.getLocation().getDirection().multiply(4);
                direction.setY(direction.getY() + 1);
                p.setVelocity(direction);

                p.getPlayer().playSound(p.getPlayer().getLocation(), Sound.ENTITY_CHICKEN_EGG, 4, 4);
            }
        }
    }

    @EventHandler
    public void onLand(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                e.setCancelled(true);
                p.setFallDistance(0.0f);
                e.setDamage(0.0);
            }
        }
    }

}
