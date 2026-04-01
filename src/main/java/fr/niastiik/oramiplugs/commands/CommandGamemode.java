package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGamemode implements CommandExecutor {
  private Main main;
  
  public CommandGamemode(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0)
        player.sendMessage(String.valueOf(this.main.prefix) + "§c/gamemode <Mode> (Player)."); 
      if (args.length == 1)
        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0")) {
          player.setGameMode(GameMode.SURVIVAL);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous mis en gamemode survie.");
        } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1")) {
          player.setGameMode(GameMode.CREATIVE);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous mis en gamemode creatif.");
        } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2")) {
          player.setGameMode(GameMode.ADVENTURE);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous mis en gamemode aventure.");
        } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3")) {
          player.setGameMode(GameMode.SPECTATOR);
          player.sendMessage(String.valueOf(this.main.prefix) + "Vous vous mis en gamemode spectateur.");
        }  
      if (args.length == 2)
        player.sendMessage(String.valueOf(this.main.maintenance) + "EN CREATION !"); 
    } else {
      sender.sendMessage(String.valueOf(this.main.prefix) + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return false;
  }
}
