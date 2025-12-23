package io.github.alerithe.thotpatrol.checks;

import io.github.alerithe.spoiler.listeners.BaseEventListener;
import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Check extends BaseEventListener<ThotPatrolPlugin> {
    private final String label;

    public Check(String label, ThotPatrolPlugin plugin) {
        super(plugin);

        this.label = label;
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

    public boolean onPlayerChat(Player player, String message) {
        return true;
    }
}
