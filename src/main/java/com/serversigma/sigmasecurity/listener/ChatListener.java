package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@RequiredArgsConstructor
public class ChatListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onSend(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(player)) return;

        event.setMessage(null);
        event.setCancelled(true);

        loginManager.startLogin(player, true);
    }

}
