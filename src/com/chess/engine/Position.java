package com.chess.engine;

public class Position {

    // type of var with less memory // short/byte?
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }


    //possible check for input?
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    //further improvement?
    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Position){
            Position that = (Position) obj;

            return this.row == that.row && this.col == that.col;
        } else {
            return false;
        }
    }
    //implement hashcode?
    //implement to string?
}
