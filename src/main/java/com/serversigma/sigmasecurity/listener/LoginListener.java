package com.serversigma.sigmasecurity.listener;

import com.nickuc.login.api.events.AuthenticateEvent;
import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class LoginListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler
    public void onLogin(AuthenticateEvent event) {

        if (!event.getPlayer().hasPermission("sigmasecurity.use")) return;

        Player player = event.getPlayer();

        player.setFlySpeed(0.0F);
        player.setWalkSpeed(0.0F);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 6000, 100));

        loginManager.startLogin(player, false);

    }

}
