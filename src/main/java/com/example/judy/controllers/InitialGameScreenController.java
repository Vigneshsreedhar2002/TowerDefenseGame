package com.example.judy.controllers;

import com.example.judy.TowerDefenseApplication;
import com.example.judy.modules.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Label healthLabel;
    @FXML
    private Label enemyHealthLabel;
    @FXML
    private VBox gameButtons;
    @FXML
    private Button towerMenu;
    @FXML
    private Button inventoryMenu;
    @FXML
    private Button startCombat;

    private static Tower towerToPlace;
    private static boolean placementDone;


    private Image image;
    private Game game;
    private Player player;
    private Monument monument;
    private ArrayList<Enemy> enemy = new ArrayList<>();

    private boolean inDamageZone;

    public static final int NUM_ROWS = 5;
    public static final int NUM_COLS = 9;
    public static final int TILE_WIDTH = 140;
    private Tile[][] grid;



    @FXML
    private void initialize() {
        System.out.println("initialize");
        game = GameAdmin.getGame();
        if (game != null) {
            HashMap<Integer, ArrayList<Integer>> path = new HashMap<>();
            HashMap<Integer, ArrayList<Integer>> excluded = new HashMap<>();
            player = GameAdmin.getGame().getPlayer();
            monument = GameAdmin.getGame().getMonument();
            enemy = GameAdmin.getGame().getEnemy();

            grid = new Tile[NUM_ROWS][NUM_COLS];

            ArrayList<Integer> onePath = new ArrayList<>();
            onePath.add(2);
            onePath.add(3);
            onePath.add(4);
            onePath.add(5);
            onePath.add(6);
            path.put(1, onePath);

            ArrayList<Integer> twoPath = new ArrayList<>();
            twoPath.add(2);
            twoPath.add(6);
            path.put(2, twoPath);

            ArrayList<Integer> threePath = new ArrayList<>();
            threePath.add(0);
            threePath.add(1);
            threePath.add(2);
            threePath.add(6);
            threePath.add(7);
            threePath.add(8);
            path.put(3, threePath);

            ArrayList<Integer> zeroExcluded = new ArrayList<>();
            zeroExcluded.add(1);
            zeroExcluded.add(7);
            excluded.put(0, zeroExcluded);

            ArrayList<Integer> oneTwoExcluded = new ArrayList<>();
            oneTwoExcluded.add(0);
            oneTwoExcluded.add(8);
            excluded.put(1, oneTwoExcluded);
            excluded.put(2, oneTwoExcluded);

            ArrayList<Integer> threeExcluded = new ArrayList<>();
            threeExcluded.add(4);
            excluded.put(3, threeExcluded);

            ArrayList<Integer> fourExcluded = new ArrayList<>();
            fourExcluded.add(3);
            fourExcluded.add(4);
            fourExcluded.add(5);
            excluded.put(4, fourExcluded);

            for (int i = 0; i < NUM_COLS; i++) {
                ColumnConstraints column = new ColumnConstraints(TILE_WIDTH);
                gridPane.getColumnConstraints().add(column);
            }
            for (int i = 0; i < NUM_ROWS; i++) {
                RowConstraints row = new RowConstraints(TILE_WIDTH);
                gridPane.getRowConstraints().add(row);
            }
            for (int row = 0; row < NUM_ROWS; row++) {
                for (int col = 0; col < NUM_COLS; col++) {
                    if ((row == 0 && col == 0) || (row == 0 && col == 8)) {
                        continue;
                    }
                    if (path.containsKey(row) && path.get(row).contains(col)) {
                        Button tileButton = new Button();
                        tileButton.setId(row + "" + col);
                        tileButton.setPrefWidth(TILE_WIDTH);
                        tileButton.setPrefHeight(TILE_WIDTH);
                        tileButton.setGraphic(getWhiteImage());
                        tileButton.setStyle("-fx-background-color: #FFFFFF; "
                                + "-fx-background-radius: 0;");
                        Tile tile = new Tile(row, col, tileButton, false,
                                true, false);
                        tile.getButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                onNotTowerTileClicked(tile);
                            }
                        });
                        gridPane.add(tile.getButton(), tile.getCol(), tile.getRow());
                        grid[row][col] = tile;
                    } else if (excluded.containsKey(row) && excluded.get(row).contains(col)) {
                        Button tileButton = new Button();
                        tileButton.setId(row + "" + col);
                        tileButton.setPrefWidth(TILE_WIDTH);
                        tileButton.setPrefHeight(TILE_WIDTH);
                        ImageView xImage = new ImageView();
                        tileButton.setGraphic(getXImage(xImage));
                        tileButton.setStyle("-fx-background-color: transparent; "
                                + "-fx-border-color: transparent; "
                                + "-fx-background-radius: 0;");
                        Tile tile = new Tile(row, col, tileButton, null,
                                false, false);
                        tile.getButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                onNotTowerTileClicked(tile);
                            }
                        });
                        gridPane.add(tile.getButton(), tile.getCol(), tile.getRow());
                        grid[row][col] = tile;
                    } else {
                        Button tileButton = new Button();
                        tileButton.setId(row + "" + col);
                        tileButton.setPrefWidth(TILE_WIDTH);
                        tileButton.setPrefHeight(TILE_WIDTH);
                        tileButton.setStyle("-fx-background-color: transparent; "
                                + "-fx-border-color: transparent; "
                                + "-fx-background-radius: 0;");
                        Tile tile = new Tile(row, col, tileButton, null,
                                false, true);
                        tile.getButton().setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                onTowerTileClicked(tile);
                            }
                        });
                        gridPane.add(tile.getButton(), tile.getCol(), tile.getRow());
                        grid[row][col] = tile;
                    }
                }
            }

            placeMonument();
            setMenuButtons();
            updateGameData();
        }



    }

    /**
     * Constantly updates the game data
     */
    private void updateGameData() {
        GridPane.setRowIndex(gameData, 0);
        GridPane.setColumnIndex(gameData, 0);
        gameData.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        moneyLabel.setFont(Font.font("Courier New", 15));

        scoreLabel.setFont(Font.font("Courier New", 15));

        levelLabel.setFont(Font.font("Courier New", 15));

        healthLabel.setFont(Font.font("Courier New", 15));

        enemyHealthLabel.setFont(Font.font("Courier New", 15));

        Task task = new Task<Integer>() {
            @Override
            public Integer call() throws Exception {
                while (monument.getHealth() > 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            moneyLabel.setText("MONEY: $"
                                    + String.format("%.2f", player.getMoney()));
                            scoreLabel.setText("SCORE: "
                                    + player.getScore());
                            levelLabel.setText("LEVEL: "
                                    + game.getLevel());
                            healthLabel.setText("HP: " + monument.getHealth());
                            enemyHealthLabel.setText("ENEMY HP: " + enemy.get(0).getHealth());
                        }
                    });
                    Thread.sleep(100);
                }
                return monument.getHealth();
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();

    }

    /**
     * Gets skull image
     * @return skullImage
     */
    private ImageView getSkullImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/skull.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            System.out.println("error");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        ImageView skullImage = new ImageView();
        skullImage.setImage(image);
        skullImage.setFitWidth(120);
        skullImage.setPreserveRatio(true);
        return skullImage;
    }

    /**
     * Gets red skull image
     * @return skullImage
     */
    private ImageView getRedSkullImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/redskull.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            System.out.println("error");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        ImageView skullImage = new ImageView();
        skullImage.setImage(image);
        skullImage.setFitWidth(120);
        skullImage.setPreserveRatio(true);
        return skullImage;
    }

    /**
     * Places the monument
     */
    private void placeMonument() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/gate.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        ImageView monumentImage = new ImageView();
        monumentImage.setImage(image);
        monumentImage.setFitWidth(120);
        monumentImage.setPreserveRatio(true);
        grid[3][8].getButton().setGraphic(monumentImage);
        grid[3][8].setOccupied(monument);
    }

    /**
     * Gets x image
     * @param xImage xImage
     * @return xImage
     */
    private ImageView getXImage(ImageView xImage) {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/images/X.png");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        xImage.setImage(image);
        xImage.setFitWidth(120);
        xImage.setPreserveRatio(true);
        return xImage;
    }

    /**
     * Gets white image
     * @return whiteImage
     */
    private ImageView getWhiteImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/images/white.jpeg");
            image = new Image(String.valueOf(url));
        } catch (IllegalArgumentException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error in accessing assets");
            alert.show();
        }
        ImageView whiteImage = new ImageView();
        whiteImage.setImage(image);
        whiteImage.setFitWidth(120);
        whiteImage.setPreserveRatio(true);
        return whiteImage;
    }

    /**
     * Sets the menu data
     */
    private void setMenuButtons() {
        GridPane.setRowIndex(gameButtons, 0);
        GridPane.setColumnIndex(gameButtons, 8);
        towerMenu.setFont(Font.font("Courier New", 15));
        inventoryMenu.setFont(Font.font("Courier New", 15));
        startCombat.setFont(Font.font("Courier New", 15));
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
     * Starts combat
     */
    @FXML
    private void onStartCombat() throws Exception {
        if (game.hasStarted()) {
            return;
        }
        System.out.println("start");
        grid[3][0].getButton().setGraphic(getSkullImage());
        grid[3][0].setOccupied(enemy.get(0));
        game.setStarted(true);
        System.out.println(game.hasStarted());
        startWave();
        System.out.println("here");
    }


    /**
     * On tower tile clicked
     *
     * @param tile tile
     */
    public void onTowerTileClicked(Tile tile) {
        System.out.println("clicked");
        if (towerToPlace != null && towerToPlace.getImage() != null
                && !InitialGameScreenController.placementDone && tile.occupiedBy() == null) {
            tile.getButton().setGraphic(towerToPlace.getImage());
            tile.setOccupied(towerToPlace);
            game.getTowersPlaced().add(towerToPlace);
            game.removeTower(towerToPlace);
            towerToPlace = null;
            InitialGameScreenController.placementDone = true;
        } else if (towerToPlace != null && towerToPlace.getImage() != null
                && !InitialGameScreenController.placementDone && tile.occupiedBy() != null) {
            onNotTowerTileClicked(tile);
        } else if (tile.occupiedBy() != null) {
            System.out.println("occupied");
            ImageView graphic = (ImageView) tile.getButton().getGraphic();
            graphic.setRotate(graphic.getRotate() + 90);
            tile.getButton().setGraphic(graphic);
        }
    }

    /**
     * On path/excluded tile clicked
     *
     * @param tile tile
     */
    public void onNotTowerTileClicked(Tile tile) {
        if (towerToPlace != null && towerToPlace.getImage() != null
                && !InitialGameScreenController.placementDone) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Place the tower in an empty blue spot!");
            alert.show();
            towerToPlace = null;
            InitialGameScreenController.placementDone = true;
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

    private void startWave() throws Exception {
        int sleepTimer = 0;
        if (!game.hasStarted()) {
            return;
        }
        System.out.println("Start wave");
        for (int i = 0; i < enemy.size(); i++) {
            Thread.sleep(sleepTimer);
            sleepTimer += enemy.get(i).getSpeed();
            int finalI = i;
            Task task = new Task<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int row = 3;
                    int col = -1;
                    int nextRow = 3;
                    int nextCol = 0;
                    while (nextRow < NUM_ROWS && nextCol < NUM_COLS && grid[nextRow][nextCol].isPath()
                            && enemy.get(finalI).getHealth() > 0 && monument.getHealth() > 0) {
                        System.out.println("Loop: Enemy: " + enemy.get(finalI).getClass() + nextRow + " " + nextCol);
                        if (nextRow == 3 && nextCol == 7) {
                            inDamageZone = true;
                        }
                        final int finalCol = col;
                        final int finalRow = row;
                        final int finalNextCol = nextCol;
                        final int finalNextRow = nextRow;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                grid[finalNextRow][finalNextCol].setOccupied(enemy.get(finalI));
                                grid[finalNextRow][finalNextCol].getButton().setFocusTraversable(true);
                                if (enemy.get(finalI) instanceof BasicEnemy) {
                                    grid[finalNextRow][finalNextCol].getButton().setGraphic(
                                            getSkullImage());
                                } else {
                                    grid[finalNextRow][finalNextCol].getButton().setGraphic(
                                            getRedSkullImage());
                                }
                                if (!(finalNextRow == 3 && finalNextCol == 0)) {
                                    if (grid[finalRow][finalCol].occupiedBy() == enemy.get(finalI)) {
                                        grid[finalRow][finalCol].setOccupied(null);
                                        grid[finalRow][finalCol].getButton().setFocusTraversable(false);
                                        grid[finalRow][finalCol].getButton().setGraphic(
                                                getWhiteImage());
                                    }
                                }

                            }
                        });
                        if (nextRow == 3 && nextCol < 2) {
                            nextCol++;
                        } else if (nextRow > 1 && nextCol == 2) {
                            nextRow--;
                        } else if (nextRow == 1 && nextCol < 6) {
                            nextCol++;
                        } else if (nextRow < 3 && nextCol == 6) {
                            nextRow++;
                        } else if (nextRow == 3 && nextCol < 7) {
                            nextCol++;
                        } else {
                            break;
                        }
                        if (row == 3 && col < 2) {
                            col++;
                        } else if (row > 1 && col == 2) {
                            row--;
                        } else if (row == 1 && col < 6) {
                            col++;
                        } else if (row < 3 && col == 6) {
                            row++;
                        } else if (row == 3 && col < 6) {
                            col++;
                        } else {
                            break;
                        }
                        Thread.sleep(enemy.get(finalI).getSpeed());
                    }
                    System.out.println("end of loop");
                    System.out.println(inDamageZone);
                    if (inDamageZone) {
                        enemy.get(finalI).setInLine(true);
                        grid[3][8].getButton().setStyle("-fx-background-color: #FF0000; "
                                + "-fx-background-radius: 0;");
                        healthLabel.setTextFill(Paint.valueOf("RED"));
                        damageMonument();
                    }
                    return monument.getHealth();
                }
            };
            Thread th = new Thread(task);
            th.setDaemon(true);
            th.start();
        }

    }

    /**
     * Damage monument
     */
    private void damageMonument() {
        System.out.println("damaging");
        Task task = new Task<Integer>() {
            @Override
            public Integer call() throws Exception {
                while (enemy.get(0).getHealth() > 0 && monument.getHealth() > 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            monument.setHealth(monument.getHealth() - enemy.get(0).getDamage());
                            if (monument.getHealth() <= 0) {
                                try {
                                    openGameOverScreen();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    Thread.sleep(2000);
                }
                return monument.getHealth();
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    public void openGameOverScreen() throws IOException {
        // getting loader and a pane for the initial game screen
        FXMLLoader initialGamePaneLoader = new FXMLLoader(
                TowerDefenseApplication.class.getResource("screens/game-over-screen.fxml"));
        Parent initialGamePane = initialGamePaneLoader.load();
        Scene initialGameScene = new Scene(initialGamePane, 1260, 700);
        initialGameScene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        initialGameScene.getStylesheets().addAll(Objects.requireNonNull(
                TowerDefenseApplication.class.getResource("assets/style.css")).toExternalForm());
        Stage stage = (Stage) gridPane.getScene().getWindow();
        stage.setScene(initialGameScene);
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

    /**
     * Gets grid
     *
     * @return grid
     */
    public Tile[][] getGrid() {
        return grid;
    }
}