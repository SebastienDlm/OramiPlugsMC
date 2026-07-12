package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKick implements CommandExecutor {
  private Main main;
  
  public CommandKick(Main main) {
    this.main = main;
  }
  
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (args.length == 0 || args.length == 1) {
        sender.sendMessage(String.valueOf(this.main.prefix) + "§c/kick <Player> <Message>.");
        } else if (Bukkit.getPlayer(args[0]) == null) {
        sender.sendMessage(String.valueOf(this.main.prefix) + "§cLe joueur est introuvable.");
        } else {
            Player pl = Bukkit.getPlayer(args[0]);
            StringBuilder m = new StringBuilder();
            for (int i = 1; i < args.length; i++)
                m.append(String.valueOf(args[i]) + " ");
            pl.kickPlayer(this.main.punition + "Tu as été Kick par §4" + sender.getName() + " §cpour §4§l\n"+ m.toString().replaceAll("&", "§"));
            sender.sendMessage(this.main.staff + "Tu as Kick §4" + pl.getDisplayName() + " §cpour §4§l" + m.toString().replaceAll("&", "§"));
        } 
        return true;
    }
}