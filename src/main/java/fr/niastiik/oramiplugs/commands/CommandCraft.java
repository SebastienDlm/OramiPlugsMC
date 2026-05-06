package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MenuType;

public class CommandCraft implements CommandExecutor {
  private Main main;
  
  public CommandCraft(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
        Player player = (Player)sender;
        player.openInventory(MenuType.CRAFTING.create(player, "Crafting"));
    } else {
        sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return true;
  }
}