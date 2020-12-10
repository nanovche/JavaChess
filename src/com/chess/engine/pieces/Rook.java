package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.piecemovevalidators.RookMoveValidator;
import com.chess.engine.players.Player;

public class Rook extends Piece {

    private boolean moved;

    public Rook(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                MoveI moveExecutionType) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        setPieceMoveValidator(new RookMoveValidator());
    }

    public Rook(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

    //validation
    public boolean hasMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

}
