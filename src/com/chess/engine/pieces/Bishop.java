package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.diagonals.NegativeDiagonalDirection;
import com.chess.engine.directions.diagonals.NegativeRankPositiveFileDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveRankNegativeFileDiagonalDirection;
import com.chess.engine.players.Player;

public class Bishop extends Piece {

    public Bishop(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                  CalculatorOfMoves calculatorOfMoves) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        setCalculatorOfMoves(calculatorOfMoves);
        addPieceDirection(new PositiveDiagonalDirection());
        addPieceDirection(new NegativeDiagonalDirection());
        addPieceDirection(new PositiveRankNegativeFileDiagonalDirection());
        addPieceDirection(new NegativeRankPositiveFileDiagonalDirection());
    }

    public Bishop(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

}
