package fr.niastiik.oramiplugs.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.niastiik.oramiplugs.Main;

public class CommandFeed implements CommandExecutor {
  private Main main;
  
  public CommandFeed(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (args.length == 0) {
        player.setFoodLevel(20);
        player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous êtes nourrit.");
      } 
      if (args.length == 1) {
        Player pl = Bukkit.getPlayer(args[0]);
        if (Bukkit.getPlayer(args[0]) == null) {
          player.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
        } else if (player.getName() != pl.getName()) {
          pl.setFoodLevel(20);
          pl.sendMessage(String.valueOf(this.main.prefix) + "Vous avez été nourrit.");
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez nourrit §c" + pl.getName() + ".");
        } else {
          player.setFoodLevel(20);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous êtes nourrit.");
        } 
      } 
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return true;
  }
}