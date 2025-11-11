package chess.backend;

import java.util.ArrayList;

public class GameManager {
    private ChessBoard board = new ChessBoard();
    private ArrayList<Character> captured = new ArrayList<>();

    public void makeMove(int x1, int y1, int x2, int y2) {
        if (!validMove(x1, y1, x2, y2)) {
           throw new InvalidMoveException(""); //TODO add actual error message
        }
        char p = board.setPiece(board.removePiece(x1,y1), x2, y2);
        if (p != ' ') captured.add(p);
    }


    //TODO actually make this shitty fucking method.
    private boolean validMove(int x1, int y1, int x2, int y2) {
       return true;
    }
}
