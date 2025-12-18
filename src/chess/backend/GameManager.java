package chess.backend;

import chess.utilities.ChessUtil.TurnType;

import java.util.ArrayList;

public class GameManager {
    private ChessBoard board = new ChessBoard();
    private ArrayList<Character> captured = new ArrayList<>();

    public TurnType turnType = TurnType.White;

    public void makeMove(int x1, int y1, int x2, int y2) {
        if (!validMove(x1, y1, x2, y2)) {
            throw new InvalidMoveException(""); //TODO add actual error message
        }
        char p = board.setPiece(board.removePiece(x1, y1), x2, y2);
        if (p != ' ') captured.add(p);
    }

    public char[][] getCharArr() {
        return board.getCharArr();
    }


    /**
     * Returns the point value of a chess piece.
     *
     * @param p the piece character
     * @return the piece value, or -1 if invalid
     */
    public static int pieceValue(char p) {
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


    //TODO actually make this shitty fucking method.
    public boolean validMove(int x1, int y1, int x2, int y2) {
        char p = board.getPiece(x1, y1);
        char t = board.getPiece(x2, y2);
        return switch (Character.toLowerCase(p)) {
            case 'p' -> pawnValid(x1, y1, x2, y2, p, t);
            case 'n' -> knightValid(x1, y1, x2, y2);
            case 'b' -> bishopValid(x1, y1, x2, y2);
            case 'r' -> rookValid(x1, y1, x2, y2);
            case 'q' -> queenValid(x1, y1, x2, y2);
            case 'k' -> kingValid(x1, y1, x2, y2);
            default -> false;
        };
    }

    //region validMove helpers

    private boolean isEnemy(char p, char t) {
        return (Character.isLowerCase(p) && Character.isUpperCase(t)) ||
                (Character.isLowerCase(t) && Character.isUpperCase(p));
    }

    //TODO add en passant support, promotion, initial +2 move
    private boolean pawnValid(int x1, int y1, int x2, int y2, char p, char t) {
        if (t == ' ') {
            if (Character.isUpperCase(p)) {
                return x2 == x1 && y2 == y1 + 1;
            } else {
                return x2 == x1 && y2 == y1 - 1;
            }

        } else {
            if (!isEnemy(p, t)) return false;
            if (Character.isUpperCase(p)) {
                return (x2 == x1 + 1 || x2 == x1-1) && y2 == y1 + 1;
            }
            else {
                return (x2 == x1 + 1 || x2 == x1-1) && y2 == y1 - 1;
            }
        }

    }


private boolean knightValid(int x1, int y1, int x2, int y2) {
    return true;
}

private boolean bishopValid(int x1, int y1, int x2, int y2) {
    return true;
}

private boolean rookValid(int x1, int y1, int x2, int y2) {
    return true;
}

private boolean queenValid(int x1, int y1, int x2, int y2) {
    return true;
}

private boolean kingValid(int x1, int y1, int x2, int y2) {
    return true;
}

//endregion


}
