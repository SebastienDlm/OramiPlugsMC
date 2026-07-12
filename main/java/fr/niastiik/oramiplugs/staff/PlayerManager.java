package fr.niastiik.oramiplugs.staff;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {

    private final Player player;
    private final ItemStack[] savedInventory = new ItemStack[40];

    public PlayerManager(Player player) {
        this.player = player;
    }

    public void init() {
        Main main = Main.getInstance();
        main.getPlayers().put(player.getUniqueId(), this);
        main.getModo().add(player.getUniqueId());

        saveInventory();

        player.setAllowFlight(true);
        player.setFlying(true);

        ItemStack[] modItems = new ItemStack[]{
                new ItemBuilder(Material.STICK).setName("§eKB Tester")
                        .setLore("§7Click gauche sur un joueur", "§7pour tester son recul.")
                        .addUnsafeEnchantment(Enchantment.KNOCKBACK, 5).toItemStack(),

                new ItemBuilder(Material.BLAZE_ROD).setName("§eTHE KILLER")
                        .setLore("§7Click droit sur un joueur", "§7pour le tuer.").toItemStack(),

                new ItemBuilder(Material.PACKED_ICE).setName("§eFreeze")
                        .setLore("§7Click droit sur un joueur", "§7pour le freeze.").toItemStack(),

                new ItemBuilder(Material.BOOK).setName("§eInformations")
                        .setLore("§7Click droit sur un joueur", "§7pour voir ses informations.").toItemStack(),

                new ItemBuilder(Material.PAPER).setName("§eSignalements")
                        .setLore("§7Click droit sur un joueur", "§7pour voir ses signalements.").toItemStack(),

                new ItemBuilder(Material.CHEST).setName("§eInventaire")
                        .setLore("§7Click droit sur un joueur", "§7pour voir son inventaire.").toItemStack(),

                null,

                new ItemBuilder(Material.BLAZE_POWDER).setName("§eVanish")
                        .setLore("§7Click droit pour activer/désactiver", "§7le vanish.").toItemStack(),

                new ItemBuilder(Material.COMPASS).setName("§eTP Aléatoire")
                        .setLore("§7Click droit pour se téléporter", "§7aléatoirement à un joueur.").toItemStack()
        };

        for (int i = 0; i < modItems.length; i++) {
            player.getInventory().setItem(i, modItems[i]);
        }
    }

    public void destroy() {
        Main main = Main.getInstance();
        main.getPlayers().remove(player.getUniqueId());
        main.getModo().remove(player.getUniqueId());

        player.getInventory().clear();
        restoreInventory();

        player.setAllowFlight(false);
        player.setFlying(false);
    }

    public static boolean isInModerationMod(Player player) {
        return Main.getInstance().getModo().contains(player.getUniqueId());
    }

    public static PlayerManager getFromPlayer(Player player) {
        return Main.getInstance().getPlayers().get(player.getUniqueId());
    }

    public void saveInventory() {
        for (int i = 0; i < 36; i++) {
            savedInventory[i] = player.getInventory().getItem(i);
        }
        savedInventory[36] = player.getInventory().getHelmet();
        savedInventory[37] = player.getInventory().getChestplate();
        savedInventory[38] = player.getInventory().getLeggings();
        savedInventory[39] = player.getInventory().getBoots();

        player.getInventory().clear();
    }

    public void restoreInventory() {
        for (int i = 0; i < 36; i++) {
            if (savedInventory[i] != null) player.getInventory().setItem(i, savedInventory[i]);
        }
        player.getInventory().setHelmet(savedInventory[36]);
        player.getInventory().setChestplate(savedInventory[37]);
        player.getInventory().setLeggings(savedInventory[38]);
        player.getInventory().setBoots(savedInventory[39]);
    }

    public ItemStack[] getSavedInventory() {
        return savedInventory;
    }
}