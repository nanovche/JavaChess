package com.chess.engine.moves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

public interface MoveTypeI {

    void move(Tile sourceTile, Tile destinationTile, Board... board);

}
