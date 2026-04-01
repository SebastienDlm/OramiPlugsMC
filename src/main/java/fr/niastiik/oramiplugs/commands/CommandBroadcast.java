package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBroadcast implements CommandExecutor {
  private Main main;
  
  public CommandBroadcast(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (args.length == 0)
      sender.sendMessage(String.valueOf(this.main.prefix) + "§c /broadcast <Message>."); 
    if (args.length >= 1) {
      StringBuilder bc = new StringBuilder();
      String[] arrayOfString;
      int j = (arrayOfString = args).length;
      for (int i = 0; i < j; i++) {
        String part = arrayOfString[i];
        bc.append(String.valueOf(part) + " ");
      } 
      Bukkit.broadcastMessage(String.valueOf(this.main.bc) + bc.toString().replaceAll("&", "§"));
    } 
    return true;
  }
}