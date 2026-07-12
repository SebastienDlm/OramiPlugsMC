package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKill implements CommandExecutor {
  private Main main;
  
  public CommandKill(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (args.length == 0)
      sender.sendMessage(String.valueOf(this.main.prefix) + "§c/kill <Player>."); 
    if (args.length == 1) {
      Player pl = Bukkit.getPlayer(args[0]);
      if (Bukkit.getPlayer(args[0]) == null) {
        sender.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
      } else if (sender.getName() != pl.getName()) {
        pl.setHealth(0.0D);
        sender.sendMessage(String.valueOf(this.main.prefix) + "Vous avez tuer §c" + pl.getName() + "§e.");
      } else if (sender instanceof Player) {
        Player player = (Player) sender;
        player.setHealth(0.0D);
        player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous êtes suicider.");
      } 
    } 
    return true;
  }
}
