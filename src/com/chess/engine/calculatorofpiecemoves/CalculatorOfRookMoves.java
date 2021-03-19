package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class CalculatorOfRookMoves extends CalculatorOfGenericMoves implements CalculatorOfMoves, CalculatorOfPinnedPieceMoves{

    @Override
    public List<Tile> getValidDestinationTilesWhenPinned(Board board, Tile tileOfKing, Tile tileOfPinningPiece, Tile tileOfPinnedPiece) {


        int rowOfPinningPiece = tileOfPinningPiece.getPosition().getRow();
        int colOfPinningPiece = tileOfPinningPiece.getPosition().getCol();
        int rowOfPinnedPiece = tileOfPinnedPiece.getPosition().getRow();
        int colOfPinnedPiece = tileOfPinnedPiece.getPosition().getCol();
        int rowOfKing = tileOfKing.getPosition().getRow();
        int colOfKing = tileOfKing.getPosition().getCol();

        //rook pinned via diagonal cannot move->use empty list from super
        if(rowOfPinningPiece != rowOfPinnedPiece && colOfPinningPiece != colOfPinnedPiece){
            super.getValidDestinationTilesWhenPinned(board, tileOfKing, tileOfPinningPiece, tileOfPinnedPiece);
        }

        //rook or queen pinning, pinned rook
        List<Tile> possibleDestinationTiles = new ArrayList<>();
        if(rowOfPinningPiece == rowOfPinnedPiece){

            if(colOfKing < colOfPinningPiece){
                for (int c = colOfKing + 1; c <= colOfPinningPiece ; c++) {
                    if(c == colOfPinnedPiece){
                        continue;
                    }
                    int r = rowOfKing;
                    Tile currentTile = board.accessTile(r, c);
                    possibleDestinationTiles.add(currentTile);
                }
            } else {
                for (int c = colOfKing - 1; c >= colOfPinningPiece ; c--) {
                    if(c == colOfPinnedPiece){
                        continue;
                    }
                    int r = rowOfKing;
                    Tile currentTile = board.accessTile(r, c);
                    possibleDestinationTiles.add(currentTile);
                }
            }
        } else {
            if(rowOfKing < rowOfPinningPiece){
                for (int r = rowOfKing + 1; r <= rowOfPinningPiece ; r++) {
                    if(r == rowOfPinnedPiece){
                        continue;
                    }
                    int c = colOfKing;
                    Tile currentTile = board.accessTile(r, c);
                    possibleDestinationTiles.add(currentTile);
                }
            } else {
                for (int r = rowOfKing - 1; r >= rowOfPinningPiece ; r--) {
                    if(r == rowOfPinnedPiece){
                        continue;
                    }
                    int c = colOfKing;
                    Tile currentTile = board.accessTile(r, c);
                    possibleDestinationTiles.add(currentTile);
                }
            }

        }
        return possibleDestinationTiles;
    }
}
