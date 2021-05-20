package com.serversigma;

import com.serversigma.command.LoginCommand;
import com.serversigma.command.SetPasswordCommand;
import com.serversigma.listener.*;
import com.serversigma.manager.LoginManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SigmaSecurity extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();
        LoginManager loginManager = new LoginManager(this);

        registerListener(
                new PlayerMoveListener(loginManager),
                new PlayerAuthenticatingListener(this),
                new PlayerCommandListener(loginManager),
                new PlayerInteractListener(loginManager),
                new PlayerDropItemListener(loginManager),
                new PlayerJoinListener(this, loginManager)
        );

        getCommand("security").setExecutor(new LoginCommand(loginManager));
        getCommand("setsecurity").setExecutor(new SetPasswordCommand(this, loginManager));
    }

    private void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

}