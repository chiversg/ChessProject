package chess.ai;

import chess.backend.ChessBoard;
import chess.utilities.Evaluations;
import chess.utilities.ChessUtil.*;
import chess.utilities.Moves;

import java.awt.*;
import java.util.LinkedList;

public class GameTree {
    private Moves moveChecker = new Moves();

    public Result Minimax(Node node, int depth, boolean maximizingPlayer) {
        //Base Case. We have reached max search depth, or we have reached a terminal state
        if (depth == 0) {
            return new Result(node, Evaluations.EvaluateBoard(node.data.getCharArr()));
        }

        Result value;
        LinkedList<Node> children = generateAllChildren(node);

        if (maximizingPlayer) {
            value = new Result(null, -Float.MAX_VALUE);
            for(Node child : children) {
                value = maxResult(value, Minimax(child, depth - 1, false), child);
            }
        } else {
            value = new Result (null, Float.MAX_VALUE);
            for(Node child : children) {
                value = minResult(value, Minimax(child, depth - 1, true), child);
            }
        }
        return value;
    }

    public Result MinimaxAB(Node node, int depth, float alpha, float beta, boolean maximizingPlayer) {
        Result value;
        LinkedList<Node> children = generateAllChildren(node);

        //Terminal state, no more legal moves can be made
        if(children.isEmpty()){
            return new Result(node, Evaluations.EvaluateBoard(node.data.getCharArr()));
        }
        //Base Case. We have reached max search depth
        if (depth == 0) {
            return new Result(node, Evaluations.EvaluateBoard(node.data.getCharArr()));
        }

        if (maximizingPlayer) {
            value = new Result(null, -Float.MAX_VALUE);
            for(Node child : children) {
                value = maxResult(value, MinimaxAB(child, depth - 1, alpha, beta, false), child);
                alpha = Math.max(alpha, value.evaluation);
                if(beta <= alpha){
                    //System.out.println("pruning da branch");
                    break;
                }
            }
        } else {
            value = new Result (null, Float.MAX_VALUE);
            for(Node child : children) {
                value = minResult(value, MinimaxAB(child, depth - 1, alpha, beta, true), child);
                beta = Math.min(beta, value.evaluation);
                if(beta <= alpha){
                    //System.out.println("pruning da branch");
                    break;
                }
            }
        }
        return value;
    }

    public LinkedList<Node> generateAllChildren(Node parent){
        LinkedList<Point> pieces = parent.data.GetAllPieces(parent.Turn);
        LinkedList<Node> moves = new LinkedList<>();
        for(Point start : pieces) {
           LinkedList<Point> valid = moveChecker.allValidMoves(start.x, start.y, parent.data, parent.Turn);
            for(Point dest : valid){
                ChessBoard b = parent.data.copy();
                b.setPiece(b.removePiece(start.x, start.y), dest.x, dest.y);
                Node n = new Node(b, start, dest);
                n.Turn = parent.Turn == Turn.White ? Turn.Black : Turn.White;
                moves.add(n);
            }

        }
        return moves;
    }

    private boolean isTerminal(Node n){
        //If the node could not generate children,
        //then we must have reached a checkmate

        return false;
    }

    private Result maxResult(Result r1, Result r2, Node node){
        if (r1.evaluation >= r2.evaluation) {
            return r1;
        } else {
            return new Result(node, r2.evaluation);
        }
    }

    private Result minResult(Result r1, Result r2, Node node){
        if (r1.evaluation <= r2.evaluation) {
            return r1;
        } else {
            return new Result(node, r2.evaluation);
        }
    }
}
