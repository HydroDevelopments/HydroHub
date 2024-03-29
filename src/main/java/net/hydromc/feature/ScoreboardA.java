package net.hydromc.feature;

import me.clip.placeholderapi.PlaceholderAPI;
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

        // String + PAPI Intergration!
        String disName = format("Name: &e%player_name%");
        disName = PlaceholderAPI.setPlaceholders(player, disName);

        String server = format("Server:");
        server = PlaceholderAPI.setPlaceholders(player, server);

        String serverName = format("%hhub_servername%");
        serverName = PlaceholderAPI.setPlaceholders(player, serverName);


        String space1 = " ";
        String space2 = "";
        String space3 = "&r";

        ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("Test", "Dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score1 = objective.getScore(space1);
        score1.setScore(6);

        Score score2 = objective.getScore(disName);
        score2.setScore(5);

        Score score3 = objective.getScore(space2);
        score3.setScore(4);

        Score score4 = objective.getScore(server);
        score4.setScore(3);

        Score score5 = objective.getScore(serverName);
        score5.setScore(2);

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
