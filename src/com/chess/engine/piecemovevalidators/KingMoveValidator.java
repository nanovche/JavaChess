package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.*;
import com.chess.engine.players.Player;


import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.piecemovevalidators.CheckValidator.isInCheck;
import static com.chess.engine.utils.BoardUtils.*;

public class KingMoveValidator implements PieceMoveValidator {

    @Override
    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile) {

        int row = sourceTile.getPosition().getRow();
        int col = sourceTile.getPosition().getCol();

        // vulnerable cast?
        if(!((King) sourceTile.getPiece()).hasMoved()){
            if(destinationTile.equals(board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() + 2))
                    && board.accessTile(row, col + 3).isTileOccupied()){
                Piece piece = board.accessTile(row, col + 3).getPiece();
                if(piece instanceof Rook){
                    if(!((Rook)piece).hasMoved()){
                        if(!board.accessTile(row, col + 1).isTileOccupied() &&
                           !board.accessTile(row, col + 2).isTileOccupied() &&
                           !isInCheck(sourceTile, alliance, board) &&
                           !isInCheck(sourceTile, alliance, board) &&
                           !isInCheck(sourceTile, alliance, board)){
                           ((King)sourceTile.getPiece()).setShortCastle(true);
                           return true;
                        }
                    }
                }
            }
        }
        if(!((King) sourceTile.getPiece()).hasMoved()){
                if(destinationTile.equals(board.accessTile(sourceTile.getPosition().getRow(), sourceTile.getPosition().getCol() - 2)) &&
                board.accessTile(row, col - 4).isTileOccupied()){
                Piece piece = board.accessTile(row, col - 4).getPiece();
                if(piece instanceof Rook){
                    if(!((Rook)piece).hasMoved()){
                        if(!board.accessTile(row, col - 1).isTileOccupied() &&
                           !board.accessTile(row, col - 2).isTileOccupied() &&
                           !board.accessTile(row, col - 3).isTileOccupied() &&
                           !isInCheck(sourceTile, alliance, board) &&
                           !isInCheck(sourceTile, alliance, board) &&
                           !isInCheck(sourceTile, alliance, board)){
                           ((King)sourceTile.getPiece()).setLongCastle(true);
                           return true;
                        }
                    }
                }
            }
        }

        return isInCheck(sourceTile, alliance, board);

    }

}