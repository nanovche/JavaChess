package com.chess.engine.pieces;

import com.chess.engine.*;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.piecemovedeterminators.PawnMoveDeterminator;

import java.io.IOException;

public class Pawn extends Piece {

    //where to open scanner //constr ot method
    //close scanner
    //do i wrap sysout?>
    //what reader to use?
    //rename row to file and col to rank

    private boolean isFirstMove;
    private boolean isEnPassant;

    public Pawn(String pieceTitle, Alliance alliance, Position currentPosition, Position initialPosition) {
        super(pieceTitle, alliance, currentPosition, initialPosition);
        //how can this be avoided
        if(this.getAlliance() == Alliance.WHITE){
            setPieceInterval(-1);
        } else {
            setPieceInterval(1);
        }
        this.isFirstMove = true;
        setPieceMoveDeterminator(new PawnMoveDeterminator());
    }


    //validation for setters

    public boolean isFirstMove() {
        return isFirstMove;
    }
    public void setFirstMove(boolean isFirstMove) {
        this.isFirstMove = isFirstMove  ;
    }

    //getter here?
    public void setEnPassant(boolean enPassant) {
        isEnPassant = enPassant;
    }

    @Override
    public void move(Board board, Tile sourceTile, Tile destinationTile) throws IOException {

        sourceTile.getPieceFromTile();

        //en passant is not mandatory

        if(isEnPassant){
            destinationTile.addPiece(this);
            board.accessTile(sourceTile.getPosition().getRow(), destinationTile.getPosition().getCol()).removePieceFromPlay();
        } else if(destinationTile.isTileOccupied()){
            attackPiece(destinationTile);
        } else {
            destinationTile.addPiece(this);
        }
        setCurrentPosition(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol());
    }
}