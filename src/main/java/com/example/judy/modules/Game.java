package com.example.judy.modules;

import java.util.HashMap;

public class Game {

    private Player player;
    private Monument monument;
    private Enemy enemy;
    private EnemyPath path;
    private int difficulty; // between 0 and 2 (easy, medium, hard)
    private int level;
    private HashMap<String, Integer> towers;

    /**
     * Constructor
     *
     * @param difficulty the game difficulty
     * @param player player
     * @throws IllegalArgumentException when difficulty is invalid
     */
    public Game(int difficulty, Player player) throws IllegalArgumentException {
        this.difficulty = difficulty;
        this.level = 0;
        this.player = player;
        this.path = new EnemyPath();
        towers = new HashMap<>(3);
        towers.put(Cannon.NAME, 0);
        towers.put(Crossbow.NAME, 0);
        towers.put(Tank.NAME, 0);
        switch (difficulty) {
        case 0:
            this.player.setMoney(200);
            this.enemy = new Enemy(5, 50);
            this.monument = new Monument(100);
            Cannon.setCost(50);
            Crossbow.setCost(75);
            Tank.setCost(100);
            break;
        case 1:
            this.player.setMoney(150);
            this.enemy = new Enemy(7, 75);
            this.monument = new Monument(75);
            Cannon.setCost(60);
            Crossbow.setCost(85);
            Tank.setCost(110);
            break;
        case 2:
            this.player.setMoney(100);
            this.enemy = new Enemy(10, 100);
            this.monument = new Monument(50);
            Cannon.setCost(70);
            Crossbow.setCost(95);
            Tank.setCost(120);
            break;
        default:
            throw new IllegalArgumentException("Difficulty is not valid");
        }
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

    /**
     * Gets the player level.
     *
     * @return level the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the player level.
     *
     * @param level the player level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the monument.
     *
     * @return monument the monument
     */
    public Monument getMonument() {
        return monument;
    }

    /**
     * Sets the monument.
     *
     * @param monument the monument to set
     */
    public void setMonument(Monument monument) {
        this.monument = monument;
    }

    /**
     * Gets the enemy.
     *
     * @return enemy the enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Sets the enemy.
     *
     * @param enemy the enemy to set
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Gets the path.
     * @return path the path
     */
    public EnemyPath getPath() { return path; }

    /**
     * Sets the path.
     * @param path the path to set
     */
    public void setPath(EnemyPath path) { this.path = path; }
    /**
     * Gets the towers.
     *
     * @return towers towers
     */
    public HashMap<String, Integer> getTowers() {
        return towers;
    }

    /**
     * Add cannon.
     *
     */
    public void addCannon() { towers.put(Cannon.NAME, towers.get(Cannon.NAME) + 1); }

    /**
     * Remove cannon.
     */
    public void removeCannon() {
        if (towers.get(Cannon.NAME) > 0) {
            towers.put(Cannon.NAME, towers.get(Cannon.NAME) - 1);
        }
    }

    /**
     * Add crossbow.
     *
     */
    public void addCrossbow() { towers.put(Crossbow.NAME, towers.get(Crossbow.NAME) + 1); }

    /**
     * Remove crossbow.
     */
    public void removeCrossbow() {
        if (towers.get(Crossbow.NAME) > 0) {
            towers.put(Crossbow.NAME, towers.get(Crossbow.NAME) - 1);
        }
    }

    /**
     * Add tank.
     *
     */
    public void addTank() { towers.put(Tank.NAME, towers.get(Tank.NAME) + 1); }

    /**
     * Remove tank.
     */
    public void removeTank() {
        if (towers.get(Tank.NAME) > 0) {
            towers.put(Tank.NAME, towers.get(Tank.NAME) - 1);
        }
    }
}
