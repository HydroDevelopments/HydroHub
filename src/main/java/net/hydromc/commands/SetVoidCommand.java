package net.hydromc.commands;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.hydromc.HHub.pluginPrefix;

public class SetVoidCommand implements CommandExecutor {
    private HHub plugin;
    public SetVoidCommand(HHub plugin){
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("setvoidtp")) {

            if (sender.hasPermission("hhub.admin.features.voidtp")) {
                if(args.length == 0) {
                    if(!plugin.getPosConfig().getBoolean("voidTPEnabled")) {
                        sender.sendMessage(format(pluginPrefix + "&eVoidTeleport isn't enabled."));
                        return true;
                    } else if(plugin.getPosConfig().getBoolean("voidTPEnabled")) {
                        sender.sendMessage(format(pluginPrefix + "&eYour VoidTeleport is set to Y: &f" + plugin.getPosConfig().getString("voidTPLocationY") + "&e. Use '&o/setvoidtp set&e' to change this."));
                        return true;
                    }
                } else if(args[0].equals("set")) {
                    if (!plugin.getPosConfig().getBoolean("voidTPEnabled")) {
                        sender.sendMessage(format(pluginPrefix + "&eVoidTeleport isn't enabled."));
                        return true;
                    } else if (plugin.getPosConfig().getBoolean("voidTPEnabled")) {
                        Player p = (Player) sender;
                        Location loc = p.getLocation();
                        plugin.getPosConfig().set("voidTPLocationY", loc.getBlockY());
                        plugin.savePosConfig();
                        plugin.reloadPosConfig();
                        sender.sendMessage(format(pluginPrefix + "&aVoidTeleport Set To Y: " + plugin.getPosConfig().getString("voidTPLocationY")));
                        return true;
                    }
                }
            }
        }

        return true;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
