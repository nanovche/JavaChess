package com.chess.engine.piecemovedeterminators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.*;


import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.*;

public class KingMoveDeterminator implements PieceMoveDeterminator {

    @Override
    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile) {

        int row = sourceTile.getPosition().getRow();
        int col = sourceTile.getPosition().getCol();

        List<Tile> possibleDestinationTiles = new ArrayList<>();


        // vulnerable cast?
        if(!((King) sourceTile.getPiece()).hasMoved()){
            if(board.accessTile(row, col + 3).isTileOccupied()){
                Piece piece = board.accessTile(row, col + 3).getPiece();
                if(piece instanceof Rook){
                    if(!((Rook)piece).hasMoved()){
                        if(!board.accessTile(row, col + 1).isTileOccupied() && !board.accessTile(row, col + 2).isTileOccupied() &&
                                !isInCheck(alliance, board.accessTile(row, col + 1), board) &&
                                !isInCheck(alliance, board.accessTile(row, col + 2), board)){
                            ((King)sourceTile.getPiece()).setShortCastle(true);
                            return true;
                        }
                    }
                }
            }
        }
        if(!((King) sourceTile.getPiece()).hasMoved()){
            if(board.accessTile(row, col - 4).isTileOccupied()){
                Piece piece = board.accessTile(row, col - 4).getPiece();
                if(piece instanceof Rook){
                    if(!((Rook)piece).hasMoved()){
                        if(!board.accessTile(row, col - 1).isTileOccupied() && !board.accessTile(row, col - 2).isTileOccupied() &&
                                !board.accessTile(row, col - 2).isTileOccupied() && !isInCheck(alliance, board.accessTile(row, col - 1), board) &&
                                !isInCheck(alliance, board.accessTile(row, col - 2), board) && !isInCheck(alliance, board.accessTile(row, col - 3), board)){
                            ((King)sourceTile.getPiece()).setLongCastle(true);
                            return true;
                        }
                    }
                }
            }
        }

        for (int r = row - 1; r < 3; r++) {
            for (int c = col - 1; c < 3; c++) {

                if(r == row && c == col){
                    continue;
                }

                if(tileIsInBoard(r, c)){
                    possibleDestinationTiles.add(board.accessTile(r, c));
                }
            }
        }

        for (Tile possibleDestinationTile: possibleDestinationTiles) {

            if(possibleDestinationTile.getPosition().getRow() == destinationTile.getPosition().getRow() &&
            possibleDestinationTile.getPosition().getCol() == destinationTile.getPosition().getCol()){

                if(isInCheck(alliance, destinationTile, board)){
                    return false;
                } else {
                    if(destinationTile.isTileOccupied()){//if that tile is also under check/attack
                        return destinationTile.getPiece().getAlliance() != alliance;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isInCheck(Alliance alliance, Tile tile, Board board) {

        return tileIsUnderFileOrRankAttack(alliance, tile, board) ||
                tileIsUnderBishopAttack(alliance, tile, board) ||
                tileIsUnderKnightAttack(alliance, tile, board) ||
                tileIsUnderPawnAttack(alliance, tile, board);
    }

    //alliance of what?
    private boolean tileIsUnderFileOrRankAttack(Alliance alliance, Tile tile, Board board) {

        int row = tile.getPosition().getRow() - 1;
        int col = tile.getPosition().getCol();

        while (tileIsInBoard(row, col)) {

            if (board.accessTile(row, col).isTileOccupied()) {
                Piece piece = board.accessTile(row, col).getPiece();
                if (piece.getAlliance() != alliance) {
                    if (piece instanceof Rook || piece instanceof Queen) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            row--;
        }

        row = tile.getPosition().getRow() + 1;
        col = tile.getPosition().getCol();
        while (tileIsInBoard(row, col)) {

            if (board.accessTile(row, col).isTileOccupied()) {
                Piece piece = board.accessTile(row, col).getPiece();
                if (piece.getAlliance() != alliance) {
                    if (piece instanceof Rook || piece instanceof Queen) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            row++;
        }

        row = tile.getPosition().getRow();
        col = tile.getPosition().getCol() - 1;
        while (tileIsInBoard(row, col)) {

            if (board.accessTile(row, col).isTileOccupied()) {
                Piece piece = board.accessTile(row, col).getPiece();
                if (piece.getAlliance() != alliance) {
                    if (piece instanceof Rook || piece instanceof Queen) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            col--;
        }

        row = tile.getPosition().getRow();
        col = tile.getPosition().getCol() + 1;
        while (tileIsInBoard(row, col)) {

            if (board.accessTile(row, col).isTileOccupied()) {
                Piece piece = board.accessTile(row, col).getPiece();
                if (piece.getAlliance() != alliance) {
                    if (piece instanceof Rook || piece instanceof Queen) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            col++;
        }

        return false;
    }
    private boolean tileIsUnderBishopAttack(Alliance alliance, Tile tile, Board board){

        int row = tile.getPosition().getRow() - 1;
        int col = tile.getPosition().getCol() - 1;
        while(tileIsInBoard(row, col)){

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece.getAlliance() != alliance){
                    if(piece instanceof Bishop){
                        return true;
                    }
                } else {
                    break;
                }
            }
            row--;
            col--;
        }

        row = tile.getPosition().getRow() - 1;
        col = tile.getPosition().getCol() + 1;
        while(tileIsInBoard(row, col)){

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece.getAlliance() != alliance){
                    if(piece instanceof Bishop){
                        return true;
                    }
                } else {
                    break;
                }
            }
            row--;
            col++;
        }

        row = tile.getPosition().getRow() + 1;
        col = tile.getPosition().getCol() + 1;
        while(tileIsInBoard(row, col)){

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece.getAlliance() != alliance){
                    if(piece instanceof Bishop){
                        return true;
                    }
                } else {
                    break;
                }
            }
            row++;
            col++;
        }

        row = tile.getPosition().getRow() + 1;
        col = tile.getPosition().getCol() - 1;
        while(tileIsInBoard(row, col)){

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece.getAlliance() != alliance){
                    if(piece instanceof Bishop){
                        return true;
                    }
                } else {
                    break;
                }
            }
            row++;
            col--;
        }

        return false;
    }
    private boolean tileIsUnderPawnAttack(Alliance alliance, Tile tile, Board board){

        if(alliance == Alliance.WHITE){

            int row = tile.getPosition().getRow() - 1;
            int col = tile.getPosition().getCol() - 1;

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece instanceof Pawn && piece.getAlliance() != alliance){
                    return true;
                }
            }

            row = tile.getPosition().getRow() - 1;
            col = tile.getPosition().getCol() + 1;

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece instanceof Pawn && piece.getAlliance() != alliance){
                    return true;
                }
            }
        } else {
            int row = tile.getPosition().getRow() + 1;
            int col = tile.getPosition().getCol() + 1;

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece instanceof Pawn && piece.getAlliance() != alliance){
                    return true;
                }
            }

            row = tile.getPosition().getRow() + 1;
            col = tile.getPosition().getCol() - 1;

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece instanceof Pawn && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }

        return false;
    }
    private boolean tileIsUnderKnightAttack(Alliance alliance, Tile tile, Board board){

        int row = tile.getPosition().getRow();
        int col = tile.getPosition().getCol();

        if(tileIsInBoard(row + 2, col - 1)){
            if(board.accessTile(row + 2, col - 1).isTileOccupied()){
                Piece piece = board.accessTile(row + 2, col - 1).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row + 2, col + 1)){
            if(board.accessTile(row + 2, col + 1).isTileOccupied()){
                Piece piece = board.accessTile(row + 2, col + 1).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row + 1, col - 2)){
            if(board.accessTile(row + 1, col - 2).isTileOccupied()){
                Piece piece = board.accessTile(row + 1, col - 2).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row + 1, col + 2)){
            if(board.accessTile(row + 1, col + 2).isTileOccupied()){
                Piece piece = board.accessTile(row + 1, col + 2).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row - 2, col - 1)){
            if(board.accessTile(row - 2, col - 1).isTileOccupied()){
                Piece piece = board.accessTile(row - 2, col - 1).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row - 2, col + 1)){
            if(board.accessTile(row - 2, col + 1).isTileOccupied()){
                Piece piece = board.accessTile(row - 2, col + 1).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row - 1, col - 2)){
            if(board.accessTile(row - 1, col - 2).isTileOccupied()){
                Piece piece = board.accessTile(row - 1, col - 2).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        else if(tileIsInBoard(row - 1, col + 2)){
            if(board.accessTile(row - 1, col + 2).isTileOccupied()){
                Piece piece = board.accessTile(row - 1, col + 2).getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    return true;
                }
            }
        }
        return false;
    }

}