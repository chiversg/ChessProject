import chess.ai.GameTree;
import chess.frontend.*;
import chess.backend.*;

public class Test {
    public static void main(String[] args) {
        GameTree gt = new GameTree();
        gt.IncreaseTreeDepth();
        gt.IncreaseTreeDepth();
        gt.PrintTree(gt.GetRoot());
        gt.IncreaseTreeDepth();
        gt.PrintTree(gt.GetRoot());
        //new Client();
    }
}
