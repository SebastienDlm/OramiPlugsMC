package fr.niastiik.oramiplugs.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import fr.niastiik.oramiplugs.Main;

public class CommandRepair implements CommandExecutor {

    private final Main main;

    public CommandRepair(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item == null || item.getType() == Material.AIR) {
                player.sendMessage(this.main.prefix + "§cVous devez tenir un objet à réparer.");
                return true;
            }

            ItemMeta meta = item.getItemMeta();
            if (!(meta instanceof Damageable)) {
                player.sendMessage(this.main.prefix + "§cCet objet ne peut pas être réparé.");
                return true;
            } else {
                Damageable dmg = (Damageable) meta;
                dmg.setDamage(0);
                item.setItemMeta((ItemMeta) dmg);
                player.getInventory().setItemInMainHand(item);
                player.sendMessage(this.main.prefix + "§eTu as réparé ton objet.");
                return true;
            }
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("all")) {
            ItemStack[] contents = player.getInventory().getContents();
            for (int i = 0; i < contents.length; i++) {
                ItemStack it = contents[i];
                if (it == null) continue;
                ItemMeta meta = it.getItemMeta();
                if (meta instanceof Damageable) {
                    Damageable d = (Damageable) meta;
                    if (d.getDamage() > 0) {
                        d.setDamage(0);
                        it.setItemMeta((ItemMeta) d);
                    }
                }
            }

            ItemStack helmet = player.getInventory().getHelmet();
            if (helmet != null) {
                ItemMeta m = helmet.getItemMeta();
                if (m instanceof Damageable) {
                    Damageable d = (Damageable) m;
                    if (d.getDamage() > 0) {
                        d.setDamage(0);
                        helmet.setItemMeta((ItemMeta) d);
                        player.getInventory().setHelmet(helmet);
                    }
                }
            }
            ItemStack chest = player.getInventory().getChestplate();
            if (chest != null) {
                ItemMeta m = chest.getItemMeta();
                if (m instanceof Damageable) {
                    Damageable d = (Damageable) m;
                    if (d.getDamage() > 0) {
                        d.setDamage(0);
                        chest.setItemMeta((ItemMeta) d);
                        player.getInventory().setChestplate(chest);
                    }
                }
            }
            ItemStack legs = player.getInventory().getLeggings();
            if (legs != null) {
                ItemMeta m = legs.getItemMeta();
                if (m instanceof Damageable) {
                    Damageable d = (Damageable) m;
                    if (d.getDamage() > 0) {
                        d.setDamage(0);
                        legs.setItemMeta((ItemMeta) d);
                        player.getInventory().setLeggings(legs);
                    }
                }
            }

            ItemStack boots = player.getInventory().getBoots();
            if (boots != null) {
                ItemMeta m = boots.getItemMeta();
                if (m instanceof Damageable) {
                    Damageable d = (Damageable) m;
                    if (d.getDamage() > 0) {
                        d.setDamage(0);
                        boots.setItemMeta((ItemMeta) d);
                        player.getInventory().setBoots(boots);
                    }
                }
            }

            player.sendMessage(this.main.prefix + "§eTu as réparé tout tes objets.");
            return true;
        }
        return true;
    }
}