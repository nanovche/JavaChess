package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.piecemovevalidators.KnightMoveValidator;
import com.chess.engine.players.Player;


public class Knight extends Piece{

    //inject instance vs current implementation
    public Knight(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                  MoveI moveExecutionType) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        setPieceMoveValidator(new KnightMoveValidator());
    }

    // usage?
    /*public Knight(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }*/


}
