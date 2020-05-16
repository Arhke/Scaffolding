package net.waterraid.scaffold;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public static JavaPlugin Plugin = null;
    public static ItemStack scaffoldItem;
    public void onEnable() {
        Plugin = this;
        scaffoldItem = new ItemStack(Material.HUGE_MUSHROOM_1, 1);
        ItemMeta im = scaffoldItem.getItemMeta();
        im.setDisplayName(ChatColor.YELLOW + "Scaffolding Block");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "This is a Scaffolding Block");
        lore.add(ChatColor.YELLOW + "Breaking One Scaffolding Block will ");
        lore.add(ChatColor.YELLOW + "Break All Scaffolding Blocks Connected");
        im.setLore(lore);
        scaffoldItem.setItemMeta(im);
        Bukkit.getPluginManager().registerEvents(new Listeners(), Main.getInstance());
        NamespacedKey key = new NamespacedKey(this, "emerald_sword");
        ItemStack is = new ItemStack(scaffoldItem);
        is.setAmount(5);
        ShapedRecipe recipe = new ShapedRecipe(key, is);
        recipe.shape("SSS", "SSS", "SSS");
        recipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(recipe);

    }
    public void onDisable() {

    }
    public static JavaPlugin getInstance() {
        return Plugin;
    }
}
