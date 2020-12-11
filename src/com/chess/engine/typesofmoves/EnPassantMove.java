package com.chess.engine.typesofmoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import static com.chess.engine.piecemovevalidators.MoveReverter.revertMove;

import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;

public class EnPassantMove extends GenericPieceMove {

    @Override
    public boolean move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {

        Tile tileOfPawnToRemoveViaEnPassant = board.accessTile(sourceTile.getPosition().getRow(),
                destinationTile.getPosition().getCol());

        piece = sourceTile.getPieceFromTile();
        Piece removedPiece = tileOfPawnToRemoveViaEnPassant.getPieceFromTile();
        destinationTile.addPiece(piece);

        if(revertMove(player, board,  piece, removedPiece, sourceTile, destinationTile, tileOfPawnToRemoveViaEnPassant)){
            return false;
        }

        piece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());
        piece.setMoveExecutionType(new GenericPieceMove());
        return true;
    }
}
