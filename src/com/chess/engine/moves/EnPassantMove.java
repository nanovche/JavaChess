package com.chess.engine.moves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Pawn;

public class EnPassantMove extends RegularMove implements MoveTypeI {

    /* pawn holds reference to the tile of piece which will be captured
       via en passant */

    @Override
    public void move(Tile sourceTile, Tile destinationTile, Board... board) {
        Tile targetTileOfEnPassantAttack = ((Pawn)sourceTile.getPiece()).getTargetTileOfEnPassantAttack();
        targetTileOfEnPassantAttack.removePieceFromPlay();
        super.move(sourceTile, destinationTile, board);
    }

}
