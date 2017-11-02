package me.alerithe.thotpatrol.patches;

import me.alerithe.thotpatrol.ThotPatrol;
import me.alerithe.thotpatrol.managers.PermissionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class Patch implements Listener {

    private String label;
    private List<String> tags;

    public Patch(String label) {
        this.label = label;
        this.tags = new ArrayList<>(0);
    }

    public String getLabel() {
        return label;
    }

    public List<String> getTags() {
        return tags;
    }

    public void alertOnline(Player player) {
        ThotPatrol.instance.getServer().getOnlinePlayers().stream().filter(p -> p.hasPermission(PermissionManager.RECEIVE_ALERTS)).forEach(p -> {
            p.sendMessage(String.format("%s Player \247c%s \247rfailed check \2477%s", ThotPatrol.instance.chatPrefix, player.getName(), this.label));
            p.sendMessage(String.format("%s \2477Tags: \247r%s", ThotPatrol.instance.chatPrefix, String.join(", ", tags)));
        });
    }
}
