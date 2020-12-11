package com.chess.engine.typesofmoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;

public class SpecialKingMove extends GenericPieceMove {

    @Override
    public boolean move(Player player, Board board, Piece piece, Tile sourceTile, Tile destinationTile) {

        sourceTile.getPieceFromTile();

        if(((King)piece).hasShortCastled()){
            Piece rook = board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() + 3).getPieceFromTile();
            board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() + 1).addPiece(rook);
        } else if(((King)piece).hasLongCastled()){
            Piece rook = board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() - 4).getPieceFromTile();
            board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() - 1).addPiece(rook);
        } else if(destinationTile.isTileOccupied()){
            destinationTile.removePieceFromPlay();
        }
        destinationTile.addPiece(piece);
        ((King)piece).setMoved(true);
        return true;
    }
}
