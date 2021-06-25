package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

@RequiredArgsConstructor
public class TabCompleteListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onTabComplete(PlayerChatTabCompleteEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(player)) return;

        event.getTabCompletions().clear();

    }

}
