package com.serversigma.command;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class LoginCommand implements CommandExecutor {

    private final LoginManager loginManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (!player.hasPermission("sigmaloginstaff.use")) return false;

        if (loginManager.isLogged(player)) {
            player.sendMessage("§aVocê já está logado.");
            return false;
        }

        if (args.length == 1) {
            if (args[0].equals(loginManager.getPassword(player))) {
                player.sendMessage("§aVocê logou.");
                loginManager.loginPlayer(player);
                return true;
            } else {
                player.sendMessage("§cSenha inválida.");
                return false;
            }
        } else {
            player.sendMessage("§c/logarstaff <senha>");
            return false;
        }
    }
}