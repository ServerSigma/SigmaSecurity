package com.serversigma;

import com.serversigma.command.LoginCommand;
import com.serversigma.command.SetPasswordCommand;
import com.serversigma.listener.PlayerCommandListener;
import com.serversigma.listener.PlayerDropItemListener;
import com.serversigma.listener.PlayerLoginListener;
import com.serversigma.listener.PlayerMoveListener;
import com.serversigma.manager.LoginManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SigmaLoginStaff extends JavaPlugin {

    @Override
    public void onEnable() {

        LoginManager loginManager = new LoginManager(this);
        saveDefaultConfig();

        registerListener(
                new PlayerDropItemListener(loginManager),
                new PlayerLoginListener(loginManager, this),
                new PlayerCommandListener(loginManager),
                new PlayerMoveListener(loginManager)
        );

        getCommand("logarstaff").setExecutor(new LoginCommand(loginManager));
        getCommand("setls").setExecutor(new SetPasswordCommand(loginManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListener(Listener... listeners) {
        for(Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }
}
