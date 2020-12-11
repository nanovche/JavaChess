package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.piecemovevalidators.BishopMoveValidator;
import com.chess.engine.players.Player;
import com.chess.engine.typesofmoves.MoveI;

public class Bishop extends Piece {

    public Bishop(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                  MoveI moveExecutionType) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        setPieceMoveValidator(new BishopMoveValidator());
    }

    public Bishop(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

}
