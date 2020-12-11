package com.chess.engine.piecemovevalidators;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;

import static com.chess.engine.piecemovevalidators.CheckValidator.isInCheck;

public class MoveReverter {

    public static boolean revertMove(Player player, Board board, Piece sourcePiece, Piece removedPiece,
                                     Tile sourceTile, Tile... destinationTile){
        if(isInCheck(player,board)){

            sourcePiece = destinationTile[0].getPieceFromTile();
            sourceTile.addPiece(sourcePiece);
            if(removedPiece != null) {

                if(sourcePiece instanceof Pawn && ((Pawn) sourcePiece).getAnPassant()){
                    destinationTile[1].addPiece(removedPiece);
                } else {
                    destinationTile[0].addPiece(removedPiece);
                }
            }
            return true;
        }
        return false;
    }
}
