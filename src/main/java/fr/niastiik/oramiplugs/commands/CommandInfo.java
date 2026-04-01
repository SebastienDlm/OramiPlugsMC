package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInfo implements CommandExecutor{

    private Main main;
  
  public CommandInfo(Main main) {
    this.main = main;
  }

   public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (args.length == 0)
      sender.sendMessage(String.valueOf(this.main.prefix) + "§c /broadcast <Player>."); 
    if (args.length == 1) {
      Player pl = Bukkit.getPlayer(args[0]);
      if (Bukkit.getPlayer(args[0]) == null) {
        sender.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
      } else {
        sender.sendMessage("" + this.main.info + "");
        sender.sendMessage("      §eUUID : " + pl.getUniqueId());
        sender.sendMessage("      §ePseudo : " + pl.getName());
        sender.sendMessage("      §egrade : ");
        sender.sendMessage("      §eReport : ");
        sender.sendMessage("      §eLocalisation: Monde: " + pl.getLocation().getWorld().getName() + 
            ", X: " + pl.getLocation().getBlockX() + ", Y: " + pl.getLocation().getBlockY() + 
            " et Z: " + pl.getLocation().getBlockZ());
        sender.sendMessage("      §eGamemode : " + pl.getGameMode());
        sender.sendMessage("      §eFly : " + pl.getAllowFlight());
        sender.sendMessage("      §eVie : " + pl.getHealth());
        sender.sendMessage("      §eKills : " + pl.getStatistic(Statistic.PLAYER_KILLS));
        sender.sendMessage("      §eMorts : " + pl.getStatistic(Statistic.DEATHS));
        sender.sendMessage("" + this.main.info + "");
      } 
    } 
    return false;
  }
    
}
