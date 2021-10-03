package com.example.judy.controllers;

import com.example.judy.modules.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class InitialConfigScreenController {
    @FXML
    private Label initialConfigText;

    private Scene nextScene;
    private Player player;
    private Game game;

    @FXML
    private void initialize() {
        initialConfigText.setText("Initial Config Screen");
    }

    /**
     *
     * Method to set next scene
     *
     * @param scene nextScene
     */
    public void setNextScene(Scene scene) {
        nextScene = scene;
    }

    /**
     *
     * Method to switch screens
     *
     * @param actionEvent actionEvent to trigger screen switch
     */
    public void openNextScene(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(nextScene);
    }

    @FXML
    protected void onNextButtonClick(ActionEvent actionEvent) {
        openNextScene(actionEvent);
    }

    public void setGameConfigurations(String name, int difficulty) {
        player = new Player(name);
        game = new Game(difficulty, player);
        GameDataHolder.setGame(game);
    }

}