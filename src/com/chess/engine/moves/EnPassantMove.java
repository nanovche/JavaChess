package com.chess.engine.moves;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;

public class EnPassantMove implements MoveTypeI {

    @Override
    public void move(Tile sourceTile, Tile destinationTile, Board... board) {

        int row = sourceTile.getPosition().getRow();
        int col = destinationTile.getPosition().getCol();
        Tile tileOfPawnToBeCapturedViaEnPassant = board[0].accessTile(row, col);
        tileOfPawnToBeCapturedViaEnPassant.removePieceFromPlay();

        Piece piece = sourceTile.getPieceFromTile();
        destinationTile.addPiece(piece);
        piece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());

    }

}
