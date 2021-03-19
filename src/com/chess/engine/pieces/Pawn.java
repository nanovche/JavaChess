package com.chess.engine.pieces;

import com.chess.engine.*;
import com.chess.engine.board.Tile;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.Direction;
import com.chess.engine.directions.diagonals.NegativeDiagonalDirection;
import com.chess.engine.directions.diagonals.NegativeRankPositiveFileDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveRankNegativeFileDiagonalDirection;
import com.chess.engine.directions.ranksandfiles.NegativeRankDirection;
import com.chess.engine.directions.ranksandfiles.PositiveRankDirection;
import com.chess.engine.players.Player;

import java.util.List;


public class Pawn extends Piece {

    //rename row to file and col to rank

    private boolean moved;
    private boolean enPassantIsPossible;

    public Pawn(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                CalculatorOfMoves calculatorOfMoves, List<Direction> directions) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves, directions);
    }

    public Pawn(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                CalculatorOfMoves calculatorOfMoves) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
    }

    //validation for setters

    //where usage?
    public boolean hasMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
         this.moved = moved;
    }

    //getter here?
    public boolean getAnPassant() { return enPassantIsPossible;}
    public void setEnPassant(boolean enPassant) {
        this.enPassantIsPossible = enPassant;
    }

}