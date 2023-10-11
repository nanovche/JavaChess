package com.chess.engine.board;

import static com.chess.engine.utils.BoardUtils.NUM_OF_COLS;
import static com.chess.engine.utils.BoardUtils.NUM_OF_ROWS;

public class Board {

    private final Tile[][] board;

    public Board() {
        this.board = new Tile[NUM_OF_ROWS][NUM_OF_COLS];
    }

    public Tile[][] getBoard() {
	int x = 2;
        return board;
    }

    //tile has position instance why the parameters
    public void addTile(final Tile tile, final int row, final int col){
        board[row][col] = tile;
    }

    public Tile accessTile(int row, int col){
        return board[row][col];
    }

    public Tile accessTileByLetter(String letter, int row){
        int col = 0;

        if(row == 1){
            row = 7;
        } else if(row == 2){
            row = 6;
        }
        else if(row == 3){
            row = 5;
        }
        else if(row == 4){
            row = 4;
        }
        else if(row == 5){
            row = 3;
        }
        else if(row == 6){
            row = 2;
        }
        else if(row == 7){
            row = 1;
        }
        else if(row == 8){
            row = 0;
        }

        switch(letter){
            case "A":
                col = 0;
                break;
            case "B":
                col = 1;
                break;
            case "C":
                col = 2;
                break;
            case "D":
                col = 3;
                break;
            case "E":
                col = 4;
                break;
            case "F":
                col = 5;
                break;
            case "G":
                col = 6;
                break;
            case "H":
                col = 7;
                break;
        }
        return board[row][col];
    }
}
