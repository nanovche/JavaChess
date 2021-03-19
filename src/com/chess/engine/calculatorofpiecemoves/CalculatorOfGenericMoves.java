package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.enums.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.directions.Direction;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.*;

public class CalculatorOfGenericMoves implements CalculatorOfMoves, CalculatorOfPinnedPieceMoves {

    @Override
    public List<Tile> getValidDestinationTilesWhenPinned(Board board, Tile tileOfKing, Tile tileOfPinningPiece, Tile tileOfPinnedPiece) {
        return new ArrayList<>();
    }

    public List<Tile> getValidDestinationTiles(Board board, Tile sourceTile) {

        int sourceRow = sourceTile.getPosition().getRow();
        int sourceCol = sourceTile.getPosition().getCol();
        List<Direction> directions = sourceTile.getPiece().getPieceDirections();
        Piece piece = sourceTile.getPiece();
        Alliance allianceOfMovingPiece = piece.getAlliance();

        List<Tile> validDestinationTiles = new ArrayList<>(); // inject it empty from client and return it filled?
        if(CheckValidator.isPinned(piece)){

        }

        for (Direction direction : directions) {
            int rowDir = direction.getRowDir();
            int colDir = direction.getColDir();

            for (int currentRow = sourceRow + rowDir, currentCol = sourceCol + colDir; isTileInBoard(currentRow, currentCol);
                 currentRow += rowDir, currentCol += colDir) {

                Tile currentTile = board.accessTile(currentRow, currentCol);
                if (!currentTile.isTileOccupied()) {

                    validDestinationTiles.add(currentTile);
                    if(piece instanceof Knight){
                        break;
                    }
                } else {
                    if (currentTile.getPiece().getAlliance() != allianceOfMovingPiece) {
                        validDestinationTiles.add(currentTile);
                        //occupied tile is of opposite alliance -> this move is possible but
                        //no moves beyond this one are allowed in this direction
                        break;
                    } else {
                        //occupied tile is of same alliance -> no further moves in this direction are allowed
                        break;
                    }
                }
            }
        }
        return validDestinationTiles;
    }
}