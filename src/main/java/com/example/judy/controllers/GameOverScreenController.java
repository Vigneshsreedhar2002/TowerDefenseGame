package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.GameAdmin;
import javafx.application.Platform;
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

public class GameOverScreenController {
    @FXML
    private Label gameOverText;
    @FXML
    private Button restart;
    @FXML
    private Button close;

    @FXML
    private void initialize() {
        gameOverText.setText("You lost! The enemy has captured Judy!");
    }

    /**
     * Method to return to welcome screen
     * @param actionEvent actionEvent
     * @throws IOException IOException
     */
    public void openWelcomeScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader welcomePaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/welcome-screen.fxml"));
        Parent welcomePane = welcomePaneLoader.load();
        Scene welcomeConfigScene = new Scene(welcomePane, 1260, 700);
        welcomeConfigScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(welcomeConfigScene);
    }

    /**
     * Method to close screen
     * @param actionEvent actionEvent
     */
    @FXML
    protected void onCloseButtonClick(ActionEvent actionEvent) {
        close.setOnAction(e -> Platform.exit());
    }

    /**
     * Onclick restart button
     * @param actionEvent actionEvent
     * @throws IOException IOException
     */
    @FXML
    protected void onRestartButtonClick(ActionEvent actionEvent) throws IOException {
        GameAdmin.setGame(null);
        openWelcomeScreen(actionEvent);
    }

}
