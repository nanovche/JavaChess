package com.chess.engine.moves;

import com.chess.engine.board.Tile;

public interface ShortCastleI {

    void performShortCastle(Tile kingTileBeforeCastle, Tile kingTileAfterCastle, Tile rookTileBeforeCastle, Tile rookTileAfterCastle);

}
