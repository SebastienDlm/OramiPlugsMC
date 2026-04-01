package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.events.EventFreeze;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFreeze implements CommandExecutor {

    private final Main main;

    public CommandFreeze(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        CommandSender staff = sender;

        if (args.length != 1) {
            staff.sendMessage(main.prefix + "§c/freeze <Player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            staff.sendMessage(main.prefix + "§cLe joueur n'est pas en ligne.");
            return true;
        }

        EventFreeze freezeManager = main.getEventFreeze();

        if (freezeManager.isFrozen(target)) {
            freezeManager.unfreezePlayer(target);
            sender.sendMessage(this.main.staff + "Tu as freeze §c" + target.getDisplayName() + "§e.");
        } else {
            freezeManager.freezePlayer(target);
            sender.sendMessage(this.main.staff + "Tu as libéré §c" + target.getDisplayName() + "§e.");
        }
        return true;
    }
}