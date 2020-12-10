package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;

//make interface

public interface PieceMoveValidator {

    boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile, Player... players);

}
