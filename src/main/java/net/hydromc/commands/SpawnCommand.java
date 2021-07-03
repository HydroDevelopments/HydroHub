package net.hydromc.commands;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Objects;

import static net.hydromc.HHub.pluginPrefix;

public class SpawnCommand implements CommandExecutor, Listener {

    private HHub plugin;
    public SpawnCommand(HHub plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("spawn")) {

            if (sender.hasPermission("hhub.member.basic.commands.spawn")) {
                Player p = (Player) sender;

                p.sendMessage(format(pluginPrefix + "&cYou will be teleported to spawn in 5 seconds. You are allowed to move."));

                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                    @Override
                    public void run() {
                        p.teleport(p.getWorld().getSpawnLocation());
                    }

                }, 20*5);
            }
        }

        return true;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
