package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.List;

public class CalculatorOfBishopMoves extends CalculatorOfGenericMoves implements CalculatorOfMoves, CalculatorOfPinnedPieceMoves{

    @Override
    public List<Tile> getValidDestinationTilesWhenPinned(Board board, Tile tileOfKing, Tile tileOfPinningPiece, Tile tileOfPinnedPiece) {

        int rowOfPinningPiece = tileOfPinningPiece.getPosition().getRow();
        int colOfPinningPiece = tileOfPinningPiece.getPosition().getCol();
        int rowOfPinnedPiece = tileOfPinnedPiece.getPosition().getRow();
        int colOfPinnedPiece = tileOfPinnedPiece.getPosition().getCol();
        int rowOfKing = tileOfKing.getPosition().getRow();
        int colOfKing = tileOfKing.getPosition().getCol();

        if(rowOfPinningPiece == rowOfPinnedPiece || colOfPinningPiece == colOfPinnedPiece){
            super.getValidDestinationTilesWhenPinned(board, tileOfKing, tileOfPinningPiece, tileOfPinnedPiece);
        }

        List<Tile> possibleDestinationTiles = new ArrayList<>();
        if(rowOfKing < rowOfPinningPiece && colOfKing < colOfPinningPiece) {

            for (int r = rowOfKing + 1, c = colOfKing + 1; c <= colOfPinningPiece; r++, c++) {
                if (c == colOfPinnedPiece) {
                    continue;
                }
                Tile currentTile = board.accessTile(r, c);
                possibleDestinationTiles.add(currentTile);
            }

        }else if(rowOfKing < rowOfPinningPiece && colOfKing > colOfPinningPiece) {
                for (int r = rowOfKing + 1, c = colOfKing - 1; c >= colOfPinningPiece; r++, c--) {
                    if(c == colOfPinnedPiece){
                        continue;
                    }
                    Tile currentTile = board.accessTile(r, c);
                    possibleDestinationTiles.add(currentTile);
                }
        } else if(rowOfKing > rowOfPinningPiece && colOfKing < colOfPinningPiece) {
            for (int r = rowOfKing - 1, c = colOfKing + 1; c <= colOfPinningPiece; r--, c++) {
                if(c == colOfPinnedPiece){
                    continue;
                }
                Tile currentTile = board.accessTile(r, c);
                possibleDestinationTiles.add(currentTile);
            }
        } else if(rowOfKing > rowOfPinningPiece && colOfKing > colOfPinningPiece) {
                for (int r = rowOfKing - 1, c = colOfKing - 1; c >= colOfPinningPiece; r--, c--) {
                    if(c == colOfPinnedPiece){
                        continue;
                    }
                    Tile currentTile = board.accessTile(r, c);
                    possibleDestinationTiles.add(currentTile);
                }
            }
        return possibleDestinationTiles;
    }
}
