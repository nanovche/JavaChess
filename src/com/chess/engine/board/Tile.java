package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.position.Position;

public class Tile {


    /* field holds reference to the piece(if there is one) */
    private Piece pieceOnTile;

    /* field shows whether there is a piece on this tile during the execution of the move */
    private boolean occupiedTile;

    /* position of the tile */
    private final Position position;

    /* coordinate of the tile */
    private final String tileCoordinate;

    /* defensive programming -> initialize position with new instance, so "outside"
    * reference cannot meddle with it */
    //string???
    public Tile(final String tileCoordinate, Position position) {
        this.tileCoordinate = tileCoordinate;
        this.position = new Position(position.getRow(), position.getCol());
    }

    public void addPiece(Piece piece){
        pieceOnTile = piece;
        occupiedTile = true;
    }

    /*  */

    public Piece getPieceFromTile(){
        Piece piece = pieceOnTile;
        pieceOnTile = null;
        occupiedTile = false;
        return piece;
    }



    public void removePieceFromPlay(){
        pieceOnTile.setInPlay(false); //?
        this.pieceOnTile = null;
        this.occupiedTile = false;
    }

    //rename to better name (show etc)
    public Position getPosition() {
        return position;
    }

    public Piece getPiece(){
        return pieceOnTile;
    }

    String getTileCoordinate(){
        return tileCoordinate;
    }

    public boolean isTileOccupied(){
        return occupiedTile;
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this){
            return true;
        }

        if(!(obj instanceof Tile)){
            return false;
        }

        Tile that = (Tile)obj;

        return this.tileCoordinate.equals(that.tileCoordinate);
    }

    //hashcode, tostring?
}
