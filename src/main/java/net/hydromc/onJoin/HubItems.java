package net.hydromc.onJoin;

import com.sun.org.apache.xerces.internal.xs.StringList;
import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.ArrayList;
import java.util.List;

public class HubItems implements Listener {

    private final HHub plugin;
    public HubItems(HHub plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        plugin.getLogger().info("Something: " + ignoredWorld);
        items(e.getPlayer());
    }

    public void items(Player p) {
      List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world)) {
            ItemStack compass = new ItemStack(Material.COMPASS);
            //ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
            //ItemStack book = new ItemStack(Material.BOOK);

            ItemMeta compass_meta = compass.getItemMeta();
            compass_meta.setDisplayName(format("&3Server Selector"));
            ArrayList<String> compass_lore = new ArrayList();
            compass_lore.add(format("&dChoose A Server To Join!"));
            compass_meta.setLore(compass_lore);
            compass.setItemMeta(compass_meta);

            PlayerInventory playerInventory = p.getInventory();

            playerInventory.setItem(4, compass);
            //playerInventory.setItem(4, blaze);
            //playerInventory.setItem(7, book);
        } else {
            return;
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world) && !p.hasPermission("hhub.admin.items.bypass")) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onOffHandMove(PlayerSwapHandItemsEvent e) {
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world) && !p.hasPermission("hhub.admin.items.bypass")) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world) && !p.hasPermission("hhub.admin.items.bypass")) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onInvDrag(InventoryDragEvent e) {
        Player p = (Player) e.getWhoClicked();
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world) && !p.hasPermission("hhub.admin.items.bypass")) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onInvInteract(InventoryInteractEvent e) {
        Player p = (Player) e.getWhoClicked();
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world) && !p.hasPermission("hhub.admin.items.bypass")) {
            e.setCancelled(true);
        } else {
            return;
        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world)) {
            items(p);
        } else {
            return;
        }
    }

    @EventHandler
    public void onDeath(PlayerRespawnEvent e) {
        List<String> ignoredWorld = plugin.getConfig().getStringList("hubItemWorlds.ignored");
        Player p = e.getPlayer();
        String world = p.getWorld().getName();
        if(!ignoredWorld.contains(world)) {
            items(p);
        } else {
            return;
        }
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
