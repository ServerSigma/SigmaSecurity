package com.serversigma.sigmasecurity.listener;

import com.nickuc.login.api.events.RegisterEvent;
import com.serversigma.sigmasecurity.manager.LoginManager;
import com.serversigma.sigmasecurity.runnable.LoginRunnable;
import com.serversigma.sigmasecurity.runnable.RegisterRunnable;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class RegisterListener implements Listener {

    private final Plugin plugin;
    private final LoginManager loginManager;

    @EventHandler
    public void onRegister(RegisterEvent event) {

        if (!event.getPlayer().hasPermission("sigmasecurity.use")) return;

        Player player = event.getPlayer();

        player.setFlySpeed(0.0F);
        player.setWalkSpeed(0.0F);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 6000, 100));

        if (!loginManager.hasAccount(player)) {
            new RegisterRunnable(player, loginManager).runTaskTimer(plugin, 20, 20);
            return;
        }

        if (!loginManager.isAuthenticated(player)) {
            new LoginRunnable(player, loginManager).runTaskTimer(plugin, 20, 20);
        }
    }

}
