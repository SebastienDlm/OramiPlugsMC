package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.staff.PlayerManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStaff implements CommandExecutor {

    private final Main main;

    public CommandStaff(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(main.prefix + "§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;

        if (PlayerManager.isInModerationMod(player)) {
            PlayerManager.getFromPlayer(player).destroy();
            player.sendMessage(main.staff + "Vous n'êtes plus dans le mode modérateur.");
        } else {
            new PlayerManager(player).init();
            player.sendMessage(main.staff + "Vous êtes maintenant en mode modérateur.");
        }

        return true;
    }
}