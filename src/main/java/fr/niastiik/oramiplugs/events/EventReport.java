package fr.niastiik.oramiplugs.events;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventReport implements Listener {

    private final Main main;

    public EventReport(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack item = e.getCurrentItem();
        if (item == null) return;

        ItemMeta meta = item.getItemMeta();
        if (meta == null || meta.getDisplayName() == null) return;

        Player player = (Player) e.getWhoClicked();
        String displayName = meta.getDisplayName();
        String targetName = e.getView().getTitle().length() > 11 ? e.getView().getTitle().substring(9) : e.getView().getTitle();

        switch (item.getType()) {
            case DIAMOND_SWORD:
                if (displayName.equals("§cKillAura")) {
                    handleReport(player, targetName, "KillAura", e);
                }
                break;
            case BOW:
                if (displayName.equals("§cBowAibot")) {
                    handleReport(player, targetName, "BowAibot", e);
                }
                break;
            case DIAMOND_ORE:
                if (displayName.equals("§cXRay")) {
                    handleReport(player, targetName, "XRay", e);
                }
                break;
            case FEATHER:
                if (displayName.equals("§cFly")) {
                    handleReport(player, targetName, "Fly", e);
                }
                break;
            default:
                break;
        }
    }

    private void handleReport(Player player, String targetName, String reason, InventoryClickEvent e) {
        e.setCancelled(true);
        player.closeInventory();
        sendToMods(reason, targetName);
        player.sendMessage(main.report + "Vous avez report §c" + targetName + " §epour §c" + reason + "§e.");
    }

    private void sendToMods(String reason, String targetName) {
        for (Player staff : Bukkit.getOnlinePlayers()) {
            if (staff.hasPermission("oramiplugs.staff")) {
                staff.sendMessage(main.report + "Le joueur §c" + targetName + " §ea été signalé pour §c" + reason + "§e.");
            }
        }
    }
}