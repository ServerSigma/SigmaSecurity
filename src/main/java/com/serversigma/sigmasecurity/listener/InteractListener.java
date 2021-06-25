package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class InteractListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {

        if (!event.getPlayer().hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(event.getPlayer())) return;

        event.setCancelled(true);

    }

}
