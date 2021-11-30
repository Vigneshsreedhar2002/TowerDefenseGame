package com.example.judy.modules;

public class Monument {

    private int health;
    private final int maxHealth;

    public Monument(int health) {
        this.health = health;
        this.maxHealth = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public int getMaxHealth() {
        return maxHealth;
    }

}
