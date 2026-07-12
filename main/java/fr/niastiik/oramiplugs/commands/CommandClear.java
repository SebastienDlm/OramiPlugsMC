package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClear implements CommandExecutor {
  private Main main;
  
  public CommandClear(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if (args.length == 0) {
        player.getInventory().clear();
        player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez supprimer votre inventaire.");
      } 
      if (args.length == 1) {
        Player pl = Bukkit.getPlayer(args[0]);
        if (Bukkit.getPlayer(args[0]) == null) {
          player.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
        } else if (player.getName() != pl.getName()) {
          pl.getInventory().clear();
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez supprimer l'Inventaire de §c" + pl.getName() + "§e.");
        } else {
          player.getInventory().clear();
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous avez supprimer votre inventaire.");
        } 
      } 
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return true;
  }
}