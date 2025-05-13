
package main.ChessProject.Helper;

import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import java.util.ArrayList;
import java.util.List;


public class MovementHelper {


    private static final int[][] LINEAR_DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };


    private static final int[][] DIAGONAL_DIRECTIONS = {
            {-1, -1},
            {1, -1},
            {1, 1},
            {-1, 1}
    };


    public static List<Square> getLinearMoves(Board chessBoard, Piece piece) {
        List<Square> legalSquares = new ArrayList<>();
        Square[][] board = chessBoard.getSquareArray();
        Square position = piece.getPosition();
        int x = position.getXNum();
        int y = position.getYNum();

        for (int[] direction : LINEAR_DIRECTIONS) {
            int dy = direction[0];
            int dx = direction[1];
            int currentY = y + dy;
            int currentX = x + dx;

            while (PositionValidator.isValid(currentX, currentY)) {
                Square targetSquare = board[currentX][currentY];

                if (targetSquare.isOccupied()) {
                    if (targetSquare.getOccupyingPiece().getColor() != piece.getColor()) {
                        legalSquares.add(targetSquare);
                    }
                    break;
                }

                legalSquares.add(targetSquare);

                currentY += dy;
                currentX += dx;
            }
        }

        return legalSquares;
    }


    public static List<Square> getDiagonalMoves(Board chessBoard, Piece piece) {
        List<Square> legalSquares = new ArrayList<>();
        Square[][] board = chessBoard.getSquareArray();
        Square position = piece.getPosition();
        int x = position.getXNum();
        int y = position.getYNum();

        for (int[] direction : DIAGONAL_DIRECTIONS) {
            int dy = direction[0];
            int dx = direction[1];

            int currentY = y + dy;
            int currentX = x + dx;

            while (PositionValidator.isValid(currentX, currentY)) {
                Square targetSquare = board[currentY][currentX];

                if (targetSquare.isOccupied()) {
                    if (targetSquare.getOccupyingPiece().getColor() != piece.getColor()) {
                        legalSquares.add(targetSquare);
                    }
                    break;
                }

                legalSquares.add(targetSquare);

                currentY += dy;
                currentX += dx;
            }
        }

        return legalSquares;
    }


    public static List<Square> getCombinedMoves(Board chessBoard, Piece piece) {
        List<Square> moves = new ArrayList<>();
        moves.addAll(getLinearMoves(chessBoard, piece));
        moves.addAll(getDiagonalMoves(chessBoard, piece));
        return moves;
    }
}
