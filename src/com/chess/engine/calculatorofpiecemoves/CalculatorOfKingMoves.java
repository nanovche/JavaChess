package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.directions.Direction;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.calculatorofpiecemoves.CheckValidator.findPositionOfKing;
import static com.chess.engine.calculatorofpiecemoves.CheckValidator.isUnderAttack;
import static com.chess.engine.utils.BoardUtils.isTileInBoard;

public class CalculatorOfKingMoves extends CalculatorOfGenericMoves implements CalculatorOfMoves{
    @Override
    public List<Tile> getValidDestinationTiles(Board board, Tile sourceTile) {

        int sourceRow = sourceTile.getPosition().getRow();
        int sourceCol = sourceTile.getPosition().getCol();
        List<Direction> directions = sourceTile.getPiece().getPieceDirections();
        Piece piece = sourceTile.getPiece();
        Alliance allianceOfMovingPiece = piece.getAlliance();
        Alliance oppositeAlliance;

        if(allianceOfMovingPiece == Alliance.WHITE){
            oppositeAlliance = Alliance.BLACK;
        } else {
            oppositeAlliance = Alliance.WHITE;
        }

        List<Tile> validDestinationTiles = new ArrayList<>(); // inject it empty from client and return it filled?

        for (Direction direction: directions) {
            int destinationRow = sourceRow + direction.getRowDir();
            int destinationCol = sourceCol + direction.getColDir();
            if(isTileInBoard(destinationRow, destinationCol)){
                Tile destinationTile = board.accessTile(destinationRow, destinationCol);
                if(!isUnderAttack(destinationTile, allianceOfMovingPiece, board) && !getTilesAdjacentToEnemyKing(board, oppositeAlliance).contains(destinationTile)){
                    validDestinationTiles.add(destinationTile);
                }
            }
        }

        if(!(((King)piece).hasCastled())){
            isShortCastlePossible(board, sourceTile, validDestinationTiles);
            isLongCastlePossible(board, sourceTile, validDestinationTiles);
        }

        return validDestinationTiles;
    }

    private void isShortCastlePossible(Board board, Tile sourceTile, List<Tile> validDestinationTiles) {

        Piece sourcePiece = sourceTile.getPiece();
        int rowOfSourceTile = sourceTile.getPosition().getRow();
        int colOfSourceTile = sourceTile.getPosition().getCol();

        if (!((King) sourcePiece).hasMoved()) {
            Tile rightEndTile = board.accessTile(rowOfSourceTile, colOfSourceTile + 3);
            if (rightEndTile.isTileOccupied()) {
                Piece piece = rightEndTile.getPiece();
                if (piece instanceof Rook && !((Rook) piece).hasMoved()) {
                    Tile crossedTile = board.accessTile(rowOfSourceTile, colOfSourceTile + 1);
                    Tile destinationTile = board.accessTile(rowOfSourceTile, colOfSourceTile + 2);
                    Alliance allianceOfCastlingPlayer = sourcePiece.getAlliance();

                    if (!crossedTile.isTileOccupied() &&
                            !destinationTile.isTileOccupied() &&
                            !isUnderAttack(crossedTile, allianceOfCastlingPlayer, board) &&
                            !isUnderAttack(sourceTile, allianceOfCastlingPlayer, board) &&
                            !isUnderAttack(destinationTile, allianceOfCastlingPlayer, board)) {
                        validDestinationTiles.add(destinationTile);
                    }
                }
            }
        }
    }
    private void isLongCastlePossible(Board board, Tile sourceTile, List<Tile> validDestinationTiles){
        Piece sourcePiece = sourceTile.getPiece();
        int rowOfSourceTile = sourceTile.getPosition().getRow();
        int colOfSourceTile = sourceTile.getPosition().getCol();

        if (!((King) sourcePiece).hasMoved()) {
            Tile leftEndTile = board.accessTile(rowOfSourceTile, colOfSourceTile - 4);
            if (leftEndTile.isTileOccupied()) {
                Piece piece = leftEndTile.getPiece();
                if (piece instanceof Rook && !((Rook) piece).hasMoved()) {
                    Tile crossedTile = board.accessTile(rowOfSourceTile, colOfSourceTile - 1);
                    Tile destinationTile = board.accessTile(rowOfSourceTile, colOfSourceTile - 2);
                    Alliance allianceOfCastlingPlayer = sourcePiece.getAlliance();

                    if (!crossedTile.isTileOccupied() &&
                        !destinationTile.isTileOccupied() &&
                        !isUnderAttack(crossedTile, allianceOfCastlingPlayer, board) &&
                            !isUnderAttack(sourceTile, allianceOfCastlingPlayer, board) &&
                            !isUnderAttack(destinationTile, allianceOfCastlingPlayer, board)) {
                        validDestinationTiles.add(destinationTile);
                    }
                }
            }
        }
    }

    private List<Tile> getTilesAdjacentToEnemyKing(Board board, Alliance alliance){

        List<Tile> tilesAdjacent = new ArrayList<>();

        Position positionOfEnemyKing = findPositionOfKing(board, alliance);
        int rowOfEnemyKing = positionOfEnemyKing.getRow();
        int colOfEnemyKing = positionOfEnemyKing.getCol();


        for (int r = rowOfEnemyKing - 1; r <= rowOfEnemyKing + 1; r++) {
            for (int c = colOfEnemyKing - 1; c <= colOfEnemyKing + 1; c++) {
                if(r == rowOfEnemyKing && c == colOfEnemyKing){
                    continue;
                }
                if(isTileInBoard(r, c)){
                    Tile adjacentTile = board.accessTile(r, c);
                    tilesAdjacent.add(adjacentTile);
                }
            }
        }

        return tilesAdjacent;

    }

}
