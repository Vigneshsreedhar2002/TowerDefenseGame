package com.example.judym2.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InitialConfigScreenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}