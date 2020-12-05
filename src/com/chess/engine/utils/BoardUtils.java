package com.chess.engine.utils;

public class BoardUtils {

    //change to short/byte

    //change to regular number flow
    public static final int FIRST_RANK = 0;
    public static final int SECOND_RANK = 1;
    public static final int THIRD_RANK = 2;
    public static final int FOURTH_RANK = 3;
    public static final int FIFTH_RANK = 4;
    public static final int SIXTH_RANK = 5;
    public static final int SEVENTH_RANK = 6;
    public static final int LAST_RANK = 7;

    public static final int FIRST_FILE = 0;
    public static final int SECOND_FILE = 1;
    public static final int THIRD_FILE = 2;
    public static final int FOURTH_FILE = 3;
    public static final int FIFTH_FILE = 4;
    public static final int SIXTH_FILE = 5;
    public static final int SEVENTH_FILE = 6;
    public static final int LAST_FILE = 7;

    public static final int NUM_OF_ROWS = 8;
    public static final int NUM_OF_COLS = 8;
    public static final int NUM_OF_PAWNS = 16;
    public static final int NUMBER_OF_TILES = 64;

    //why not char[]
    public static final String[] characters = {"A","B","C","D","E","F","G","H"};
    public static final String[] digits = {"8","7","6","5","4","3","2","1"};

    public static boolean tileIsInBoard(int rowBound, int colBound){
        return (rowBound >= FIRST_RANK && rowBound <= LAST_RANK) &&
                colBound >= FIRST_RANK && colBound <= LAST_RANK;
    }
}
