package com.chess.engine.directions.diagonals;

import com.chess.engine.directions.Direction;
import com.chess.engine.utils.BoardUtils;

import static com.chess.engine.utils.BoardUtils.NEGATIVE;
import static com.chess.engine.utils.BoardUtils.POSITIVE;

public class PositiveRankNegativeFileDiagonalDirection extends Direction {
    public PositiveRankNegativeFileDiagonalDirection() {
        super(POSITIVE, NEGATIVE);
    }
}
