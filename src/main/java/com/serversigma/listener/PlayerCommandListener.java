package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class PlayerCommandListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("sigmasecurity.use")) return;
        //if (loginManager.isAuthenticated(player)) return;

        String command  = event.getMessage();

        //for (String allowedCommand : loginManager.getAllowedCommands()) {
        //    if (allowedCommand.startsWith(command.toLowerCase())) return;
        //}
        event.setCancelled(true);
    }

}