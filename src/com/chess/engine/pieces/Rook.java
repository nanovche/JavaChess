package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.piecemovedeterminators.RookMoveDeterminator;

public class Rook extends Piece {

    private boolean moved;

    public Rook(String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition) {
        super(pieceTitle, alliance, currentPosition, initialPosition);
        setPieceMoveDeterminator(new RookMoveDeterminator());
    }

    public Rook(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

    //validation
    public boolean hasMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
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
        moved = true;
    }
}
