package com.serversigma.sigmasecurity.runnable;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class RegisterRunnable extends BukkitRunnable {

    int seconds = 30;
    private final Player player;
    private final LoginManager loginManager;

    @Override
    public void run() {

        if (!player.isOnline()) {
            this.cancel();
            return;
        }

        if (seconds > 0) {
            if (loginManager.hasAccount(player)) {
                this.cancel();
            } else {
                Location location = player.getLocation();

                if (location.getYaw() != 0 || location.getPitch() != 0) {
                    location.setPitch(0);
                    location.setYaw(0);
                    player.teleport(location);
                }
                if (seconds % 5 == 0) {
                    player.sendMessage("");
                    player.sendMessage("§cVocê ainda não se registrou como administrador.");
                    player.sendMessage("§cPara se autênticar utilize: §7/addsec <senha>");
                    player.sendMessage("");
                    player.sendMessage("§cTempo restante: §7" + seconds + " §csegundos.");
                    player.sendMessage("");
                }
            }
        } else if (!loginManager.hasAccount(player)) {
            player.kickPlayer("§cVocê demorou muito para se autênticar.");
            this.cancel();
        }
        seconds--;
    }

}