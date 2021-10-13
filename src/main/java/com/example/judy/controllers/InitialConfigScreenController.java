package com.example.judy.controllers;
import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class InitialConfigScreenController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label chooseText;
    @FXML
    private Label nameText;
    @FXML
    private Label difficultyText;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    @FXML
    private TextField nameInput;
    private String name;
    private int difficulty = -1;

    @FXML
    private void initialize() {
        welcomeText.setText("Welcome to Save Judy!");
        chooseText.setText("Please choose a name and difficulty.");
    }

    /**
     *
     * Method to switch screens
     *
     * @param actionEvent actionEvent to trigger screen switch
     * @throws IOException if file not found
     */
    public void openNextScene(ActionEvent actionEvent) throws IOException {
        // getting loader and a pane for the initial game screen
        FXMLLoader initialGamePaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/initial-game-screen.fxml"));
        Parent initialGamePane = initialGamePaneLoader.load();
        Scene initialGameScene = new Scene(initialGamePane, 1280, 720);
        initialGameScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        initialGameScene.getStylesheets().addAll(Objects.requireNonNull(
                TowerDefenseApplication.class.getResource("assets/style.css")).toExternalForm());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(initialGameScene);
    }

    @FXML
    protected void onStartClick(ActionEvent actionEvent) throws IOException {
        if (setGameConfigurations(name, difficulty)) {
            openNextScene(actionEvent);
        }
    }

    public boolean setGameConfigurations(String name, int difficulty) {
        boolean validName = name != null;
        boolean difficultySelected = difficulty != -1;
        if (!validName && !difficultySelected) {
            welcomeText.setText("Please choose a name and difficulty to continue.");
            chooseText.setText("");
            return false;
        }
        if (!validName) {
            welcomeText.setText("Please enter a name to continue.");
            chooseText.setText("");
            return false;
        }
        if (!difficultySelected) {
            welcomeText.setText("Please choose a difficulty to continue.");
            chooseText.setText("");
            return false;
        }
        Player player = new Player(name);
        Game game = new Game(difficulty, player);
        GameDataHolder.setGame(game);
        return true;
    }

    @FXML
    protected void onNameButtonClick(ActionEvent actionEvent) {
        String name = nameInput.getText();
        nameInput.clear();
        if (name.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Enter a valid name!");
            alert.show();
        } else {
            nameText.setText("Name: " + name);
            this.name = name;
        }
    }

    public void onEasyClick(ActionEvent actionEvent) {
        difficultyText.setText("Difficulty: Easy");
        difficulty = 0;
        easyButton.setStyle("-fx-background-color: white");
        mediumButton.setStyle("-fx-background-color: gold");
        hardButton.setStyle("-fx-background-color: gold");
    }

    public void onMediumClick(ActionEvent actionEvent) {
        difficultyText.setText("Difficulty: Medium");
        difficulty = 1;
        easyButton.setStyle("-fx-background-color: gold");
        mediumButton.setStyle("-fx-background-color: white");
        hardButton.setStyle("-fx-background-color: gold");
    }

    public void onHardClick(ActionEvent actionEvent) {
        difficultyText.setText("Difficulty: Hard");
        difficulty = 2;
        easyButton.setStyle("-fx-background-color: gold");
        mediumButton.setStyle("-fx-background-color: gold");
        hardButton.setStyle("-fx-background-color: white");
    }
}