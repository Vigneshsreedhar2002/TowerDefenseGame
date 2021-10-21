package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private Button inventoryMenu;
    @FXML
    private Button rotateTop;
    @FXML
    private Button rotateBottom;

    private static Tower towerToPlace;
    private static boolean placementDone;


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
                ColumnConstraints column;
                if (i == 0 || i == 5) {
                    column = new ColumnConstraints(140, 120, Double.MAX_VALUE);
                } else {
                    column = new ColumnConstraints(120, 120, Double.MAX_VALUE);
                }
                gridPane.getColumnConstraints().add(column);
            }
            for (int i = 0; i < 20; i++) {
                RowConstraints row = new RowConstraints(40);
                gridPane.getRowConstraints().add(row);
            }
            updateGameData();
            setEnemyImage();
            setMonumentData();
            setMenuButtons();
            setRotateButtons();
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
                            if (towerToPlace != null) {
                                placeTower();
                            }
                        }
                    });
                    i++;
                    Thread.sleep(1000);
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
        GridPane.setColumnIndex(monumentData, 9);
        monumentImage.setPreserveRatio(true);
        monumentImage.toFront();
    }

    /**
     * Sets the menu data
     */
    private void setMenuButtons() {
        GridPane.setRowIndex(gameButtons, 1);
        GridPane.setColumnIndex(gameButtons, 9);
        towerMenu.setText("Store");
        towerMenu.setPrefWidth(200);
        towerMenu.setStyle("-fx-background-color: #FF0000; ");
        towerMenu.setFont(Font.font("Courier New", 15));
        inventoryMenu.setText("Inventory");
        inventoryMenu.setPrefWidth(200);
        inventoryMenu.setStyle("-fx-background-color: #FFFF00; ");
        inventoryMenu.setFont(Font.font("Courier New", 15));
    }

    /**
     * Sets the rotate buttons
     */
    private void setRotateButtons() {
        GridPane.setRowIndex(rotateTop, 1);
        GridPane.setColumnIndex(rotateTop, 5);
        rotateTop.setText("Rotate");
        rotateTop.setPrefWidth(120);
        rotateTop.setStyle("-fx-background-color: #FF0000; ");
        rotateTop.setFont(Font.font("Courier New", 15));
        GridPane.setRowIndex(rotateBottom, 12);
        GridPane.setColumnIndex(rotateBottom, 3);
        rotateBottom.setText("Rotate");
        rotateBottom.setPrefWidth(120);
        rotateBottom.setStyle("-fx-background-color: #FF0000; ");
        rotateBottom.setFont(Font.font("Courier New", 15));
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

    /**
     * Sets rotate top button
     */
    @FXML
    private void onRotateTop() {
        for (int i = 0; i < game.getTowersPlaced().size(); i++) {
            if (game.getTowersPlaced().get(i).getX() == 1
                    && game.getTowersPlaced().get(i).getY() == 6) {
                game.getTowersPlaced().get(i).getImage().setRotate(
                        game.getTowersPlaced().get(i).getImage().getRotate() + 90);
            }
        }
    }

    /**
     * Sets rotate bottom button
     */
    @FXML
    private void onRotateBottom() {
        for (int i = 0; i < game.getTowersPlaced().size(); i++) {
            if (game.getTowersPlaced().get(i).getX() == 12
                    && game.getTowersPlaced().get(i).getY() == 2) {
                game.getTowersPlaced().get(i).getImage().setRotate(
                        game.getTowersPlaced().get(i).getImage().getRotate() + 90);
            }
        }
    }

    /**
     * Sets the monument data
     *
     * @throws IOException FileNotFoundException
     */
    @FXML
    private void onShowInventoryMenu() throws IOException {
        final Stage inventoryMenu = new Stage();
        inventoryMenu.setTitle("Inventory");
        inventoryMenu.initModality(Modality.APPLICATION_MODAL);
        AnchorPane root = FXMLLoader.load(Objects.requireNonNull(
                TowerDefenseApplication.class.getResource("screens/inventory-menu.fxml")));
        Scene dialogScene = new Scene(root);
        inventoryMenu.setScene(dialogScene);
        inventoryMenu.show();
    }

    /**
     * Places tower on map at (x,y) coordinate where player clicks
     *
     */
    private void placeTower() {
        if (towerToPlace != null && towerToPlace.getImage() != null
                && !InitialGameScreenController.placementDone) {
            EventHandler onClickHandler = new EventHandler<javafx.scene.input.MouseEvent>() {
                public void handle(javafx.scene.input.MouseEvent mouseEvent) {
                    gridPane.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                    double x = Objects.requireNonNull(mouseEvent).getSceneX();
                    double y = Objects.requireNonNull(mouseEvent).getSceneY();
                    int column = (int) x / 120;
                    int row = (int) y / 40;
                    if (checkPlacement(towerToPlace, row, column)) {
                        gridPane.add(towerToPlace.getImage(), column, row);
                        System.out.println("added");
                        game.getTowersPlaced().add(towerToPlace);
                        towerToPlace.setX(row);
                        towerToPlace.setY(column);
                        System.out.println(game.getTowersPlaced());
                        game.removeTower(towerToPlace);
                        towerToPlace = null;
                    } else {
                        towerToPlace = null;
                        Alert alert = new Alert(Alert.AlertType.ERROR,
                                "Place the tower in an empty yellow square!");
                        alert.show();
                    }
                    InitialGameScreenController.placementDone = true;
                }
            };
            gridPane.addEventHandler(MouseEvent.MOUSE_CLICKED, onClickHandler);
        }
    }


    /**
     * Checks if placement is appropriate
     *
     * @param tower tower
     * @param col col
     * @param row row
     * @return if correct placement
     */
    private boolean checkPlacement(Tower tower, int row, int col) {
        if ((row == 12 && col == 2) || (row == 1 && col == 6)) {
            for (int i = 0; i < game.getTowersPlaced().size(); i++) {
                if (game.getTowersPlaced().get(i).getX() == row
                        && game.getTowersPlaced().get(i).getY() == col) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Gets towerToPlace
     *
     * @return towerToPlace
     */
    public static Tower getTowerToPlace() {
        return towerToPlace;
    }

    /**
     *
     * Sets towerToPlace
     *
     * @param towerToPlace towerToPlace
     */
    public static void setTowerToPlace(Tower towerToPlace) {
        InitialGameScreenController.towerToPlace = towerToPlace;
    }

    /**
     *
     * Sets placementDone
     *
     * @param placementDone placementDone
     */
    public static void setPlacementDone(boolean placementDone) {
        InitialGameScreenController.placementDone = placementDone;
    }
}