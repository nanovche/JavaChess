package com.chess.engine.typesofmoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.piecemovevalidators.MoveReverter;
import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;

import static com.chess.engine.piecemovevalidators.CheckValidator.isInCheck;

public class GenericPieceMove implements MoveI {

    public boolean move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {

        //return boolean?
        //piece not needed as par?
        //null pointer

            piece = sourceTile.getPieceFromTile();
            Piece removedPiece = null;
            if(destinationTile.isTileOccupied()){
                removedPiece = destinationTile.getPieceFromTile();
            }
            destinationTile.addPiece(piece);

            if(MoveReverter.revertMove(player, board,  piece, removedPiece, sourceTile, destinationTile)){
                return false;
            }

            piece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());
            return true;
    }
}

