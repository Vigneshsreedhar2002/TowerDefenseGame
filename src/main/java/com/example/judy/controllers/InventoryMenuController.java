package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;


public class InventoryMenuController {

    @FXML
    private HBox cannon;
    @FXML
    private Label cannonName;
    @FXML
    private Text cannonCount;

    @FXML
    private HBox crossbow;
    @FXML
    private Label crossbowName;
    @FXML
    private Text crossbowCount;

    @FXML
    private HBox tank;
    @FXML
    private Label tankName;
    @FXML
    private Text tankCount;

    private Image image;
    private Game game;


    @FXML
    private void initialize() {
        game = GameAdmin.getGame();
        setCannonData();
        setCrossbowData();
        setTankData();
        updateData();
    }

    /**
     * Method to set cannon data
     */
    private void setCannonData() {
        cannon.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

        cannonName.setText(Cannon.NAME);
        cannonName.setTextFill(Color.web("#000000"));
        cannonName.setFont(Font.font("Courier New", 15));

        cannonCount.setText("You have: " + GameAdmin.getGame().getTowers().get(Cannon.NAME));
        cannonCount.setFill(Color.BLACK);
        cannonCount.setFont(Font.font("Courier New", 10));
    }

    @FXML
    private void onCannonClicked() throws IOException {
        Integer cannonValue = game.getTowers().get(Cannon.NAME);
        if (cannonValue > 0) {
            placeTower(Cannon.NAME);
        }
    }

    /**
     * Method to set crossbow data
     */
    private void setCrossbowData() {

        crossbow.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

        crossbowName.setText(Crossbow.NAME);
        crossbowName.setTextFill(Color.web("#000000"));
        crossbowName.setFont(Font.font("Courier New", 15));

        crossbowCount.setText("You have: " + GameAdmin.getGame().getTowers().get(Crossbow.NAME));
        crossbowCount.setFill(Color.BLACK);
        crossbowCount.setFont(Font.font("Courier New", 10));
    }


    @FXML
    private void onCrossbowClicked() throws IOException {
        Integer crossbowValue = game.getTowers().get(Crossbow.NAME);
        if (crossbowValue > 0) {
            placeTower(Crossbow.NAME);
        }


    }

    /**
     * Method to set tank data
     */
    private void setTankData() {

        tank.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

        tankName.setText(Tank.NAME);
        tankName.setTextFill(Color.web("#000000"));
        tankName.setFont(Font.font("Courier New", 15));

        tankCount.setText("You have: " + GameAdmin.getGame().getTowers().get(Tank.NAME));
        tankCount.setFill(Color.BLACK);
        tankCount.setFont(Font.font("Courier New", 10));
    }


    @FXML
    private void onTankClicked() throws IOException {
        Integer tankValue = game.getTowers().get(Tank.NAME);
        if (tankValue > 0) {
            placeTower(Tank.NAME);
        }
    }

    /**
     *
     * Method to close tower menu
     *
     * @throws IOException if file not found
     */
    private void openGameScreen() throws IOException {
        // getting loader and a pane for the initial game screen
        FXMLLoader initialGamePaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/initial-game-screen.fxml"));
        Parent initialGamePane = initialGamePaneLoader.load();
        Scene initialGameScene = new Scene(initialGamePane, 1280, 720);
        initialGameScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        initialGameScene.getStylesheets().addAll(Objects.requireNonNull(
                TowerDefenseApplication.class.getResource("assets/style.css")).toExternalForm());
        Stage stage = (Stage) cannon.getScene().getWindow();
        stage.close();
    }

    /**
     * Method to set tank image
     * @param tank tank
     */
    private void setTankImage(ImageView tank) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/tank.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        tank.setImage(image);
        tank.setFitWidth(100);
        tank.setPreserveRatio(true);
        tank.toFront();
    }

    /**
     * Method to set cannon image
     * @param cannon cannon
     */
    private void setCannonImage(ImageView cannon) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/cannon.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        cannon.setImage(image);
        cannon.setFitWidth(100);
        cannon.setPreserveRatio(true);
        cannon.toFront();
    }

    /**
     * Method to set crossbow image
     * @param crossbow crossbow
     */
    private void setCrossbowImage(ImageView crossbow) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/crossbow.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        crossbow.setImage(image);
        crossbow.setFitWidth(100);
        crossbow.setPreserveRatio(true);
        crossbow.toFront();
    }

    /**
     * Update data
     *
     */
    private void updateData() {
        if (game.getTowers().get(Cannon.NAME) > 0) {
            cannonCount.setText(cannonCount.getText() + "\nClick to place it on the map!");
        }
        if (game.getTowers().get(Crossbow.NAME) > 0) {
            crossbowCount.setText(crossbowCount.getText() + "\nClick to place it on the map!");
        }
        if (game.getTowers().get(Tank.NAME) > 0) {
            tankCount.setText(tankCount.getText() + "\nClick to place it on the map!");
        }
    }

    /**
     * Places tower on map at (x,y) coordinate where player clicks
     *
     * @param tower to be placed
     * @throws IOException IOException
     */
    private void placeTower(String tower) throws IOException {
        InitialGameScreenController.setPlacementDone(false);
        ImageView imageView = new ImageView();
        Tower towerAdd = null;
        if (tower.equals(Cannon.NAME)) {
            setCannonImage(imageView);
            towerAdd = new Cannon(imageView);
        } else if (tower.equals(Crossbow.NAME)) {
            setCrossbowImage(imageView);
            towerAdd = new Crossbow(imageView);
        } else if (tower.equals(Tank.NAME)) {
            setTankImage(imageView);
            towerAdd = new Tank(imageView);
        }
        if (towerAdd != null) {
            InitialGameScreenController.setTowerToPlace(towerAdd);
        }

        openGameScreen();
    }


}
