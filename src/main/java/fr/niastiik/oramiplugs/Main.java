package fr.niastiik.oramiplugs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.niastiik.oramiplugs.commands.*;
import fr.niastiik.oramiplugs.events.*;
import fr.niastiik.oramiplugs.staff.ModItemsInteract;
import fr.niastiik.oramiplugs.staff.PlayerDataManager;
import fr.niastiik.oramiplugs.staff.PlayerManager;
import fr.niastiik.oramiplugs.staff.StaffProtection;

public class Main extends JavaPlugin {

    private static Main instance;

    private List<UUID> modo;
    private Map<UUID, PlayerManager> players;
    private Map<UUID, Location> freezedPlayers;
    private Map<Player, org.bukkit.inventory.ItemStack> freeze;

    private PlayerDataManager playerDataManager;
    private EventFreeze eventFreeze;

    public String prefix = "§7[§c§lOramiPlugs§7] §e";
    public String connect = "§7[§c§lOramiConnect§7] §c";
    public String maintenance = "§7[§c§lOramiMaintenance§7] §c";
    
    public String bc = "§7[§c§lOramiAnnonce§7] §e";
    public String info = "§7[§c§lOramiInformations§7] §c";

    public String report = "§7[§c§lOramiReport§7] §e";
    public String staff = "§7[§c§lOramiStaff§7] §e";
    public String punition = "§7[§c§lOramiPunition§7] §c";

    @Override
    public void onEnable() {
        instance = this;

        modo = new ArrayList<>();
        players = new HashMap<>();
        freezedPlayers = new HashMap<>();
        freeze = new HashMap<>();

        saveDefaultConfig();

        playerDataManager = new PlayerDataManager(this);
        eventFreeze = new EventFreeze(this);

        System.out.println("OramiPlugs -> Activated");

        //Commandes
        getCommand("broadcast").setExecutor(new CommandBroadcast(this));
        getCommand("clear").setExecutor(new CommandClear(this));
        getCommand("day").setExecutor(new CommandDay(this));
        getCommand("enderchest").setExecutor(new CommandEnderchest(this));
        getCommand("feed").setExecutor(new CommandFeed(this));
        getCommand("fly").setExecutor(new CommandFly(this));
        getCommand("freeze").setExecutor(new CommandFreeze(this));
        getCommand("gamemode").setExecutor(new CommandGamemode(this));
        getCommand("heal").setExecutor(new CommandHeal(this));
        getCommand("information").setExecutor(new CommandInfo(this));
        getCommand("inventory").setExecutor(new CommandInventory(this));
        getCommand("kick").setExecutor(new CommandKick(this));
        getCommand("kill").setExecutor(new CommandKill(this));
        getCommand("msg").setExecutor(new CommandMessage(this));
        getCommand("night").setExecutor(new CommandNight(this));
        getCommand("rain").setExecutor(new CommandRain(this));
        getCommand("report").setExecutor(new CommandReport(this));
        getCommand("staff").setExecutor(new CommandStaff(this));
        getCommand("suicide").setExecutor(new CommandSuicide(this));
        getCommand("sun").setExecutor(new CommandSun(this));
        getCommand("vanish").setExecutor(new CommandVanish(this));

        //Events
        getServer().getPluginManager().registerEvents(new EventJoin(this), this);
        getServer().getPluginManager().registerEvents(new EventQuit(this), this);
        getServer().getPluginManager().registerEvents(new ModItemsInteract(this), this);
        getServer().getPluginManager().registerEvents(eventFreeze, this);
        getServer().getPluginManager().registerEvents(new EventMobIgnore(this), this);
        getServer().getPluginManager().registerEvents(new StaffProtection(this), this);
        getServer().getPluginManager().registerEvents(new EventReport(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("OramiPlugs -> Desactivate");
    }

    public static Main getInstance() {
        return instance;
    }

    public List<UUID> getModo() {
        return modo;
    }

    public Map<UUID, PlayerManager> getPlayers() {
        return players;
    }

    public Map<UUID, Location> getFreezedPlayers() {
        return freezedPlayers;
    }

    public boolean isFreeze(Player player) {
        return freezedPlayers.containsKey(player.getUniqueId());
    }

    public Map<Player, org.bukkit.inventory.ItemStack> getFreeze() {
        return freeze;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public EventFreeze getEventFreeze() {
        return eventFreeze;
    }
}