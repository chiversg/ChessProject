package chess.ai;

import chess.backend.ChessBoard;

import java.awt.*;

public class Node {
    public ChessBoard data;
    Point fromPos;
    Point toPos;
    int item;
    public Node firstChild;
    public Node nextSibling;

    public Node(int item, Node child, Node Sibling){
        this.item = item;
        firstChild = child;
        nextSibling = Sibling;
    }
}
