package gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.utils.BoardUtils.*;
import static com.chess.engine.Launcher.*;
import static com.chess.engine.PiecesInitializer.initializePieces;
import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class Table {

    //REWRITE RANK CONSTANTS

    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessBoard;
    List<Player> players;
    Player whitePlayer;
    Player blackPlayer;
    Player currentPlayer;

    private Tile sourceTile;
    private Tile destinationTile;
    private Piece humanMovedPiece;

    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600,600);
    private final static Dimension BOARD_PANEL_DIMENSION  = new Dimension(400,350);
    private final static Dimension TILE_PANEL_DIMENSION  = new Dimension(10,10);
    private final static String defaultPieceImagesPath = "art/pieces/plain/";

    private final Color lightTileColor = Color.decode("#FFFACD");
    private final Color darkTileColor = Color.decode("#593E1A");

    public Table(){
        gameFrame = new JFrame("JChess");
        gameFrame.setLayout(new BorderLayout());
        final JMenuBar tableMenuBar = createTableMenuBar();
        gameFrame.setJMenuBar(tableMenuBar);
        gameFrame.setSize(OUTER_FRAME_DIMENSION);
        chessBoard = createBoard();
        initializeBoard(chessBoard);
        players = createPlayers();
        whitePlayer = players.get(0);
        blackPlayer = players.get(1);
        currentPlayer = whitePlayer;
        initializePieces(chessBoard, players.get(0), players.get(1));
        boardPanel = new BoardPanel();
        gameFrame.add(boardPanel, BorderLayout.CENTER);
        gameFrame.setVisible(true);
    }

    private JMenuBar createTableMenuBar(){
        final JMenuBar tableMenuBar = new JMenuBar();
        tableMenuBar.add(createFileMenu());
        return tableMenuBar;
    }

    private static JMenu createFileMenu(){
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(actionEvent -> System.out.println("open up that pgn file!"));
        fileMenu.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(actionEvent -> System.exit(0));
        fileMenu.add(exitMenuItem);

        return fileMenu;
    }

    private class BoardPanel extends JPanel{

        final List<TilePanel> boardTiles;

        BoardPanel(){
            super(new GridLayout(8, 8));
            boardTiles = new ArrayList<>();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    final String tileCoordinate = characters[col] + digits[row];
                    final TilePanel tilePanel = new TilePanel(this, tileCoordinate);
                    boardTiles.add(tilePanel);
                    add(tilePanel);
                }
            }
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }

        public void drawBoard(final Board board){
            removeAll();
            for(final TilePanel tilePanel: boardTiles){
                tilePanel.drawTile(board);
                add(tilePanel);
            }
            validate();
            repaint();
        }
    }

    private class TilePanel extends JPanel {

        private final String tileCoordinate;

        TilePanel(final BoardPanel boardPanel, final String tileCoordinate) {
            super(new GridBagLayout());
            this.tileCoordinate = tileCoordinate;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignTilePieceIcon(chessBoard);

            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent mouseEvent) {

                    if(isRightMouseButton(mouseEvent)){
                        sourceTile = null;
                        destinationTile = null;
                        humanMovedPiece = null;
                    } else if(isLeftMouseButton(mouseEvent)){
                        if(sourceTile == null){
                            sourceTile = chessBoard.accessTileByLetter(Character.toString(tileCoordinate.charAt(0)),
                                    Integer.parseInt(Character.toString(tileCoordinate.charAt(1))));
                            humanMovedPiece = sourceTile.getPiece(); //does not make the piece var null

                            currentPlayer.setPieceToPlayWith(humanMovedPiece);

                            if(humanMovedPiece == null){
                                sourceTile = null;
                            }
                        } else {
                            destinationTile = chessBoard.accessTileByLetter(Character.toString(tileCoordinate.charAt(0)),
                                    Integer.parseInt(Character.toString(tileCoordinate.charAt(1))));

                            try {
                                currentPlayer.movePiece(chessBoard, sourceTile, destinationTile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            sourceTile = null;
                            destinationTile = null;
                            humanMovedPiece = null;

                            if(currentPlayer.equals(whitePlayer)){
                                currentPlayer = blackPlayer;
                            } else if(currentPlayer.equals(blackPlayer)){
                                currentPlayer = whitePlayer;
                            }

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    boardPanel.drawBoard(chessBoard);
                                }
                            });
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent mouseEvent) {

                }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {

                }

                @Override
                public void mouseEntered(MouseEvent mouseEvent) {

                }

                @Override
                public void mouseExited(MouseEvent mouseEvent) {

                }
            });

            validate();
        }

        public void drawTile(final Board board){
            assignTileColor();
            assignTilePieceIcon(board);
            validate();
            repaint();
        }

        private void assignTilePieceIcon(final Board board){
            this.removeAll();

            String letterPart = Character.toString(tileCoordinate.charAt(0));
            int numberPart = Integer.parseInt(Character.toString(tileCoordinate.charAt(1)));
            if(board.accessTileByLetter(letterPart, numberPart).isTileOccupied()){

                String allianceFirstLetter = board.accessTileByLetter(letterPart, numberPart).getPiece().getAlliance().toString().substring(0, 1);
                String pieceFirstLetter = board.accessTileByLetter(letterPart, numberPart).getPiece().getPieceTitle();

                try{
                    final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath +
                            allianceFirstLetter + pieceFirstLetter + ".gif"));
                    add(new JLabel(new ImageIcon(image)));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        private void assignTileColor() {

            int intPartOfTileCoordinate = Integer.parseInt(Character.toString(this.tileCoordinate.charAt(1)));
            char charPartOfTileCoordinate = this.tileCoordinate.charAt(0);

            if (LAST_RANK + 1 == intPartOfTileCoordinate ||
                    SIXTH_RANK + 1 == intPartOfTileCoordinate ||
                    FOURTH_RANK + 1 == intPartOfTileCoordinate ||
                    SECOND_RANK + 1 == intPartOfTileCoordinate) {

                switch (charPartOfTileCoordinate) {

                    case 'A':
                    case 'C':
                    case 'E':
                    case 'G':
                        setBackground(lightTileColor);
                        break;
                    case 'B':
                    case 'D':
                    case 'F':
                    case 'H':
                        setBackground(darkTileColor);
                        break;
                }

            } else if (SEVENTH_RANK + 1 == intPartOfTileCoordinate ||
                    FIFTH_RANK + 1 == intPartOfTileCoordinate ||
                    THIRD_RANK + 1 == intPartOfTileCoordinate ||
                    FIRST_RANK + 1 == intPartOfTileCoordinate) {

                switch (charPartOfTileCoordinate) {

                    case 'A':
                    case 'C':
                    case 'E':
                    case 'G':
                        setBackground(darkTileColor);
                        break;
                    case 'B':
                    case 'D':
                    case 'F':
                    case 'H':
                        setBackground(lightTileColor);
                        break;
                }
            }
        }
        }
}

