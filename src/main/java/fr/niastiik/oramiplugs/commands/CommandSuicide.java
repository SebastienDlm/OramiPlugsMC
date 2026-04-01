package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSuicide implements CommandExecutor {
  private Main main;
  
  public CommandSuicide(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      player.setHealth(0.0);
      player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous êtes suicider.");
      return true;
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
      return true;
    } 
  }
}