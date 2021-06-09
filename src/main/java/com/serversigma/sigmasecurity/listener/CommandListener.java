package com.serversigma.sigmasecurity.listener;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CommandListener implements Listener {

    private final LoginManager loginManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("sigmasecurity.use")) return;
        if (!loginManager.isAuthenticating(player)) return;

        String command = getFirstCommand(event.getMessage());
        List<String> allowedCommands = new ArrayList<String>() {{
            add("/ls");
            add("/sec");
            add("/login");
            add("/logar");
            add("/addls");
            add("/addsec");
            add("/register");
            add("/security");
            add("/registrar");
            add("/logarstaff");
            add("/loginstaff");
            add("/addlogarstaff");
            add("/addloginstaff");
        }};

        if (!loginManager.isAuthenticating(player)) {
            loginManager.startLogin(player, true);
        }

        for (String allowedCommand : allowedCommands) {
            if (command.startsWith(allowedCommand)) return;
        }
        event.setCancelled(true);
    }

    private String getFirstCommand(String message) {
        String[] splittedComamand = message.split(" ");
        return splittedComamand[0].toLowerCase();
    }

}