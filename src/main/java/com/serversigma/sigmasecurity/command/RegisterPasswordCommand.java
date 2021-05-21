package com.serversigma.sigmasecurity.command;

import com.serversigma.sigmasecurity.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class RegisterPasswordCommand implements CommandExecutor {

    private final LoginManager loginManager;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("§cQuer ter uma senha, bobinho?");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("sigmasecurity.use")) {
            player.sendMessage("§cVocê não tem permissão para utilizar esse comando.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUso incorreto, utilize §7/addsec <senha>");
            return true;
        }

        if (loginManager.hasAccount(player)) {
            player.sendMessage("§cVocê já tem uma senha definida.");
            player.sendMessage("§cCaso queira redefinir sua senha, utilize §7/setsec <senha>§c.");
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
        player.kickPlayer("§eSenha definida com sucesso. \n§eNunca compartilhe sua senha com ninguém!");
        return true;
    }

}