package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.piecemovevalidators.KingMoveValidator;
import com.chess.engine.players.Player;

public class King extends Piece {

    private boolean isShortCastle;
    private boolean isLongCastle;
    private boolean moved;

    public King(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                MoveI moveExecutionType) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        setPieceMoveValidator(new KingMoveValidator());
    }

    public King(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

    //validation for setter

    public boolean hasMoved() { return moved; }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    //naming?
    public boolean hasShortCastled() { return isShortCastle;}
    public boolean hasLongCastled() { return isLongCastle; }
    public void setShortCastle(boolean shortCastle) {
        isShortCastle = shortCastle;
    }
    public void setLongCastle(boolean longCastle) {
        isLongCastle = longCastle;
    }

}
