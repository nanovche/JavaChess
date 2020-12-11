package com.chess.engine.piecemovevalidators;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.typesofmoves.EnPassantMove;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.typesofmoves.PawnPromotionMove;
import com.chess.engine.pieces.Piece;

import static com.chess.engine.utils.BoardUtils.FIFTH_RANK;
import static com.chess.engine.utils.BoardUtils.FOURTH_RANK;

public class PawnMoveValidator implements PieceMoveValidator {

    @Override
    public boolean isPieceMoveValid(Alliance alliance, Board board, Tile sourceTile, Tile destinationTile) {

        int rowDifference = destinationTile.getPosition().getRow() - sourceTile.getPosition().getRow();
        int colDifference = destinationTile.getPosition().getCol() - sourceTile.getPosition().getCol();

        //moving backwards check
        if (sourceTile.getPiece().getAlliance() == Alliance.WHITE &&
                sourceTile.getPosition().getRow() < destinationTile.getPosition().getRow()) {
            return false;
        }

        if (sourceTile.getPiece().getAlliance() == Alliance.BLACK &&
                sourceTile.getPosition().getRow() > destinationTile.getPosition().getRow()) {
            return false;
        }

        if (colDifference == 0) {
            if (Math.abs(rowDifference) == 2) {
                return !board.accessTile(destinationTile.getPosition().getRow() - ((Pawn) sourceTile.getPiece()).getPawnMoveDirection(), destinationTile.getPosition().getCol()).isTileOccupied()
                        && !board.accessTile(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol()).isTileOccupied();
            } else if (Math.abs(rowDifference) == 1) {
                Piece piece = sourceTile.getPiece();
                if(piece.getCurrentPosition().getRow() == piece.getInitialPosition().getRow() + 6){
                    piece.setMoveExecutionType(new PawnPromotionMove());
                }
                return !board.accessTile(destinationTile.getPosition().getRow(), destinationTile.getPosition().getCol()).isTileOccupied();
            }
        } else if (Math.abs(colDifference) == 1 && Math.abs(rowDifference) == 1 ) {

            if(destinationTile.isTileOccupied()){
                return alliance != destinationTile.getPiece().getAlliance();
            } else {
                Tile tileOfPieceToBeCapturedViaEnPassant = board.accessTile(sourceTile.getPosition().getRow(), destinationTile.getPosition().getCol());
                Piece pieceToBeCapturedViaEnPassant = tileOfPieceToBeCapturedViaEnPassant.getPiece();
                Piece attackingPiece = sourceTile.getPiece();

                if (tileOfPieceToBeCapturedViaEnPassant.isTileOccupied() && pieceToBeCapturedViaEnPassant instanceof Pawn &&
                        pieceToBeCapturedViaEnPassant.getAlliance() != alliance) {
                    if ((sourceTile.getPosition().getRow() == FOURTH_RANK &&
                            attackingPiece.getAlliance() == Alliance.WHITE &&
                            attackingPiece.getHolderOfThisPiece().getConsecutiveMove() ==
                                    pieceToBeCapturedViaEnPassant.getHolderOfThisPiece().getConsecutiveMove())) {
                        ((Pawn)attackingPiece).setEnPassant(true); // unnesecary
                        attackingPiece.setMoveExecutionType(new EnPassantMove());
                        return true;

                    } else if (sourceTile.getPosition().getRow() == FIFTH_RANK
                            && attackingPiece.getAlliance() == Alliance.BLACK &&
                            attackingPiece.getHolderOfThisPiece().getConsecutiveMove() + 1 ==
                                    pieceToBeCapturedViaEnPassant.getHolderOfThisPiece().getConsecutiveMove()) {
                        ((Pawn)attackingPiece).setEnPassant(true);
                        attackingPiece.setMoveExecutionType(new EnPassantMove());
                        return true;
                    }
                }
            }
        }
        return false;
    }
}