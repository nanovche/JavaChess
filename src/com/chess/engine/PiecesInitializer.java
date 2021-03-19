package com.chess.engine;

import com.chess.engine.board.Board;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfGenericMoves;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfKingMoves;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfKnightMoves;
import com.chess.engine.calculatorofpiecemoves.CalculatorOfPawnMoves;
import com.chess.engine.directions.Direction;
import com.chess.engine.directions.diagonals.NegativeDiagonalDirection;
import com.chess.engine.directions.diagonals.NegativeRankPositiveFileDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveDiagonalDirection;
import com.chess.engine.directions.diagonals.PositiveRankNegativeFileDiagonalDirection;
import com.chess.engine.directions.ranksandfiles.BlackPawnEnhancedDirection;
import com.chess.engine.directions.ranksandfiles.NegativeRankDirection;
import com.chess.engine.directions.ranksandfiles.PositiveRankDirection;
import com.chess.engine.directions.ranksandfiles.WhitePawnEnhancedDirection;
import com.chess.engine.pieces.*;
import com.chess.engine.players.Player;
import com.chess.engine.utils.BoardUtils;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.*;
import static com.chess.engine.utils.BoardUtils.FIFTH_FILE;

public class PiecesInitializer {

    public static void initializePieces(Board board, Player whitePlayer, Player blackPlayer){

        initializeRooks(board, whitePlayer, blackPlayer);
        initializeKnights(board, whitePlayer, blackPlayer);
        initializeBishops(board, whitePlayer, blackPlayer);
        initializeQueens(board, whitePlayer, blackPlayer);
        initializeKings(board, whitePlayer, blackPlayer);
        initializePawns(board, whitePlayer, blackPlayer);
    }

    //piecetile export to single var const
    //why naming piece variables / blackBishopC8... ?

    private static void initializePawns(Board board, Player whitePlayer, Player blackPlayer){

        List<Direction> whitePawnDirections = new ArrayList<>();
        whitePawnDirections.add(new NegativeRankDirection());
        whitePawnDirections.add(new NegativeDiagonalDirection());
        whitePawnDirections.add(new NegativeRankPositiveFileDiagonalDirection());
        whitePawnDirections.add(new WhitePawnEnhancedDirection());
        List<Direction> blackPawnDirections = new ArrayList<>();
        blackPawnDirections.add(new PositiveRankDirection());
        blackPawnDirections.add(new PositiveDiagonalDirection());
        blackPawnDirections.add(new PositiveRankNegativeFileDiagonalDirection());
        blackPawnDirections.add(new BlackPawnEnhancedDirection());

        for (int col = 0; col < BoardUtils.NUM_OF_PAWNS / 2; col++) {

            Pawn whitePawn = new Pawn(whitePlayer, "P", Alliance.WHITE,
                    new Position(SEVENTH_RANK, col), new Position(SEVENTH_RANK, col),
                    new CalculatorOfPawnMoves(), whitePawnDirections);
            board.accessTile(SEVENTH_RANK, col).addPiece(whitePawn);
            whitePlayer.bindPieceToPlayer(whitePawn);

            Pawn blackPawn = new Pawn(blackPlayer, "P", Alliance.BLACK,
                    new Position(SECOND_RANK, col), new Position(SECOND_RANK, col),
                    new CalculatorOfPawnMoves(), blackPawnDirections);
            board.accessTile(SECOND_RANK, col).addPiece(blackPawn);
            blackPlayer.bindPieceToPlayer(blackPawn);

        }
    }
    private static void initializeRooks(Board board, Player whitePlayer, Player blackPlayer){

        Rook blackRookA8 = new Rook(blackPlayer, "R", Alliance.BLACK,
                new Position(FIRST_RANK, FIRST_FILE), new Position(FIRST_RANK, FIRST_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(FIRST_RANK, FIRST_FILE).addPiece(blackRookA8);
        blackPlayer.bindPieceToPlayer(blackRookA8);

        Rook blackRookH8 = new Rook(blackPlayer, "R", Alliance.BLACK,
                new Position(FIRST_RANK, LAST_FILE), new Position(FIRST_RANK, LAST_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(FIRST_RANK, LAST_FILE).addPiece(blackRookH8);
        blackPlayer.bindPieceToPlayer(blackRookH8);

        Rook whiteRookA1 = new Rook(whitePlayer, "R", Alliance.WHITE,
                new Position(LAST_RANK, FIRST_FILE), new Position(LAST_RANK, FIRST_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(LAST_RANK, FIRST_FILE).addPiece(whiteRookA1);
        whitePlayer.bindPieceToPlayer(whiteRookA1);

        Rook whiteRookA8 = new Rook(whitePlayer, "R", Alliance.WHITE,
                new Position(LAST_RANK, LAST_FILE ), new Position(LAST_RANK, LAST_FILE ),
                new CalculatorOfGenericMoves());
        board.accessTile(LAST_RANK, LAST_FILE).addPiece(whiteRookA8);
        whitePlayer.bindPieceToPlayer(whiteRookA8);


    }
    private static void initializeKnights(Board board, Player whitePlayer, Player blackPlayer){

        Knight blackKnightB8 = new Knight(blackPlayer, "H", Alliance.BLACK,
                new Position(FIRST_RANK, SECOND_FILE), new Position(FIRST_RANK, SECOND_FILE),
                new CalculatorOfKnightMoves());
        board.accessTile(FIRST_RANK, SECOND_FILE).addPiece(blackKnightB8);
        blackPlayer.bindPieceToPlayer(blackKnightB8);

        Knight blackKnightG8 = new Knight(blackPlayer, "H", Alliance.BLACK,
                new Position(FIRST_RANK, SEVENTH_FILE), new Position(FIRST_RANK, SEVENTH_FILE),
                new CalculatorOfKnightMoves());
        board.accessTile(FIRST_RANK, SEVENTH_FILE).addPiece(blackKnightG8);
        blackPlayer.bindPieceToPlayer(blackKnightG8);

        Knight whiteKnightB1 = new Knight(whitePlayer, "H", Alliance.WHITE,
                new Position(LAST_RANK, SECOND_FILE),  new Position(LAST_RANK, SECOND_FILE),
                new CalculatorOfKnightMoves());
        board.accessTile(LAST_RANK, SECOND_FILE).addPiece(whiteKnightB1);
        whitePlayer.bindPieceToPlayer(whiteKnightB1);

        Knight whiteKnightG1 = new Knight(whitePlayer, "H", Alliance.WHITE,
                new Position(LAST_RANK, SEVENTH_FILE), new Position(LAST_RANK, SEVENTH_FILE),
                new CalculatorOfKnightMoves());
        board.accessTile(LAST_RANK, SEVENTH_FILE).addPiece(whiteKnightG1);
        whitePlayer.bindPieceToPlayer(whiteKnightG1);

    }
    private static void initializeBishops(Board board, Player whitePlayer, Player blackPlayer){

        Bishop blackBishopC8 = new Bishop(blackPlayer, "B", Alliance.BLACK,
                new Position(FIRST_RANK, THIRD_FILE), new Position(FIRST_RANK, THIRD_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(FIRST_RANK, THIRD_FILE).addPiece(blackBishopC8);
        blackPlayer.bindPieceToPlayer(blackBishopC8);

        Bishop blackBishopF8 = new Bishop(blackPlayer, "B", Alliance.BLACK,
                new Position(FIRST_RANK, SIXTH_FILE), new Position(FIRST_RANK, SIXTH_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(FIRST_RANK, SIXTH_FILE).addPiece(blackBishopF8);
        blackPlayer.bindPieceToPlayer(blackBishopF8);

        Bishop whiteBishopC1 = new Bishop(whitePlayer, "B", Alliance.WHITE,
                new Position(LAST_RANK, THIRD_FILE), new Position(LAST_RANK, THIRD_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(LAST_RANK, THIRD_FILE).addPiece(whiteBishopC1);
        whitePlayer.bindPieceToPlayer(whiteBishopC1);

        Bishop whiteBishopF1 = new Bishop(whitePlayer, "B", Alliance.WHITE,
                new Position(LAST_RANK, SIXTH_FILE), new Position(LAST_RANK, SIXTH_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(LAST_RANK, SIXTH_FILE).addPiece(whiteBishopF1);
        whitePlayer.bindPieceToPlayer(whiteBishopF1);

    }
    private static void initializeQueens(Board board, Player whitePlayer, Player blackPlayer){

        Queen blackQueen = new Queen(blackPlayer, "Q", Alliance.BLACK,
                new Position(FIRST_RANK, FOURTH_FILE), new Position(FIRST_RANK, FOURTH_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(FIRST_RANK, FOURTH_FILE).addPiece(blackQueen);
        blackPlayer.bindPieceToPlayer(blackQueen);

        Queen whiteQueen = new Queen(whitePlayer, "Q", Alliance.WHITE,
                new Position(LAST_RANK, FOURTH_FILE), new Position(LAST_RANK, FOURTH_FILE),
                new CalculatorOfGenericMoves());
        board.accessTile(LAST_RANK, FOURTH_FILE).addPiece(whiteQueen);
        whitePlayer.bindPieceToPlayer(whiteQueen);

    }
    private static void initializeKings(Board board, Player whitePlayer, Player blackPlayer){

        King blackKing = new King(blackPlayer, "K", Alliance.BLACK,
                new Position(FIRST_RANK, FIFTH_FILE), new Position(FIRST_RANK, FIFTH_FILE),
                new CalculatorOfKingMoves());
        board.accessTile(FIRST_RANK, FIFTH_FILE).addPiece(blackKing);
        blackPlayer.bindPieceToPlayer(blackKing);

        King whiteKing = new King(whitePlayer, "K", Alliance.WHITE,
                new Position(LAST_RANK, FIFTH_FILE), new Position(LAST_RANK, FIFTH_FILE),
                new CalculatorOfKingMoves());
        board.accessTile(LAST_RANK, FIFTH_FILE).addPiece(whiteKing);
        whitePlayer.bindPieceToPlayer(whiteKing);

    }

}
