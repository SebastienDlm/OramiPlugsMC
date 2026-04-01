package fr.niastiik.oramiplugs.staff;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.events.EventFreeze;
import fr.niastiik.oramiplugs.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModItemsInteract implements Listener {

    private final Main main;
    private final Random random = new Random();

    public ModItemsInteract(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();
        if (!PlayerManager.isInModerationMod(player)) return;
        if (!(e.getRightClicked() instanceof Player)) return;

        Player target = (Player) e.getRightClicked();
        e.setCancelled(true);

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand == null) return;

        switch (mainHand.getType()) {

            case CHEST:
                openPlayerInventory(player, target);
                break;

            case BOOK:
                showPlayerInfo(player, target);
                break;

            case PACKED_ICE:
                toggleFreeze(player, target);
                break;

            case BLAZE_ROD:
                target.setHealth(0);
                player.sendMessage(main.staff + "Vous avez éliminé §c" + target.getDisplayName() + "§e.");
                break;

            default:
                break;
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (!PlayerManager.isInModerationMod(player)) return;

        if (e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR)
            return;

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        if (mainHand == null) return;

        switch (mainHand.getType()) {

            case COMPASS:
                teleportRandomPlayer(player);
                break;

            case BLAZE_POWDER:
                toggleVanish(player);
                break;

            default:
                break;
        }
    }

    private void openPlayerInventory(Player player, Player target) {
        Inventory inv = Bukkit.createInventory(null, 45, "§eInventaire de §c" + target.getName() + "§e.");
        for (int i = 0; i < 36; i++) {
            ItemStack item = target.getInventory().getItem(i);
            if (item != null) inv.setItem(i, item);
        }
        inv.setItem(36, target.getInventory().getHelmet());
        inv.setItem(37, target.getInventory().getChestplate());
        inv.setItem(38, target.getInventory().getLeggings());
        inv.setItem(39, target.getInventory().getBoots());

        player.openInventory(inv);
    }

    private void showPlayerInfo(Player player, Player target) {
        Inventory info = Bukkit.createInventory(null, 45, "§eInformations de §c" + target.getName() + "§e.");

        ItemBuilder uuid = new ItemBuilder(Material.IRON_INGOT)
                .setName("§eUUID :")
                .setLore("§e" + target.getUniqueId());

        ItemBuilder pseudo = new ItemBuilder(Material.DIAMOND)
                .setName("§ePseudo :")
                .setLore("§e" + target.getName());

        ItemBuilder grade = new ItemBuilder(Material.EMERALD)
                .setName("§eGrade :")
                .setLore("§eNon défini");

        ItemBuilder report = new ItemBuilder(Material.PAPER)
                .setName("§eReport :")
                .setLore("§eAucun");

        ItemBuilder gamemode = new ItemBuilder(Material.GOLD_INGOT)
                .setName("§eGamemode :")
                .setLore("§e" + target.getGameMode());

        ItemBuilder fly = new ItemBuilder(Material.FEATHER)
                .setName("§eFly :")
                .setLore("§e" + target.getAllowFlight());

        ItemBuilder vie = new ItemBuilder(Material.GOLDEN_APPLE)
                .setName("§eVie :")
                .setLore("§e" + target.getHealth());

        ItemBuilder morts = new ItemBuilder(Material.DIAMOND_CHESTPLATE)
                .setName("§eMorts :")
                .setLore("§e" + target.getStatistic(Statistic.DEATHS));

        ItemBuilder kills = new ItemBuilder(Material.DIAMOND_SWORD)
                .setName("§eKills :")
                .setLore("§e" + target.getStatistic(Statistic.PLAYER_KILLS));

        info.setItem(0, uuid.toItemStack());
        info.setItem(1, pseudo.toItemStack());
        info.setItem(2, grade.toItemStack());
        info.setItem(3, report.toItemStack());
        info.setItem(5, gamemode.toItemStack());
        info.setItem(6, fly.toItemStack());
        info.setItem(7, vie.toItemStack());
        info.setItem(8, morts.toItemStack());
        info.setItem(9, kills.toItemStack());

        player.openInventory(info);
    }

    private void toggleFreeze(Player staff, Player target) {
        EventFreeze freezeManager = Main.getInstance().getEventFreeze();

        if (freezeManager.isFrozen(target)) {
            freezeManager.unfreezePlayer(target);
            staff.sendMessage(main.staff + "Vous avez libéré §c" + target.getDisplayName() + "§e.");
            target.sendMessage(main.punition + "Vous avez été libéré par " + staff.getName() + "§e.");
        } else {
            freezeManager.freezePlayer(target);
            staff.sendMessage(main.staff + "Vous avez freeze §c" + target.getDisplayName() + "§e.");
            target.sendMessage(main.punition + "Vous avez été freeze par " + staff.getName() + "§e.");
        }
    }

    private void teleportRandomPlayer(Player player) {
        List<Player> list = new ArrayList<>(Bukkit.getOnlinePlayers());
        list.remove(player);

        if (list.isEmpty()) {
            player.sendMessage(main.staff + "§cIl n'y a aucun autre joueur en ligne.");
            return;
        }

        Player target = list.get(random.nextInt(list.size()));
        player.teleport(target.getLocation());
        player.sendMessage(main.staff + "Vous avez été téléporté à §c" + target.getDisplayName() + "§e.");
    }

    private void toggleVanish(Player player) {
        PlayerData data = main.getPlayerDataManager().get(player.getUniqueId());
        boolean newState = !data.isVanished();
        data.setVanished(newState);

        Bukkit.getOnlinePlayers().forEach(p -> {
            if (newState) p.hidePlayer(main, player);
            else p.showPlayer(main, player);
        });

        player.sendMessage(newState
                ? main.staff + "§7Vous devenez invisible !"
                : main.staff + "§aVous devenez visible !");
    }
}