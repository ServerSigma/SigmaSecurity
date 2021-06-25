package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

@RequiredArgsConstructor
public class InventoryClickListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(player)) return;

        event.setCancelled(true);

    }

}
