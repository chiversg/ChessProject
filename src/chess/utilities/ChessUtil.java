package chess.utilities;

    public class ChessUtil {
        public static String iconPath = "src/chess/frontend/icons/";

        public enum TurnType {
            White, Black, CPU
        }

        public static int letterToNumber(char letter) {
            letter = Character.toLowerCase(letter);
            return switch (letter) {
                case 'a' -> 1;
                case 'b' -> 2;
                case 'c' -> 3;
                case 'd' -> 4;
                case 'e' -> 5;
                case 'f' -> 6;
                case 'g' -> 7;
                case 'h' -> 8;
                default -> -1;
            };
        }

        public static char numberToLetter(int number) {
            return switch (number) {
                case 1 -> 'a';
                case 2 -> 'b';
                case 3 -> 'c';
                case 4 -> 'd';
                case 5 -> 'e';
                case 6 -> 'f';
                case 7 -> 'g';
                case 8 -> 'h';
                default -> '-';
            };
        }

        private static boolean IsWhitePiece(char piece){
            return Character.isUpperCase(piece);
        }

        public static boolean IsValidStart(char piece, TurnType turn){
            return switch(turn){
                case White -> IsWhitePiece(piece);
                case Black ->  !IsWhitePiece(piece);
                case CPU -> false;
            };
        }




    }

