package fr.niastiik.oramiplugs.utils;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack.clone();
    }

    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
    }

    public ItemBuilder(Material material, int amount, int damage) {
        this.itemStack = new ItemStack(material, amount);
        setDamage(damage);
    }

    public ItemBuilder clone() {
        return new ItemBuilder(this.itemStack);
    }

    public ItemBuilder setDamage(int damage) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta instanceof Damageable damageable) {
            damageable.setDamage(damage);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            meta.addEnchant(enchantment, level, true);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment enchantment, int level) {
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment enchantment) {
        this.itemStack.removeEnchantment(enchantment);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setSkullOwner(String owner) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta instanceof SkullMeta skullMeta) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(owner);
            skullMeta.setOwningPlayer(player);
            this.itemStack.setItemMeta(skullMeta);
        }
        return this;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            meta.setUnbreakable(unbreakable);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            meta.setLore(lore);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setWoolColor(DyeColor color) {
        Material woolMaterial = Material.matchMaterial(color.name() + "_WOOL");
        if (woolMaterial != null) {
            this.itemStack.setType(woolMaterial);
        }
        return this;
    }

    public ItemBuilder setLeatherArmorColor(Color color) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta instanceof LeatherArmorMeta leatherMeta) {
            leatherMeta.setColor(color);
            this.itemStack.setItemMeta(leatherMeta);
        }
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setCustomModelData(int data) {
        ItemMeta meta = this.itemStack.getItemMeta();
        if (meta != null) {
            meta.setCustomModelData(data);
            this.itemStack.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemStack build() {
        return this.itemStack.clone();
    }

    public ItemStack toItemStack() {
        return build();
    }
}