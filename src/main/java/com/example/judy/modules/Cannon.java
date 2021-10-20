package com.example.judy.modules;

public class Cannon extends Tower {


    private static double cost;

    public static final String NAME = "CANNON";
    private static String description;
    public static final int DAMAGE = 10;
    public static final int DAMAGE_PER_SECOND = 10;

    public static double getCost() {
        return cost;
    }

    public static String getDescription() {
        return description;
    }

    public static void setCost(double cost) {
        Cannon.cost = cost;
        description = "A single-target building with decent damage output and moderate speed.    "
                + "\nCost: $" + String.format("%.2f", cost)
                + "\nDamage: 10"
                + "\nDamage per second: 10";
    }

}
