package chess.ai;

public class Result {
    public Node state;
    public float evaluation;
    private float time = 0.0f;

    public Result(){
        state = null;
        evaluation = 0.0f;
    }
    public Result(Node node, float score){
        state = node;
        evaluation = score;
    }

    public void SetTime(float millis){
        time = millis/1000;
    }

    public float GetTime(){
        return time;
    }
}
