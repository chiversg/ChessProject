package chess.frontend;

import chess.backend.InvalidMoveException;
import chess.utilities.ChessUtil.Turn;
import chess.utilities.ChessUtil;
import chess.backend.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public class Client {
    static Client Reference;
    private char[][] board;
    BufferedImage bKing, bQueen, bRook, bBishop, bKnight, bPawn;    //Black piece icons
    BufferedImage wKing, wQueen, wRook, wBishop, wKnight, wPawn;    //White piece icons
    BufferedImage background, validBorder, selectedBorder;          //Misc. images
    private Point fromPos = new Point(-1, -1);    //Coordinates of the selected piece
    private Point toPos = new Point(-1, -1); //Coordinates of the destination
    private Turn currentTurn;
    private Log log;
    private BoardManager boardManager;
    private GameManager gameManager;
    Point to = new Point();

    public Client() {
        gameManager = new GameManager();

        Reference = this;
        log = new Log();
        boardManager = new BoardManager(100, 100, this);

        gameManager.client = this;

        board = gameManager.getCharArr();

        loadImages();

        JFrame frame = new JFrame("Chess Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.pink);
        frame.setSize(750, 750);
        frame.setLayout(null);

        buildBoard(frame);
        boardStateChanged();
        frame.setVisible(true);

        log.UpdateMinimaxLog(10, 2, 43.56646f);
    }

    private void buildBoard(JFrame frame) {
        JPanel boardUI = boardManager.GeneratePanel(background);
        JPanel logUI = log.GeneratePanel();

        frame.add(boardUI);
        frame.add(logUI);
    }

    public void TileClicked(int x, int y) {
        if (fromPos.x <= 0 && !boardManager.IsEmptyTile(x, y)) {
            System.out.println("start point selected");
            LinkedList<Point> validTiles = gameManager.allValidMoves(x, y);
            for(Point p : validTiles){
                boardManager.UpdateBoardHighlight(p.x, p.y, validBorder);
            }
            fromPos.x = x;
            fromPos.y = y;
            boardManager.UpdateBoardHighlight(x, y, selectedBorder);
        } else if (fromPos.x >= 0) {
            System.out.println("destination selected");
            toPos.x = x;
            toPos.y = y;

            checkMove();
        }
    }

    private void checkMove() {
        if (!fromPos.equals(toPos)) {
            try {
                gameManager.makeMove(fromPos.x, fromPos.y, toPos.x, toPos.y);
                System.out.println("It's movin' time");

                log.UpdateMoveLog((fromPos.x + 1) + ", " + (fromPos.y + 1) + " -> " + (toPos.x + 1) + ", " + (toPos.y + 1));

            } catch (InvalidMoveException e) {
                System.out.println("Invalid Move");
            }
        }
        fromPos.setLocation(-1, -1);
        toPos.setLocation(-1, -1);
        boardManager.RemoveBoardHighlights();
    }




public void boardStateChanged() {
    board = gameManager.getCharArr();
    currentTurn = gameManager.turnType;
    boardManager.RemovePieceIcons();
    log.UpdateTurnLabel(currentTurn);
    // Loop through the new board,
    // place pieces on the GUI board accordingly
    BufferedImage piece;
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            piece = letterToImage(board[j][i]);
            if (piece != null) {
                boardManager.UpdatePieceIcon(j, i, piece);
            }
        }
    }
}


private BufferedImage letterToImage(char letter) {
    return switch (letter) {
        case 'P' -> wPawn;
        case 'R' -> wRook;
        case 'N' -> wKnight;
        case 'B' -> wBishop;
        case 'K' -> wKing;
        case 'Q' -> wQueen;
        case 'p' -> bPawn;
        case 'r' -> bRook;
        case 'n' -> bKnight;
        case 'b' -> bBishop;
        case 'k' -> bKing;
        case 'q' -> bQueen;
        default -> null;
    };
}

private void loadImages() {
    try {
        bKing = ImageIO.read(new File(ChessUtil.iconPath + "black_king.png"));
        bQueen = ImageIO.read(new File(ChessUtil.iconPath + "black_queen.png"));
        bRook = ImageIO.read(new File(ChessUtil.iconPath + "black_rook.png"));
        bBishop = ImageIO.read(new File(ChessUtil.iconPath + "black_bishop.png"));
        bKnight = ImageIO.read(new File(ChessUtil.iconPath + "black_knight.png"));
        bPawn = ImageIO.read(new File(ChessUtil.iconPath + "black_pawn.png"));

        wKing = ImageIO.read(new File(ChessUtil.iconPath + "white_king.png"));
        wQueen = ImageIO.read(new File(ChessUtil.iconPath + "white_queen.png"));
        wRook = ImageIO.read(new File(ChessUtil.iconPath + "white_rook.png"));
        wBishop = ImageIO.read(new File(ChessUtil.iconPath + "white_bishop.png"));
        wKnight = ImageIO.read(new File(ChessUtil.iconPath + "white_knight.png"));
        wPawn = ImageIO.read(new File(ChessUtil.iconPath + "white_pawn.png"));

        background = ImageIO.read(new File(ChessUtil.iconPath + "board.png"));
        selectedBorder = ImageIO.read(new File(ChessUtil.iconPath + "selected_border.png"));
        validBorder = ImageIO.read(new File(ChessUtil.iconPath + "valid_move_border.png"));
    } catch (IOException e) {
        System.out.println("There was an error loading images");
    }

}
}
