package main.ChessProject.PieceMovement;

import main.ChessProject.Helper.MovementHelper;
import main.ChessProject.Models.Board;
import main.ChessProject.Models.Piece;
import main.ChessProject.Models.Square;

import java.util.List;

public class RookMovement implements PieceMovementStrategy {
    private Piece rook;

    public RookMovement(Piece rook) {
        this.rook = rook;
    }

    @Override
    public List<Square> getAllowedMoves(Board board) {
        return MovementHelper.getLinearMoves(board, rook);
    }
}
