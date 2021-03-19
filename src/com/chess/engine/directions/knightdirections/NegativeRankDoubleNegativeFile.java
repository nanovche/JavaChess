package com.chess.engine.directions.knightdirections;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.NEGATIVE;
import static com.chess.engine.utils.BoardUtils.TWO_NEGATIVE;

public class NegativeRankDoubleNegativeFile extends Direction {
    public NegativeRankDoubleNegativeFile() {
        super(NEGATIVE, TWO_NEGATIVE);
    }
}
