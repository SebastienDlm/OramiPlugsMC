package fr.niastiik.oramiplugs.events;

import fr.niastiik.oramiplugs.Main;
import fr.niastiik.oramiplugs.staff.PlayerData;
import fr.niastiik.oramiplugs.staff.PlayerManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventQuit implements Listener {
  private Main main;
  
  public EventQuit(Main main) {
    this.main = main;
  }
  
  @EventHandler
  public void onPlayerQuitEvent(PlayerQuitEvent e) {
    Player player = e.getPlayer();

    PlayerData data = main.getPlayerDataManager().get(e.getPlayer().getUniqueId());

    if (data.isVanished()) {
      e.setQuitMessage(null);
    }

    e.setQuitMessage(String.valueOf(this.main.connect) + player.getDisplayName() + " §evient de se déconnecter !");
    if (PlayerManager.isInModerationMod(player))
      PlayerManager.getFromPlayer(player).destroy(); 
  }
}