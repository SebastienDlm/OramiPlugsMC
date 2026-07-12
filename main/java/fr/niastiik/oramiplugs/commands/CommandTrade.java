package fr.niastiik.oramiplugs.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;

import fr.niastiik.oramiplugs.Main;

public class CommandTrade implements CommandExecutor {

    private final Main main;

    public CommandTrade(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.main.prefix + "§cSeul les joueurs peuvent utiliser cette commande.");
            return true;
        }

        Player player = (Player) sender;

        Merchant virtualMerchant = Bukkit.createMerchant("§7[§c§lOramiTrade§7]");
        List<MerchantRecipe> recipes = new ArrayList<>();

        MerchantRecipe recipe1 = new MerchantRecipe(new ItemStack(Material.COAL_BLOCK, 2), 10);
        recipe1.addIngredient(new ItemStack(Material.VILLAGER_SPAWN_EGG, 1));
        MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(Material.IRON_BLOCK, 1), 10);
        recipe2.addIngredient(new ItemStack(Material.VILLAGER_SPAWN_EGG, 1));
        MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(Material.GOLD_BLOCK, 1), 10);
        recipe3.addIngredient(new ItemStack(Material.VILLAGER_SPAWN_EGG, 3));
        MerchantRecipe recipe4 = new MerchantRecipe(new ItemStack(Material.EMERALD_BLOCK, 1), 10);
        recipe4.addIngredient(new ItemStack(Material.VILLAGER_SPAWN_EGG, 5));
        MerchantRecipe recipe5 = new MerchantRecipe(new ItemStack(Material.DIAMOND_BLOCK, 1), 1);
        recipe5.addIngredient(new ItemStack(Material.VILLAGER_SPAWN_EGG, 10));
        MerchantRecipe recipe6 = new MerchantRecipe(new ItemStack(Material.GOLD_INGOT, 1), 1000);
        recipe6.addIngredient(new ItemStack(Material.GOLDEN_SWORD, 1));
        
        recipes.add(recipe1);
        recipes.add(recipe2);
        recipes.add(recipe3);
        recipes.add(recipe4);
        recipes.add(recipe5);
        recipes.add(recipe6);
        virtualMerchant.setRecipes(recipes);

        player.openMerchant(virtualMerchant, true);
        return true;
    }
}