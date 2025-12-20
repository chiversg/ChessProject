package chess.ai;

import chess.backend.ChessBoard;
import chess.utilities.ChessUtil.*;

import java.awt.*;

public class Node {
    public ChessBoard data;

    Point fromPos;
    Point toPos;
    int item;
    public Node firstChild;
    public Node nextSibling;
    public Turn Turn;

    public Node(int item, Node child, Node Sibling){
        this.item = item;
        firstChild = child;
        nextSibling = Sibling;
    }
    public Node(ChessBoard board, Node child, Node Sibling){
        data = board;
        firstChild = child;
        nextSibling = Sibling;
    }
    public Node(ChessBoard board, Point parentPos, Point pos, Node child, Node Sibling){
        data = board;
        this.fromPos = parentPos;
        this.toPos = pos;
        firstChild = child;
        nextSibling = Sibling;
    }
}
