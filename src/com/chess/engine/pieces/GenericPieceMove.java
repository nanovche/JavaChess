package com.chess.engine.pieces;

import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;

import static com.chess.engine.piecemovevalidators.CheckValidator.isInCheck;

public class GenericPieceMove implements MoveI{

    public void move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {

        sourceTile.getPieceFromTile();
//        piece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());

/*        if(isInCheck(player,board)){
            sourceTile.addPiece(piece);
            return;
        }*/


        if(destinationTile.isTileOccupied()){
            destinationTile.removePieceFromPlay();
        }
        destinationTile.addPiece(piece);

        piece.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());
    }

}
