package me.alerithe.thotpatrol.patches.impl.movement;

import me.alerithe.thotpatrol.patches.Patch;
import me.alerithe.thotpatrol.utils.UserData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.Potion;

public class FastUsePatch extends Patch {


    public FastUsePatch() {
        super("FAST_USE");
    }

    @EventHandler
    private void onItemUse(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            UserData userData = UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player));
            userData.itemUseTime = System.nanoTime();

        }
    }

    @EventHandler
    private void onFinishUse(PlayerItemConsumeEvent event) {
        if (event.getItem().getType().isEdible() || (event.getItem().getType() == Material.POTION && !Potion.fromItemStack(event.getItem()).isSplash()) || event.getItem().getType() == Material.MILK_BUCKET) {
            Player player = event.getPlayer();
            boolean cancel = false;
            UserData userData = UserData.userDataMap.getOrDefault(player.getUniqueId().toString(), new UserData(player));
            long deltaTime = System.nanoTime() - userData.itemUseTime;
            if (deltaTime / 1000000 < 1500) {
                cancel = true;
                this.getTags().add(String.format("bad_use_time(%sms)", deltaTime / 1000000));
            }
            userData.itemUseTime = 0;
            if (cancel) {
                event.setCancelled(true);
            }
            if (!this.getTags().isEmpty()) {
                this.alertOnline(player);
                this.getTags().clear();
            }
        }
    }
}
