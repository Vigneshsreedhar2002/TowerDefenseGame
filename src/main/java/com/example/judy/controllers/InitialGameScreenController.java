package com.example.judy.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InitialGameScreenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}