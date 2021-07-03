package net.hydromc.feature;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static net.hydromc.HHub.pluginPrefix;

public class VoidTeleport implements Listener {
    private HHub plugin;
    public VoidTeleport(HHub plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onTrigger(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        Location loc = p.getLocation();

        if(loc.getBlockY() <= plugin.getPosConfig().getInt("voidTPLocationY")) {

            p.teleport(p.getWorld().getSpawnLocation());

            p.sendMessage(format(pluginPrefix + "You travelled too far, and have been brought back to spawn."));
        }
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
