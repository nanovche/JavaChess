package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.piecemovedeterminators.KingMoveDeterminator;

public class King extends Piece {

    private boolean isShortCastle;
    private boolean isLongCastle;
    private boolean moved;

    public King(String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition) {
        super(pieceTitle, alliance, currentPosition, initialPosition);
        setPieceMoveDeterminator(new KingMoveDeterminator());
    }

    public King(String pieceTitle, Alliance alliance) {
        super(pieceTitle, alliance);
    }

    //validation for setter

    public boolean hasMoved() { return moved; }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }


    //getters?
    public void setShortCastle(boolean shortCastle) {
        isShortCastle = shortCastle;
    }
    public void setLongCastle(boolean longCastle) {
        isLongCastle = longCastle;
    }

    @Override
    public void move(Board board, Tile sourceTile, Tile destinationTile) {

        sourceTile.getPieceFromTile();

        // possible not mandatory

        if(isShortCastle){
            Piece rook = board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() + 3).getPieceFromTile();
            board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() + 1).addPiece(rook);
            destinationTile.addPiece(this);
        } else if(isLongCastle){
            Piece rook = board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() - 4).getPieceFromTile();
            board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() - 1).addPiece(rook);
            destinationTile.addPiece(this);
        }
        else if(destinationTile.isTileOccupied()){
            attackPiece(destinationTile);
        } else {
            destinationTile.addPiece(this);
        }
        moved = true;
    }
}
