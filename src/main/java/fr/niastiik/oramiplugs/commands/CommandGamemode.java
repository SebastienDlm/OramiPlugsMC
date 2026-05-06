package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;

import org.bukkit.Bukkit;
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
      if (args.length == 0){
        player.sendMessage(String.valueOf(this.main.prefix) + "§c/gamemode <Mode> (Player)."); 
      }else if (args.length == 1){
        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
          player.setGameMode(GameMode.SURVIVAL);
          player.sendMessage(this.main.prefix + "Vous vous êtes mis en gamemode survie.");
        } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
          player.setGameMode(GameMode.CREATIVE);
          player.sendMessage(this.main.prefix + "Vous vous êtes mis en gamemode creatif.");
        } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
          player.setGameMode(GameMode.ADVENTURE);
          player.sendMessage(this.main.prefix + "Vous vous êtes mis en gamemode aventure.");
        } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")) {
          player.setGameMode(GameMode.SPECTATOR);
          player.sendMessage(this.main.prefix + "Vous vous êtes mis en gamemode spectateur.");
        }  
      }else if (args.length == 2){
        Player pl = Bukkit.getPlayer(args[1]);
        if (Bukkit.getPlayer(args[1]) == null) {
          player.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
        } else {
        if (args[0].equalsIgnoreCase("survival") || args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
            pl.setGameMode(GameMode.SURVIVAL);
            pl.sendMessage(this.main.prefix + "Vous avez été mis en gamemode survie.");
            player.sendMessage(this.main.prefix + "Vous avez mis §c" + pl.getName() + " §een gamemode survie.");
        } else if (args[0].equalsIgnoreCase("creative") || args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
            pl.setGameMode(GameMode.CREATIVE);
            pl.sendMessage(this.main.prefix + "Vous avez été mis en gamemode creatif.");
            player.sendMessage(this.main.prefix + "Vous avez mis §c" + pl.getName() + " §een gamemode creatif.");
        } else if (args[0].equalsIgnoreCase("adventure") || args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
            pl.setGameMode(GameMode.ADVENTURE);
            pl.sendMessage(this.main.prefix + "Vous avez été mis en gamemode aventure.");
            player.sendMessage(this.main.prefix + "Vous avez mis §c" + pl.getName() + " §een gamemode aventure.");
          } else if (args[0].equalsIgnoreCase("spectator") || args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("sp")) {
            pl.setGameMode(GameMode.SPECTATOR);
            pl.sendMessage(this.main.prefix + "Vous avez été mis en gamemode spectateur.");
            player.sendMessage(this.main.prefix + "Vous avez mis §c" + pl.getName() + " §een gamemode spectateur.");
          }
        }
      }
    } else {
      sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
    } 
    return true;
  }
}