package com.example.judy.modules;

import javafx.scene.image.ImageView;

public class Tank extends Tower {

    private static double cost;
    public static final String NAME = "TANK";
    private static String description;
    public static final int DAMAGE = 40;
    public static final int DAMAGE_PER_SECOND = 12;

    public Tank() {
        super();
        setDamage(DAMAGE_PER_SECOND);
    }

    public Tank(ImageView image) {
        super(image);
        setDamage(DAMAGE_PER_SECOND);
    }

    public Tank(int x, int y, ImageView image) {
        super(x, y, image);
        setDamage(DAMAGE_PER_SECOND);
    }

    public static void setDescription(String description) {
        Tank.description = description;
    }

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

    public String toString() {
        return getX() + " " + getY() + " " + Tower.NAME;
    }


}
