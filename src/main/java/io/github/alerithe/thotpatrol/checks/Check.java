package io.github.alerithe.thotpatrol.checks;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Check {
    private final ThotPatrolPlugin plugin;
    private final String label;

    public Check(ThotPatrolPlugin plugin, String label) {
        this.plugin = plugin;
        this.label = label;
    }

    public ThotPatrolPlugin getPlugin() {
        return plugin;
    }

    public String getLabel() {
        return label;
    }

    public boolean onPlayerMove(Player player, Location from, Location to) {
        return true;
    }

    public boolean onPlayerStartUseItem(Player player, ItemStack item) {
        return true;
    }

    public boolean onPlayerEndUseItem(Player player, ItemStack item) {
        return true;
    }

    public boolean onPlayerAttack(Player attacker, Entity victim) {
        return true;
    }
}
