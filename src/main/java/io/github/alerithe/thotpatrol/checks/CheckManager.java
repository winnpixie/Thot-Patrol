package io.github.alerithe.thotpatrol.checks;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.impl.*;
import io.github.alerithe.thotpatrol.listeners.PlayerEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckManager {
    private final ThotPatrolPlugin plugin;

    private final List<Check> checks = new ArrayList<>();

    public CheckManager(ThotPatrolPlugin plugin) {
        this.plugin = plugin;
    }

    public List<Check> getChecks() {
        return checks;
    }

    public void load() {
        checks.add(new AimAssistCheck(plugin));
        checks.add(new FastUseCheck(plugin));
        checks.add(new FlightCheck(plugin));
        checks.add(new NormalMoveCheck(plugin));
        checks.add(new NoFallCheck(plugin));
    }
}
