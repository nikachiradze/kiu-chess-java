package ChessProject.PieceMovement;

import ChessProject.Helper.PositionValidator;
import ChessProject.Models.Board;
import ChessProject.Models.Knight;
import ChessProject.Models.Piece;
import ChessProject.Models.Square;

import java.util.LinkedList;
import java.util.List;

public class KnightMovement implements PieceMovementStrategy {

    private Piece knight;

    public KnightMovement(Piece knight) {
        this.knight = knight;
    }


    @Override
    public List<Square> getAllowedMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] chessBoard = board.getSquareArray();
        Square position = knight.getPosition();

        int x = position.getXNum();
        int y = position.getYNum();

        int[][] moves = {
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1},
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1}
        };

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (PositionValidator.isValid(newX, newY)) {
                Square targetSquare = chessBoard[newY][newX];
                if (!targetSquare.isOccupied() ||
                        targetSquare.getOccupyingPiece().getColor() != knight.getColor()) {
                    legalMoves.add(targetSquare);
                }
            }
        }

        return legalMoves;
    }
}
