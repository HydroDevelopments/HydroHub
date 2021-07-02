package net.hydromc.onJoin;

import me.clip.placeholderapi.PlaceholderAPI;
import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class JoinMessage implements Listener {

    private final HHub plugin;
    public JoinMessage(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(plugin.getConfig().getString("joinMsg") == null) {
            return;
        } else {
            Player player = e.getPlayer();

            String message = plugin.getConfig().getString("joinMsg");
            message = PlaceholderAPI.setPlaceholders(player, message);

            player.sendMessage(format(message));
        }
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
