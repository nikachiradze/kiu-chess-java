package main.ChessProject.Helper;

public class PositionValidator {

    public static boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
