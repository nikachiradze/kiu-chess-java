package ChessProject.PieceMovement;

import ChessProject.Helper.MovementHelper;
import ChessProject.Models.Board;
import ChessProject.Models.Piece;
import ChessProject.Models.Square;

import java.util.List;

public class QueenMovement implements PieceMovementStrategy {

    private Piece queen;

    public QueenMovement(Piece queen) {
        this.queen = queen;

    }

    @Override
    public List<Square> getAllowedMoves(Board board) {
        return MovementHelper.getCombinedMoves(board, queen);
    }
}
