package chess.backend;

import java.util.ArrayList;

/**
 * Stores and manages the state of a chess board.
 *
 * @author Parker Railton
 */
public class ChessBoard {
    private char[][] board;

    public ChessBoard() {
        board = new char[][]{ //white is capital, black is lowercase
                {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'}
        };
    }

    public ChessBoard(boolean empty) {
        if (empty) {
            board = new char[8][8];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = ' ';
                }
            }
        } else {
            board = new char[][]{ //white is capital, black is lowercase
                    {'r', 'n', 'b', 'k', 'q', 'b', 'n', 'r'},
                    {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                    {'R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'}
            };
        }
    }

    public ChessBoard copy() {
        ChessBoard copy = new ChessBoard(true);
        for (int i = 0; i < board.length; i++) {
            copy.board[i] = board[i].clone();
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        String line = "+-+-+-+-+-+-+-+-+";
        for (int i = 0; i < board.length; i++) {
            out.append(line).append("\n");
            for (int j = 0; j < board[i].length; j++) {
                out.append("|").append(board[i][j]);
            }
            out.append("|").append("\n");
        }
        out.append(line);
        return out.toString();
    }

    /**
     * Returns the point value of a chess piece.
     *
     * @param p the piece character
     * @return the piece value, or -1 if invalid
     */
    public int pieceValue(char p) {
        p = Character.toLowerCase(p);
        return switch (p) {
            case 'p' -> 1;
            case 'n', 'b' -> 3;
            case 'r' -> 5;
            case 'q' -> 9;
            case 'k' -> Integer.MAX_VALUE;
            default -> -1;
        };
    }

    public int pieceValue(int x, int y) {
        char p = Character.toLowerCase(board[y][x]);
        return switch (p) {
            case 'p' -> 1;
            case 'n', 'b' -> 3;
            case 'r' -> 5;
            case 'q' -> 9;
            case 'k' -> Integer.MAX_VALUE;
            default -> -1;
        };
    }

    private void boundsCheck(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= board[0].length || y < 0 || y >= board.length) {
            throw new IndexOutOfBoundsException("Index must be within (0,0) and (7,7).");
        }
    }


    public char getPiece(int x, int y) {
        boundsCheck(x, y);
        return board[y][x];
    }

    public char setPiece(char p, int x, int y) {
        boundsCheck(x, y);
        char out = board[y][x];
        board[y][x] = p;
        return out;
    }

    public char removePiece(int x, int y) {
        boundsCheck(x, y);
        char out = board[y][x];
        board[y][x] = ' ';
        return out;
    }

    public ArrayList<Character> listPieces() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != ' ') list.add(board[i][j]);
            }
        }
        return list;
    }

    public ArrayList<Character> listWhite() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != ' ' && Character.isUpperCase(board[i][j])) list.add(board[i][j]);
            }
        }
        return list;
    }

    public ArrayList<Character> listBlack() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != ' ' && Character.isLowerCase(board[i][j])) list.add(board[i][j]);
            }
        }
        return list;
    }

    public boolean isWhite(char piece) {
        return Character.isUpperCase(piece);
    }

    public boolean isBlack(char piece) {
        return Character.isLowerCase(piece);
    }
}
