package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNight implements CommandExecutor {
  private Main main;
  
  public CommandNight(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      player.getWorld().setTime(13000L);
      player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez mis la nuit.");
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return true;
  }
}