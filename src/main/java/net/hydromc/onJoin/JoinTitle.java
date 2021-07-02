package net.hydromc.onJoin;

import me.clip.placeholderapi.PlaceholderAPI;
import net.hydromc.HHub;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinTitle implements Listener {

    private final HHub plugin;
    public JoinTitle(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        String title = plugin.getConfig().getString("welcomeTitle");
        title = PlaceholderAPI.setPlaceholders(player, title);

        String sub = plugin.getConfig().getString("welcomeSub");
        sub = PlaceholderAPI.setPlaceholders(player, sub);

        player.sendTitle(title, sub);
    }
}
