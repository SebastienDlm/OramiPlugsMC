package fr.niastiik.oramiplugs.events;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class EventKillEntity implements Listener {

    private final Random random = new Random();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {

        if (e.getEntity() instanceof Villager
                || e.getEntity() instanceof Pillager
                || e.getEntity() instanceof ZombieVillager) {

            if (!(e.getEntity().getKiller() instanceof Player)) return;

            if (random.nextInt(10) == 0) {
                e.getDrops().add(new ItemStack(Material.VILLAGER_SPAWN_EGG));
            }
        }

        if (e.getEntity() instanceof Player player) {
            Player killer = player.getKiller();
            if (killer == null) return;
            if (random.nextInt(50) == 0) {
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                if (meta != null) {
                    meta.setOwningPlayer(player);
                    head.setItemMeta(meta);
                }
                e.getDrops().add(head);
            }
        }

        
    }
}