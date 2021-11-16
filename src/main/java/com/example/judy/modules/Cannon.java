package com.example.judy.modules;

import javafx.scene.image.ImageView;

public class Cannon extends Tower {


    private static double cost;

    public static final String NAME = "CANNON";
    private static String description;
    public static final int DAMAGE = 10;
    public static final int DAMAGE_PER_SECOND = 5;

    public Cannon() {
        super();
    }

    public Cannon(ImageView image) {
        super(image);
    }

    public Cannon(int x, int y, ImageView image) {
        super(x, y, image);
    }

    public static void setDescription(String description) {
        Cannon.description = description;
    }

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

    public String toString() {
        return getX() + " " + getY() + " " + Cannon.NAME;
    }

}
