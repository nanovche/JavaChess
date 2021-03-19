package com.chess.engine.moves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;

public class RegularMove implements MoveTypeI {

    /* method implements regular move:
    *  - removes piece from its source square
    *  - adds it to its destination square
    *  - sets its new position */

    @Override
    public void move(Tile sourceTile, Tile destinationTile, Board... board) {

        Piece movingPiece = sourceTile.getPieceFromTile();
        destinationTile.addPiece(movingPiece);
        movingPiece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());

    }
}
