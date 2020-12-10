package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.piecemovevalidators.KingMoveValidator;
import com.chess.engine.players.Player;

public class EnPassantMove extends GenericPieceMove{

    @Override
    public void move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {

        Tile tileOfPawnToRemoveViaEnPassant = board.accessTile(sourceTile.getPosition().getRow(),
                destinationTile.getPosition().getCol());

        sourceTile.getPieceFromTile();
/*        if(KingMoveValidator.isInCheck(player, board)){
            sourceTile.addPiece(piece);
            return;
        }*/
        tileOfPawnToRemoveViaEnPassant.removePieceFromPlay();
        destinationTile.addPiece(piece);

        piece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());

        piece.setMoveExecutionType(new GenericPieceMove());
    }
}
