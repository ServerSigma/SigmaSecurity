package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

@RequiredArgsConstructor
public class DamageListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        if (!event.getEntity().hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(((Player) event.getEntity()))) return;

        event.setCancelled(true);
    }

}
