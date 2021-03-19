package com.chess.engine.directions.knightdirections;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.NEGATIVE;
import static com.chess.engine.utils.BoardUtils.TWO_POSITIVE;

public class NegativeRankDoublePositiveFile extends Direction {
    public NegativeRankDoublePositiveFile() {
        super(NEGATIVE, TWO_POSITIVE);
    }
}
