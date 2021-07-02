package net.hydromc.feature;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Objects;

public class MOTDMessage implements Listener {

    private HHub plugin;
    public MOTDMessage(HHub plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void countDown(final ServerListPingEvent event){

        if(!plugin.getConfig().getBoolean("motdOn")) {

            return;

        } else if(plugin.getConfig().getBoolean("motdOn")) {

            if (format(plugin.getConfig().getString("motdMsg")).contains("&")) {

                event.setMotd(format(plugin.getConfig().getString("motdMsg")));

            } else {

                event.setMotd(format(plugin.getConfig().getString("motdMsg")));

            }
        }
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}