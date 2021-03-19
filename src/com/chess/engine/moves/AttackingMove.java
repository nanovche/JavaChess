package com.chess.engine.moves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

public class AttackingMove extends RegularMove implements MoveTypeI{

    /* method implements attacking move
    *  - removes attacked piece from play
    *  - calls super to perform the moving of the piece */

    @Override
    public void move(Tile sourceTile, Tile destinationTile, Board... board) {
        destinationTile.removePieceFromPlay();
        super.move(sourceTile, destinationTile, board);
    }
}
