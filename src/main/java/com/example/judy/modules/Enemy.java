package com.example.judy.modules;

public class Enemy {

    private int speed;
    private int health;
    private int damage;

    public Enemy(int speed, int health, int damage) {
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
