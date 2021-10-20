package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private EnemyPath path;


    @FXML
    private void initialize() {

        game = GameAdmin.getGame();
        if (game != null) {
            player = GameAdmin.getGame().getPlayer();
            monument = GameAdmin.getGame().getMonument();
            path = GameAdmin.getGame().getPath();

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
            setPathComponents();
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
                            moneyLabel.setText("MONEY: $"
                                    + String.format("%.2f", player.getMoney()));
                            scoreLabel.setText("SCORE: "
                                    + player.getScore());
                            levelLabel.setText("LEVEL: "
                                    + game.getLevel());
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
     * Sets the rectangles that make up the path
     */
    private void setPathComponents() {
        path.getPath().getElements().add(new MoveTo(0.0f, 500.0f));
        path.getPath().getElements().add(new HLineTo(300.0f));
    }

    /**
     * Sets the monument data
     *
     * @throws IOException FileNotFoundException
     */
    @FXML
    private void onShowTowerMenu() throws IOException {
        final Stage towerMenu = new Stage();
        towerMenu.setTitle("Tower Menu");
        towerMenu.initModality(Modality.APPLICATION_MODAL);
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                TowerDefenseApplication.class.getResource("screens/tower-menu.fxml")));
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

        setCannonImage(cannonImage);
        setCrossbowImage(crossbowImage);
        setTankImage(tankImage);
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

    @FXML
    private void onCannonClicked() throws IOException {
        Integer cannonValue = game.getTowers().get(Cannon.NAME);
        if (cannonValue > 0) {
            placeTower(new Cannon());
        }
        game.removeCannon();
    }

    /**
     * Method to set crossbow image
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

    @FXML
    private void onCrossbowClicked() throws IOException {
        Integer crossbowValue = game.getTowers().get(Crossbow.NAME);
        if (crossbowValue > 0) {
            placeTower(new Crossbow());
        }
        game.removeCrossbow();
    }

    /**
     * Method to set tank image
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

    @FXML
    private void onTankClicked() throws IOException {
        Integer tankValue = game.getTowers().get(Tank.NAME);
        if (tankValue > 0) {
            placeTower(new Tank());
        }
        game.removeTank();
    }

    /**
     * Places tower on map at (x,y) coordinate where player clicks
     *
     * @param tower to be placed
     */
    private void placeTower(Tower tower) {
        System.out.println("Tower " + tower.toString() + " is being placed");
        ImageView image = new ImageView();
        if (tower instanceof Cannon) {
            setCannonImage(image);
        } else if (tower instanceof Crossbow) {
            setCrossbowImage(image);
        } else if (tower instanceof Tank) {
            setTankImage(image);
        }

        gridPane.setOnMouseClicked(
                mouseEvent -> {
                    System.out.println("MouseEvent ran");
                    image.setX(mouseEvent.getSceneX());
                    image.setY(mouseEvent.getSceneY());
                    int column = (int) image.getX() / 140;
                    int row = (int) image.getY() / 40;
                    gridPane.add(image, column, row);
                    gridPane.setOnMouseClicked(null);
                }
        );
        System.out.println("Finished placing tower");
    }
}