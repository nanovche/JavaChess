package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.*;
import com.chess.engine.players.Player;

import static com.chess.engine.utils.BoardUtils.tileIsInBoard;

public class CheckValidator {

    public static boolean isInCheck(Player player, Board board) {

        Position positionOfKing = null;
        for (Piece currentPiece: player.getPieces()) {
            if(currentPiece.getPieceTitle().equals("K")){
                positionOfKing = currentPiece.getCurrentPosition();
            }
        }

        Tile tile = board.accessTile(positionOfKing.getRow(), positionOfKing.getCol());
        Alliance alliance = player.getAlliance();

        return tileIsUnderFileOrRankAttack(alliance, tile, board) ||
                tileIsUnderBishopAttack(alliance, tile, board) ||
                tileIsUnderKnightAttack(alliance, tile, board) ||
                tileIsUnderPawnAttack(alliance, tile, board);
    }

    //alliance of what?
    private static boolean tileIsUnderFileOrRankAttack(Alliance alliance, Tile tile, Board board) {

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
    private static boolean tileIsUnderBishopAttack(Alliance alliance, Tile tile, Board board){

        int row = tile.getPosition().getRow() - 1;
        int col = tile.getPosition().getCol() - 1;
        while(tileIsInBoard(row, col)){

            if(board.accessTile(row, col).isTileOccupied()){
                Piece piece = board.accessTile(row, col).getPiece();
                if(piece.getAlliance() != alliance){
                    if(piece instanceof Bishop || piece instanceof Queen){
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
                    if(piece instanceof Bishop || piece instanceof Queen){
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
                    if(piece instanceof Bishop || piece instanceof Queen){
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
                    if(piece instanceof Bishop || piece instanceof Queen){
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
    private static boolean tileIsUnderPawnAttack(Alliance alliance, Tile tile, Board board){

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
    private static boolean tileIsUnderKnightAttack(Alliance alliance, Tile tile, Board board){

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
