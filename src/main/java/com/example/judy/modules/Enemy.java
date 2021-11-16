package com.example.judy.modules;

public abstract class Enemy {

    private int speed;
    private int health;
    private int damage;
    private boolean inDamageZone;

    public Enemy() {
        this.speed = 0;
        this.health = 0;
        this.damage = 0;
        this.inDamageZone = false;
    }

    public Enemy(int speed, int health, int damage) {
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.inDamageZone = false;
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

    public boolean isInDamageZone() {
        return inDamageZone;
    }

    public void setInDamageZone(boolean inDamageZone) {
        this.inDamageZone = inDamageZone;
    }
}
