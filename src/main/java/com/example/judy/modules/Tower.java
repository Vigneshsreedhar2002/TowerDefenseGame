package com.example.judy.modules;

import javafx.scene.image.ImageView;

public abstract class Tower {

    public static final String NAME = "TOWER";
    public static final String DESCRIPTION = "An abstract tower";
    private static double cost;

    private int x;
    private int y;
    private int damage;
    private ImageView image;

    public Tower() {

    }

    public Tower(ImageView image) {
        this.image = image;
    }

    public Tower(int x, int y, ImageView image) {
        this.x = x;
        this.y = y;
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public String toString() {
        return x + " " + y;
    }

    public static double getCost() {
        return cost;
    }

    public static void setCost(double cost) {
        Tower.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
