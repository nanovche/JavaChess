package com.chess.engine.pieces;

import com.chess.engine.enums.Alliance;
import com.chess.engine.position.Position;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.ranksandfiles.NegativeFileDirection;
import com.chess.engine.directions.ranksandfiles.NegativeRankDirection;
import com.chess.engine.directions.ranksandfiles.PositiveFileDirection;
import com.chess.engine.directions.ranksandfiles.PositiveRankDirection;
import com.chess.engine.players.Player;

public class Rook extends Piece {

    private boolean moved;

    public Rook(Player player, String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition,
                CalculatorOfMoves calculatorOfMoves) {
        super(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        addPieceDirection(new PositiveRankDirection());
        addPieceDirection(new NegativeRankDirection());
        addPieceDirection(new PositiveFileDirection());
        addPieceDirection(new NegativeFileDirection());
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
