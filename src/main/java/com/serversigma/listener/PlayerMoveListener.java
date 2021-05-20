package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public class PlayerMoveListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(player)) return;

        double distance = event.getTo().distance(event.getFrom());
        if (distance <= 0 || event.isCancelled()) return;

        player.teleport(event.getFrom());
    }

}