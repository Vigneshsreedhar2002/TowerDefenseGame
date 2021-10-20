package com.example.judy.modules;

import javafx.scene.shape.Path;

public class EnemyPath {

    Path path;

    public EnemyPath() {
        path = new Path();
    }

    public void setPath(Path pathLine) {
        this.path = pathLine;
    }

    public Path getPath() { return path; }

}
