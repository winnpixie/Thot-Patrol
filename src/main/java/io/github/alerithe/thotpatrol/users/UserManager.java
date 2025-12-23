package io.github.alerithe.thotpatrol.users;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private final Map<UUID, User> users = new ConcurrentHashMap<>();

    public void add(Player player) {
        add(player.getUniqueId());
    }

    public void add(UUID userId) {
        users.computeIfAbsent(userId, User::new);
    }

    public User get(Player player) {
        return get(player.getUniqueId());
    }

    public User get(UUID userId) {
        return users.get(userId);
    }

    public void remove(Player player) {
        remove(player.getUniqueId());
    }

    public void remove(UUID userId) {
        users.remove(userId);
    }
}
