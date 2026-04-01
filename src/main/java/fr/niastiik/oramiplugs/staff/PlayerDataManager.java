package fr.niastiik.oramiplugs.staff;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {

    private final Map<UUID, PlayerData> cache = new HashMap<>();

    public PlayerData get(UUID uuid) {
        return cache.computeIfAbsent(uuid, PlayerData::new);
    }

    public void remove(UUID uuid) {
        cache.remove(uuid);
    }

    public Map<UUID, PlayerData> getCache() {
        return cache;
    }   
}