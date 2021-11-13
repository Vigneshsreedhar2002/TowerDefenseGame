package com.example.judy.modules;

public class StrongEnemy extends Enemy {

    public StrongEnemy(int speed, int health, int damage) {
        super(speed, (int) (health * 1.5), damage);
    }
}
