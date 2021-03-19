package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.directions.Direction;
import com.chess.engine.pieces.Piece;
import com.chess.engine.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.isTileInBoard;

public class CalculatorOfKnightMoves extends CalculatorOfGenericMoves implements CalculatorOfMoves, CalculatorOfPinnedPieceMoves{

    @Override
    public List<Tile> getValidDestinationTiles(Board board, Tile sourceTile) {

        int sourceRow = sourceTile.getPosition().getRow();
        int sourceCol = sourceTile.getPosition().getCol();
        List<Direction> directions = sourceTile.getPiece().getPieceDirections();
        Piece piece = sourceTile.getPiece();
        Alliance allianceOfMovingPiece = piece.getAlliance();

        if(CheckValidator.isPinned(piece)){
            return new ArrayList<>();
        }

        List<Tile> validDestinationTiles = new ArrayList<>();

        for (Direction direction: directions) {
            if(isTileInBoard(sourceRow + direction.getRowDir(), sourceCol + direction.getColDir())){
                Tile destinationTile = board.accessTile(sourceRow + direction.getRowDir(), sourceCol + direction.getColDir());
                if(destinationTile.isTileOccupied() && destinationTile.getPiece().getAlliance() != allianceOfMovingPiece){
                    validDestinationTiles.add(destinationTile);
                } else if(!destinationTile.isTileOccupied()){
                    validDestinationTiles.add(destinationTile);
                }
            }
        }
        return validDestinationTiles;
    }


    @Override
    public List<Tile> getValidDestinationTilesWhenPinned() {
        return new ArrayList<>();
    }
}
