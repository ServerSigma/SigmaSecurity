package com.serversigma.manager;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LoginManager {

    private final List<Player> staff = new ArrayList<>();
    private final Plugin plugin;

    public boolean isLogged(Player player) {
        return staff.contains(player);
    }

    public void loginPlayer(Player player) {
        if (staff.contains(player)) return;
        staff.add(player);
    }

    public String getPassword(Player player) {
        return plugin.getConfig().getString("password." + player.getName());
    }

    public void setPassword(CommandSender sender,  Player player, String password) {
            plugin.getConfig().set("password." + player.getName(), password);
            plugin.saveConfig();
    }
}
