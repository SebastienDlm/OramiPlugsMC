package fr.niastiik.oramiplugs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.niastiik.oramiplugs.Main;

public class CommandRain implements CommandExecutor{

    private Main main;
  
    public CommandRain(Main main) {
        this.main = main;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.getWorld().setStorm(true);
            player.getWorld().setThundering(false);
            player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez invoqué la pluie.");
        } else {
            sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
        } 
        return true;
    }
}