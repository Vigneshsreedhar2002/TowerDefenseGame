package com.example.judy.controllers;
import com.example.judy.modules.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class InitialConfigScreenController {
    @FXML
    private Label initialConfigText;
    @FXML
    private TextField nameInput;
    @FXML
    private Label nameText;
    @FXML
    private Label difficultyText;
    private Scene nextScene;
    private String name;
    private int difficulty = 0;
    private Player player;
    private Game game;

    @FXML
    private void initialize() {
        initialConfigText.setText("Welcome to Save Judy! Please choose a name and difficulty.");
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
    protected void onStartClick(ActionEvent actionEvent) {
        if (setGameConfigurations(name, difficulty)) {
            openNextScene(actionEvent);
        }
    }

    public boolean setGameConfigurations(String name, int difficulty) {
        if (name == null || difficulty == 0) {
            return false;
        }
        player = new Player(name);
        game = new Game(difficulty, player);
        return true;
    }

    @FXML
    protected void onNameButtonClick(ActionEvent actionEvent) {
        String name = nameInput.getText();
        nameInput.clear();
        if (name.isBlank()) {
            initialConfigText.setText("Please enter a valid name.");
        } else {
            nameText.setText("Name: " + name);
            this.name = name;
        }
    }

    public void onEasyClick(ActionEvent actionEvent) {
        difficultyText.setText("Difficulty: Easy");
        difficulty = 1;
    }

    public void onMediumClick(ActionEvent actionEvent) {
        difficultyText.setText("Difficulty: Medium");
        difficulty = 2;
    }

    public void onHardClick(ActionEvent actionEvent) {
        difficultyText.setText("Difficulty: Hard");
        difficulty = 3;
    }
}