package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.directions.Direction;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.*;

public class CalculatorOfPawnMoves extends CalculatorOfGenericMoves implements CalculatorOfMoves {

    @Override
    public List<Tile> getValidDestinationTiles(Board board, Tile sourceTile) {

        int sourceRow = sourceTile.getPosition().getRow();
        int sourceCol = sourceTile.getPosition().getCol();
        List<Direction> directions = sourceTile.getPiece().getPieceDirections();
        Piece movingPiece = sourceTile.getPiece();
        Alliance allianceOfMovingPiece = movingPiece.getAlliance();

        // -1 -1 \ -1 0 \ -2 0 \ -1 1
        // 1 -1 \ 1 0 \ 2 0  \ 1 1

        if(CheckValidator.isPinned(movingPiece)){
            return new ArrayList<>(); //?
        }

        List<Tile> validDestinationTiles = new ArrayList<>(); // inject it empty from client and return it filled?

        for (Direction direction : directions) {
            int destinationRow = sourceRow + direction.getRowDir();
            int destinationCol = sourceCol + direction.getColDir();
            if (isTileInBoard(destinationRow, destinationCol)) {
                Tile destinationTile = board.accessTile(destinationRow, destinationCol);
                if (sourceCol != destinationCol) {
                    if (destinationTile.isTileOccupied() && destinationTile.getPiece().getAlliance() != allianceOfMovingPiece) {
                        validDestinationTiles.add(destinationTile);
                    } else if (!destinationTile.isTileOccupied()) {

                        Tile tileOfPieceToBeCapturedViaEnPassant = board.accessTile(sourceRow, destinationCol);
                        Piece pieceToBeCapturedViaEnPassant = tileOfPieceToBeCapturedViaEnPassant.getPiece();

                        if (tileOfPieceToBeCapturedViaEnPassant.isTileOccupied() && pieceToBeCapturedViaEnPassant instanceof Pawn &&
                                pieceToBeCapturedViaEnPassant.getAlliance() != allianceOfMovingPiece) {
                            if ((sourceTile.getPosition().getRow() == FOURTH_RANK &&
                                    movingPiece.getAlliance() == Alliance.WHITE &&
                                    movingPiece.getHolderOfThisPiece().getConsecutiveMove() ==
                                            pieceToBeCapturedViaEnPassant.getHolderOfThisPiece().getConsecutiveMove())) {
                                ((Pawn) movingPiece).setEnPassant(true);
                                validDestinationTiles.add(destinationTile);


                            } else if (sourceTile.getPosition().getRow() == FIFTH_RANK
                                    && movingPiece.getAlliance() == Alliance.BLACK &&
                                    movingPiece.getHolderOfThisPiece().getConsecutiveMove() + 1 ==
                                            pieceToBeCapturedViaEnPassant.getHolderOfThisPiece().getConsecutiveMove()) {
                                ((Pawn) movingPiece).setEnPassant(true);
                                validDestinationTiles.add(destinationTile);
                            }
                        }
                    }
                } else {
                    if (Math.abs(direction.getRowDir()) == 1) {
                        if (!destinationTile.isTileOccupied()) {
                            validDestinationTiles.add(destinationTile);
                        }
                    } else if (!((Pawn)movingPiece).hasMoved() && Math.abs(direction.getRowDir()) == 2) {
                        Tile jumpedOverTile = board.accessTile(destinationRow - 1, destinationCol);
                        if (!destinationTile.isTileOccupied() && !jumpedOverTile.isTileOccupied()) {
                            validDestinationTiles.add(destinationTile);
                        }
                    }
                }
            }
        }
        return validDestinationTiles;
    }
}