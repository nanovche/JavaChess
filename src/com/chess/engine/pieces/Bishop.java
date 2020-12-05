package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.piecemovedeterminators.BishopMoveDeterminator;

public class Bishop extends Piece {

    public Bishop(String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition) {
        super(pieceTitle, alliance, currentPosition, initialPosition);
        setPieceMoveDeterminator(new BishopMoveDeterminator());
    }

    public Bishop(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

    @Override
    public void move(Board board, Tile sourceTile, Tile destinationTile) {

        sourceTile.getPieceFromTile();

        if(destinationTile.isTileOccupied()){
            attackPiece(destinationTile);
        } else {
            destinationTile.addPiece(this);
        }
        setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());
    }
}
