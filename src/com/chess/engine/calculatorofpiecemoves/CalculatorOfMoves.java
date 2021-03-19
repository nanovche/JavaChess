package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

import java.util.List;

public interface CalculatorOfMoves {
    List<Tile> getValidDestinationTiles(Board board, Tile sourceTile);
}
