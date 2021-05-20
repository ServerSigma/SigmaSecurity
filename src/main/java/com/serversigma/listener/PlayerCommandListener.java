package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public class PlayerCommandListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!loginManager.isLogged(player)) {
            if (!event.getMessage().contains("logar")) {

                player.sendMessage("§cLogue como staff primeiro.");
                event.setCancelled(true);
            }
        }
    }
}