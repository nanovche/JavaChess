package com.chess.engine;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.players.HumanPlayer;
import com.chess.engine.players.Player;
import com.chess.engine.reader.ConsoleReader;
import com.chess.engine.writer.ConsoleWriter;
import gui.Table;

import java.io.IOException;
import java.util.*;

import static com.chess.engine.utils.BoardUtils.*;

public class Launcher {

    

    public static void main(String[] args) throws IOException {

        Table table = new Table();

    }
    private static boolean isMate() {
        return true;
    }

    public static List<Player> createPlayers(){

        ConsoleWriter consoleWriter = new ConsoleWriter();
        ConsoleReader consoleReader = new ConsoleReader();

        List<Player> players = new ArrayList<>();
        Player whitePlayer = new HumanPlayer("Asen", Alliance.WHITE, new ArrayList<>(), consoleWriter, consoleReader);
        Player blackPlayer = new HumanPlayer("Petar", Alliance.BLACK, new ArrayList<>(),consoleWriter, consoleReader);
        players.add(whitePlayer);
        players.add(blackPlayer);
        return players;
    }

    public static Board createBoard(){
        return new Board();
    }

    public static void initializeBoard(final Board board){

            for (int row = 0; row < NUM_OF_ROWS; row++) {

                for (int col = 0; col < NUM_OF_COLS; col++) {

                    final String tileCoordinate = characters[col] + digits[row];
                    final Tile tile = new Tile(tileCoordinate, new Position(row, col));
                    board.addTile(tile, row, col);

                }
            }
        }
}

