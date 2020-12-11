package com.chess.engine.typesofmoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.chess.engine.players.Player;

public class SpecialRookMove extends GenericPieceMove {

    @Override
    public boolean move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {
        boolean moved = super.move(player, board, piece, sourceTile, destinationTile);
        ((Rook)piece).setMoved(moved);
        return moved;
    }
}
