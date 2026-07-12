package fr.niastiik.oramiplugs.staff;

import fr.niastiik.oramiplugs.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    private final Map<UUID, PlayerData> cache = new HashMap<>();
    private final Main main;

    public PlayerDataManager(Main main) {
        this.main = main;
    }

    public PlayerData get(UUID uuid) {
        return cache.computeIfAbsent(uuid, id -> {
            PlayerData data = new PlayerData(id);

            boolean vanished = main.getConfig().getBoolean("players." + id + ".vanish", false);
            boolean staff = main.getConfig().getBoolean("players." + id + ".staff", false);

            data.setVanished(vanished);
            data.setStaff(staff);

            return data;
        });
    }

    public void save(PlayerData data) {
        UUID uuid = data.getUuid();

        main.getConfig().set("players." + uuid + ".vanish", data.isVanished());
        main.getConfig().set("players." + uuid + ".staff", data.isStaff());

        main.saveConfig();
    }

    public void remove(UUID uuid) {
        cache.remove(uuid);
    }

    public Map<UUID, PlayerData> getCache() {
        return cache;
    }
}