package com.chess.engine.calculatorofpiecemoves;

import com.chess.engine.enums.Alliance;
import com.chess.engine.position.Position;
import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.*;
import com.chess.engine.players.Player;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.*;

public class CheckValidator {

    private static final List<Tile> knightsAttacksLocations = new ArrayList<>();

    private static final List<Piece> piecesOnFileAbovePiece = new ArrayList<>();
    private static final List<Piece> piecesOnFileBelowPiece = new ArrayList<>();
    private static final List<Piece> piecesOnRankLeftOfPiece = new ArrayList<>();
    private static final List<Piece> piecesOnRankRightOfPiece = new ArrayList<>();

    private static final List<Piece> piecesOnTopRightDiagonal = new ArrayList<>();
    private static final List<Piece> piecesOnBottomRightDiagonal = new ArrayList<>();
    private static final List<Piece> piecesOnBottomLeftDiagonal = new ArrayList<>();
    private static final List<Piece> piecesOnTopLeftDiagonal = new ArrayList<>();


    /*If the corresponding collection is not empty, it means we have pieces on that line of the board(rank, file or diagonal),
    * We check whether only the first piece closest to the king(the first in the collection) is an attacker since pieces in
    * chess cannot attack over other pieces(except the knight). That's why all attacks(if any) from knights are added(max two).
    * If any attackers are found they are added to the list.  */
    public static List<Tile> getTilesFromWhichAttacksArePerformed(Board board, Alliance allianceOfDefender, Tile... tile) {

        List<Tile> attackingTiles = new ArrayList<>();

        Piece piece;
        if(piecesOnFileAbovePiece.size() > 0){
            piece = piecesOnFileAbovePiece.get(0);
            if(isValidStraightAttacker(piece, allianceOfDefender)){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnFileBelowPiece.size() > 0){
            piece = piecesOnFileBelowPiece.get(0);
            if(isValidStraightAttacker(piece, allianceOfDefender)){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnRankRightOfPiece.size() > 0){
            piece = piecesOnRankRightOfPiece.get(0);
            if(isValidStraightAttacker(piece, allianceOfDefender)){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnRankLeftOfPiece.size() > 0){
            piece = piecesOnRankLeftOfPiece.get(0);
            if(isValidStraightAttacker(piece, allianceOfDefender)){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnBottomLeftDiagonal.size() > 0){
            piece = piecesOnBottomLeftDiagonal.get(0);
            if(isValidDiagonalAttacker(piece, allianceOfDefender, tile[0])){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnBottomRightDiagonal.size() > 0){
            piece = piecesOnBottomRightDiagonal.get(0);
            if(isValidDiagonalAttacker(piece, allianceOfDefender, tile[0])){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnTopLeftDiagonal.size() > 0){
            piece = piecesOnTopLeftDiagonal.get(0);
            if(isValidDiagonalAttacker(piece, allianceOfDefender, tile[0])){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }
        if(piecesOnTopRightDiagonal.size() > 0){
            piece = piecesOnTopRightDiagonal.get(0);
            if(isValidDiagonalAttacker(piece, allianceOfDefender, tile[0])){
                attackingTiles.add(board.accessTile(piece.getCurrentPosition().getRow(), piece.getCurrentPosition().getCol()));
            }
        }

        attackingTiles.addAll(knightsAttacksLocations);

        return attackingTiles;
    }

    public static boolean isUnderAttack(Player player, Board board) {

        clearAllAttackersFromPreviousMove();

        Position positionOfKing = findPositionOfKing(board, player.getAlliance());
        Tile tile = board.accessTile(positionOfKing.getRow(), positionOfKing.getCol());
        Alliance alliance = player.getAlliance();

        collectPiecesFromRanksAndFiles(tile, board);
        collectPiecesFromDiagonals(tile, board);
        collectKnightsAttacks(alliance, tile, board);

        return getTilesFromWhichAttacksArePerformed(board, alliance, tile).size() != 0;

    }

    public static boolean isUnderAttack(Tile tile, Alliance alliance, Board board) {

        clearAllAttackersFromPreviousMove();

        collectPiecesFromRanksAndFiles(tile, board);
        collectPiecesFromDiagonals(tile, board);
        collectKnightsAttacks(alliance, tile, board);

        return getTilesFromWhichAttacksArePerformed(board, alliance, tile).size() != 0;

    }

    public static List<Tile> findTilesBetweenAttackerInclusiveAndKing(Player player, Board board){

        List<Tile> tilesBetweenAttackerInclusiveAndKing = new ArrayList<>();
        Position positionOfKing = findPositionOfKing(board, player.getAlliance());
        Tile tileOfKing = board.accessTile(positionOfKing.getRow(), positionOfKing.getCol());
        Tile tileOfAttacker = getTilesFromWhichAttacksArePerformed(board, player.getAlliance(), tileOfKing).get(0);

        int attackingRow = tileOfAttacker.getPosition().getRow();
        int attackingCol = tileOfAttacker.getPosition().getCol();
        int defendingRow = tileOfKing.getPosition().getRow();
        int defendingCol = tileOfKing.getPosition().getCol();

        int[] rowAndColDir = calculateDirectionFromAttackingPiece(attackingRow, attackingCol, defendingRow, defendingCol);
        int rowDir = rowAndColDir[0];
        int colDir = rowAndColDir[1];

        int currentRow = attackingRow;
        int currentCol = attackingCol;
        while(currentRow != defendingRow || currentCol != defendingCol){

            Tile currentTile = board.accessTile(currentRow, currentCol);
            tilesBetweenAttackerInclusiveAndKing.add(currentTile);

            currentRow += rowDir;
            currentCol += colDir;
        }

        return tilesBetweenAttackerInclusiveAndKing;
    }

    public static boolean isMate(Board board, Player player){

        Position positionOfKing = findPositionOfKing(board, player.getAlliance());
        Tile sourceTile = board.accessTile(positionOfKing.getRow(), positionOfKing.getCol());
        Alliance allianceOfDefender = sourceTile.getPiece().getAlliance();

        if(isDoubleCheck(board, allianceOfDefender, sourceTile) && !kingIsAbleToMoveUnderCheck(board, sourceTile)){
            return true;
        }

        Alliance allianceOfAttacker;
        if(allianceOfDefender == Alliance.BLACK){
            allianceOfAttacker = Alliance.WHITE;
        } else {
            allianceOfAttacker = Alliance.BLACK;
        }

        int attackingRow = getTilesFromWhichAttacksArePerformed(board, allianceOfDefender, sourceTile).get(0).getPosition().getRow();
        int attackingCol = getTilesFromWhichAttacksArePerformed(board, allianceOfDefender, sourceTile).get(0).getPosition().getCol();

        int defendingRow = sourceTile.getPosition().getRow();
        int defendingCol = sourceTile.getPosition().getCol();

        int[] rowAndColDirArr = calculateDirectionFromAttackingPiece(attackingRow, attackingCol, defendingRow, defendingCol);
        int rowDir = rowAndColDirArr[0];
        int colDir = rowAndColDirArr[1];

        Tile currentTile = board.accessTile(attackingRow, attackingCol);
        for (int currentRow = attackingRow, currentCol = attackingCol; !currentTile.equals(sourceTile);
             currentRow += rowDir, currentCol += colDir, currentTile = board.accessTile(currentRow, currentCol)) {

            if(isUnderAttack(currentTile, allianceOfAttacker, board)){

                for (Tile tile: CheckValidator.getTilesFromWhichAttacksArePerformed(board, allianceOfAttacker, sourceTile)) {
                    if(!isPinned(tile.getPiece())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isDoubleCheck(Board board, Alliance allianceOfDefender, Tile sourceTile){
        return getTilesFromWhichAttacksArePerformed(board, allianceOfDefender, sourceTile).size() == 2;
    }

    public static boolean isPinned(Piece pinnedPiece){

        Alliance alliance = pinnedPiece.getAlliance();
        Piece pinningPiece;

        return ((piecesOnFileBelowPiece.size() > 1 && piecesOnFileBelowPiece.get(0) == (pinnedPiece)) &&
                ((pinningPiece =  piecesOnFileBelowPiece.get(1)) instanceof Rook || pinningPiece instanceof Queen) &&
                (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnFileAbovePiece.size() > 1 && piecesOnFileAbovePiece.get(0) == (pinnedPiece)) &&
                        ((pinningPiece =  piecesOnFileAbovePiece.get(1)) instanceof Rook || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnRankLeftOfPiece.size() > 1 && piecesOnRankLeftOfPiece.get(0) == (pinnedPiece)) &&
                        ((pinningPiece =  piecesOnRankLeftOfPiece.get(1)) instanceof Rook || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnRankRightOfPiece.size() > 1 && piecesOnRankRightOfPiece.get(0) == (pinnedPiece)) &&
                        ((pinningPiece = piecesOnRankRightOfPiece.get(1)) instanceof Rook || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnTopLeftDiagonal.size() > 1 && piecesOnTopLeftDiagonal.get(0) == (pinnedPiece)) &&
                        ((pinningPiece = piecesOnTopLeftDiagonal.get(1)) instanceof Bishop || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnTopRightDiagonal.size() > 1 && piecesOnTopRightDiagonal.get(0) == (pinnedPiece)) &&
                        ((pinningPiece = piecesOnTopRightDiagonal.get(1)) instanceof Bishop || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnBottomRightDiagonal.size() > 1 && piecesOnBottomRightDiagonal.get(0) == (pinnedPiece)) &&
                        ((pinningPiece = piecesOnBottomRightDiagonal.get(1)) instanceof Bishop || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance)) ||
                ((piecesOnBottomLeftDiagonal.size() > 1 && piecesOnBottomLeftDiagonal.get(0) == (pinnedPiece)) &&
                        ((pinningPiece = piecesOnBottomLeftDiagonal.get(1)) instanceof Bishop || pinningPiece instanceof Queen) &&
                        (pinningPiece.getAlliance() != alliance));

    }

    public static Position findPositionOfKing(Board board, Alliance alliance){

        Position positionOfKing = null;

        for (Tile[] rowOfTiles: board.getBoard()) {
            for (Tile currentTile: rowOfTiles) {
                if(currentTile.isTileOccupied()){
                    Piece currentPiece = currentTile.getPiece();
                    if(currentPiece.getPieceTitle().equals("K") && currentPiece.getAlliance() == alliance){
                        positionOfKing = currentPiece.getCurrentPosition();
                        break;
                    }
                }
            }
            if(positionOfKing != null){
                break;
            }
        }
        return positionOfKing;
    }

    private static int[] calculateDirectionFromAttackingPiece(int attackingRow, int attackingCol, int defendingRow, int defendingCol){

        int rowDir, colDir;
        rowDir = colDir = 0;

        if(attackingRow < defendingRow && attackingCol < defendingCol){
            rowDir = 1;
            colDir = 1;
        } else if(attackingRow > defendingRow && attackingCol > defendingCol){
            rowDir = -1;
            colDir = -1;
        } else if(attackingRow < defendingRow && attackingCol > defendingCol){
            rowDir = 1;
            colDir = -1;
        }
        else if(attackingRow > defendingRow && attackingCol < defendingCol){
            rowDir = -1;
            colDir = 1;
        }
        else if(attackingRow == defendingRow && attackingCol < defendingCol){
            rowDir = 0;
            colDir = 1;
        }
        else if(attackingRow == defendingRow && attackingCol > defendingCol){
            rowDir = 0;
            colDir = -1;
        }
        else if(attackingRow < defendingRow && attackingCol == defendingCol){
            rowDir = 1;
            colDir = 0;
        }
        else if(attackingRow > defendingRow && attackingCol == defendingCol){
            rowDir = 1;
            colDir = 0;
        }

        return new int[]{rowDir, colDir};
    }

    private static boolean kingIsAbleToMoveUnderCheck(Board board, Tile sourceTile){

        return sourceTile.getPiece().getCalculatorOfMoves().getValidDestinationTiles(board, sourceTile).size() != 0;

    }

    private static void clearAllAttackersFromPreviousMove(){

        piecesOnTopLeftDiagonal.clear();
        piecesOnTopRightDiagonal.clear();
        piecesOnBottomLeftDiagonal.clear();
        piecesOnBottomRightDiagonal.clear();
        piecesOnFileAbovePiece.clear();
        piecesOnFileBelowPiece.clear();
        piecesOnRankRightOfPiece.clear();
        piecesOnRankLeftOfPiece.clear();
        knightsAttacksLocations.clear();

    }

    private static boolean isValidStraightAttacker(Piece piece, Alliance allianceOfDefender){
        return (piece instanceof Rook || piece instanceof Queen) && (piece.getAlliance() != allianceOfDefender);
    }


    private static boolean isValidDiagonalAttacker(Piece piece, Alliance allianceOfDefender, Tile tile){

        Tile tileOfDefendingPiece = tile;

        if(piece instanceof Pawn){
            int rowOfAttackingPiece = piece.getCurrentPosition().getRow();
            int colOfAttackingPiece = piece.getCurrentPosition().getCol();
            Alliance allianceOfAttacker = piece.getAlliance();
            int rowOfDefendingPiece = tileOfDefendingPiece.getPosition().getRow();
            int colOfDefendingPiece = tileOfDefendingPiece.getPosition().getCol();
            if(piece.getAlliance() == Alliance.WHITE){
                if(rowOfAttackingPiece - 1 == rowOfDefendingPiece && colOfAttackingPiece + 1 == colOfDefendingPiece ||
                        colOfAttackingPiece - 1 == colOfDefendingPiece && allianceOfAttacker != allianceOfDefender){
                    return true;
                }
            } else {
                if(rowOfAttackingPiece + 1 == rowOfDefendingPiece && colOfAttackingPiece + 1 == colOfDefendingPiece ||
                        colOfAttackingPiece - 1 == colOfDefendingPiece && allianceOfAttacker != allianceOfDefender){
                    return true;
                }
            }
        }

        return (piece instanceof Bishop || piece instanceof Queen) && (piece.getAlliance() != allianceOfDefender);


    }


    /*These three methods collect all the pieces from lines and diagonals which come into the king's square.
    * They are used to determine whether a piece of the king's alliance is allowed to move since that piece may be
    * pinned and discover a check on the king.*/
    private static void collectPiecesFromRanksAndFiles(Tile tile, Board board) {

        int row = tile.getPosition().getRow() - 1;
        int col = tile.getPosition().getCol();

        while(isTileInBoard(row, col)){
            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnFileAbovePiece.add(currentTile.getPiece());
            }
            row--;
        }

        row = tile.getPosition().getRow() + 1;
        col = tile.getPosition().getCol();

        while(isTileInBoard(row, col)){
            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnFileBelowPiece.add(currentTile.getPiece());
            }
            row++;
        }

        row = tile.getPosition().getRow();
        col = tile.getPosition().getCol() - 1;

        while(isTileInBoard(row, col)){
            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnRankLeftOfPiece.add(currentTile.getPiece());
            }
            col--;
        }

        row = tile.getPosition().getRow();
        col = tile.getPosition().getCol() + 1;

        while(isTileInBoard(row, col)){
            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnRankRightOfPiece.add(currentTile.getPiece());
            }
            col++;
        }
    }
    private static void collectPiecesFromDiagonals(Tile tile, Board board){

        int row = tile.getPosition().getRow() - 1;
        int col = tile.getPosition().getCol() - 1;

        while(isTileInBoard(row, col)){

            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnTopLeftDiagonal.add(currentTile.getPiece());
            }
            row--;
            col--;
        }

        row = tile.getPosition().getRow() - 1;
        col = tile.getPosition().getCol() + 1;

        while(isTileInBoard(row, col)){

            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnTopRightDiagonal.add(currentTile.getPiece());
            }
            row--;
            col++;
        }

        row = tile.getPosition().getRow() + 1;
        col = tile.getPosition().getCol() + 1;

        while(isTileInBoard(row, col)){

            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnBottomRightDiagonal.add(currentTile.getPiece());
            }
            row++;
            col++;
        }

        row = tile.getPosition().getRow() + 1;
        col = tile.getPosition().getCol() - 1;

        while(isTileInBoard(row, col)){

            Tile currentTile = board.accessTile(row, col);
            if(currentTile.isTileOccupied()){
                piecesOnBottomLeftDiagonal.add(currentTile.getPiece());
            }
            row++;
            col--;
        }

    }
    //why do i collect these?
    private static void collectKnightsAttacks(Alliance alliance, Tile tile, Board board){

        int row = tile.getPosition().getRow();
        int col = tile.getPosition().getCol();

        //extract common part into method

        if(isTileInBoard(row + 2, col - 1)){
            Tile currentTile = board.accessTile(row + 2, col - 1);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row + 2, col + 1)){
            Tile currentTile = board.accessTile(row + 2, col + 1);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row + 1, col - 2)){
            Tile currentTile = board.accessTile(row + 1, col - 2);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row + 1, col + 2)){
            Tile currentTile = board.accessTile(row + 1, col + 2);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row - 2, col - 1)){
            Tile currentTile = board.accessTile(row - 2, col - 1);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row - 2, col + 1)){
            Tile currentTile = board.accessTile(row - 2, col + 1);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row - 1, col - 2)){
            Tile currentTile = board.accessTile(row - 1, col - 2);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
        if(isTileInBoard(row - 1, col + 2)){
            Tile currentTile = board.accessTile(row - 1, col + 2);
            if(currentTile.isTileOccupied()){
                Piece piece = currentTile.getPiece();
                if(piece instanceof Knight && piece.getAlliance() != alliance){
                    knightsAttacksLocations.add(currentTile);
                }
            }
        }
    }
}
