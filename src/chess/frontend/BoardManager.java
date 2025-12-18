package chess.frontend;

import chess.utilities.ChessUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class manages board events on the client
 */
public class BoardManager {
    BoardTile[][] pieces = new BoardTile[8][8];
    JLabel[][] highlights = new JLabel[8][8];
    int[] boardOrigin = new int[2];
    Client client;

    public BoardManager(int x, int y, Client cl) {
        boardOrigin[0] = x;
        boardOrigin[1] = y;
        client = cl;
    }

    public JPanel GeneratePanel(BufferedImage background) {
        JPanel container = new JPanel();
        container.setOpaque(false);
        container.setLayout(null);
        container.setSize(750, 750);

        //Create Board
        JPanel board = new JPanel();
        board.setLayout(null); //Set layout to null so that we can accurately place labels using pixel coordinates
        board.setSize(background.getWidth(), background.getHeight());
        board.setBackground(Color.blue);
        JLabel boardIcon = new JLabel(new ImageIcon(background));
        boardIcon.setBounds(0, 0, background.getWidth(), background.getHeight());

        //Vertical Labels
        JPanel vLabels = new JPanel();
        vLabels.setLayout(new GridLayout(8, 1));
        vLabels.setBounds(0, 0, board.getWidth() / 8, board.getHeight());
        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(String.valueOf(8 - i), JLabel.CENTER);
            vLabels.add(label);
        }

        //Horizontal Labels
        JPanel hLabels = new JPanel();
        hLabels.setLayout(new GridLayout(1, 8));
        hLabels.setBounds(0, 0, board.getWidth(), board.getHeight() / 8);
        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel(String.valueOf(ChessUtil.numberToLetter(i + 1)), JLabel.CENTER);
            hLabels.add(label);
        }

        //Pieces Layer
        JPanel pieceLayer = new JPanel();
        pieceLayer.setOpaque(false);
        pieceLayer.setLayout(new GridLayout(8, 8));
        pieceLayer.setBounds(0, 0, board.getWidth(), board.getHeight());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BoardTile icon = new BoardTile(j + 1, i + 1);
                pieces[j][i] = icon;
                pieceLayer.add(icon);
            }
        }

        //Effects Layer
        JPanel effectLayer = new JPanel();
        effectLayer.setOpaque(false);
        effectLayer.setLayout(new GridLayout(8, 8));
        effectLayer.setBounds(0, 0, board.getWidth(), board.getHeight());
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JLabel icon = new JLabel();
                highlights[j][i] = icon;
                effectLayer.add(icon);
            }
        }

        //Add layers to the board. Images added first will display over those placed after
        board.add(effectLayer);
        board.add(pieceLayer);
        board.add(boardIcon);

        board.setBounds(boardOrigin[0], boardOrigin[1], board.getWidth(), board.getHeight());
        vLabels.setBounds(boardOrigin[0] - vLabels.getWidth(), boardOrigin[1], vLabels.getWidth(), vLabels.getHeight());
        hLabels.setBounds(boardOrigin[0], boardOrigin[1] + board.getHeight(), hLabels.getWidth(), hLabels.getHeight());

        container.add(board);
        container.add(vLabels);
        container.add(hLabels);

        return container;
    }

    public void UpdatePieceIcon(int x, int y, BufferedImage icon) {
        pieces[y - 1][x - 1].setIcon(new ImageIcon(icon));
    }

    public void UpdateBoardHighlight(int x, int y, BufferedImage icon) {
        highlights[x - 1][y - 1].setIcon(new ImageIcon(icon));
    }

    public void RemoveBoardHighlights() {
        for (int i = 0; i < highlights.length; i++) {
            for (int j = 0; j < highlights[i].length; j++) {
                highlights[i][j].setIcon(null);
            }
        }
    }

    public void MovePiece(int x1, int y1, int x2, int y2) {
        Icon piece = pieces[x1 - 1][y1 - 1].getIcon();
        System.out.println(pieces[y1 - 1][x1 - 1]);
        pieces[x1 - 1][y1 - 1].setIcon(null);
        pieces[x2 - 1][y2 - 1].setIcon(piece);
    }

    public boolean IsEmptyTile(int x, int y) {
        Icon piece = pieces[x - 1][y - 1].getIcon();
        return piece == null;
    }


}
