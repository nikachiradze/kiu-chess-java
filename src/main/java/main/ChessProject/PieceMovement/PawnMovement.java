package main.ChessProject.PieceMovement;

import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import java.util.LinkedList;
import java.util.List;

public class PawnMovement implements PieceMovementStrategy {

    private Piece pawn;
    private boolean wasMoved;

    public PawnMovement(Piece pawn, boolean wasMoved) {
        this.pawn = pawn;
        this.wasMoved = wasMoved;
    }

    ;

    @Override
    public List<Square> getAllowedMoves(Board board) {
        List<Square> legalMoves = new LinkedList<>();
        Square[][] chessBoard = board.getSquareArray();
        Square position = pawn.getPosition();
        int x = position.getXNum();
        int y = position.getYNum();
        int color = pawn.getColor();

        int dir = (color == 1) ? -1 : 1;

        if (y + dir >= 0 && y + dir < 8 && !chessBoard[y + dir][x].isOccupied()) {
            legalMoves.add(chessBoard[y + dir][x]);

            if (!wasMoved && y + 2 * dir >= 0 && y + 2 * dir < 8 && !chessBoard[y + 2 * dir][x].isOccupied()) {
                legalMoves.add(chessBoard[y + 2 * dir][x]);
            }
        }

        // Diagonal captures
        for (int dx : new int[]{-1, 1}) {
            int nx = x + dx;
            int ny = y + dir;

            if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8) {
                Square diag = chessBoard[ny][nx];
                if (diag.isOccupied() && diag.getOccupyingPiece().getColor() != color) {
                    legalMoves.add(diag);
                }
            }
        }

        return legalMoves;
    }

}
