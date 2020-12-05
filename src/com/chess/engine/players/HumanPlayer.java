package com.chess.engine.players;

import com.chess.engine.*;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.utils.MessagesClass;
import com.chess.engine.reader.ConsoleReader;
import com.chess.engine.writer.ConsoleWriter;

import java.io.IOException;
import java.util.List;

public class HumanPlayer extends Player {

    public HumanPlayer(String nameOfPlayer, Alliance alliance, List<Piece> pieces, ConsoleWriter consoleWriter, ConsoleReader consoleReader) {
        super(nameOfPlayer, alliance, pieces, consoleWriter, consoleReader);
    }

//    @Override
//    public List<Move> choosePiece(Board board) throws IOException {
//        List<Piece> availablePieces = this.getPieces();
//        List<Move> possibleMoves;
//
//        int locatePiece;
//        consoleWriter.writeToConsole("Pick a pawn:");
//        for (Piece piece: availablePieces) {
//            consoleWriter.writeToConsole(piece.getTitle());
//        }
//        do{
//
//            locatePiece = Integer.parseInt(consoleReader.readConsoleInput());
//
//            if(!(locatePiece >= 1 && locatePiece <= 8)){
//                consoleWriter.writeToConsole("Between 1 and 8");
//                continue;
//            }
//
//            setPieceToPlayWith(availablePieces.get(locatePiece - 1));
//
//            if((possibleMoves = calculatePossibleMovesForPawn(board)).size() == 0){
//                for (Piece piece: availablePieces) {
//                    if(Integer.parseInt(Character.toString(piece.getTitle().charAt(piece.getTitle().length() - 1))) == locatePiece){
//                        continue;
//                    }
//                    consoleWriter.writeToConsole(piece.getTitle());
//                }
//            } else {
//                return possibleMoves;
//            }
//
//        } while (true);
//
//    }

    @Override
    public void movePiece(Board board, Tile sourceTile, Tile destinationTile) throws IOException {

        if(determineMove(board, sourceTile, destinationTile)){
            getPieceToPlayWith().move(board, sourceTile, destinationTile);
        }
    }

    @Override
    public Move chooseMove(List<Move> possiblePieceMoves) throws IOException {

        String chosenMove;

        consoleWriter.writeToConsole(MessagesClass.CHOOSE_MOVE_MESSAGE);
        for (Move move: possiblePieceMoves) {
            consoleWriter.writeToConsole(move.toString());
        }
        consoleWriter.printNewLine();

        do{
            chosenMove = consoleReader.readConsoleInput();
        } while(!(chosenMove.equals(Move.PLA.toString()) || chosenMove.equals( Move.PRA.toString()) ||
                chosenMove.equals(Move.PR.toString())  || chosenMove.equals(Move.PE.toString())));

        return Move.valueOf(chosenMove);
    }

}
