package fr.niastiik.oramiplugs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.niastiik.oramiplugs.Main;

public class CommandSun implements CommandExecutor{

    private Main main;
  
    public CommandSun(Main main) {
        this.main = main;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getWorld().setStorm(false);
            player.getWorld().setThundering(false);
            player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez invoqué un beau soleil.");
        } else {
            sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
        } 
        return false;
    }
}