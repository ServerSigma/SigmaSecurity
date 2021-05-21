package com.serversigma.sigmasecurity.manager;

import com.serversigma.sigmasecurity.event.PlayerAuthenticatedEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LoginManager {

    private final Plugin plugin;
    private final List<Player> authenticated = new ArrayList<>();

    public boolean isAuthenticated(Player player) {
        return authenticated.contains(player);
    }

    public void loginPlayer(Player player) {
        if (authenticated.contains(player)) return;
        authenticated.add(player);
        plugin.getServer().getPluginManager().callEvent(new PlayerAuthenticatedEvent(player));
    }

    public boolean hasAccount(Player player) {
        return plugin.getConfig().getString("passwords." + player.getName().toLowerCase()) != null;
    }

    public String getPassword(Player player) {
        return plugin.getConfig().getString("passwords." + player.getName().toLowerCase());
    }

    public void setPassword(String target, String password) {
        plugin.getConfig().set("passwords." + target.toLowerCase(), password);
        plugin.saveConfig();
        plugin.reloadConfig();
    }

    public boolean passwordIsInsecure(String password) {
        return !password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$");
    }

}