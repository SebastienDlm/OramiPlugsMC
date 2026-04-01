package fr.niastiik.oramiplugs.events;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventFreeze implements Listener {

    private final Main main;

    private final Map<UUID, ItemStack> frozenPlayers = new HashMap<>();

    public EventFreeze(Main main) {
        this.main = main;
    }

    public void freezePlayer(Player player) {
        if (frozenPlayers.containsKey(player.getUniqueId())) return;

        frozenPlayers.put(player.getUniqueId(), player.getInventory().getHelmet());
        player.getInventory().setHelmet(new ItemStack(Material.ICE));
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setVelocity(new Vector(0, 0, 0));
        player.sendMessage(main.punition + "Vous avez été freeze !");
    }

    public void unfreezePlayer(Player player) {
        if (!frozenPlayers.containsKey(player.getUniqueId())) return;

        player.getInventory().setHelmet(frozenPlayers.get(player.getUniqueId()));
        frozenPlayers.remove(player.getUniqueId());
        player.sendMessage(main.punition + "Vous avez été libéré !");
    }

    public boolean isFrozen(Player player) {
        return frozenPlayers.containsKey(player.getUniqueId());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (isFrozen(player)) {
            e.setCancelled(true);
            e.setTo(e.getFrom());
            player.setFlying(false);
            player.setVelocity(new Vector(0, 0, 0));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (isFrozen(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (isFrozen(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if (isFrozen(player)) e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (isFrozen(e.getPlayer())) e.setCancelled(true);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();
        if (isFrozen(player)) e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        if (isFrozen(player)) e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (isFrozen(e.getPlayer())) e.setCancelled(true);
    }

    public Map<UUID, ItemStack> getFrozenPlayers() {
        return frozenPlayers;
    }
}