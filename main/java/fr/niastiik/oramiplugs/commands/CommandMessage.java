package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMessage implements CommandExecutor {
  private Main main;
  
  public CommandMessage(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (args.length == 0 || args.length == 1) {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§c/msg <Player> <Message>.");
    } else if (Bukkit.getPlayer(args[0]) == null) {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
    } else {
      StringBuilder m = new StringBuilder();
      for (int i = 1; i < args.length; i++)
        m.append(String.valueOf(args[i]) + " ");
      Player pl = Bukkit.getPlayer(args[0]);
      if (sender.getName() != pl.getName()) {
        if (sender == Bukkit.getConsoleSender()) {
          sender.sendMessage("§7[§c"+ sender.getName() + "§7] ⮕ §7[§c" + pl.getName() + "§7] §f" + m.toString().replaceAll("&", "§"));
          pl.sendMessage("§7[§c"+ sender.getName() + "§7] ⮕ §7[§c" + pl.getDisplayName() + "§7] §f" + m.toString().replaceAll("&", "§"));
        } else {
          sender.sendMessage("§7[§c"+ sender.getName() + "§7] ⮕ §7[§c" + pl.getName() + "§7] §f" + m.toString().replaceAll("&", "§"));
          pl.sendMessage("§7[§c"+ sender.getName() + "§7] ⮕ §7[§c" + pl.getDisplayName() + "§7] §f" + m.toString().replaceAll("&", "§"));
        } 
      } else {
        sender.sendMessage(String.valueOf(this.main.prefix) + "Vous ne pouvez pas vous parler vous même");
      } 
    } 
    return true;
  }
}