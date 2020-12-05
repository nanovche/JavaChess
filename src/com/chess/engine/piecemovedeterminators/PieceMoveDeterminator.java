package com.chess.engine.piecemovedeterminators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

//make interface

public interface PieceMoveDeterminator {

    boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile);

}
