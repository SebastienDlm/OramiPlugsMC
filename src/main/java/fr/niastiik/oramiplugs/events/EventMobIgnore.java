package fr.niastiik.oramiplugs.events;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.staff.PlayerData;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class EventMobIgnore implements Listener {

    private final Main main;

    public EventMobIgnore(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onMobTarget(EntityTargetLivingEntityEvent e) {

        if (!(e.getTarget() instanceof Player)) return;

        Player player = (Player) e.getTarget();

        PlayerData data = main.getPlayerDataManager().get(player.getUniqueId());

        if (data.isVanished()) {
            e.setCancelled(true);
            e.setTarget(null);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player player) {
            if (main.getPlayerDataManager().get(player.getUniqueId()).isVanished()) {
                e.setCancelled(true);
            }
        }
    }
}