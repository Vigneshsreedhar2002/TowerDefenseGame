package com.example.judy.modules;

public class Tank extends Tower {

    private static double cost;
    public static final String NAME = "TANK";
    private static String description;
    public static final int DAMAGE = 40;
    public static final int DAMAGE_PER_SECOND = 25;

    public static double getCost() {
        return cost;
    }

    public static String getDescription() {
        return description;
    }

    public static void setCost(double cost) {
        Tank.cost = cost;
        description = "A splash-damage building with incredible damage output but slow speed.    "
                + "\nCost: $" + String.format("%.2f", cost)
                + "\nDamage: 40"
                + "\nDamage per second: 25";
    }


}
