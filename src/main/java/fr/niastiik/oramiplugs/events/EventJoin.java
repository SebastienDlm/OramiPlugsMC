package fr.niastiik.oramiplugs.events;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.staff.PlayerData;
import fr.niastiik.oramiplugs.staff.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventJoin implements Listener {

    private final Main main;

    public EventJoin(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        PlayerData data = main.getPlayerDataManager().get(player.getUniqueId());

        main.getPlayerDataManager().getCache().values().forEach(other -> {

            if (!other.isVanished()) return;

            Player vanished = other.getPlayer();
            if (vanished == null) return;

            if (!player.hasPermission("oramiplugs.vanish.see")) {
                player.hidePlayer(main, vanished);
            }
        });

        if (data.isVanished()) {
            data.setVanished(true);
            player.setPlayerListName(" ");
            e.setJoinMessage(null);
        } else {
            e.setJoinMessage(main.connect + player.getDisplayName() + " §evient de se connecter !");
        }

        if (data.isStaff()) {
            new PlayerManager(player).init();
        }
    }
}