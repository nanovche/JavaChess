package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.Position;
import com.chess.engine.piecemovedeterminators.PieceMoveDeterminator;
import com.chess.engine.writer.ConsoleWriter;
import com.chess.engine.Move;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;

import java.io.IOException;
import java.util.List;

public abstract class Piece {

    //add final to some fields?

    private final Alliance alliance;
    private Position initialPosition;
    private Position currentPosition;
    private PieceMoveDeterminator pieceMoveDeterminator;
    private boolean isInPlay;
    private int pieceInterval;
    private final String pieceTitle;

    //validation input here??
    public Piece(String pieceTitle, Alliance alliance){
        this.pieceTitle = pieceTitle;
        this.alliance = alliance;
    }
    //validation input here??
    //pass
    public Piece(String pieceTitle, Alliance alliance,Position currentPosition, Position initialPosition) {
        this(pieceTitle, alliance);
        this.initialPosition = initialPosition;
        this.currentPosition = currentPosition;
        this.isInPlay = true;
    }

    //validation input for setters?
    //remove any unnecessary getters/setters

    public Alliance getAlliance() {
        return alliance;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }
    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    //change setcurrent position name in clients
    public Position getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(final int destinationRow, final int destinationCol) {
        currentPosition.setRow(destinationRow);
        currentPosition.setCol(destinationCol);
    }

    public PieceMoveDeterminator getPieceMoveDeterminator() {
        return pieceMoveDeterminator;
    }
    public void setPieceMoveDeterminator(PieceMoveDeterminator pieceMoveDeterminator) {
        this.pieceMoveDeterminator = pieceMoveDeterminator;
    }

    public boolean isInPlay() {
        return isInPlay;
    }
    public void setInPlay(boolean inPlay) {
        isInPlay = inPlay;
    }

    public int getPieceInterval() {
        return pieceInterval;
    }
    public void setPieceInterval(int pieceInterval) {
        this.pieceInterval = pieceInterval;
    }

    //setter for pieceTile?
    public String getPieceTitle() {
        return pieceTitle;
    }

    public void printPossiblePieceMoves(List<Move> possiblePieceMoves){

        //dependency injection//compare
        ConsoleWriter consoleWriter = new ConsoleWriter();

        for (Move move: possiblePieceMoves) {
            consoleWriter.writeToConsole(move.toString());
        }

    }

    public void attackPiece(Tile destinationTile){
        destinationTile.removePieceFromPlay();
        destinationTile.addPiece(this);
    }

    public abstract void move(Board board, Tile sourceTile, Tile destinationTile) throws IOException;

    //hashcode, equals, tostring?

}
