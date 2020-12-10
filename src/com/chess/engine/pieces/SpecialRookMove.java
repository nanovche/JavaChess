package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;

public class SpecialRookMove extends GenericPieceMove{

    @Override
    public void move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {
        super.move(player, board, piece, sourceTile, destinationTile);
        ((Rook)piece).setMoved(true);
    }

}
