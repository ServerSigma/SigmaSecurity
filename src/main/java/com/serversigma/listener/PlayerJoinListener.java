package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import com.serversigma.runnable.LoginRunnable;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class PlayerJoinListener implements Listener {

    private final Plugin plugin;
    private final LoginManager loginManager;

    @EventHandler
    public void onJoin(PlayerLoginEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (loginManager.isAuthenticated(player)) return;

        player.setFlySpeed(0.0F);
        player.setWalkSpeed(0.0F);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 600, 100));

        new LoginRunnable(player, loginManager).runTaskTimer(plugin, 20, 20);
    }

}
