package com.jaimemartz.playerbalanceraddon;

import com.jaimemartz.playerbalanceraddon.task.UpdatePlaceholder;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerBalancerPlaceholderExpansion extends PlaceholderExpansion {
    private final Map<String, Integer> sectionPlayerCounts = new LinkedHashMap<>();
    private final PlayerBalancerAddon plugin;

    public PlayerBalancerPlaceholderExpansion(PlayerBalancerAddon plugin) {
        this.plugin = plugin;
        new UpdatePlaceholder(plugin);
    }

    @Override
    public String getIdentifier() {
        return "pb";
    }

    @Override
    public String getAuthor() {
        return "Happy_FZM";
    }

    @Override
    public String getVersion() {
        return "bundled";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {

        if (identifier.startsWith("pc")) {
            String section = identifier.split("pc_")[1];
            String[] sections = section.split(",");
            int players = 0;
            // 第一次返回 0
            // 主类中异步刷新获取服务器的人数在线
            for (String section1 : sections) {
                players += UpdatePlaceholder.getSectionOnlinePlayers(section1);
            }

            return String.valueOf(players);
        }

        return null;
    }

}
