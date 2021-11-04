package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
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
        gameOverText.setText("Your monument has reached 0 health. It was an outstanding effort! Click 'restart' to " +
                "again or 'close' to exit application");
    }

    /**
     * method to return to welcome screen
     * @param actionEvent
     * @throws IOException
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
    @FXML
    protected void onCloseButtonClick (ActionEvent actionEvent) throws IOException {
        close.setOnAction(e -> Platform.exit());
    }

    @FXML
    protected void onRestartButtonClick(ActionEvent actionEvent) throws IOException {
        openWelcomeScreen(actionEvent);
    }

    @FXML
    public void onRestartMouseDrag() {
        restart.setStyle("-fx-background-color: white");
    }

    @FXML
    public void onRestartMouseExited() {
        restart.setStyle("-fx-background-color: gold");
    }

    @FXML
    public void onCloseMouseDrag() {
        close.setStyle("-fx-background-color: white");
    }

    @FXML
    public void onCloseMouseExited() {
        close.setStyle("-fx-background-color: gold");
    }
}
