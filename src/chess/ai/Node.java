package chess.ai;

import chess.backend.ChessBoard;
import chess.utilities.ChessUtil.*;

import java.awt.*;

public class Node {
    public ChessBoard data;
    public Point fromPos;
    public Point toPos;
    public Turn Turn;

    public Node(ChessBoard board) {
        data = board;
    }

    public Node(ChessBoard board, Point parentPos, Point pos) {
        data = board;
        this.fromPos = parentPos;
        this.toPos = pos;
    }

    public void PrintMove() {
        System.out.println("(" + fromPos.x + ", " + fromPos.y + ") -> (" + toPos.x + ", " + toPos.y + ")");
    }
}
