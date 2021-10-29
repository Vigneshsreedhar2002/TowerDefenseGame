package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button start;


    @FXML
    private void initialize() {
        welcomeText.setText("Welcome to Save Judy! Click START to play.");
    }

    /**
     *
     * Method to switch screens
     *
     * @param actionEvent actionEvent to trigger screen switch
     * @throws IOException if file not found
     */
    public void openNextScene(ActionEvent actionEvent) throws IOException {
        // getting loader and a pane for the initial config screen
        FXMLLoader initialConfigPaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/initial-config-screen.fxml"));
        Parent initialConfigPane = initialConfigPaneLoader.load();
        Scene initialConfigScene = new Scene(initialConfigPane, 1280, 720);
        initialConfigScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(initialConfigScene);
    }

    @FXML
    protected void onStartButtonClick(ActionEvent actionEvent) throws IOException {
        openNextScene(actionEvent);
    }
    @FXML
    public void onMouseDrag() {
        start.setStyle("-fx-background-color: white");
    }

    public void onMouseExited() {
        start.setStyle("-fx-background-color: gold");
    }
}