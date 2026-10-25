package ru.kitstartered;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

public final class KitStartered extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("KitStartered включён.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Эту команду можно использовать только в игре.");
            return true;
        }

        ItemStack[] kit = {
                new ItemStack(Material.STONE_SWORD),
                new ItemStack(Material.STONE_PICKAXE),
                new ItemStack(Material.STONE_AXE),
                new ItemStack(Material.STONE_SHOVEL),
                new ItemStack(Material.COOKED_BEEF, 16),
                new ItemStack(Material.TORCH, 16),
                new ItemStack(Material.OAK_LOG, 16),
                new ItemStack(Material.GOLDEN_APPLE, 1) // бонус
        };

        for (ItemStack item : kit) {
            Map<Integer, ItemStack> leftover = player.getInventory().addItem(item);
            leftover.values().forEach(rest ->
                    player.getWorld().dropItemNaturally(player.getLocation(), rest));
        }

        player.sendMessage("§aВы получили стартовый набор! §6Бонус: золотое яблоко.");
        return true;
    }
}
