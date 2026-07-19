package fr.niastiik.oramiplugs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.niastiik.oramiplugs.commands.CommandBroadcast;
import fr.niastiik.oramiplugs.commands.CommandClear;
import fr.niastiik.oramiplugs.commands.CommandCraft;
import fr.niastiik.oramiplugs.commands.CommandDay;
import fr.niastiik.oramiplugs.commands.CommandEnderchest;
import fr.niastiik.oramiplugs.commands.CommandFeed;
import fr.niastiik.oramiplugs.commands.CommandFly;
import fr.niastiik.oramiplugs.commands.CommandFreeze;
import fr.niastiik.oramiplugs.commands.CommandGamemode;
import fr.niastiik.oramiplugs.commands.CommandGod;
import fr.niastiik.oramiplugs.commands.CommandHeal;
import fr.niastiik.oramiplugs.commands.CommandInfo;
import fr.niastiik.oramiplugs.commands.CommandInventory;
import fr.niastiik.oramiplugs.commands.CommandKick;
import fr.niastiik.oramiplugs.commands.CommandKill;
import fr.niastiik.oramiplugs.commands.CommandMessage;
import fr.niastiik.oramiplugs.commands.CommandNight;
import fr.niastiik.oramiplugs.commands.CommandRain;
import fr.niastiik.oramiplugs.commands.CommandRandomTP;
import fr.niastiik.oramiplugs.commands.CommandRepair;
import fr.niastiik.oramiplugs.commands.CommandReport;
import fr.niastiik.oramiplugs.commands.CommandSpawn;
import fr.niastiik.oramiplugs.commands.CommandStaff;
import fr.niastiik.oramiplugs.commands.CommandSuicide;
import fr.niastiik.oramiplugs.commands.CommandSun;
import fr.niastiik.oramiplugs.commands.CommandTp;
import fr.niastiik.oramiplugs.commands.CommandTrade;
import fr.niastiik.oramiplugs.commands.CommandVanish;
import fr.niastiik.oramiplugs.events.EventFreeze;
import fr.niastiik.oramiplugs.events.EventJoin;
import fr.niastiik.oramiplugs.events.EventKillEntity;
import fr.niastiik.oramiplugs.events.EventMobIgnore;
import fr.niastiik.oramiplugs.events.EventQuit;
import fr.niastiik.oramiplugs.events.EventReport;
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
        getCommand("craft").setExecutor(new CommandCraft(this));
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
        getCommand("randomtp").setExecutor(new CommandRandomTP(this)); //A LIMITER (TIMER)
        getCommand("repair").setExecutor(new CommandRepair(this)); //A FINIR
        getCommand("report").setExecutor(new CommandReport(this));
        getCommand("spawn").setExecutor(new CommandSpawn(this));
        getCommand("staff").setExecutor(new CommandStaff(this));
        getCommand("suicide").setExecutor(new CommandSuicide(this));
        getCommand("sun").setExecutor(new CommandSun(this));
        getCommand("tp").setExecutor(new CommandTp(this)); //A EVOLUER
        getCommand("trade").setExecutor(new CommandTrade(this));
        getCommand("vanish").setExecutor(new CommandVanish(this));

        getCommand("god").setExecutor(new CommandGod(this)); //A FAIRE

        //Events
        getServer().getPluginManager().registerEvents(new EventJoin(this), this);
        getServer().getPluginManager().registerEvents(new EventQuit(this), this);
        getServer().getPluginManager().registerEvents(new ModItemsInteract(this), this);
        getServer().getPluginManager().registerEvents(eventFreeze, this);
        getServer().getPluginManager().registerEvents(new EventMobIgnore(this), this);
        getServer().getPluginManager().registerEvents(new StaffProtection(this), this);
        getServer().getPluginManager().registerEvents(new EventReport(this), this);

        getServer().getPluginManager().registerEvents(new EventKillEntity(), this); //EXCLUS SURVIE
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