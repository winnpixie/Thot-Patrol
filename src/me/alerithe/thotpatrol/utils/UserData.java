package me.alerithe.thotpatrol.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class UserData {

    public static HashMap<String, UserData> userDataMap = new HashMap<>(0);
    public double fallDistance;
    public long itemUseTime;
    public int flightVL;
    public int nofallVL;
    public Player player;

    public UserData(Player player) {
        this.player = player;
        this.flightVL = 0;
        this.nofallVL = 0;
        this.fallDistance = 0;
        this.itemUseTime = 0;
    }

    public boolean isHeadObscured() {
        return this.player.getLocation().getPitch() > 90 || this.player.getLocation().getPitch() < -90;
    }

}
