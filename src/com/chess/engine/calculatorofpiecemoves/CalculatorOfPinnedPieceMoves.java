package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

import java.util.List;

public interface CalculatorOfPinnedPieceMoves {

    List<Tile> getValidDestinationTilesWhenPinned(Board board, Tile tileOfKing, Tile tileOfPinningPiece, Tile tileOfPinnedPiece);

}
