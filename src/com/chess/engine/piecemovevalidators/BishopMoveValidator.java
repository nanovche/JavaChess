package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;

public class BishopMoveValidator implements PieceMoveValidator {

    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile, Player... players){

        int currentRow = sourceTile.getPosition().getRow();
        int currentCol = sourceTile.getPosition().getCol();
        int destinationRow = destinationTile.getPosition().getRow();
        int destinationCol = destinationTile.getPosition().getCol();
        int rowDifference = destinationRow - currentRow;
        int colDifference = destinationCol - currentCol;

        if(Math.abs(rowDifference) == Math.abs(colDifference) && rowDifference != 0){

            if(currentRow < destinationRow && currentCol < destinationCol){

                for (int row = currentRow + 1, col = currentCol + 1; row <= destinationRow; row++, col++) {

                    if(board.accessTile(row, col).isTileOccupied()){

                        if(row == destinationRow){
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }
                        return false;
                    }
                }
                return true;
            } else if(currentRow > destinationRow && currentCol > destinationCol){

                for (int row = currentRow - 1, col = currentCol - 1; row >= destinationRow; row--, col--) {

                    if(board.accessTile(row, col).isTileOccupied()){

                        if(row == destinationRow){
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }
                        return false;
                    }
                }
                return true;

            } else if(currentRow < destinationRow && currentCol > destinationCol){

                for (int row = currentRow + 1, col = currentCol - 1; row <= destinationRow; row++, col--) {

                    if(board.accessTile(row, col).isTileOccupied()){

                        if(row == destinationRow){
                            return alliance != board.accessTile(row, col).getPiece().getAlliance();
                        }

                        return false;
                    }

                }
                return true;
            } else if(currentRow > destinationRow && currentCol < destinationCol){

                for (int row = currentRow - 1, col = currentCol + 1; col <= destinationCol; row--, col++) {

                    if(board.accessTile(row, col).isTileOccupied()){

                        if(row == destinationRow){
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
