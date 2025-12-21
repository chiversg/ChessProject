package chess.ai;

import chess.backend.ChessBoard;
import chess.utilities.Evaluations;
import chess.utilities.ChessUtil.*;
import chess.utilities.Moves;

import java.awt.*;
import java.util.LinkedList;

public class GameTree {
    private Node root;
    private Moves moveChecker = new Moves();

    public GameTree() {
        root = new Node(100, null, null);
    }
    public GameTree(ChessBoard start){
        root = new Node(start, null, null);
        root.Turn = Turn.White;
    }

    /**
     * Increases the tree depth by one
     */
    public void IncreaseTreeDepth() {
        LinkedList<Node> leafNodes = new LinkedList<>();

        breadthFirst(root, leafNodes);
        System.out.println("LEAF NODES: " + leafNodes.size());
        for (Node node : leafNodes) {
            LinkedList<Point> pieces = node.data.GetAllPieces(node.Turn);
            for (Point p : pieces) {
                addMoves(node, p);
            }
        }
    }

    /**
     * Add all possible moves a node can make as children
     *
     * @param parent The node that we want to add moves to
     */
    private void addMoves(Node parent, Point startPoint) {
        LinkedList<Point> validMoves = moveChecker.allValidMoves(startPoint.x, startPoint.y, parent.data, parent.Turn);
        for (Point move : validMoves) {
            ChessBoard b = parent.data.copy();
            b.setPiece(b.removePiece(startPoint.x, startPoint.y), move.x, move.y);
            if (parent.firstChild == null) {
                parent.firstChild = new Node(b.copy(), startPoint, move, null, null);
            } else {
                parent.firstChild = new Node(b.copy(), startPoint, move, null, parent.firstChild);
            }
            parent.firstChild.Turn = parent.Turn == Turn.White ? Turn.Black : Turn.White;
        }
    }

    /**
     * Find all the leaf nodes in the Game Tree using BFS
     *
     * @param node The root node of the tree
     * @param list The list to fill with leaf nodes
     */
    private void breadthFirst(Node node, LinkedList<Node> list) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        Node parent;
        Node child;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                parent = queue.poll();

                //If the parent has no children, it must be a leaf node
                if (parent.firstChild == null) {
                    list.add(parent);
                }

                child = parent.firstChild;
                while (child != null) {
                    queue.add(child);
                    child = child.nextSibling;
                }
                size--;
            }
        }
    }

    public Node FindMove(Point from, Point to){
        Node child = root.firstChild;
        while(child != null){
            if(child.fromPos.equals(from) && child.toPos.equals(to)){
                return child;
            }
            child = child.nextSibling;
        }
        return null;
    }

    //Useful for testing tree accuracy
    public void PrintTree(Node node) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(node);
        Node parent;
        Node child;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                parent = queue.poll();

                //System.out.print(parent.item + ", ");
                child = parent.firstChild;
                while (child != null) {
                    queue.add(child);
                    child = child.nextSibling;
                }
                size--;
            }
        }
    }

    public Result Minimax(Node node, int depth, boolean maximizingPlayer) {
        //Base Case. We have reached max search depth, or we have reached a terminal state
        if (depth == 0 ) {
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

    public LinkedList<Node> generateAllChildren(Node parent){
        LinkedList<Point> pieces = parent.data.GetAllPieces(parent.Turn);
        LinkedList<Node> moves = new LinkedList<>();
        for(Point start : pieces) {
           LinkedList<Point> valid = moveChecker.allValidMoves(start.x, start.y, parent.data, parent.Turn);
            for(Point dest : valid){
                ChessBoard b = parent.data.copy();
                b.setPiece(b.removePiece(start.x, start.y), dest.x, dest.y);
                Node n = new Node(b, start, dest, null, null);
                n.Turn = parent.Turn == Turn.White ? Turn.Black : Turn.White;
                moves.add(n);
            }

        }
        return moves;
    }

    private boolean isTerminal(Node n){
        //If the node could not generate children,
        //then we must have reached a checkmate
        if(n.firstChild == null){
            return true;
        }
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

    public Node GetRoot() {
        return root;
    }

    public void SetRoot(Node newRoot){
        root = newRoot;
       //IncreaseTreeDepth();
    }
}
