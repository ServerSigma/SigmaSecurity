package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class PlayerInteractListener implements Listener {

    private final LoginManager loginManager;

    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        //if (loginManager.isAuthenticated(player)) return;

        event.setCancelled(true);
    }

}