package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {
  private Main main;
  
  public CommandFly(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0)
        if (!player.getAllowFlight()) {
          player.setAllowFlight(true);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous pouvez voler.");
        } else {
          player.setAllowFlight(false);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous ne pouvez plus voler.");
        }  
      if (args.length == 1) {
        Player pl = Bukkit.getPlayer(args[0]);
        if (Bukkit.getPlayer(args[0]) == null) {
          player.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
        } else if (player.getName() != pl.getName()) {
          if (!pl.getAllowFlight()) {
            pl.setAllowFlight(true);
            pl.sendMessage(String.valueOf(this.main.prefix) + "Vous pouvez voler.");
            player.sendMessage(String.valueOf(this.main.prefix) + "§c" + pl.getName() + " §epeut désormais de voler.");
          } else {
            pl.setAllowFlight(false);
            pl.sendMessage(String.valueOf(this.main.prefix) + "Vous ne pouvez plus volez.");
            player.sendMessage(String.valueOf(this.main.prefix) + "§c" + pl.getName() + " §ene peut désormais plus voler.");
          } 
        } else if (!player.getAllowFlight()) {
          player.setAllowFlight(true);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous pouvez voler.");
        } else {
          player.setAllowFlight(false);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous ne pouvez plus voler.");
        }  
      } 
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return false;
  }
}
