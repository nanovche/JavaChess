package com.chess.engine.directions.knightdirections;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.POSITIVE;
import static com.chess.engine.utils.BoardUtils.TWO_NEGATIVE;

public class DoubleNegativeRankPositiveFile extends Direction {
    public DoubleNegativeRankPositiveFile() {
        super(TWO_NEGATIVE, POSITIVE);
    }
}
