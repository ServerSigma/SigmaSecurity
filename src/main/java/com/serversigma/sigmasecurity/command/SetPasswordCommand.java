package com.serversigma.sigmasecurity.command;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class SetPasswordCommand implements CommandExecutor {

    private final LoginManager loginManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            if (args.length != 2) {
                sender.sendMessage("§cUso correto /setsec <jogador> <senha>");
                return true;
            }

            String target = args[0];
            String password = args[1];

            if (loginManager.passwordIsInsecure(password)) {
                sender.sendMessage("");
                sender.sendMessage("§cA senha definida é muito fraca!");
                sender.sendMessage("§cA senha precisa conter pelo menus §7um §cnúmero.");
                sender.sendMessage("§cA senha precisa conter entre §7oito §ce §7vinte §ccaracteres.");
                sender.sendMessage("§cA senha precisa conter letras §7maiúsculas §ce §7minúsculas.");
                sender.sendMessage("");
                return true;
            }

            loginManager.setPassword(target, password);
            sender.sendMessage("§aVocê definiu a senha de §f" + target + "§a com sucesso.");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("sigmasecurity.use")) {
            player.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUso correto /setsec <senha>");
            return true;
        }

        String password = args[0];

        if (loginManager.passwordIsInsecure(password)) {
            player.sendMessage("");
            player.sendMessage("§cA senha definida é muito fraca!");
            player.sendMessage("§cA senha precisa conter pelo menus §7um §cnúmero.");
            player.sendMessage("§cA senha precisa conter entre §7oito §ce §7vinte §ccaracteres.");
            player.sendMessage("§cA senha precisa conter letras §7maiúsculas §ce §7minúsculas.");
            player.sendMessage("");
            return true;
        }

        loginManager.setPassword(player.getName(), password);
        player.sendMessage("§aSua senha foi alterada com sucesso!");
        return true;
    }

}
