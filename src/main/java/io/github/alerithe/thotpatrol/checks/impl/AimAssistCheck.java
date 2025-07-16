package io.github.alerithe.thotpatrol.checks.impl;

import io.github.alerithe.thotpatrol.ThotPatrolPlugin;
import io.github.alerithe.thotpatrol.checks.Check;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

/**
 * Basically a broken check :/
 */
public class AimAssistCheck extends Check {
    public AimAssistCheck(ThotPatrolPlugin plugin) {
        super(plugin, "AIM_ASSIST");
    }

    @Override
    public boolean onPlayerAttack(Player attacker, Entity victim) {
        if (!(victim instanceof LivingEntity)) return true;

        float[] rotations = this.getRotations(attacker, (LivingEntity) victim);
        if (attacker.getLocation().getYaw() == rotations[0]
                && attacker.getLocation().getPitch() == rotations[1]) {
            return false;
        }

        return true;
    }

    private float[] getRotations(Player attacker, LivingEntity victim) {
        Location startLoc = attacker.getLocation();
        Location endLoc = victim.getLocation();

        double deltaX = endLoc.getX() - startLoc.getX();
        double deltaY = (endLoc.getY() + victim.getEyeHeight()) - (startLoc.getY() + attacker.getEyeHeight());
        double deltaZ = endLoc.getZ() - startLoc.getZ();

        return new float[]{(float) (Math.atan2(deltaZ, deltaX) * 180.0 / Math.PI) - 90f,
                (float) (-Math.atan2(deltaY, Math.hypot(deltaX, deltaZ)) * 180.0 / Math.PI)};
    }
}
