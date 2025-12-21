import chess.frontend.*;
import chess.backend.*;
import chess.utilities.Evaluations;

public class Test {
    public static void main(String[] args) {
        //GameTree gt = new GameTree();
        //gt.IncreaseTreeDepth();
        //gt.IncreaseTreeDepth();
        //gt.PrintTree(gt.GetRoot());
        //gt.IncreaseTreeDepth();
        //gt.PrintTree(gt.GetRoot());
        new Client();
        System.out.println(Evaluations.EvaluateBoard(new ChessBoard().getCharArr()));
    }
}
