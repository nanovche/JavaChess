package com.chess.engine.piecemovedeterminators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Pawn;

import static com.chess.engine.utils.BoardUtils.FIFTH_RANK;
import static com.chess.engine.utils.BoardUtils.FOURTH_RANK;


public class PawnMoveDeterminator implements PieceMoveDeterminator {

    @Override
    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile) {

        int rowDifference = destinationTile.getPosition().getRow() - sourceTile.getPosition().getRow();
        int colDifference = destinationTile.getPosition().getCol() - sourceTile.getPosition().getCol();

        if(colDifference == 0){
            if(Math.abs(rowDifference) == 2){
                return !board.accessTile(destinationTile.getPosition().getRow() + sourceTile.getPiece().getPieceInterval(), destinationTile.getPosition().getCol()).isTileOccupied()
                        && !board.accessTile(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol()).isTileOccupied();
            } else if(Math.abs(rowDifference) == 1){
                return !board.accessTile(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol()).isTileOccupied();
            }
        } else {
            if(destinationTile.isTileOccupied()){
                return alliance != destinationTile.getPiece().getAlliance();
            }
        }

        //
        Tile enPassantTile;
        if(sourceTile.getPosition().getRow() == FOURTH_RANK || sourceTile.getPosition().getRow() == FIFTH_RANK){

            enPassantTile = board.accessTile(sourceTile.getPosition().getRow(),
                    destinationTile.getPosition().getCol());
            if(enPassantTile.isTileOccupied()){
                if(enPassantTile.getPiece() instanceof Pawn && enPassantTile.getPiece().getAlliance() != alliance){
                    ((Pawn)sourceTile.getPiece()).setEnPassant(true);
                    return true;
                }
            }
        }
        return false;
    }
}
