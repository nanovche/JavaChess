package com.chess.engine.piecemovedeterminators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.tileIsInBoard;

public class KnightMoveDeterminator implements PieceMoveDeterminator {

    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile){

        int currentRow = sourceTile.getPosition().getRow();
        int currentCol = sourceTile.getPosition().getCol();

        List<Tile> possibleDestinationTiles = new ArrayList<>();

        if(tileIsInBoard(currentRow + 2, currentCol - 1)){
            possibleDestinationTiles.add(board.accessTile(currentRow + 2, currentCol - 1));
        }
        if(tileIsInBoard(currentRow + 2, currentCol + 1)){
            possibleDestinationTiles.add(board.accessTile(currentRow + 2, currentCol + 1));
        }
        if(tileIsInBoard(currentRow + 1, currentCol - 2)){
            possibleDestinationTiles.add(board.accessTile(currentRow + 1, currentCol - 2));
        }
        if(tileIsInBoard(currentRow + 1, currentCol + 2)){
            possibleDestinationTiles.add(board.accessTile(currentRow + 1, currentCol + 2));
        }
        if(tileIsInBoard(currentRow - 2, currentCol - 1)){
            possibleDestinationTiles.add(board.accessTile(currentRow - 2, currentCol - 1));
        }
        if(tileIsInBoard(currentRow - 2, currentCol + 1)){
            possibleDestinationTiles.add(board.accessTile(currentRow - 2, currentCol + 1));
        }
        if(tileIsInBoard(currentRow - 1, currentCol - 2)){
            possibleDestinationTiles.add(board.accessTile(currentRow - 1, currentCol - 2));
        }
        if(tileIsInBoard(currentRow - 1, currentCol + 2)){
            possibleDestinationTiles.add(board.accessTile(currentRow - 1, currentCol + 2));
        }

        for (Tile possibleDestinationTile: possibleDestinationTiles) {

            if(possibleDestinationTile.getPosition().getRow() == destinationTile.getPosition().getRow() &&
               possibleDestinationTile.getPosition().getCol() == destinationTile.getPosition().getCol()){

                if(destinationTile.isTileOccupied()){
                    return alliance != destinationTile.getPiece().getAlliance();
                }
                return true;
            }
        }
        return false;
    }
}
