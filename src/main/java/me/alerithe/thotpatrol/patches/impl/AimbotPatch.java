package me.alerithe.thotpatrol.patches.impl;

import me.alerithe.thotpatrol.patches.Patch;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Basically a broken check :/
 */
public class AimbotPatch extends Patch {
    public AimbotPatch() {
        super("AIMBOT");
    }

    @EventHandler
    private void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity) {
            Player player = (Player) event.getDamager();
            float[] rotations = this.getRotations(player, (LivingEntity) event.getEntity());
            if (event.getDamager().getLocation().getYaw() == rotations[0] && event.getDamager().getLocation().getPitch() == rotations[1]) {
                this.getTags().add("check_type_a");
            }
            if (!this.getTags().isEmpty()) {
                this.alertOnline(player);
                this.getTags().clear();
            }
        }
    }

    private float[] getRotations(Player attacker, LivingEntity entity) {
        double xDiff = entity.getLocation().getX() - attacker.getLocation().getX();
        double zDiff = entity.getLocation().getZ() - attacker.getLocation().getZ();
        return new float[]{(float) (Math.atan2(zDiff, xDiff) * 180D / 3.141592653589793) - 90F, (float) (-Math.atan2((entity.getLocation().getY() + entity.getEyeHeight()) - (attacker.getLocation().getY() + attacker.getEyeHeight()), Math.hypot(xDiff, zDiff)) * 180D / 3.141592653589793)};
    }

}
