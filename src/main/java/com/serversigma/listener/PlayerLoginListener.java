package com.serversigma.listener;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class PlayerLoginListener implements Listener {

    private final LoginManager loginManager;
    private final Plugin plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(PlayerLoginEvent event) {

        if (event.getPlayer().hasPermission("sigmaloginstaff.use")) {
            Player player = event.getPlayer();
            player.sendMessage("§cVocê tem 30 segundos para logar como staff!");
            new BukkitRunnable() {

                @Override
                public void run() {
                        if(loginManager.isLogged(player)) {
                            System.out.println(loginManager.isLogged(player));
                            cancel();
                        } else {
                            player.kickPlayer("§cVocê não logou e foi expulso!");
                        }
                }
            }.runTaskLater(plugin, 20 * 30);
        }
    }
}
