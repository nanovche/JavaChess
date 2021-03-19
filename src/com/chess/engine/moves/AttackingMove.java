package com.chess.engine.moves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;

public class AttackingMove implements MoveTypeI{

    @Override
    public void move(Tile sourceTile, Tile destinationTile, Board... board) {

        destinationTile.removePieceFromPlay();

        Piece movingPiece = sourceTile.getPieceFromTile();
        destinationTile.addPiece(movingPiece);
        movingPiece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());

    }
}
