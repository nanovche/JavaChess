package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;

public class QueenMoveValidator implements PieceMoveValidator {

    private final RookMoveValidator rookMoveValidator;
    private final BishopMoveValidator bishopMoveValidator;

    public QueenMoveValidator() {
        this.rookMoveValidator = new RookMoveValidator();
        this.bishopMoveValidator = new BishopMoveValidator();
    }

    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile){

        return rookMoveValidator.isPieceMoveValid(alliance, board, sourceTile, destinationTile)
        || bishopMoveValidator.isPieceMoveValid(alliance, board, sourceTile, destinationTile);

    }

}
