package com.chess.engine.directions.knightdirections;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.POSITIVE;
import static com.chess.engine.utils.BoardUtils.TWO_POSITIVE;

public class PositiveRankDoublePositiveFile extends Direction {
    public PositiveRankDoublePositiveFile() {
        super(POSITIVE, TWO_POSITIVE);
    }
}
