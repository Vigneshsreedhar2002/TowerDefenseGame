package com.example.judy.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InitialGameScreenController {
    @FXML
    private Label initialGameText;

    private Scene nextScene;

    @FXML
    private void initialize() {
        initialGameText.setText("Initial Game Screen");
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
    protected void onHelloButtonClick() {
        initialGameText.setText("Welcome to JavaFX Application!");
    }
}