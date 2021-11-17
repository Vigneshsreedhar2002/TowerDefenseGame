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
    private VBox gameButtons;
    @FXML
    private Button towerMenu;
    @FXML
    private Button inventoryMenu;
    @FXML
    private Button startCombat;
    @FXML
    private VBox enemyHealthData;
    @FXML
    private Label basicEnemyHealthLabel;
    @FXML
    private Label strongEnemyHealthLabel;
    @FXML
    private Label bossEnemyHealthLabel;

    private static Tower towerToPlace;
    private static boolean placementDone;


    private Image image;
    private Game game;
    private Player player;
    private Monument monument;
    private ArrayList<Enemy> enemy = new ArrayList<>();

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
                    if ((row == 0 && col == 0) || (row == 0 && col == 8)
                            || (row == 0 && col == 4)) {
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

        GridPane.setRowIndex(enemyHealthData, 0);
        GridPane.setColumnIndex(enemyHealthData, 4);
        enemyHealthData.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        basicEnemyHealthLabel.setFont(Font.font("Courier New", 15));

        strongEnemyHealthLabel.setFont(Font.font("Courier New", 15));

        bossEnemyHealthLabel.setFont(Font.font("Courier New", 15));

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
                            basicEnemyHealthLabel.setText("TROOP HP: " + enemy.get(0).getHealth());
                            strongEnemyHealthLabel.setText("LT. HP: " + enemy.get(1).getHealth());
                            bossEnemyHealthLabel.setText("BOSS HP: " + enemy.get(2).getHealth());
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
     * Gets boss skull image
     * @return skullImage
     */
    private ImageView getBossSkullImage() {
        try {
            URL url = TowerDefenseApplication.class.getResource("assets/icons/bossskull.png");
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
     * Sets monument data
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
     * @throws Exception exception
     */
    @FXML
    private void onStartCombat() throws Exception {
        if (game.hasStarted()) {
            return;
        }
        game.setStarted(true);
        startWave();
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
            towerToPlace.setX(tile.getRow());
            towerToPlace.setY(tile.getCol());
            tile.setOccupied(towerToPlace);
            game.getTowersPlaced().add(towerToPlace);
            game.removeTower(towerToPlace);
            towerToPlace = null;
            InitialGameScreenController.placementDone = true;
            System.out.println(game.getTowersPlaced());
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
        int sleepTimer = enemy.get(0).getSpeed();
        if (!game.hasStarted()) {
            return;
        }
        System.out.println("Start wave");
        for (int i = 0; i < enemy.size(); i++) {
            int finalI = i;
            Task task = new Task<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int row = 3;
                    int col = -1;
                    int nextRow = 3;
                    int nextCol = 0;
                    while (nextRow < NUM_ROWS && nextCol < NUM_COLS
                            && grid[nextRow][nextCol].isPath()
                            && enemy.get(finalI).getHealth() > 0 && monument.getHealth() > 0) {
                        if (finalI == 0 && nextRow == 3 && nextCol == 8) {
                            enemy.get(finalI).setInDamageZone(true);
                        } else if (finalI == 1 && nextRow == 3 && nextCol == 7) {
                            enemy.get(finalI).setInDamageZone(true);
                        } else if (finalI == 2 && nextRow == 3 && nextCol == 6) {
                            enemy.get(finalI).setInDamageZone(true);
                        }
                        final int finalCol = col;
                        final int finalRow = row;
                        final int finalNextCol = nextCol;
                        final int finalNextRow = nextRow;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Run function");
                                if (!enemy.get(finalI).isInDamageZone()) {
                                    grid[finalNextRow][finalNextCol].setOccupied(
                                            enemy.get(finalI));
                                    grid[finalNextRow][finalNextCol].getButton()
                                            .setFocusTraversable(true);
                                    if (enemy.get(finalI) instanceof BasicEnemy) {
                                        grid[finalNextRow][finalNextCol].getButton().setGraphic(
                                                getSkullImage());
                                    } else if (enemy.get(finalI) instanceof StrongEnemy) {
                                        grid[finalNextRow][finalNextCol].getButton().setGraphic(
                                                getRedSkullImage());
                                    } else {
                                        grid[finalNextRow][finalNextCol].getButton().setGraphic(
                                                getBossSkullImage());
                                    }
                                    if (!(finalNextRow == 3 && finalNextCol == 0)) {
                                        if (grid[finalRow][finalCol].occupiedBy()
                                                == enemy.get(finalI)) {
                                            grid[finalRow][finalCol].setOccupied(null);
                                            grid[finalRow][finalCol].getButton()
                                                    .setFocusTraversable(false);
                                            grid[finalRow][finalCol].getButton().setGraphic(
                                                    getWhiteImage());
                                        }
                                    }
                                }
                                towerDamage(finalI, finalNextRow, finalNextCol);
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
                    if (enemy.get(finalI).isInDamageZone()) {
                        grid[3][8].getButton().setStyle("-fx-background-color: #FF0000; "
                                + "-fx-background-radius: 0;");
                        healthLabel.setTextFill(Paint.valueOf("RED"));
                        if (finalI == 0) {
                            System.out.println("damage basic");
                            damageMonument(finalI, 3, 7);
                        } else if (finalI == 1) {
                            System.out.println("damage strong");
                            damageMonument(finalI, 3, 6);
                        } else {
                            System.out.println("damage boss");
                            damageMonument(finalI, 2, 6);
                        }

                    }
                    return monument.getHealth();
                }
            };

            Thread.sleep(sleepTimer);
            Thread th = new Thread(task);
            th.setDaemon(true);
            System.out.println("Thread " + finalI + " starting");
            th.start();
        }

    }

    /**
     * Tower damage
     * @param finalI finalI
     * @param finalNextRow finalNextRow
     * @param finalNextCol finalNextCol
     */
    public void towerDamage(int finalI, int finalNextRow, int finalNextCol) {
        for (Tower tower : game.getTowersPlaced()) {
            if (tower instanceof Cannon && (finalNextRow == tower.getX()
                    || finalNextCol == tower.getY())) {
                System.out.println("Cannon damage");
                System.out.print(GameAdmin.getGame().getDifficulty());
                int difference = enemy.get(finalI).getHealth()
                        - (5 - GameAdmin.getGame().getDifficulty())
                        * Cannon.DAMAGE_PER_SECOND;
                if (difference <= 0) {
                    enemy.get(finalI).setHealth(0);
                    if (enemy.get(finalI) instanceof BasicEnemy) {
                        player.setMoney(player.getMoney() + 30);
                        player.setScore(player.getScore() + 10);
                    } else if (enemy.get(finalI) instanceof StrongEnemy) {
                        player.setMoney(player.getMoney() + 50);
                        player.setScore(player.getScore() + 25);
                    } else if (enemy.get(finalI) instanceof BossEnemy) {
                        player.setMoney(player.getMoney() + 100);
                        player.setScore(player.getScore() + 50);
                    }
                    grid[finalNextRow][finalNextCol].setOccupied(null);
                    grid[finalNextRow][finalNextCol].getButton()
                            .setFocusTraversable(false);
                    grid[finalNextRow][finalNextCol].getButton().setGraphic(
                            getWhiteImage());
                    break;
                } else {
                    enemy.get(finalI).setHealth(difference);
                }
            }  else if (tower instanceof Crossbow
                    && (finalNextRow == tower.getX()
                    || finalNextCol == tower.getY())) {
                System.out.println("Crossbow damage");
                int difference = enemy.get(finalI).getHealth()
                        - (5 - GameAdmin.getGame().getDifficulty())
                        * Crossbow.DAMAGE_PER_SECOND;
                if (difference <= 0) {
                    enemy.get(finalI).setHealth(0);
                    if (enemy.get(finalI) instanceof BasicEnemy) {
                        player.setMoney(player.getMoney() + 30);
                        player.setScore(player.getScore() + 10);
                    } else if (enemy.get(finalI) instanceof StrongEnemy) {
                        player.setMoney(player.getMoney() + 50);
                        player.setScore(player.getScore() + 25);
                    } else if (enemy.get(finalI) instanceof BossEnemy) {
                        player.setMoney(player.getMoney() + 100);
                        player.setScore(player.getScore() + 50);
                    }
                    grid[finalNextRow][finalNextCol].setOccupied(null);
                    grid[finalNextRow][finalNextCol].getButton()
                            .setFocusTraversable(false);
                    grid[finalNextRow][finalNextCol].getButton().setGraphic(
                            getWhiteImage());
                    break;
                } else {
                    enemy.get(finalI).setHealth(difference);
                }
            } else if (tower instanceof Tank
                    && (Math.abs(finalNextRow - tower.getX()) <= 2
                    && Math.abs(finalNextCol - tower.getY()) <= 2)) {
                System.out.println("Tank damage");
                int difference = enemy.get(finalI).getHealth()
                        - (5 - GameAdmin.getGame().getDifficulty())
                        * Tank.DAMAGE_PER_SECOND;
                if (difference <= 0) {
                    enemy.get(finalI).setHealth(0);
                    if (enemy.get(finalI) instanceof BasicEnemy) {
                        player.setMoney(player.getMoney() + 30);
                        player.setScore(player.getScore() + 10);
                    } else if (enemy.get(finalI) instanceof StrongEnemy) {
                        player.setMoney(player.getMoney() + 50);
                        player.setScore(player.getScore() + 25);
                    } else if (enemy.get(finalI) instanceof BossEnemy) {
                        player.setMoney(player.getMoney() + 100);
                        player.setScore(player.getScore() + 50);
                    }
                    grid[finalNextRow][finalNextCol].setOccupied(null);
                    grid[finalNextRow][finalNextCol].getButton()
                            .setFocusTraversable(false);
                    grid[finalNextRow][finalNextCol].getButton().setGraphic(
                            getWhiteImage());
                    break;
                } else {
                    enemy.get(finalI).setHealth(difference);
                }
            }
        }
    }

    /**
     * Damage monument
     * @param finalI finalI
     * @param finalNextRow finalNextRow
     * @param finalNextCol finalNextCol
     */
    private void damageMonument(int finalI, int finalNextRow, int finalNextCol) {
        System.out.println("damaging");
        if (enemy.get(finalI).getHealth() <= 0) {
            if (enemy.get(finalI) instanceof BasicEnemy) {
                player.setMoney(player.getMoney() + 30);
            } else if (enemy.get(finalI) instanceof StrongEnemy) {
                player.setMoney(player.getMoney() + 50);
            } else {
                player.setMoney(player.getMoney() + 100);
            }
            grid[finalNextRow][finalNextCol].setOccupied(null);
            grid[finalNextRow][finalNextCol].getButton().setFocusTraversable(false);
            grid[finalNextRow][finalNextCol].getButton().setGraphic(
                    getWhiteImage());
            return;
        }

        Task task = new Task<Integer>() {
            @Override
            public Integer call() throws Exception {
                while (enemy.get(finalI).getHealth() > 0 && monument.getHealth() > 0) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            towerDamage(finalI, finalNextRow, finalNextCol);
                            monument.setHealth(monument.getHealth()
                                    - enemy.get(finalI).getDamage());
                            if (monument.getHealth() <= 0) {
                                try {
                                    openGameOverScreen();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    Thread.sleep(enemy.get(0).getSpeed());
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