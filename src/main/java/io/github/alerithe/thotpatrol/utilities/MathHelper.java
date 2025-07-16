package io.github.alerithe.thotpatrol.utilities;

public class MathHelper {
    private MathHelper() {
    }

    public static int abs(int value) {
        return value < 0 ? -value : value;
    }

    public static float abs(float value) {
        return value < 0f ? -value : value;
    }

    public static double abs(double value) {
        return value < 0.0 ? -value : value;
    }
}
