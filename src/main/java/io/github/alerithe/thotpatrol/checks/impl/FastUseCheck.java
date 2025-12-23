package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import io.github.alerithe.thotpatrol.users.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

public class FastUseCheck extends Check {
    public FastUseCheck(ThotPatrolPlugin plugin) {
        super("FAST_CONSUME", plugin);
    }

    @Override
    public boolean onPlayerStartUseItem(Player player, ItemStack item) {
        getPlugin().getUserManager().get(player).setItemUseStartTime(System.nanoTime());

        return true;
    }

    @Override
    public boolean onPlayerEndUseItem(Player player, ItemStack item) {
        if (!isConsumable(item)) {
            return true;
        }

        User user = getPlugin().getUserManager().get(player);
        long timeInUse = System.nanoTime() - user.getItemUseStartTime();
        if (timeInUse / 1_000_000 < 1450) {
            return false;
        }

        user.setItemUseStartTime(0L);
        return true;
    }

    private boolean isConsumable(ItemStack item) {
        Material type = item.getType();

        return type.isEdible()
                || type == Material.MILK_BUCKET
                || (type == Material.POTION && !Potion.fromItemStack(item).isSplash());
    }
}
