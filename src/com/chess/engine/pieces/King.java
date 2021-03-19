package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.players.Player;

public class King extends Piece {

    private boolean moved;
    private boolean castled;

    public King(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                CalculatorOfMoves calculatorOfMoves) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
    }

    public King(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

    //validation for setter

    public boolean hasMoved() { return moved; }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean hasCastled(){
        return castled;
    }
    public void setCastled(boolean castled){
        this.castled = castled;
    }

}
