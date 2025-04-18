package ChessProject.PieceMovement;

import ChessProject.Helper.MovementHelper;
import ChessProject.Models.Board;
import ChessProject.Models.Piece;
import ChessProject.Models.Square;

import java.util.List;

public class RookMovement implements PieceMovementStrategy {
    private Piece rook;

    public RookMovement(Piece rook) {
        this.rook = rook;
    }

    @Override
    public List<Square> getAllowedMoves(Board board) {
        return MovementHelper.getCombinedMoves(board, rook);
    }
}
