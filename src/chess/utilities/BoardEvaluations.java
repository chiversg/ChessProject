package chess.utilities;

public class BoardEvaluations {
    private static float[][] kingValue = new float[][]{
            {-3, -4, -4, -5, -5, -4, -4, -3},
            {-3, -4, -4, -5, -5, -4, -4, -3},
            {-3, -4, -4, -5, -5, -4, -4, -3},
            {-3, -4, -4, -5, -5, -4, -4, -3},
            {-2, -3, -3, -4, -4, -3, -3, -2},
            {-1, -2, -2, -2, -2, -2, -2, -1},
            {2, 2, 0, 0, 0, 0, 2, 2},
            {2, 3, 1, 0, 0, 1, 3, 2}
    };
    private static float[][] queenValue = new float[][]{
            {-2, -1, -1, -0.5f, -0.5f, -1, -1, -2},
            {-1, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1},
            {-0.5f, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -0.5f},
            {0, 0, 0.5f, 0.5f, 0.5f, 0.5f, 0, -0.5f},
            {-1, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0, -1},
            {-1, 0, 0.5f, 0, 0, 0, 0, -1},
            {-2, -1, -1, -0.5f, -0.5f, -1, -1, -2}
    };
    private static float[][] rookValue = new float[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0.5f, 1, 1, 1, 1, 1, 1, 0.5f},
            {-0.5f, 0, 0, 0, 0, 0, 0, -0.5f},
            {-0.5f, 0, 0, 0, 0, 0, 0, -0.5f},
            {-0.5f, 0, 0, 0, 0, 0, 0, -0.5f},
            {-0.5f, 0, 0, 0, 0, 0, 0, -0.5f},
            {-0.5f, 0, 0, 0, 0, 0, 0, -0.5f},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };
    private static float[][] bishopValue = new float[][]{
            {-2, -1, -1, -1, -1, -1, -1, -2},
            {-1, 0, 0, 0, 0, 0, 0, -1},
            {-1, 0, 0.5f, 1, 1, 0.5f, 0, -1},
            {-1, 0.5f, 0.5f, 1, 1, 0.5f, 0.5f, -1},
            {-1, 0, 1, 1, 1, 1, 0, -1},
            {-1, 1, 1, 1, 1, 1, 1, -1},
            {-1, 0.5f, 0, 0, 0, 0, 0.5f, -1},
            {-2, -1, -1, -1, -1, -1, -1, -2}
    };
    private static float[][] knightValue = new float[][]{
            {-5, -4, -3, -3, -3, -3, -4, -5},
            {-4, -2, 0, 0, 0, 0, -2, -4},
            {-3, 0, 1, 1.5f, 1.5f, 1, 0, -3},
            {-3, 0.5f, 1.5f, 2, 2, 1.5f, 0.5f, -3},
            {-3, 0, 1.5f, 2, 2, 1.5f, 0, -3},
            {-3, 0.5f, 1, 1.5f, 1.5f, 1, 0.5f, -3},
            {-4, -2, 0, 0.5f, 0.5f, 0, -2, -4},
            {-5, -4, -3,-3, -3, -3, -4, -5}
    };
    private static float[][] pawnValue = new float[][]{
            {0, 0, 0, 0, 0, 0, 0, 0},
            {5, 5, 5, 5, 5, 5, 5, 5},
            {1, 1, 2, 3, 3, 2, 1, 1},
            {0.5f, 0.5f, 1, 2.5f, 2.5f, 1, 0.5f, 0.5f},
            {0, 0, 0, 2, 2, 0, 0, 0},
            {0.5f, 1, 1, -2, -2, 1, 1, 0.5f},
            {0.5f, 1, 1, -2, -2, 1, 1, 0.5f},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static float pieceValue(char p) {
        return switch (p) {
            case 'p' -> -10;
            case 'P' -> 10;
            case 'n', 'b' -> -30;
            case 'N', 'B' -> 30;
            case 'r' -> -50;
            case 'R' -> 50;
            case 'q' -> -90;
            case 'Q' -> 90;
            case 'k' -> -900;
            case 'K' -> 900;
            default -> 0;
        };
    }

    private static float[][] pieceSquare(char p) {
        p = Character.toLowerCase(p);
        return switch (p) {
            case 'p' -> pawnValue;
            case 'n' -> knightValue;
            case 'b' -> bishopValue;
            case 'r' -> rookValue;
            case 'q' -> queenValue;
            case 'K' -> kingValue;
            default -> new float[0][0];
        };
    }

    public static float EvaluateBoard(char[][] board){
        float boardValue = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                  char p = board[y][x];
                  if (p != ' ') {
                      boardValue += pieceValue(p);
                      boardValue += (Character.isUpperCase(p)) ? pieceSquare(p)[y][x] : pieceSquare(p)[7-y][x];
                  }
            }
        }
        return boardValue;
    }
}
