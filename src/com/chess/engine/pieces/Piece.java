package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.PieceType;
import com.chess.engine.Position;
import com.chess.engine.piecemovevalidators.PieceMoveValidator;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.Player;
import com.chess.engine.typesofmoves.MoveI;

import static com.chess.engine.PieceType.*;

public class Piece {

    //add final to some fields?

    private Player player;
    private final Alliance alliance;
    private Position initialPosition;
    private Position currentPosition;
    private PieceMoveValidator pieceMoveValidator;
    private MoveI moveExecutionType;
    private final String pieceTitle;
    private boolean isInPlay;

    //validation input here??
    public Piece(String pieceTitle, Alliance alliance){
        this.pieceTitle = pieceTitle;
        this.alliance = alliance;
    }
    //validation input here??
    //pass
    public Piece(Player player, String pieceTitle, Alliance alliance,Position currentPosition, Position initialPosition,
             MoveI move) {
        this(pieceTitle, alliance);
        setPlayer(player);
        this.initialPosition = initialPosition;
        this.currentPosition = currentPosition;
        this.isInPlay = true;
        this.moveExecutionType = move;
    }

    public static Piece getInstance(Player player, String pieceTitle, Alliance alliance,
                                    Position currentPosition, Position initialPosition, MoveI moveExecutionType, PieceType type){

        if(type.equals(KING)){
            return new King(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        } else  if(type.equals(QUEEN)){
            return new Queen(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        } else  if(type.equals(BISHOP)){
            return new Bishop(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        } else  if(type.equals(KNIGHT)){
            return new Knight(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        } else  if(type.equals(ROOK)){
            return new Rook(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        } else {
            return new Pawn(player, pieceTitle, alliance, currentPosition, initialPosition, moveExecutionType);
        }

    }

    public boolean move(Board board,Piece piece,Tile sourceTile,Tile destinationTile){
        return moveExecutionType.move(player, board, piece, sourceTile, destinationTile);
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

    //change set current position name in clients
    public Position getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(final int destinationRow, final int destinationCol) {
        currentPosition.setRow(destinationRow);
        currentPosition.setCol(destinationCol);
    }

    public PieceMoveValidator getPieceMoveValidator() {
        return pieceMoveValidator;
    }
    public void setPieceMoveValidator(PieceMoveValidator pieceMoveDeterminator) {
        this.pieceMoveValidator = pieceMoveDeterminator;
    }

    public Player getHolderOfThisPiece() {
        return player;
    }
    private void setPlayer(Player player) {
        this.player = player;
    }

    public MoveI getMoveExecutionType() {
        return moveExecutionType;
    }

    public void setMoveExecutionType(MoveI moveExecutionType) {
        this.moveExecutionType = moveExecutionType;
    }

    public boolean isInPlay() {
        return isInPlay;
    }
    public void setInPlay(boolean inPlay) {
        isInPlay = inPlay;
    }

    //setter for pieceTile?
    public String getPieceTitle() {
        return pieceTitle;
    }

    //hashcode, equals, tostring?

}
