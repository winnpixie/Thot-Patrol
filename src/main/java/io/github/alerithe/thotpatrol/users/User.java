package io.github.alerithe.thotpatrol.users;

import java.util.UUID;

public class User {
    private final UUID userId;

    private double fallDistance;
    private boolean usingItem;
    private long itemUseStartTime;

    private final Violations violations = new Violations();

    public User(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public double getFallDistance() {
        return fallDistance;
    }

    public void setFallDistance(double fallDistance) {
        this.fallDistance = fallDistance;
    }

    public boolean isUsingItem() {
        return usingItem;
    }

    public void setUsingItem(boolean usingItem) {
        this.usingItem = usingItem;
    }

    public long getItemUseStartTime() {
        return itemUseStartTime;
    }

    public void setItemUseStartTime(long itemUseStartTime) {
        this.itemUseStartTime = itemUseStartTime;
    }

    public Violations getViolations() {
        return violations;
    }

    public static class Violations {
        private int survivalFlight;
        private int noFallDamage;

        public int getSurvivalFlight() {
            return survivalFlight;
        }

        public void setSurvivalFlight(int survivalFlight) {
            this.survivalFlight = survivalFlight;
        }

        public int getNoFallDamage() {
            return noFallDamage;
        }

        public void setNoFallDamage(int noFallDamage) {
            this.noFallDamage = noFallDamage;
        }
    }
}
