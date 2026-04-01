package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class CommandInventory implements CommandExecutor {
  private Main main;
  
  public CommandInventory(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0)
        player.sendMessage(String.valueOf(this.main.prefix) + "§c/inventory <Player>."); 
      if (args.length == 1) {
        Player pl = Bukkit.getPlayer(args[0]);
        if (Bukkit.getPlayer(args[0]) == null) {
          player.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
        } else {
          PlayerInventory playerInventory = pl.getInventory();
          player.openInventory((Inventory)playerInventory);
        } 
      } 
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return false;
  }
}