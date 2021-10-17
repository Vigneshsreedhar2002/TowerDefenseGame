package com.example.judy.modules;

public abstract class Tower {

    public static final String NAME = "TOWER";
    public static final String DESCRIPTION = "An abstract tower";
    public static final int DAMAGE = 0;
    public static final int DAMAGE_PER_SECOND = 0;
    private static double cost;

    public static double getCost() {
        return cost;
    }

    public static void setCost(double cost) {
        Tower.cost = cost;
    }
}
