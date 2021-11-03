package com.example.judy.modules;

import javafx.scene.control.Button;

public class Tile {

    private int row;
    private int col;
    private Button button;
    private boolean isOccupied;
    private boolean isPath;
    private boolean isTowerTerritory;

    public Tile(int row, int col, Button button, boolean isOccupied,
                boolean isPath, boolean isTowerTerritory) {
        this.row = row;
        this.col = col;
        this.button = button;
        this.isOccupied = isOccupied;
        this.isPath = isPath;
        this.isTowerTerritory = isTowerTerritory;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isPath() {
        return isPath;
    }

    public void setPath(boolean path) {
        isPath = path;
    }

    public boolean isTowerTerritory() {
        return isTowerTerritory;
    }

    public void setTowerTerritory(boolean towerTerritory) {
        isTowerTerritory = towerTerritory;
    }
}
