package chess.ai;

public class Result {
    public Node state;
    public float evaluation;
    private double time = 0.0;

    public Result(){
        state = null;
        evaluation = 0.0f;
    }
    public Result(Node node, float score){
        state = node;
        evaluation = score;
    }

    public void SetTime(double millis){
        time = millis/1000;
    }

    public double GetTime(){
        return time;
    }
}
