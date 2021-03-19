package com.chess.engine.directions.ranksandfiles;

import com.chess.engine.directions.Direction;

import static com.chess.engine.utils.BoardUtils.TWO_POSITIVE;
import static com.chess.engine.utils.BoardUtils.ZERO;

public class BlackPawnEnhancedDirection extends Direction {
    public BlackPawnEnhancedDirection() {
        super(TWO_POSITIVE, ZERO);
    }
}
