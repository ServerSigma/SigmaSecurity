package com.serversigma.command;

import com.serversigma.manager.LoginManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
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

        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("§cSomente o console pode executar esse comando.");
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage("§c/setls [player] [password]");
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer off-line");
            return false;
        }
        loginManager.setPassword(sender, target, args[1]);
        return false;
    }
}
