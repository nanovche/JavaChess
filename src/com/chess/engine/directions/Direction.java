package com.chess.engine.directions;

public class Direction {

    private int rowDir;
    private int colDir;

    public Direction(int rowDir, int colDir) {
        setRowDir(rowDir);
        setColDir(colDir);
    }

    public int getRowDir() {
        return rowDir;
    }
    public int getColDir() {
        return colDir;
    }

    private void setRowDir(int rowDir){
        this.rowDir = rowDir;
    }
    private void setColDir(int colDir){
        this.colDir = colDir;
    }
}
