package com.chess.engine.directions.ranksandfiles;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.POSITIVE;
import static com.chess.engine.utils.BoardUtils.ZERO;

public class PositiveFileDirection extends Direction {
    public PositiveFileDirection() {
        super(ZERO, POSITIVE);
    }
}
