package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;


public class TowerMenuController {

    @FXML
    private HBox cannon;
    @FXML
    private VBox cannonData;
    @FXML
    private Label cannonName;
    @FXML
    private Text cannonDescription;
    @FXML
    private ImageView cannonImage;

    @FXML
    private HBox crossbow;
    @FXML
    private VBox crossbowData;
    @FXML
    private Label crossbowName;
    @FXML
    private Text crossbowDescription;
    @FXML
    private ImageView crossbowImage;

    @FXML
    private HBox tank;
    @FXML
    private VBox tankData;
    @FXML
    private Label tankName;
    @FXML
    private Text tankDescription;
    @FXML
    private ImageView tankImage;

    private Image image;
    private Game game;


    @FXML
    private void initialize() {
        game = GameAdmin.getGame();
        setCannonData();
        setCrossbowData();
        setTankData();
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

        cannonDescription.setText(Cannon.getDescription());
        cannonDescription.setFill(Color.BLACK);
        cannonDescription.setFont(Font.font("Courier New", 10));
        setCannonImage();
    }

    /**
     * Method to set cannon image
     */
    private void setCannonImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/cannon.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        cannonImage.setImage(image);
        cannonImage.setFitWidth(50);
        cannonImage.setPreserveRatio(true);
        cannonImage.toFront();
    }

    @FXML
    private void onCannonClicked() throws IOException {
        ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert buttonAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you wish to buy a cannon?", yes, no);
        buttonAlert.setTitle("Purchase Confirmation");
        buttonAlert.setHeaderText("Cannon Purchase Confirmation");
        ImageView cannonImageAlert = new ImageView();
        buttonAlert.setGraphic(getCannonImageAlert(cannonImageAlert));
        Optional<ButtonType> result = buttonAlert.showAndWait();
        if (result.isPresent() && result.get().getButtonData().toString().equals("OK_DONE")) {
            if (game.getPlayer().getMoney() >= Cannon.getCost()) {
                game.getPlayer().setMoney(game.getPlayer().getMoney() - Cannon.getCost());
                game.addCannon();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You don't have enough money!");
                alert.show();
            }
            openGameScreen();
        }
    }

    /**
     * Method to set cannon alert image
     * @param cannonImageAlert cannonImage
     * @return cannonImageAlert
     */
    private ImageView getCannonImageAlert(ImageView cannonImageAlert) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/cannon.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        cannonImageAlert.setImage(image);
        cannonImageAlert.setFitWidth(50);
        cannonImageAlert.setPreserveRatio(true);
        return cannonImageAlert;
    }

    /**
     * Method to set crossbow data
     */
    private void setCrossbowData() {
        setCrossbowImage();

        crossbow.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

        crossbowName.setText(Crossbow.NAME);
        crossbowName.setTextFill(Color.web("#000000"));
        crossbowName.setFont(Font.font("Courier New", 15));

        crossbowDescription.setText(Crossbow.getDescription());
        crossbowDescription.setFill(Color.BLACK);
        crossbowDescription.setFont(Font.font("Courier New", 10));
    }

    /**
     * Method to set crossbow image
     */
    private void setCrossbowImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/crossbow.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        crossbowImage.setImage(image);
        crossbowImage.setFitWidth(50);
        crossbowImage.setPreserveRatio(true);
        crossbowImage.toFront();
    }

    @FXML
    private void onCrossbowClicked() throws IOException {
        ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert crossbowAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you wish to buy a crossbow?", yes, no);
        crossbowAlert.setTitle("Purchase Confirmation");
        crossbowAlert.setHeaderText("Crossbow Purchase Confirmation");
        ImageView crossbowImageAlert = new ImageView();
        crossbowAlert.setGraphic(getCrossbowAlertImage(crossbowImageAlert));
        Optional<ButtonType> result = crossbowAlert.showAndWait();
        if (result.isPresent() && result.get().getButtonData().toString().equals("OK_DONE")) {
            if (game.getPlayer().getMoney() >= Crossbow.getCost()) {
                game.getPlayer().setMoney(game.getPlayer().getMoney() - Crossbow.getCost());
                game.addCrossbow();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You don't have enough money!");
                alert.show();
            }
            openGameScreen();
        }
    }

    /**
     * Method to set crossbow alert image
     * @param crossbowImageAlert crossbowImage
     * @return crossbowImageAlert
     */
    private ImageView getCrossbowAlertImage(ImageView crossbowImageAlert) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/crossbow.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        crossbowImageAlert.setImage(image);
        crossbowImageAlert.setFitWidth(50);
        crossbowImageAlert.setPreserveRatio(true);
        return crossbowImageAlert;
    }

    /**
     * Method to set tank data
     */
    private void setTankData() {
        setTankImage();

        tank.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: black;");

        tankName.setText(Tank.NAME);
        tankName.setTextFill(Color.web("#000000"));
        tankName.setFont(Font.font("Courier New", 15));

        tankDescription.setText(Tank.getDescription());
        tankDescription.setFill(Color.BLACK);
        tankDescription.setFont(Font.font("Courier New", 10));
    }

    /**
     * Method to set tank image
     */
    private void setTankImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/tank.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        tankImage.setImage(image);
        tankImage.setFitWidth(50);
        tankImage.setPreserveRatio(true);
        tankImage.toFront();
    }

    @FXML
    private void onTankClicked() throws IOException {
        ButtonType yes = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert tankAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you wish to buy a tank?", yes, no);
        tankAlert.setTitle("Purchase Confirmation");
        tankAlert.setHeaderText("Tank Purchase Confirmation");
        ImageView tankImageAlert = new ImageView();
        tankAlert.setGraphic(getTankAlertImage(tankImageAlert));
        Optional<ButtonType> result = tankAlert.showAndWait();
        if (result.isPresent() && result.get().getButtonData().toString().equals("OK_DONE")) {
            if (game.getPlayer().getMoney() >= Tank.getCost()) {
                game.getPlayer().setMoney(game.getPlayer().getMoney() - Tank.getCost());
                game.addTank();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "You don't have enough money!");
                alert.show();
            }
            openGameScreen();
        }
    }

    /**
     * Method to set tank alert image
     * @param tankImageAlert tankImage
     * @return tankImageAlert
     */
    private ImageView getTankAlertImage(ImageView tankImageAlert) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/tank.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        tankImageAlert.setImage(image);
        tankImageAlert.setFitWidth(50);
        tankImageAlert.setPreserveRatio(true);
        return tankImageAlert;
    }

    /**
     *
     * Method to close tower menu
     *
     * @throws IOException if file not found
     */
    public void openGameScreen() throws IOException {
        // getting loader and a pane for the initial game screen
        FXMLLoader initialGamePaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/initial-game-screen.fxml"));
        Parent initialGamePane = initialGamePaneLoader.load();
        Scene initialGameScene = new Scene(initialGamePane, 1280, 720);
        initialGameScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        initialGameScene.getStylesheets().addAll(Objects.requireNonNull(
                TowerDefenseApplication.class.getResource("assets/style.css")).toExternalForm());
        Stage stage = (Stage) cannon.getScene().getWindow();
        System.out.println(game.getPlayer().getMoney());
        stage.close();
    }


}
