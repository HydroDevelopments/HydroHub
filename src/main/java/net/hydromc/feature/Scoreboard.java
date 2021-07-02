package net.hydromc.feature;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;

public class Scoreboard implements Listener {
    private HHub plugin;
    public Scoreboard(HHub plugin){
        this.plugin = plugin;
    }



    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
