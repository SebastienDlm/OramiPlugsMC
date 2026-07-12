package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.staff.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandVanish implements CommandExecutor {

    private final Main main;

    public CommandVanish(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(main.prefix + "§cSeuls les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;

        PlayerData data = main.getPlayerDataManager().get(player.getUniqueId());

        boolean newState = !data.isVanished();
        data.setVanished(newState);

        if (newState) {
            player.sendMessage(main.prefix + "§7Tu es maintenant invisible !");
        } else {
            player.sendMessage(main.prefix + "§aTu es maintenant visible !");
        }

        return true;
    }
}