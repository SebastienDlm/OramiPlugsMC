package fr.niastiik.oramiplugs.staff;

import fr.niastiik.oramiplugs.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;
    private boolean vanished;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        this.vanished = false;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished) {

        if (this.vanished == vanished) return;
        this.vanished = vanished;

        Player player = getPlayer();
        if (player == null || !player.isOnline()) return;

        Bukkit.getOnlinePlayers().forEach(p -> {

            if (p.equals(player)) return;

            if (p.hasPermission("oramiplugs.vanish.see")) return;

            if (vanished) {
                p.hidePlayer(Main.getInstance(), player);
            } else {
                p.showPlayer(Main.getInstance(), player);
            }
        });

        if (vanished) {
            player.setPlayerListName("§r");
        } else {
            player.setPlayerListName(player.getName());
        }
    }
}