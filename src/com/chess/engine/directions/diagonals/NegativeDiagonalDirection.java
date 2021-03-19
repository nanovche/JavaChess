package com.chess.engine.directions.diagonals;

import com.chess.engine.directions.Direction;
import com.chess.engine.utils.BoardUtils;

public class NegativeDiagonalDirection extends Direction {
    public NegativeDiagonalDirection() {
        super(BoardUtils.NEGATIVE, BoardUtils.NEGATIVE);
    }
}
