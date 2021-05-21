package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class CommandListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(player)) return;

        String command = event.getMessage().toLowerCase();

        if (loginManager.hasAccount(player)) {
            for (String loginCommand : loginManager.getLoginCommands()) {
                if (command.startsWith(loginCommand)) return;
            }
        } else {
            for (String registerCommand : loginManager.getRegisterCommands()) {
                if (command.startsWith(registerCommand)) return;
            }
        }
        event.setCancelled(true);
    }

}