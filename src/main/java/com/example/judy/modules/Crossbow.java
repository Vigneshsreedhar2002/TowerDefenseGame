package com.example.judy.modules;

import javafx.scene.image.ImageView;

public class Crossbow extends Tower {

    private static double cost;
    public static final String NAME = "CROSSBOW";
    private static String description;
    public static final int DAMAGE = 5;
    public static final int DAMAGE_PER_SECOND = 8;

    public Crossbow() {
        super();
        setDamage(DAMAGE_PER_SECOND);
    }

    public Crossbow(int x, int y, ImageView image) {
        super(x, y, image);
        setDamage(DAMAGE_PER_SECOND);
    }

    public Crossbow(ImageView image) {
        super(image);
        setDamage(DAMAGE_PER_SECOND);
    }

    public static void setDescription(String description) {
        Crossbow.description = description;
    }

    public static double getCost() {
        return cost;
    }

    public static String getDescription() {
        return description;
    }

    public static void setCost(double cost) {
        Crossbow.cost = cost;
        description = "A single-target building with moderate damage output and incredible speed."
                + "\nCost: $" + String.format("%.2f", cost)
                + "\nDamage: 5"
                + "\nDamage per second: 15";
    }

    public String toString() {
        return getX() + " " + getY() + " " + Crossbow.NAME;
    }

}
