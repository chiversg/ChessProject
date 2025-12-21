package chess.ai;

public class Result {
    public Node state;
    public float evaluation;

    public Result(){
        state = null;
        evaluation = 0.0f;
    }
    public Result(Node node, float score){
        state = node;
        evaluation = score;
    }
}
