package com.example.judy.modules;

public class Game {

    private Player player;
    private int difficulty; // between 0 and 2 (easy, medium, hard)

    /**
     * Constructor
     *
     * @param difficulty the game difficulty
     * @param player player
     */
    public Game(int difficulty, Player player) {
        this.difficulty = difficulty;
        this.player = player;
    }

    /**
     * Gets the player of the game.
     *
     * @return player the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the player.
     *
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the player difficulty.
     *
     * @return difficulty the difficulty
     */
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

}
