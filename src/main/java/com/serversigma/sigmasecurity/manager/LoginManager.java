package com.serversigma.sigmasecurity.manager;

import com.nickuc.login.api.nLoginAPI;
import com.serversigma.sigmasecurity.event.PlayerAuthenticatedEvent;
import com.serversigma.sigmasecurity.runnable.LoginRunnable;
import com.serversigma.sigmasecurity.runnable.RegisterRunnable;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LoginManager {

    private final Plugin plugin;
    private final List<Player> authenticated = new ArrayList<>();
    private final List<Player> authenticating = new ArrayList<>();

    public boolean isAuthenticated(Player player) {
        return authenticated.contains(player);
    }

    public void loginPlayer(Player player) {
        setAuthenticated(player, true);
        setAuthenticating(player, false);
    }

    public void startLogin(Player player, boolean verifyLogin) {
        if (isAuthenticated(player) || isAuthenticating(player)) return;

        // Recommended turn false if event is handled from LoginEvent|RegisterEvent
        if (verifyLogin) {
            if (!nLoginAPI.getApi().isAuthenticated(player.getName())
                    || !nLoginAPI.getApi().isRegistered(player)) return;
        }

        if (hasAccount(player)) {
            new LoginRunnable(player, this).runTaskTimer(plugin, 20, 20);
        } else {
            new RegisterRunnable(player, this).runTaskTimer(plugin, 20, 20);
        }
        setAuthenticating(player, true);
    }

    public void setAuthenticated(Player player, boolean logged) {
        if (logged && !isAuthenticated(player)) {
            authenticated.add(player);
        } else {
            authenticated.remove(player);
        }
        plugin.getServer().getPluginManager().callEvent(new PlayerAuthenticatedEvent(player, logged));
    }

    public void setAuthenticating(Player player, boolean mode) {
        if (mode && !isAuthenticating(player)) {
            authenticating.add(player);
        } else {
            authenticating.remove(player);
        }
    }

    public boolean isAuthenticating(Player player) {
        return authenticating.contains(player);
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

    public void logoutPlayer(Player player) {
        setAuthenticating(player, false);
        setAuthenticated(player, false);
    }

}