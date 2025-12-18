package chess.backend;

import chess.frontend.Client;
import chess.utilities.ChessUtil.TurnType;

import java.util.ArrayList;
import java.awt.Point;
import java.util.LinkedList;

public class GameManager {
    private ChessBoard board = new ChessBoard();
    public ArrayList<Character> captured = new ArrayList<>();
    public Client client;

    public TurnType turnType = TurnType.White;

    public void makeMove(int x1, int y1, int x2, int y2) {
        if (!validMove(x1, y1, x2, y2)) {
            throw new InvalidMoveException(""); //TODO add actual error message
        }
        char p = board.setPiece(board.removePiece(x1, y1), x2, y2);
        if (p != ' ') captured.add(p);

        turnType = (turnType == TurnType.White) ? TurnType.Black : TurnType.White;
        client.boardStateChanged();
    }

    public char[][] getCharArr() {
        return board.getCharArr();
    }





    //TODO actually make this shitty fucking method.
    public boolean validMove(int x1, int y1, int x2, int y2) {
        if (x1-x2 == 0 && y1-y2 == 0) return false;
        char p = board.getPiece(x1, y1);
        char t = board.getPiece(x2, y2);
        if (Character.isUpperCase(p) && turnType != TurnType.White ||
        Character.isLowerCase(p) && turnType != TurnType.Black) {
            return false;
        }
        return switch (Character.toLowerCase(p)) {
            case 'p' -> pawnValid(x1, y1, x2, y2, p, t);
            case 'n' -> knightValid(x1, y1, x2, y2, p, t);
            case 'b' -> bishopValid(x1, y1, x2, y2, p, t);
            case 'r' -> rookValid(x1, y1, x2, y2, p, t);
            case 'q' -> queenValid(x1, y1, x2, y2, p, t);
            case 'k' -> kingValid(x1, y1, x2, y2, p, t);
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
                return x2 == x1 && y2 == y1 - 1;
            } else {
                return x2 == x1 && y2 == y1 + 1;
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


private boolean knightValid(int x1, int y1, int x2, int y2, char p, char t) {
        if (t != ' ' && !isEnemy(p, t)) return false;
    return ((x2 == x1 + 1 || x2 == x1 - 1) && (y2 == y1 + 2 || y2 == y1 - 2)) ||
            ((x2 == x1 + 2 || x2 == x1 - 2) && (y2 == y1 + 1 || y2 == y1 - 1));
}

private boolean bishopValid(int x1, int y1, int x2, int y2, char p, char t) {
    if (t != ' ' && !isEnemy(p, t)) return false;
    //check if move is on the diagonal
    int dX = x2-x1;
    int dY = y2-y1;
    if (Math.abs(dY) != Math.abs(dX)) return false;

    //check for pieces in the way
    int sigX = (int) Math.signum(dX);
    int sigY = (int) Math.signum(dY);
    for (int i = 1; i < Math.abs(dX); i++) {
        if (board.getPiece(x1 + i*sigX, y1 +i*sigY) != ' ') return false;
    }
    return true;
}

private boolean rookValid(int x1, int y1, int x2, int y2, char p, char t) {
    if (t != ' ' && !isEnemy(p, t)) return false;
    //check if move is on the cardinal
    int dX = x2-x1;
    int dY = y2-y1;
    if (dX != 0 && dY != 0) return false;

    //check for pieces in the way
    int sigX = (int) Math.signum(dX);
    int sigY = (int) Math.signum(dY);
    for (int i = 1; i < Math.max(Math.abs(dX), Math.abs(dY)); i++) {
        if (board.getPiece(x1 + i*sigX, y1 +i*sigY) != ' ') return false;
    }
    return true;
}

private boolean queenValid(int x1, int y1, int x2, int y2, char p, char t) {
    if (t != ' ' && !isEnemy(p, t)) return false;
    //check if move is on the cardinal or diagonal
    int dX = x2-x1;
    int dY = y2-y1;
    if ((dX != 0 && dY != 0) && (Math.abs(dY) != Math.abs(dX)))return false;

    //check for pieces in the way
    int sigX = (int) Math.signum(dX);
    int sigY = (int) Math.signum(dY);
    for (int i = 1; i < Math.max(Math.abs(dX), Math.abs(dY)); i++) {
        if (board.getPiece(x1 + i*sigX, y1 +i*sigY) != ' ') return false;
    }
    return true;
}
//TODO prevent moving into check
private boolean kingValid(int x1, int y1, int x2, int y2, char p, char t) {
    if (t != ' ' && !isEnemy(p, t)) return false;
    int dX = x2-x1;
    int dY = y2-y1;
    if (Math.abs(dX) > 1 || Math.abs(dY) > 1) return false;
    return true;
}

//endregion

    public LinkedList<Point> allValidMoves(int x, int y) {
        LinkedList<Point> list = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validMove(x, y, j, i)) {
                    list.add(new Point(j, i));
                }
            }
        }
        return list;
    }

}
