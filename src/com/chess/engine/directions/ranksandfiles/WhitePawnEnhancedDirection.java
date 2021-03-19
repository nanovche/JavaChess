package com.chess.engine.directions.ranksandfiles;

import com.chess.engine.directions.Direction;
import com.chess.engine.utils.BoardUtils;

import static com.chess.engine.utils.BoardUtils.TWO_NEGATIVE;
import static com.chess.engine.utils.BoardUtils.ZERO;

public class WhitePawnEnhancedDirection extends Direction {
    public WhitePawnEnhancedDirection() {
        super(TWO_NEGATIVE, ZERO);
    }
}
