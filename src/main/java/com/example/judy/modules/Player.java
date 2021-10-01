package com.example.judy.modules;

public class Player {

    private String name;
    private int difficulty; // between 0 and 2 (easy, medium, hard)
    private double money; // starting money of 50 coins


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

    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the player difficulty.
     *
     * @param difficulty the player difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

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


}
