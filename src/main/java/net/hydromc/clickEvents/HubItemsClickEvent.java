package net.hydromc.clickEvents;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class HubItemsClickEvent implements Listener {
    private final HHub plugin;
    public HubItemsClickEvent(HHub plugin) {
        this.plugin = plugin;
    }



    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
