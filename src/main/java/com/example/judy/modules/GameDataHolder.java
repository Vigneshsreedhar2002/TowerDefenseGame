package com.example.judy.modules;


public class GameDataHolder {

    private static Game game;

    /**
     *
     * Gets the current game
     *
     * @return game the current game
     */
    public static Game getGame() {
        return game;
    }

    /**
     *
     * Sets the current game
     *
     * @param game the current game to set
     */
    public static void setGame(Game game) {
        GameDataHolder.game = game;
    }


}
