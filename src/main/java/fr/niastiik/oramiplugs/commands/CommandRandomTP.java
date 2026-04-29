package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRandomTP implements CommandExecutor {

    private Main main;
    private final Random random = new Random();

    public CommandRandomTP(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();
        Location base = player.getLocation();

        Location loc = null;

        int tries = 0;
        int maxTries = 500;

        while (tries < maxTries) {

            tries++;

            int x = base.getBlockX() + random.nextInt(4000) - 2000;
            int z = base.getBlockZ() + random.nextInt(4000) - 2000;

            int y = world.getHighestBlockYAt(x, z);

            Material ground = world.getBlockAt(x, y, z).getType();
            Material above = world.getBlockAt(x, y + 1, z).getType();

            if (ground.isSolid() && above.isAir() && ground != Material.LAVA && ground != Material.WATER) {
                loc = new Location(world, x + 0.5, y + 1, z + 0.5);
                break;
            }
        }

        player.teleport(loc);
        player.sendMessage(this.main.prefix + "Téléporté en x: " + loc.getBlockX() + " y: " + loc.getBlockY() + " z: " + loc.getBlockZ());
        return true;
    }
}