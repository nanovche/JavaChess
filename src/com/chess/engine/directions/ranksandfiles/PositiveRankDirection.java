package com.chess.engine.directions.ranksandfiles;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.POSITIVE;
import static com.chess.engine.utils.BoardUtils.ZERO;

public class PositiveRankDirection extends Direction {
    public PositiveRankDirection() {
        super(POSITIVE, ZERO);
    }
}
