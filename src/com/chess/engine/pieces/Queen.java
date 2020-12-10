package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.piecemovevalidators.QueenMoveValidator;
import com.chess.engine.players.Player;

public class Queen extends Piece{

    public Queen(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                 MoveI moveExecutionType) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        setPieceMoveValidator(new QueenMoveValidator());
    }

    public Queen(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

}
