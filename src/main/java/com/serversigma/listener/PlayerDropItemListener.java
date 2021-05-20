package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

@RequiredArgsConstructor
public class PlayerDropItemListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("sigmaloginstaff.use") && !loginManager.isLogged(event.getPlayer())) {
            event.setCancelled(true);
        }
    }
}
