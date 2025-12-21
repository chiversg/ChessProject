package chess.ai;

public class Result {
    public Node state;
    public float evaluation;
    private int time = 0;

    public Result(){
        state = null;
        evaluation = 0.0f;
    }
    public Result(Node node, float score){
        state = node;
        evaluation = score;
    }

    public void SetTime(double millis){
        time = (int)millis;
    }

    public int GetTime(){
        return time;
    }
}
