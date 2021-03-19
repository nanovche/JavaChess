package com.chess.engine.directions.knightdirections;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.NEGATIVE;
import static com.chess.engine.utils.BoardUtils.TWO_POSITIVE;

public class DoublePositiveRankNegativeFile extends Direction {
    public DoublePositiveRankNegativeFile() {
        super(TWO_POSITIVE, NEGATIVE);
    }
}
