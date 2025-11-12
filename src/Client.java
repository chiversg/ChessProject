import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Client extends Thread {
    BufferedImage bKing, bQueen, bRook, bBishop, bKnight, bPawn;    //Black piece icons
    BufferedImage wKing, wQueen, wRook, wBishop, wKnight, wPawn;    //White piece icons
    BufferedImage background, validBorder, selectedBorder;          //Misc. images
    int[] selected = {-1, -1};    //Coordinates of the selected piece
    int[] destination = {-1, -1}; //Coordinates of the destination
    private boolean newTurn;
    private boolean gameOver;

    public Client() {
        loadImages();

        JFrame frame = new JFrame("Chess Client");
        frame.setSize(500, 500);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(new ImageIcon(background));

        panel.add(label);
        frame.add(panel);

        frame.setVisible(true);
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
            bKing = ImageIO.read(new File("src/icons/black_king.png"));
            bQueen = ImageIO.read(new File("src/icons/black_queen.png"));
            bRook = ImageIO.read(new File("src/icons/black_rook.png"));
            bBishop = ImageIO.read(new File("src/icons/black_bishop.png"));
            bKnight = ImageIO.read(new File("src/icons/black_knight.png"));
            bPawn = ImageIO.read(new File("src/icons/black_pawn.png"));

            wKing = ImageIO.read(new File("src/icons/white_king.png"));
            wQueen = ImageIO.read(new File("src/icons/white_queen.png"));
            wRook = ImageIO.read(new File("src/icons/white_rook.png"));
            wBishop = ImageIO.read(new File("src/icons/white_bishop.png"));
            wKnight = ImageIO.read(new File("src/icons/white_knight.png"));
            wPawn = ImageIO.read(new File("src/icons/white_pawn.png"));

            background = ImageIO.read(new File("src/icons/board.png"));
            selectedBorder = ImageIO.read(new File("src/icons/selected_border.png"));
            validBorder = ImageIO.read(new File("src/icons/valid_move_border.png"));
        } catch (IOException e) {
            System.out.println("There was an error loading images");
        }

    }
}
