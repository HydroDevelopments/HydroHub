package net.hydromc.clickEvents;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HubItemsClickEvent implements Listener {
    private final HHub plugin;
    public HubItemsClickEvent(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cocaine(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if (player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS) && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Server Selector") && (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))) {
            player.performCommand("servers");
            event.setCancelled(true);
        }
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
