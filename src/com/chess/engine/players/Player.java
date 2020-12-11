package com.chess.engine.players;

import com.chess.engine.*;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.*;
import com.chess.engine.board.Board;

import java.io.IOException;
import java.util.List;

import static com.chess.engine.piecemovevalidators.CheckValidator.isInCheck;

public class Player {

    //enhanced and normal move for pawn

    private final String nameOfPlayer;
    private final Alliance alliance;
    private Piece pieceToPlayWith;
    private final List<Piece> pieces;
    private int consecutiveMove;
    private boolean moved;

    public boolean hasMoved() {
        return moved;
    }

    private void setMoved(boolean moved) {
        this.moved = moved;
    }
/*    ConsoleReader consoleReader;
    ConsoleWriter consoleWriter;*/

    public Player(String nameOfPlayer, Alliance alliance, List<Piece> pieces/*, ConsoleWriter consoleWriter, ConsoleReader consoleReader*/) {
        this.nameOfPlayer = nameOfPlayer;
        this.alliance = alliance;
        this.pieces = pieces;
        /*this.consoleWritoter = consoleWriter;
        this.consoleReader = consoleReader;*/
    }

    public void bindPieceToPlayer(Piece piece) {
        pieces.add(piece);
    }

    public void movePiece(Board board, Tile sourceTile, Tile destinationTile) throws IOException{

        if(pieceToPlayWith.getPieceMoveValidator().isPieceMoveValid(alliance, board, sourceTile, destinationTile)) {
            moved = pieceToPlayWith.move(board, pieceToPlayWith, sourceTile, destinationTile);
            this.setMoved(moved);
            updateConsecutiveMove();
        }
    }

    //validation
    public Piece getPieceToPlayWith() {
        return pieceToPlayWith;
    }
    public void setPieceToPlayWith(Piece pieceToPlayWith) {
        this.pieceToPlayWith = pieceToPlayWith;
    }

    public int getConsecutiveMove() { return consecutiveMove;}

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
