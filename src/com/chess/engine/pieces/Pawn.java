package com.chess.engine.pieces;

import com.chess.engine.*;
import com.chess.engine.piecemovevalidators.PawnMoveValidator;
import com.chess.engine.players.Player;

public class Pawn extends Piece {

    //where to open scanner //constr ot method
    //close scanner
    //do i wrap sysout?>
    //what reader to use?
    //rename row to file and col to rank

    private boolean firstMove;
    private boolean enPassantIsPossible;
    private int pawnMoveDirection;

    public Pawn(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                MoveI moveExecutionType) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        setPawnMoveDirection();
        setPieceMoveValidator(new PawnMoveValidator());
    }

    //validation for setters

    public int getPawnMoveDirection() {
        return pawnMoveDirection;
    }
    public void setPawnMoveDirection() {
        if(this.getAlliance() == Alliance.WHITE){
            pawnMoveDirection = -1;
        } else {
            pawnMoveDirection = 1;
        }
    }

    //where usage?
    public boolean isFirstMove() {
        return firstMove;
    }
    public void setFirstMove(boolean firstMove) {
         this.firstMove = firstMove;
    }

    //getter here?
    public boolean getAnPassant() { return enPassantIsPossible;}
    public void setEnPassant(boolean enPassant) {
        this.enPassantIsPossible = enPassant;
    }

}