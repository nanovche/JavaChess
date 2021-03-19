package com.chess.engine.players;

import com.chess.engine.*;
import com.chess.engine.board.Tile;
import com.chess.engine.calculatorofpiecemoves.CheckValidator;
import com.chess.engine.pieces.*;
import com.chess.engine.board.Board;

import java.io.IOException;
import java.util.List;

public class Player {

    private final String nameOfPlayer;
    private final Alliance alliance;
    private Piece pieceToPlayWith;
    private final List<Piece> pieces;
    private int consecutiveMove;
    private boolean moved;

    public Player(String nameOfPlayer, Alliance alliance, List<Piece> pieces/*, ConsoleWriter consoleWriter, ConsoleReader consoleReader*/) {
        this.nameOfPlayer = nameOfPlayer;
        this.alliance = alliance;
        this.pieces = pieces;
    }

    public void bindPieceToPlayer(Piece piece) {
        pieces.add(piece);
    }

    private void move(Tile sourceTile, Tile destinationTile, Board board){

        List<Tile> validDestinationTiles = pieceToPlayWith.getCalculatorOfMoves().getValidDestinationTiles(board, sourceTile);
        if (validDestinationTiles.contains(destinationTile)) {

            pieceToPlayWith.move(sourceTile, destinationTile, board);

            this.setMoved(true); // first time false - > then sets it to true and has no effect..
            updateConsecutiveMove();
        }
    }

    public void movePiece(Board board, Tile sourceTile, Tile destinationTile) throws IOException {

        if (CheckValidator.isUnderAttack(this, board)) {
            if (CheckValidator.isMate(board, this)) {
                System.exit(0);
            } else {
                if(pieceToPlayWith instanceof King && !CheckValidator.isUnderAttack(destinationTile, pieceToPlayWith.getAlliance(), board)){
                    move(sourceTile, destinationTile, board);
                } else {
                    if(!CheckValidator.isDoubleCheck(board, pieceToPlayWith.getAlliance(), sourceTile)){
                        if(CheckValidator.findTilesBetweenAttackerInclusiveAndKing(this, board).contains(destinationTile)){
                            move(sourceTile, destinationTile, board);
                        }
                    }
                }
            }
        }
        else {
            move(sourceTile, destinationTile, board);
        }
    }

    //validation
    public boolean hasMoved() {
        return moved;
    }
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public Piece getPieceToPlayWith() {
        return pieceToPlayWith;
    }
    public void setPieceToPlayWith(Piece pieceToPlayWith) {
        this.pieceToPlayWith = pieceToPlayWith;
    }

    public int getConsecutiveMove() {
        return consecutiveMove;
    }
    private void updateConsecutiveMove() {
        this.consecutiveMove += 1;
    }

    public String getNameOfPlayer() {
        return nameOfPlayer;
    }
    public Alliance getAlliance() {
        return alliance;
    }
    public List<Piece> getPieces() {
        return pieces;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Player)) {
            return false;
        }

        Player that = (Player) obj;

        return this.alliance == that.alliance;

    }

    //hashcode, tostring ?

}
