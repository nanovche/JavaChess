package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.chess.engine.Position;

public class Tile {

    private Piece pieceOnTile;
    private final Position position;
    private final String tileCoordinate;
    private boolean occupiedTile;

    public Tile(final String tileCoordinate, Position position) {
        this.tileCoordinate = tileCoordinate;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void addPiece(Piece piece){
        pieceOnTile = piece;
        occupiedTile = true;
    }

    //rename to better name (show etc)
    public Piece getPiece(){
        return pieceOnTile;
    }

    public Piece getPieceFromTile(){
        Piece piece = pieceOnTile;
        pieceOnTile = null;
        occupiedTile = false;
        return piece;
    }

    public void removePieceFromPlay(){
        pieceOnTile.setInPlay(false);
        this.pieceOnTile = null;
        this.occupiedTile = false;
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
