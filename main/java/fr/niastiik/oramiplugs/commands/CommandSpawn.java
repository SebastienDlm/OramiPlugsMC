package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

    private Main main;

    public CommandSpawn(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;

        player.teleport(new Location(Bukkit.getWorld("world"), -303, 74, -768));
        player.sendMessage(this.main.prefix + "Vous avez été téléporté au spawn.");
        return true;
    }
}