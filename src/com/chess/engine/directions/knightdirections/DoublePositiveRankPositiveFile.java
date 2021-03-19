package com.chess.engine.directions.knightdirections;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.POSITIVE;
import static com.chess.engine.utils.BoardUtils.TWO_POSITIVE;

public class DoublePositiveRankPositiveFile extends Direction {
    public DoublePositiveRankPositiveFile() {
        super(TWO_POSITIVE, POSITIVE);
    }
}
