import chess.frontend.*;
import chess.backend.*;

public class Test {
    public static void main(String[] args) {
        Thread clientThread = new Thread(new Client());
        clientThread.start();

        System.out.println(new ChessBoard());
    }
}
