package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGod implements CommandExecutor {
  private Main main;
  
  public CommandGod(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0){
        player.sendMessage(this.main.maintenance + "Fonctionnalité en développement.");
      }
    } else {
      sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return true;
  }
}