package net.hydromc.feature;

import net.hydromc.HHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public class ScoreboardA implements Listener {
    private HHub plugin;
    public ScoreboardA(HHub plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void joinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team health = scoreboard.registerNewTeam("health");
        health.addEntry("Health: §b");
        health.setSuffix("");
        health.setPrefix("");
        objective.getScore("Health: §b").setScore(0);
        String title = format(plugin.getConfig().getString("objTitle"));
        char[] split = Objects.requireNonNull(title).toCharArray();

        new BukkitRunnable() {
            int counter = 0;
            String finaltitle = "";

            @Override
            public void run() {
                if (counter < split.length) {
                    String letter = String.valueOf(split[counter]);
                    counter += 1;
                    String space = "";
                    for (int i = 0; i < split.length - counter; i++) {
                        space += " ";
                    }

                    finaltitle +=   letter;
                    objective.setDisplayName(finaltitle);
                    health.setSuffix(counter + "");
                }else{
                    String space = "";
                    finaltitle = "";
                    for (int i = 0; i < split.length - counter; i++) {
                        space += " ";
                    }
                    objective.setDisplayName(space);
                    counter = 0;
                }
            }
        }.runTaskTimer(plugin, 0, 8);

        player.setScoreboard(scoreboard);
    }

    private String format(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }
}
