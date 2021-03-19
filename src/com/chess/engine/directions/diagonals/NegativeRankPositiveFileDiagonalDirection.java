package com.chess.engine.directions.diagonals;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.NEGATIVE;
import static com.chess.engine.utils.BoardUtils.POSITIVE;

public class NegativeRankPositiveFileDiagonalDirection extends Direction {
    public NegativeRankPositiveFileDiagonalDirection() {
        super(NEGATIVE, POSITIVE);
    }
}
