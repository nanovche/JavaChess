package com.chess.engine.pieces;

import com.chess.engine.enums.Alliance;
import com.chess.engine.position.Position;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.diagonals.NegativeDiagonalDirection;
import com.chess.engine.directions.diagonals.NegativeRankPositiveFileDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveRankNegativeFileDiagonalDirection;
import com.chess.engine.directions.ranksandfiles.NegativeFileDirection;
import com.chess.engine.directions.ranksandfiles.NegativeRankDirection;
import com.chess.engine.directions.ranksandfiles.PositiveFileDirection;
import com.chess.engine.directions.ranksandfiles.PositiveRankDirection;
import com.chess.engine.players.Player;

public class Queen extends Piece{

    public Queen(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                 CalculatorOfMoves calculatorOfMoves) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        addPieceDirection(new NegativeRankDirection());
        addPieceDirection(new NegativeRankPositiveFileDiagonalDirection());
        addPieceDirection(new PositiveFileDirection());
        addPieceDirection(new PositiveDiagonalDirection());
        addPieceDirection(new PositiveRankDirection());
        addPieceDirection(new PositiveRankNegativeFileDiagonalDirection());
        addPieceDirection(new NegativeFileDirection());
        addPieceDirection(new NegativeDiagonalDirection());
    }

    public Queen(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

}
