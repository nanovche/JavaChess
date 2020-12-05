package com.chess.engine.piecemovedeterminators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

public class QueenMoveDeterminator implements PieceMoveDeterminator {

    private final RookMoveDeterminator rookMoveDeterminator;
    private final BishopMoveDeterminator bishopMoveDeterminator;

    public QueenMoveDeterminator() {
        this.rookMoveDeterminator = new RookMoveDeterminator();
        this.bishopMoveDeterminator = new BishopMoveDeterminator();
    }

    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile){

        return rookMoveDeterminator.isPieceMoveValid(alliance, board, sourceTile, destinationTile)
        || bishopMoveDeterminator.isPieceMoveValid(alliance, board, sourceTile, destinationTile);

    }
}
