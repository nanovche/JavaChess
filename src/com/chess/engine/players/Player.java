package com.chess.engine.players;

import com.chess.engine.*;
import com.chess.engine.board.Tile;
import com.chess.engine.utils.MessagesClass;
import com.chess.engine.pieces.*;
import com.chess.engine.board.Board;
import com.chess.engine.reader.ConsoleReader;
import com.chess.engine.writer.ConsoleWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    //enhanced and normal move for pawn

    private final String nameOfPlayer;

    private final Alliance alliance;
    private final List<Piece> pieces;
    private Piece pieceToPlayWith;

    ConsoleReader consoleReader;
    ConsoleWriter consoleWriter;

    public Player(String nameOfPlayer, Alliance alliance, List<Piece> pieces, ConsoleWriter consoleWriter, ConsoleReader consoleReader) {
        this.nameOfPlayer = nameOfPlayer;
        this.alliance = alliance;
        this.pieces = pieces;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
    }

    public void bindPieceToPlayer(Piece piece) {
        pieces.add(piece);
    }
/*    public void promotePawn(Tile promotionSquare) throws IOException {

        consoleWriter.writeToConsole(MessagesClass.PAWN_PROMOTION_MESSAGE);
        for (PawnPromotionPiece pawnPromotionPiece : PawnPromotionPiece.values()) {
            consoleWriter.writeToConsole(pawnPromotionPiece.toString());
        }

        String choiceOfPawnPromotion;
        do {
            choiceOfPawnPromotion = consoleReader.readConsoleInput();
        } while (!(choiceOfPawnPromotion.equals(PawnPromotionPiece.BISHOP.toString()) || choiceOfPawnPromotion.equals(PawnPromotionPiece.KNIGHT.toString()) || choiceOfPawnPromotion.equals(PawnPromotionPiece.ROOK.toString()) || choiceOfPawnPromotion.equals("b") ||
                choiceOfPawnPromotion.equals(PawnPromotionPiece.QUEEN.toString())));

        PawnPromotionPiece pawnPromotionPiece = PawnPromotionPiece.valueOf(choiceOfPawnPromotion);

        Piece promotedPiece = null;

        switch (pawnPromotionPiece) {

            case KNIGHT: {
                promotedPiece = new Knight("K", getAlliance());
                break;
            }
            case ROOK: {
                promotedPiece = new Rook("R", getAlliance());
                break;
            }
            case BISHOP: {
                promotedPiece = new Bishop("B", getAlliance());
                break;
            }
            case QUEEN: {
                promotedPiece = new Queen("Q", getAlliance());
                break;
            }
        }
        promotionSquare.addPiece(promotedPiece);

    }*/
    public boolean determineMove(Board board, Tile sourceTile, Tile destinationTile)  {

        if(pieceToPlayWith instanceof Pawn){

        }

        return pieceToPlayWith.getPieceMoveDeterminator().isPieceMoveValid(alliance, board, sourceTile, destinationTile);
    }
    public abstract Move chooseMove(List<Move> possiblePieceMoves) throws IOException;
    public abstract void movePiece(Board board, Tile sourceTile, Tile destinationTile) throws IOException;

    //validation
    public Piece getPieceToPlayWith() {
        return pieceToPlayWith;
    }
    public void setPieceToPlayWith(Piece pieceToPlayWith) {
        this.pieceToPlayWith = pieceToPlayWith;
        if (pieceToPlayWith instanceof Pawn && this.alliance == Alliance.WHITE) {
            getPieceToPlayWith().setPieceInterval(-1); //from getter setting setter
        } else {
            getPieceToPlayWith().setPieceInterval(1);
        }
    }

    public String getNameOfPlayer() {
        return nameOfPlayer;
    }
    public Alliance getAlliance() {
        return alliance;
    }
    protected List<Piece> getPieces() {
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
