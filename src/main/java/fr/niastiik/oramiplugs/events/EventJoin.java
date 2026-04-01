package fr.niastiik.oramiplugs.events;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.staff.PlayerData;

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

    main.getPlayerDataManager().get(player.getUniqueId());

    main.getPlayerDataManager().getCache().values().forEach(data -> {

      if (!data.isVanished()) return;

      Player vanished = data.getPlayer();
      if (vanished == null) return;

      if (!player.hasPermission("oramiplugs.vanish.see")) {
        player.hidePlayer(main, vanished);
      }
    });
    e.setJoinMessage(main.connect + player.getDisplayName() + " §evient de se connecter !");

    PlayerData data = main.getPlayerDataManager().get(player.getUniqueId());

    if (data.isVanished()) {
      player.setPlayerListName("§r");
      e.setJoinMessage(null);
    }
  }
}