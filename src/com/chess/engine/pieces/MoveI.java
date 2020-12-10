package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;

public interface MoveI {

    void move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile);

}
