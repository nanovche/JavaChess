package com.chess.engine.typesofmoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;

public class PawnPromotionMove extends GenericPieceMove {

    @Override
    public boolean move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {
        return true;
    }

}
