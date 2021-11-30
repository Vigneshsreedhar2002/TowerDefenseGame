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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class WinScreenController {
    @FXML
    private Label winText;
    @FXML
    private Label statsText;

    @FXML
    private void initialize() {
        winText.setText("Congratulations " + GameAdmin.getGame().getPlayer().getName()
                + ", you win!");
        statsText.setText("\nScore: " + GameAdmin.getGame().getPlayer().getScore()
                + "\nDamage taken: " + (GameAdmin.getGame().getMonument().getMaxHealth()
                - GameAdmin.getGame().getMonument().getHealth())
                + "\nDamage dealt: " + GameAdmin.getGame().getDmgDealt());
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
        Platform.exit();
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
