package me.alerithe.thotpatrol.utils;

public class MathHelper {

    public static double clampDouble(double value, double min, double max) {
        return value < min ? min : value > max ? max : value;
    }

    public static float clampFloat(float value, float min, float max) {
        return value < min ? min : value > max ? max : value;
    }
}
