package com.serversigma.runnable;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@RequiredArgsConstructor
public class LoginRunnable extends BukkitRunnable {

    int seconds = 61;
    private final Player player;
    private final LoginManager loginManager;

    @Override
    public void run() {

        if (!player.isOnline()) {
            this.cancel();
            return;
        }
        if (seconds > 0) {
            if (loginManager.isAuthenticated(player)) {
                this.cancel();
            } else {
                player.sendMessage("");
                player.sendMessage("§cVocê ainda não se autênticou como administrador.");
                player.sendMessage("§cPara se autênticar utilize: §7/sec <senha>");
                player.sendMessage("");
                player.sendMessage("§cTempo restante: §7" + seconds + " §csegundos.");
                player.sendMessage("");
                seconds--;
            }
        } else if (!loginManager.isAuthenticated(player)) {
            player.kickPlayer("§cVocê demorou muito para se autênticar.");
            this.cancel();
        }
    }

}