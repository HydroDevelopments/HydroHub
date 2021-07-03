package net.hydromc.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.*;
import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.hydromc.HHub.noPermission;
import static net.hydromc.HHub.pluginPrefix;

public class ServerSelectorGUI implements CommandExecutor {
    private final HHub plugin;
    public ServerSelectorGUI(HHub plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        String permission = "hhub.member.basic.gui.joinServers";

        // This is the main gui
        Gui sGui = new Gui(3, format("&8Server Selector")); sGui.setDefaultClickAction(event -> { event.setCancelled(true); });

        // Gui Items are MADE here!

        GuiItem s1 = ItemBuilder.from(Material.ENDER_EYE).setName(format("&bSky&9Wars")).asGuiItem(event -> {
            Player p = (Player) event.getWhoClicked();
            if(p.hasPermission(permission)) {
                sGui.close(p);
                p.sendMessage(format(pluginPrefix + ("&eYou will soon be sent to a SkyWars server.")));
            } else {
                sGui.close(p);
                p.sendMessage(format(noPermission));
            }
        });

        GuiItem s2 = ItemBuilder.from(Material.RED_BED).setName(format("&4Bed&fWars")).asGuiItem(event -> {
            Player p = (Player) event.getWhoClicked();
            if(p.hasPermission(permission)) {
                sGui.close(p);
                p.sendMessage(format(pluginPrefix + "&eYou will soon be sent to a BedWars server."));
            } else {
                sGui.close(p);
                p.sendMessage(format(noPermission));
            }
        });

        GuiItem s3 = ItemBuilder.from(Material.WOODEN_SWORD).setName(format("&eKit&cPVP")).asGuiItem(event -> {
            Player p = (Player) event.getWhoClicked();
            if(p.hasPermission(permission)) {
                sGui.close(p);
                p.sendMessage(format(pluginPrefix + "&eYou will soon be sent to the KitPVP server."));
            } else {
                sGui.close(p);
                p.sendMessage(format(noPermission));
            }
        });


        // Set GUI Items Here

        sGui.setItem(2, 3, s1);
        sGui.setItem(2, 5, s2);
        sGui.setItem(2, 7, s3);


            // Actual Command Execution Handling.
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("hhub.member.basic.servers")) {
                    if (cmd.getName().equalsIgnoreCase("servers")) {
                        sGui.open(player);
                        return true;
                    }
                }
            sender.sendMessage(format(noPermission));
        }

        return true;
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
