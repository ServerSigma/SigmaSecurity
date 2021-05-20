package com.serversigma.listener;

import com.serversigma.events.PlayerAuthenticatedEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class PlayerAuthenticatingListener implements Listener {

    private final Plugin plugin;

    @EventHandler
    public void onAuthenticating(PlayerAuthenticatedEvent event) {

        Player player  = event.getPlayer();

        player.setFlySpeed(0.F);
        player.setWalkSpeed(0.2F);
        player.removePotionEffect(PotionEffectType.BLINDNESS);

        plugin.getLogger().info(player.getName() + " se autênticou como administrador.");
    }

}