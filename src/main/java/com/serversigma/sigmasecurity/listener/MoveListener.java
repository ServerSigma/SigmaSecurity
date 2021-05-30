package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@RequiredArgsConstructor
public class MoveListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        if (!event.getPlayer().hasPermission("sigmasecurity.use")) return;

        Player player = event.getPlayer();
        if (loginManager.isAuthenticated(player)) return;

        double distance = event.getTo().distance(event.getFrom());
        if (distance <= 0 || event.isCancelled()) return;

        player.teleport(event.getFrom());

        loginManager.startLogin(player);
    }

}