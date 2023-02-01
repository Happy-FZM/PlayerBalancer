package com.jaimemartz.playerbalanceraddon.task;

import com.jaimemartz.playerbalanceraddon.PlayerBalancerAddon;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class UpdatePlaceholder extends BukkitRunnable {

    private final PlayerBalancerAddon plugin;
    public static HashMap<String, Integer> sectionOnlinePlayers = new HashMap<>();

    public UpdatePlaceholder(PlayerBalancerAddon plugin) {
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 20L, 100L);
    }

    @Override
    public void run() {
        for (String section : sectionOnlinePlayers.keySet()) {
            this.plugin.getManager().getSectionPlayerCountTag(section, (result) -> {
                String[] dataStr = result.split(",");
                sectionOnlinePlayers.put(dataStr[0], Integer.valueOf(dataStr[1]));
            });
        }
    }

    public static int getSectionOnlinePlayers(String section) {
        if (!sectionOnlinePlayers.containsKey(section)) {
            sectionOnlinePlayers.put(section, 0);
        }
        return sectionOnlinePlayers.get(section);
    }

}
