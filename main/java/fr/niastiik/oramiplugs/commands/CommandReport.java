package fr.niastiik.oramiplugs.commands;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CommandReport implements CommandExecutor {
  private Main main;
  
  public CommandReport(Main main) {
    this.main = main;
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0)
        player.sendMessage(this.main.prefix + "§c/report <Player>.<Player>."); 
      if (args.length == 1) {
        Player pl = Bukkit.getPlayer(args[0]);
        if (Bukkit.getPlayer(args[0]) == null) {
          player.sendMessage(this.main.prefix + "§cLe joueur est introuvable.");
        } else {
          Inventory inv = Bukkit.createInventory(null, 18, this.main.prefix + pl.getDisplayName());
          inv.setItem(0, (new ItemBuilder(Material.DIAMOND_SWORD)).setName("§cKillAura").toItemStack());
          inv.setItem(1, (new ItemBuilder(Material.BOW)).setName("§cBowAibot").toItemStack());
          inv.setItem(2, (new ItemBuilder(Material.DIAMOND_ORE)).setName("§cXRay").toItemStack());
          inv.setItem(3, (new ItemBuilder(Material.FEATHER)).setName("§cFly").toItemStack());
          player.openInventory(inv);
        } 
      } 
    } else {
      sender.sendMessage(this.main.prefix + "§cSeulles joueurs peuvent utiliser cette commande.");
    }
    return true;
  }
}