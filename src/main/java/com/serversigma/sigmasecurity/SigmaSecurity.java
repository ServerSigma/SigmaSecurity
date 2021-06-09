package com.serversigma.sigmasecurity;

import com.serversigma.sigmasecurity.command.LoginCommand;
import com.serversigma.sigmasecurity.command.RegisterPasswordCommand;
import com.serversigma.sigmasecurity.command.SetPasswordCommand;
import com.serversigma.sigmasecurity.listener.*;
import com.serversigma.sigmasecurity.manager.LoginManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class SigmaSecurity extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();
        LoginManager loginManager = new LoginManager(this);

        registerListener(
                new MoveListener(loginManager),
                new QuitListener(loginManager),
                new DropListener(loginManager),
                new ChatListener(loginManager),
                new LoginListener(loginManager),
                new AuthenticatingListener(this),
                new DamageListener(loginManager),
                new CommandListener(loginManager),
                new InteractListener(loginManager),
                new RegisterListener(loginManager),
                new ItemHeldListener(loginManager),
                new TabCompleteListener(loginManager),
                new InventoryClickListener(loginManager),
                new GamemodeChangeListener(loginManager)
        );

        getCommand("security").setExecutor(new LoginCommand(loginManager));
        getCommand("setsecurity").setExecutor(new SetPasswordCommand(loginManager));
        getCommand("addsecurity").setExecutor(new RegisterPasswordCommand(loginManager));
    }

    private void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

}