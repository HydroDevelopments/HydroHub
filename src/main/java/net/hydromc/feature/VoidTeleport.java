package net.hydromc.feature;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
        Player player = e.getPlayer();

        Location location = player.getLocation();


        if(location.getBlockY() <= plugin.getPosConfig().getInt("voidTPLocationY")) {
            player.teleport(player.getWorld().getSpawnLocation());

            player.sendMessage(format(pluginPrefix + "You fell too far from the lobby. You have been brought back."));
        }
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
