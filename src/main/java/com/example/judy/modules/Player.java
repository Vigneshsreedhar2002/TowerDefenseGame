package com.example.judy.modules;

public class Player {

    private String name;
    private double money;
    private int health;
    private int score;

    /**
     * Constructor with starting money of 50 coins, 100 HP and 0 score
     *
     * @param name the player name
     */
    public Player(String name) {
        this(name, 50, 100, 0);
    }

    /**
     * Constructor
     *
     * @param name the player name
     * @param money the player money
     * @param health the player health
     * @param score the player score
     */
    public Player(String name, double money, int health, int score) {
        this.name = name;
        this.money = money;
        this.health = health;
        this.score = score;
    }

    /**
     * Gets the player name.
     *
     * @return name the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player name.
     *
     * @param name the player name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player difficulty.
     *
     * @return money the money
     */
    public double getMoney() {
        return money;
    }

    /**
     * Sets the player money.
     *
     * @param money the player money to set
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * Gets the player health.
     *
     * @return health the player health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the player health.
     *
     * @param health the player health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the player score.
     *
     * @return score the player score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the player score.
     *
     * @param score the player score to set
     */
    public void setScore(int score) {
        this.score = score;
    }


}
