package com.chess.engine.pieces;

import com.chess.engine.enums.Alliance;
import com.chess.engine.enums.PieceType;
import com.chess.engine.position.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfMoves;
import com.chess.engine.directions.Direction;
import com.chess.engine.moves.*;
import com.chess.engine.players.Player;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.enums.PieceType.*;

public class Piece {

    private Player player;
    private Alliance alliance;
    private Position initialPosition;
    private Position currentPosition;
    private List<Direction> pieceDirections;
    private CalculatorOfMoves calculatorOfMoves;
    private MoveTypeI typeOfMove;

    private final String pieceTitle;

    private boolean isInPlay;

    public Piece(String pieceTitle, Alliance alliance){
        this.pieceTitle = pieceTitle;
        setAlliance(alliance);
    }

    public Piece(Player player, String pieceTitle, Alliance alliance,Position currentPosition, Position initialPosition,
                 CalculatorOfMoves calculatorOfMoves) {
        this(pieceTitle, alliance);
        setPlayer(player);
        pieceDirections = new ArrayList<>();
        this.initialPosition = initialPosition;
        this.currentPosition = currentPosition;
        this.isInPlay = true;
        this.calculatorOfMoves = calculatorOfMoves;
    }
    public Piece(Player player, String pieceTitle, Alliance alliance,Position currentPosition, Position initialPosition,
                 CalculatorOfMoves calculatorOfMoves, List<Direction> directions) {
        this(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        this.pieceDirections = directions;
    }
    public static Piece getInstance(Player player, String pieceTitle, Alliance alliance,
                                    Position currentPosition, Position initialPosition, CalculatorOfMoves calculatorOfMoves, PieceType type){

        if(type.equals(KING)){
            return new King(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        } else  if(type.equals(QUEEN)){
            return new Queen(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        } else  if(type.equals(BISHOP)){
            return new Bishop(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        } else  if(type.equals(KNIGHT)){
            return new Knight(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        } else  if(type.equals(ROOK)){
            return new Rook(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        } else {
            return new Pawn(player, pieceTitle, alliance, currentPosition, initialPosition, calculatorOfMoves);
        }

    }

    public void move(Tile sourceTile, Tile destinationTile, Board board){

        Piece piece = this;

        if(destinationTile.isTileOccupied()){
            piece.typeOfMove = new AttackingMove();
        } else {
            if(piece instanceof King){
                if(destinationTile.getPosition().getCol() == sourceTile.getPosition().getCol() + 2){
                    piece.typeOfMove = new ShortCastlingMove();
                    ((King)piece).setCastled(true);
                } else if(destinationTile.getPosition().getCol() == sourceTile.getPosition().getCol() - 2){
                    piece.typeOfMove = new LongCastlingMove();
                    ((King)piece).setCastled(true);
                }
                ((King)piece).setMoved(true);
            } else if(piece instanceof Pawn){

                if(((Pawn)piece).getAnPassant()){
                    if(destinationTile.getPosition().getCol() != sourceTile.getPosition().getCol()){
                        //dependency on implementation
                        piece.typeOfMove = new EnPassantMove();
                    }
                }
                //dependency on implementation
                piece.typeOfMove = new RegularMove();
                ((Pawn)piece).setMoved(true);
            } else if(piece instanceof Rook){
                ((Rook)piece).setMoved(true);
                piece.typeOfMove = new RegularMove();
            }
            else {
              piece.typeOfMove = new RegularMove();
            }

        }

        typeOfMove.move(sourceTile, destinationTile, board);

    }

    public void setTypeOfMove(MoveTypeI typeOfMove) {
        this.typeOfMove = typeOfMove;
    }

    //validation input for setters?
    //remove any unnecessary getters/setters
    public Alliance getAlliance() {
        return alliance;
    }

    private void setAlliance(Alliance alliance){
        this.alliance = alliance;
    }

    public CalculatorOfMoves getCalculatorOfMoves() {
        return calculatorOfMoves;
    }

    public void setCalculatorOfMoves(CalculatorOfMoves calculatorOfMoves) {
        this.calculatorOfMoves = calculatorOfMoves;
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

    /* return defensive copy, so client has no reference to it */
    public List<Direction> getPieceDirections() {
        return new ArrayList<>(pieceDirections);
    }
    public void addPieceDirection(Direction direction) {
        pieceDirections.add(direction);
    }

    public void setPieceDirections(List<Direction> directions){
        this.pieceDirections = directions;
    }

    public Player getHolderOfThisPiece() {
        return player;
    }
    private void setPlayer(Player player) {
        this.player = player;
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
