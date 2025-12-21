package chess.ai;

import java.awt.*;

public class Move {
    public Point fromPos;
    public Point toPos;

    public Move(Point from, Point to){
        fromPos = from;
        toPos = to;
    }
}
