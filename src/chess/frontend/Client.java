package chess.frontend;

import chess.backend.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Client extends Thread {
    static Client Reference;
    BufferedImage bKing, bQueen, bRook, bBishop, bKnight, bPawn;    //Black piece icons
    BufferedImage wKing, wQueen, wRook, wBishop, wKnight, wPawn;    //White piece icons
    BufferedImage background, validBorder, selectedBorder;          //Misc. images
    private int[] selected = {-1, -1};    //Coordinates of the selected piece
    private int[] destination = {-1, -1}; //Coordinates of the destination
    private boolean newTurn;
    private boolean gameOver;
    private Log log;
    private BoardManager boardManager;
    private GameManager gameManager;

    public Client() {
        Reference = this;
        log = new Log();
        boardManager = new BoardManager(100, 100, this);

        loadImages();

        JFrame frame = new JFrame("Chess Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.pink);
        frame.setSize(750, 750);
        frame.setLayout(null);

        buildBoard(frame);

        frame.setVisible(true);

        log.UpdateMinimaxLog(10, 2, 43.56646f);
    }

    public void run() {
        while (!gameOver) {
            //TODO Receive new turn from backend
            if (newTurn) {
                //TODO Sync client board to match backend
                while (true) {
                    if (selected[0] < 0) {
                        selectPiece();
                    } else if (destination[0] < 0) {
                        selectDestination();
                    } else {
                        //TODO Call GameManager input move
                        break;
                    }
                }
                newTurn = false;
            }
            //TODO Receive game over command from backend
        }
    }

    private void buildBoard(JFrame frame) {

        JPanel boardUI = boardManager.GeneratePanel(background);
        JPanel logUI = log.GeneratePanel();

        frame.add(boardUI);
        frame.add(logUI);
        //frame.add(board, BorderLayout.CENTER);
        boardManager.UpdatePieceIcon(1, 2, wPawn);
    }

    static char numberToLetter(int number) {
        return switch (number) {
            case 1 -> 'a';
            case 2 -> 'b';
            case 3 -> 'c';
            case 4 -> 'd';
            case 5 -> 'e';
            case 6 -> 'f';
            case 7 -> 'g';
            case 8 -> 'h';
            default -> '-';
        };
    }

    private int letterToNumber(char letter) {
        letter = Character.toLowerCase(letter);
        return switch (letter) {
            case 'a' -> 1;
            case 'b' -> 2;
            case 'c' -> 3;
            case 'd' -> 4;
            case 'e' -> 5;
            case 'f' -> 6;
            case 'g' -> 7;
            case 'h' -> 8;
            default -> -1;
        };
    }

    public void TileClicked(int x, int y) {
        if (selected[0] < 0 && !boardManager.IsEmptyTile(x, y)) {
            System.out.println("start point selected");
            selected[0] = x;
            selected[1] = y;
            boardManager.UpdateEffectIcon(x, y, selectedBorder);
        } else if(selected[0] > 0) {
            System.out.println("destination selected");
            destination[0] = x;
            destination[1] = y;

            if(!Arrays.equals(selected, destination)) checkMove();
        }
    }

    private void checkMove() {
        //I would call the backend to verify if the move is valid
        System.out.println("It's movin' time");
        boardManager.MovePiece(selected[0], selected[1], destination[0], destination[1]);
        log.UpdateMoveLog(selected[0] + ", " + selected[0] + " -> " + destination[0] + ", " + destination[0]);
        Arrays.fill(selected, -1);
        Arrays.fill(destination, -1);
    }


    //TODO Implement function logic
    private void selectPiece() {
        //Detect mouse position on client and when mouse is clicked

        //Convert mouse position into coordinates on the grid

        //Check if there is a piece on the tile

        //Check if that piece belongs to whoever turn it is

        //Update the current selected piece

        //Get a list of valid moves for selected piece
    }

    //TODO Implement function logic
    private void selectDestination() {
        //Detect mouse position on client and when mouse is clicked

        //Convert mouse position into coordinates on the grid

        //Check if tile is in valid move list

        //Update destination if so
    }

    private void loadImages() {
        try {
            bKing = ImageIO.read(new File("src/chess/frontend/icons/black_king.png"));
            bQueen = ImageIO.read(new File("src/chess/frontend/icons/black_queen.png"));
            bRook = ImageIO.read(new File("src/chess/frontend/icons/black_rook.png"));
            bBishop = ImageIO.read(new File("src/chess/frontend/icons/black_bishop.png"));
            bKnight = ImageIO.read(new File("src/chess/frontend/icons/black_knight.png"));
            bPawn = ImageIO.read(new File("src/chess/frontend/icons/black_pawn.png"));

            wKing = ImageIO.read(new File("src/chess/frontend/icons/white_king.png"));
            wQueen = ImageIO.read(new File("src/chess/frontend/icons/white_queen.png"));
            wRook = ImageIO.read(new File("src/chess/frontend/icons/white_rook.png"));
            wBishop = ImageIO.read(new File("src/chess/frontend/icons/white_bishop.png"));
            wKnight = ImageIO.read(new File("src/chess/frontend/icons/white_knight.png"));
            wPawn = ImageIO.read(new File("src/chess/frontend/icons/white_pawn.png"));

            background = ImageIO.read(new File("src/chess/frontend/icons/board.png"));
            selectedBorder = ImageIO.read(new File("src/chess/frontend/icons/selected_border.png"));
            validBorder = ImageIO.read(new File("src/chess/frontend/icons/valid_move_border.png"));
        } catch (IOException e) {
            System.out.println("There was an error loading images");
        }

    }
}
