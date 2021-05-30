package com.serversigma.sigmasecurity.command;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class LoginCommand implements CommandExecutor {

    private final LoginManager loginManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§cVocê não pode executar esse comando.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("sigmasecurity.use")) {
            player.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
            return true;
        }

        if (loginManager.isAuthenticated(player)) {
            player.sendMessage("§aVocê já está logado como administrador.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUso incorreto, utilize §7/sec <senha>§c.");
            return true;
        }

        String password = args[0];

        if  (!loginManager.getPassword(player).equals(password)) {
            player.kickPlayer("§cSenha de autênticação incorreta.");
            return true;
        } else {
            loginManager.loginPlayer(player);
            player.sendMessage("");
            player.sendMessage(" §6§l          SigmaSecurity");
            player.sendMessage("");
            player.sendMessage(" §eSeja bem-vindo(a) novamente, §f" + player.getName() + "§e!");
            player.sendMessage(" §eVocê já pode utilizar seus comandos e permissões.");
            player.sendMessage(" §ePara falar no canal staff, utilize §f/sc <mensagem>");
            player.sendMessage(" §ePara alterar sua senha admin utilize §f/setsec <senha>");
            player.sendMessage("");
        }
        return true;
    }

}