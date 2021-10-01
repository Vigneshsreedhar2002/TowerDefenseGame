package com.example.judy.modules;

public class Enemy {

    private int speed;
    private int health;

    public Enemy(int speed, int health) {
        this.speed = speed;
        this.health = health;
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

}
