package com.chess.engine.moves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.utils.BoardUtils;


public class ShortCastlingMove implements MoveTypeI{

    @Override
    public void move(Tile sourceTile, Tile destinationTile, Board... board) {

        Piece king = sourceTile.getPieceFromTile();

        if(board != null){
            int row = sourceTile.getPosition().getRow();
            int col = BoardUtils.LAST_FILE;
            Piece rook = board[0].accessTile(row, col).getPieceFromTile();

            row = sourceTile.getPosition().getRow();
            col = sourceTile.getPosition().getCol() + 1;
            Tile rookTile = board[0].accessTile(row, col);
            rookTile.addPiece(rook);
            rook.setCurrentPosition(row, col);
        }

        destinationTile.addPiece(king);
        king.setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());


    }

}
