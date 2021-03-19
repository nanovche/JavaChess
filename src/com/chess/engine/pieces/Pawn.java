package com.chess.engine.pieces;

import com.chess.engine.board.Tile;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.Direction;
import com.chess.engine.enums.Alliance;
import com.chess.engine.players.Player;
import com.chess.engine.position.Position;

import java.util.List;


public class Pawn extends Piece {

    //rename row to file and col to rank

    /* Flag showing whether the pawn has moved.
    * It is used to determine whether a first enhanced(jump over square) move
    * is possible. */
    private boolean moved;
    private boolean enPassantIsPossible;
    private Tile targetTileOfEnPassantAttack;

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

    public Tile getTargetTileOfEnPassantAttack() {
        return targetTileOfEnPassantAttack;
    }

    public void setTargetTileOfEnPassantAttack(Tile targetTileOfEnPassantAttack) {
        this.targetTileOfEnPassantAttack = targetTileOfEnPassantAttack;
    }
}
