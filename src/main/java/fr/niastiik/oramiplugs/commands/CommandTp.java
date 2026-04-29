package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp implements CommandExecutor {

    private Main main;

    public CommandTp(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
            return true;
        }
        
        Player player = (Player) sender;

        if(args.length != 1){
            player.sendMessage(main.prefix + "§c/tp <Player>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(main.prefix + "§cLe joueur n'est pas en ligne.");
            return true;
        }
        
        Location targeLocation = target.getLocation();

        player.teleport(targeLocation);
        player.sendMessage(this.main.prefix + "Vous avez été téléporté sur §c" + target.getName() + " §e.");
        return true;
    }
}