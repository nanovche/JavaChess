package com.chess.engine.directions.ranksandfiles;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.NEGATIVE;
import static com.chess.engine.utils.BoardUtils.ZERO;

public class NegativeRankDirection extends Direction {
    public NegativeRankDirection() {
        super(NEGATIVE, ZERO);
    }
}
