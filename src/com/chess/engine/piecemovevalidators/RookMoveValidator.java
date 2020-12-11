package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;


public class RookMoveValidator implements PieceMoveValidator {

    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile) {

        if (sourceTile.getPosition().getRow() == destinationTile.getPosition().getRow()) {

            int row = destinationTile.getPosition().getRow();
            int currentCol = sourceTile.getPosition().getCol();
            int destinationCol = destinationTile.getPosition().getCol();

            if (currentCol < destinationCol) {

                for (int col = currentCol + 1; col <= destinationCol; col++) {

                    if (board.accessTile(row, col).isTileOccupied()) {

                        if (col == destinationCol) {
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }
                        return false;
                    }
                }
                return true;

            } else if (currentCol > destinationCol) {

                for (int col = currentCol - 1; col >= destinationCol; col--) {

                    if (board.accessTile(row, col).isTileOccupied()) {

                        if (col == destinationCol) {
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }
                        return false;
                    }
                }
                return true;
            }
        }
        else if (sourceTile.getPosition().getCol() == destinationTile.getPosition().getCol()) {

            int currentRow = sourceTile.getPosition().getRow();
            int destinationRow = destinationTile.getPosition().getRow();
            int col = destinationTile.getPosition().getCol();

            if (currentRow < destinationRow) {

                for (int row = currentRow + 1; row <= destinationRow; row++) {

                    if (board.accessTile(row, col).isTileOccupied()) {

                        if (row == destinationRow) {
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }
                        return false;
                    }
                }
                return true;
            } else if (currentRow > destinationRow) {


                for (int row = currentRow - 1; row >= destinationRow; row--) {

                    if (board.accessTile(row, col).isTileOccupied()) {

                        if (row == destinationRow) {
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

}
