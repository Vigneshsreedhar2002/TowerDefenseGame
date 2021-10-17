package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class InitialGameScreenController {
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox gameData;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView enemyImage;
    @FXML
    private VBox monumentData;
    @FXML
    private Label healthLabel;
    @FXML
    private ImageView monumentImage;
    @FXML
    private VBox gameButtons;
    @FXML
    private Button towerMenu;
    @FXML
    private HBox towers;
    @FXML
    private ImageView cannonImage;
    @FXML
    private Label cannonNumber;
    @FXML
    private ImageView crossbowImage;
    @FXML
    private Label crossbowNumber;
    @FXML
    private ImageView tankImage;
    @FXML
    private Label tankNumber;



    private Image image;
    private Game game;
    private Player player;
    private Monument monument;


    @FXML
    private void initialize() {

        game = GameAdmin.getGame();
        if (game != null) {
            player = GameAdmin.getGame().getPlayer();
            monument = GameAdmin.getGame().getMonument();

            for (int i = 0; i < 20; i++) {
                ColumnConstraints column = new ColumnConstraints(140);
                gridPane.getColumnConstraints().add(column);
            }
            for (int i = 0; i < 20; i++) {
                RowConstraints row = new RowConstraints(40);
                gridPane.getRowConstraints().add(row);
            }
            updateGameData();
            setEnemyImage();
            setMonumentData();
            setMenuButton();
            updateTowerData();
        }


    }

    /**
     * Constantly updates the game data
     */
    private void updateGameData() {
        GridPane.setRowIndex(gameData, 1);
        GridPane.setColumnIndex(gameData, 0);
        gameData.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        moneyLabel.setTextFill(Color.web("#FFFFFF"));
        moneyLabel.setFont(Font.font("Courier New", 15));

        scoreLabel.setTextFill(Color.web("#FFFFFF"));
        scoreLabel.setFont(Font.font("Courier New", 15));

        levelLabel.setTextFill(Color.web("#FFFFFF"));
        levelLabel.setFont(Font.font("Courier New", 15));

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            moneyLabel.setText("MONEY: $" + String.format("%.2f", player.getMoney()));
                            scoreLabel.setText("SCORE: " + player.getScore());
                            levelLabel.setText("LEVEL: " + game.getLevel());
                        }
                    });
                    i++;
                    Thread.sleep(500);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    /**
     * Sets the enemy image
     */
    private void setEnemyImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/enemy.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        enemyImage.setImage(image);
        enemyImage.setFitWidth(100);
        //Setting the image view parameters
        GridPane.setRowIndex(enemyImage, 9);
        GridPane.setColumnIndex(enemyImage, 0);
        enemyImage.setPreserveRatio(true);
        enemyImage.toFront();
    }

    /**
     * Sets the monument data
     */
    private void setMonumentData() {
        healthLabel.setText("HEALTH: " + monument.getHealth());
        healthLabel.setTextFill(Color.web("#FFFFFF"));
        healthLabel.setStyle("-fx-background-color: green;");
        healthLabel.setFont(Font.font("Courier New", 14));
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/gate.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        monumentImage.setImage(image);
        monumentImage.setFitWidth(90);
        //Setting the image view parameters
        GridPane.setRowIndex(monumentData, 12);
        GridPane.setColumnIndex(monumentData, 8);
        monumentImage.setPreserveRatio(true);
        monumentImage.toFront();
    }

    /**
     * Sets the monument data
     */
    private void setMenuButton() {
        GridPane.setRowIndex(gameButtons, 1);
        GridPane.setColumnIndex(gameButtons, 8);
        towerMenu.setText("Menu");
        towerMenu.setPrefWidth(200);
        towerMenu.setStyle("-fx-background-color: #FFFF00; ");
        towerMenu.setFont(Font.font("Courier New", 15));
    }

    /**
     * Sets the monument data
     * @throws IOException FileNotFoundException
     */
    @FXML
    private void onShowTowerMenu() throws IOException {
        final Stage towerMenu = new Stage();
        towerMenu.setTitle("Tower Menu");
        towerMenu.initModality(Modality.APPLICATION_MODAL);
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull
                (TowerDefenseApplication.class.getResource("screens/tower-menu.fxml")));
        Scene dialogScene = new Scene(root);
        towerMenu.setScene(dialogScene);
        towerMenu.show();
    }

    private void updateTowerData() {
        GridPane.setRowIndex(towers, 16);
        GridPane.setColumnIndex(towers, 0);

        towers.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;" + "-fx-border-color: yellow;");

        setCannonImage();
        setCrossbowImage();
        setTankImage();
        cannonNumber.setTextFill(Color.web("#FFFFFF"));
        cannonNumber.setFont(Font.font("Courier New", 15));
        crossbowNumber.setTextFill(Color.web("#FFFFFF"));
        crossbowNumber.setFont(Font.font("Courier New", 15));
        tankNumber.setTextFill(Color.web("#FFFFFF"));
        tankNumber.setFont(Font.font("Courier New", 15));

        Task task = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                int i = 0;
                while (true) {
                    final int finalI = i;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cannonNumber.setText(game.getTowers().get(Cannon.NAME).toString());
                            crossbowNumber.setText(game.getTowers().get(Crossbow.NAME).toString());
                            tankNumber.setText(game.getTowers().get(Tank.NAME).toString());
                        }
                    });
                    i++;
                    Thread.sleep(500);
                }
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();


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
        cannonImage.setFitWidth(100);
        cannonImage.setPreserveRatio(true);
        cannonImage.toFront();
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
        crossbowImage.setFitWidth(100);
        crossbowImage.setPreserveRatio(true);
        crossbowImage.toFront();
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
        tankImage.setFitWidth(100);
        tankImage.setPreserveRatio(true);
        tankImage.toFront();
    }





}