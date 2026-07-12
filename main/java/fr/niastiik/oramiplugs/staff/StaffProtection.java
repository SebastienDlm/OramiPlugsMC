package fr.niastiik.oramiplugs.staff;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.niastiik.oramiplugs.Main;

public class StaffProtection implements Listener {

    private final Main main;

    public StaffProtection(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (PlayerManager.isInModerationMod(player)) {
            e.setCancelled(true);
            player.sendMessage(this.main.staff + "§cVous ne pouvez pas casser de blocs en mode modérateur.");
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (PlayerManager.isInModerationMod(player)) {
            e.setCancelled(true);
            player.sendMessage(this.main.staff + "§cVous ne pouvez pas poser de blocs en mode modérateur.");
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if (PlayerManager.isInModerationMod(player)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (PlayerManager.isInModerationMod(player)) {
            e.setCancelled(true);
            player.sendMessage(this.main.staff + "§cVous ne pouvez pas jeter vos items en mode modérateur.");
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if (PlayerManager.isInModerationMod(player)) {
            e.setCancelled(true);
        }
    }
}