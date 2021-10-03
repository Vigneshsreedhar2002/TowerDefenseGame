package com.example.judy.modules;


public class GameDataHolder {

    public static Game game;

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameDataHolder.game = game;
    }


}
