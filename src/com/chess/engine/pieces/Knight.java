package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.knightdirections.*;
import com.chess.engine.directions.ranksandfiles.NegativeRankDirection;
import com.chess.engine.players.Player;


public class Knight extends Piece{

    //inject instance vs current implementation
    public Knight(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                  CalculatorOfMoves calculatorOfMoves) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        addPieceDirection(new DoubleNegativeRankNegativeFile());
        addPieceDirection(new DoubleNegativeRankPositiveFile());
        addPieceDirection(new DoublePositiveRankNegativeFile());
        addPieceDirection(new DoublePositiveRankPositiveFile());
        addPieceDirection(new NegativeRankDoubleNegativeFile());
        addPieceDirection(new NegativeRankDoublePositiveFile());
        addPieceDirection(new PositiveRankDoubleNegativeFile());
        addPieceDirection(new PositiveRankDoublePositiveFile());
    }

    // usage?
    /*public Knight(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }*/


}
