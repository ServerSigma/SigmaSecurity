package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class QuitListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (!event.getPlayer().hasPermission("sigmasecurity.use")) return;
        loginManager.logoutPlayer(event.getPlayer());
    }

}
