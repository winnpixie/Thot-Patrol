package me.alerithe.thotpatrol.managers;

import me.alerithe.thotpatrol.patches.Patch;
import me.alerithe.thotpatrol.patches.impl.movement.FastUsePatch;
import me.alerithe.thotpatrol.patches.impl.movement.FlightPatch;
import me.alerithe.thotpatrol.patches.impl.movement.NoFallPatch;
import me.alerithe.thotpatrol.patches.impl.movement.NormalMovePatch;

import java.util.ArrayList;
import java.util.List;

public class PatchManager {

    private List<Patch> patches;

    public PatchManager() {
        this.patches = new ArrayList<>(4);
        this.patches.add(new FastUsePatch());
        this.patches.add(new FlightPatch());
        this.patches.add(new NormalMovePatch());
        this.patches.add(new NoFallPatch());
    }

    public List<Patch> getPatches() {
        return patches;
    }
}
