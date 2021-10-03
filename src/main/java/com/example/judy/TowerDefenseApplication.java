package com.example.judy;

import com.example.judy.controllers.InitialConfigScreenController;
import com.example.judy.controllers.WelcomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TowerDefenseApplication extends Application {



    @Override
    public void start(Stage stage) throws Exception {

        // getting loader and a pane for the welcome screen
        FXMLLoader welcomePaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/welcome-screen.fxml"));
        Parent welcomePane = welcomePaneLoader.load();
        Scene welcomeScene = new Scene(welcomePane, 1280, 720);
        welcomeScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        stage.setTitle("SAVE JUDY");
        stage.setScene(welcomeScene);
        stage.show();

    }

    /**
     *
     * Main method of the application
     *
     * @param args args
     */
    public static void main(String[] args) {
        launch();
    }
}